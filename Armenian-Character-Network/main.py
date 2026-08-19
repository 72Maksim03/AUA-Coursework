"""
@author: Maksim Petrosyan
"""

import networkx as nx
import matplotlib.pyplot as plt
import itertools
from openai import OpenAI
from transformers import AutoTokenizer, AutoModelForTokenClassification, pipeline
import concurrent.futures
import torch
import ast  # Safe eval

# OpenRouter client setup
client = OpenAI(
    api_key="your-api-key-here",
    base_url="https://openrouter.ai/api/v1"
)

# Load Armenian-compatible NER model
tokenizer = AutoTokenizer.from_pretrained("Davlan/bert-base-multilingual-cased-ner-hrl")
model = AutoModelForTokenClassification.from_pretrained("Davlan/bert-base-multilingual-cased-ner-hrl")

device = 0 if torch.cuda.is_available() else -1
ner_pipeline = pipeline("ner", model=model, tokenizer=tokenizer, aggregation_strategy="simple", device=device)


def get_normalized_names_from_chatgpt(names):
    unique_names = list(set(filter(None, names)))
    if not unique_names:
        return []

    prompt = (
      "Դու վերլուծում ես հայկական գրական տեքստ։ Ստորև բերված է NER համակարգի միջոցով ստացված հնարավոր կերպարների անունների ցանկը։ "
      "Վերադարձրու միայն իրական մարդու անունները՝ հայկական գրականության կամ մշակույթի շրջանակում ընդունված։ "
      "\n\nԽնդրում ենք պահպանել հետևյալ կանոնները՝"
      "\n1․ Հեռացրու ոչ-անուններ, տեխնիկական արտեֆակտներ կամ մասնակի բառեր։"
      "\n2․ Հեռացրու պատիվներ, կոչումներ, նկարագրական և հոլովային վերջավորություններ։"
      "\n3․ Նորմալացրու անունների տարբեր ձևերը՝ թողնելով միայն մեկ կանոնական տարբերակ։"
      "\n4․ Հեռացրու վայրերի, կազմակերպությունների և այլ ոչ մարդ արարածների անունները։"
      "\n5․ Տվյալները վերադարձրու միայն հիմնական ձևով՝ անուն կամ ամբողջական անուն։"
      "\n7․ Զգուշությամբ վերաբերվիր հայերեն հոլովային և որոշիչ վերջավորություններին (օրինակ՝ «-ն», «-ին», «-ով», «-ից» և այլն)։ Հեռացրու միայն այն դեպքում, երբ պարզ է, որ տվյալ վերջավորությունը հավելվել է բազային անվանը։ Օրինակ՝ «Արփին» բխում է «Արփի»-ից և պետք է վերադարձնել միայն «Արփի»։"
      "\n8․ Հեռացրու նաև պետությունների կամ կազմակերպությունների հապավումները, օրինակ՝ «ՀՀ» (Հայաստանի Հանրապետություն), որոնք երբեմն սխալմամբ ընկալվում են որպես անուն։"
      "Բացի այդ, հեռացրու պետության կամ կազմակերպության կրճատումները, օրինակ՝ «ՀՀ» (Հայաստանի Հանրապետություն), որոնք երբեմն սխալմամբ ընդունվում են որպես անուն։"
      "\n\nՎերադարձրու միայն վավեր Python-ի ցուցակ՝ տողերի տեսքով։ Ոչ մի բացատրություն կամ մեկնաբանություն մի ավելացրու։"
      f"\n\nՄուտքային անուններ՝ {', '.join(unique_names)}"
    )

    try:
        response = client.chat.completions.create(
            model="openai/gpt-4.1-nano",
            messages=[
                {
                    "role": "system",
                    "content": (
                        "Դու գրականության փորձագետ ես, մասնագիտացած հայկական գրականության մեջ։ "
                        "Քո խնդիրն է՝ NER արդյունքներից առանձնացնել իրական կերպարների անունները՝ հետևելով մշակութային և լեզվական նրբություններին։"
                    )
                },
                {"role": "user", "content": prompt}
            ]
        )
        content = response.choices[0].message.content.strip()
        names_from_gpt = ast.literal_eval(content)

        # Filter out any hallucinated names (not derived from original NER names)
        if isinstance(names_from_gpt, list):
            return [
                name for name in set(names_from_gpt)
                if any(name in original or original in name for original in unique_names)
            ]
        else:
            print("ChatGPT returned non-list content:", content)
            return []
    except Exception as e:
        print("ChatGPT normalization failed:", e)
        return []


def process_chunk(chunk):
    return [
        entity["word"]
        for entity in ner_pipeline(chunk)
        if entity["entity_group"] == "PER"
    ]


def extract_characters_from_text(text):
    chunk_size = 3000
    overlap = 500
    chunks = []
    i = 0
    while i < len(text):
        chunks.append(text[i:i + chunk_size])
        i += chunk_size - overlap

    with concurrent.futures.ThreadPoolExecutor() as executor:
        results = list(executor.map(process_chunk, chunks))

    raw_names = list(set(itertools.chain.from_iterable(results)))
    raw_names = [name for name in raw_names if name]

    normalized_names = get_normalized_names_from_chatgpt(raw_names)
    return list(set(normalized_names))


# Load text
with open('armenian_text.txt', 'r', encoding='utf-8') as f:
    text = f.read()

character_names = extract_characters_from_text(text)
print("Characters extracted:", character_names)

# Split paragraphs
paragraphs = text.split('\n\n')

# Co-occurrence: paragraphs
connections = {}
for para in paragraphs:
    found_characters = [name for name in character_names if name in para]
    for name1, name2 in itertools.combinations(found_characters, 2):
        if name1 != name2:
            pair = tuple(sorted((name1, name2)))
            connections[pair] = connections.get(pair, 0) + 1

# Co-occurrence: adjacent words
words = text.split()
char_set = set(character_names)
for i in range(len(words) - 1):
    w1, w2 = words[i], words[i + 1]
    matched1 = [name for name in char_set if name in w1]
    matched2 = [name for name in char_set if name in w2]
    for name1 in matched1:
        for name2 in matched2:
            if name1 != name2:
                pair = tuple(sorted((name1, name2)))
                connections[pair] = connections.get(pair, 0) + 1

# Build graph
G = nx.Graph()
G.add_nodes_from(character_names)
for (name1, name2), weight in connections.items():
    if name1 != name2:
        G.add_edge(name1, name2, weight=weight)

# Visualize
plt.figure(figsize=(24, 24))
pos = nx.circular_layout(G)
edges = G.edges()

nx.draw_networkx_nodes(G, pos, node_color='skyblue', edgecolors='black', node_size=1500)
nx.draw_networkx_edges(G, pos, edgelist=edges, width=3, alpha=0.8)
nx.draw_networkx_labels(G, pos, font_family='DejaVu Sans', font_size=10)

plt.title('Character Network (Circular Layout)', fontsize=20)
plt.axis('off')
plt.tight_layout()
plt.savefig("character_network_circular.png", dpi=300)
plt.show()
