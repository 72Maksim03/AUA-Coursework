# Armenian Literary Character Network

This project extracts and visualizes character co-occurrence networks from Armenian literary texts. It leverages a multilingual Named Entity Recognition (NER) model and a refined character name normalization process using a language model API. The final output is a circular network graph that shows how frequently characters appear together in the same text context.

## Features

Armenian-compatible NER using Hugging Face models

AI-powered name normalization tailored for Armenian cultural context

Co-occurrence analysis in both paragraph-level and adjacent-word-level granularity

Graph-based visualization with NetworkX and Matplotlib

## File Structure

.  
├── armenian_text.txt                  # Input text file (UTF-8 encoded)  
├── character_network_circular.png    # Output graph image  
├── main.py                           # Main script  
└── README.md                         # Documentation  

## Requirements

Install dependencies using pip:

pip install torch transformers openai matplotlib networkx

You may also need:

pip install accelerate  # For some PyTorch environments

## API Key Setup (IMPORTANT)

This project uses the OpenRouter API to access GPT-based models for character name normalization.

Note: The API key is not included in this repository for security reasons.

To run this code, you'll need to create a file called .env or modify the script directly:

Option 1: Use Environment Variables (Recommended)

Set your OpenRouter API key as an environment variable:

export OPENROUTER_API_KEY="your-api-key-here"

Modify the script to read from the environment:

import os
<pre>client = OpenAI(
    api_key=os.getenv("OPENROUTER_API_KEY"),
    base_url="https://openrouter.ai/api/v1"
)</pre>

Option 2: Hardcode for Local Use (Not Recommended for Production)

Replace the placeholder in the script:

<pre> client = OpenAI(
    api_key="your-api-key-here",
    base_url="https://openrouter.ai/api/v1"
)</pre>

## How It Works

NER Pipeline: Identifies person entities using Davlan/bert-base-multilingual-cased-ner-hrl.

GPT Normalization: Filters and normalizes names via prompt engineering with a GPT model.

Co-occurrence Detection: Finds characters appearing together in:

The same paragraph

Adjacent words

Graph Construction: Builds a weighted undirected graph of character relationships.

Visualization: Saves and displays a circular layout graph using Matplotlib.

## Notes

Make sure your input file armenian_text.txt is properly UTF-8 encoded and formatted with paragraphs separated by double newlines.

This project assumes some familiarity with Python and NLP basics.

## Author

**Maksim Petrosyan**  
https://github.com/72Maksim03
