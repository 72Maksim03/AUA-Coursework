# 💰 Budgeting Game

An interactive, browser-based personal finance simulation built with vanilla JavaScript and Bootstrap. Players manage a fictional person's income, housing, transportation, health, and entertainment over a 12-month (48-week) span while trying to avoid debt spirals and stay financially solvent.

The game doubles as a lightweight economics teaching tool, pairing gameplay mechanics with real-world explanations of concepts like inflation, recession, and opportunity cost.

## Features

- **Weekly/monthly simulation loop** — 4 weeks per month, 12 months per game
- **Housing choices** — Small, Standard, or Luxury, each with different rent and entertainment trade-offs
- **Transportation choices** — Public transit, or purchase a used/new car with upfront cost + monthly maintenance
- **Health & Entertainment stats** — Decay over time based on lifestyle choices; low entertainment drags down health, and critical health forces a costly hospital visit
- **Shop system** — Optional Gym membership, Streaming subscription, and Pet, each with recurring costs and stat bonuses
- **Investments** — Put money aside to earn a monthly return
- **Debt & loans** — Take loans to stay afloat, but unpaid debt accrues interest and debt over $2000 ends the game
- **Random weekly events** — Chance-based events that add unpredictability (windfalls, minor illness, unexpected fees, etc.)
- **Rolling event log** — Displays the last 5 in-game events
- **Economics Concepts page** — In-app glossary connecting game mechanics to real-world economic ideas (inflation, recession, opportunity cost, budget constraints, utility maximization, emergency funds, risk & uncertainty)
- **Instructions page** — Full rules and gameplay explanation

## Project Structure

```
budgeting-game/
├── index.html         # Main menu — links to game, instructions, and concepts pages
├── game.html           # Core game screen (status, shop, actions, event log)
├── instructions.html    # How-to-play guide
├── concepts.html       # Economics concepts glossary
├── script.js           # Game logic and state management
└── style (inline)      # Bootstrap 5.3 + custom CSS embedded in each HTML file
```

## How to Play

1. Open `index.html` in a browser.
2. Click **Start Game** to go to `game.html`.
3. Choose a **housing** option and a **transportation** option (required before advancing).
4. Use the **Actions** panel to entertain yourself, visit the hospital, invest, or manage loans as needed.
5. Click **Next Week** to advance time. Rent, transportation costs, and salary are settled at the start of each month (week 1).
6. Keep your **balance** positive, your **debt** under $2000, and your **health**/**entertainment** stats up to survive all 12 months.

Full rules are available in-game via the **Instructions** page.

## Key Game Mechanics

| System | Details |
|---|---|
| Salary | Fixed at $2000/month by default; can be temporarily reduced by forced hospital visits |
| Rent | Charged monthly based on housing choice; missing rent ends the game |
| Transport | Recurring cost + maintenance; cars require an upfront purchase |
| Entertainment | Decays weekly based on housing/transport; boosted by Gym, Streaming, Pet, or the Entertainment action |
| Health | Drops if entertainment stays low; critical health triggers a forced (and costly) hospital visit |
| Loans | +$500 balance per loan; 10% interest applied if unpaid for 2+ weeks; game over above $2000 debt |
| Investments | $200 per investment; returns 8% of total invested, paid out monthly |
| Random Events | 25% chance per week of a random positive or negative event |

## Tech Stack

- **HTML5 / CSS3**
- **Bootstrap 5.3** (via CDN)
- **Vanilla JavaScript** (no frameworks or build tools — just `script.js`)

## Running Locally

No build step required. Clone or download the files and open `index.html` directly in a browser, or serve the folder with any static file server:

```bash
# Example using Python
python3 -m http.server 8000
```

Then visit `http://localhost:8000`.

## Possible Future Improvements

- Persist game state via `localStorage` so progress survives a page refresh
- Add difficulty levels (adjustable starting salary, event frequency, etc.)
- Expand the random event pool and add seasonal/economic-cycle events
- Add charts/graphs to visualize balance, debt, and investment growth over time
- Mobile-responsive layout refinements

## Academic Context
Developed as coursework for ECON101 at the American University of Armenia

## Author
Maksim Petrosyan