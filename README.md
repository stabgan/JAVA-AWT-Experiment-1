# Java AWT Polygon Drawing

Interactive desktop application that lets you draw polygons by entering X,Y coordinates. Points are stored in a circular linked list and rendered on an AWT canvas, with edges automatically connecting to form a closed shape.

## How It Works

1. Enter X and Y coordinates in the text fields.
2. Click **Draw** to add the point.
3. The canvas renders vertices as red dots and connects them with black lines.
4. After 3+ points, the polygon automatically closes (last point connects back to the first).

Internally, points are stored in a **Bidirectional Circular Linked List** (`BCLL`), which makes it natural to traverse the polygon edges and close the shape.

## 🛠 Tech Stack

| Component | Technology |
|-----------|------------|
| ☕ Language | Java (JDK 8+) |
| 🖼 GUI | Java AWT (Abstract Window Toolkit) |
| 🔗 Data Structure | Custom circular doubly-linked list |

## Getting Started

```bash
# Compile
javac BCLL.java methods.java

# Run
java methods
```

No external dependencies — just a standard JDK installation.

## Project Structure

```
├── methods.java   # Main Frame, UI, and PolygonCanvas
├── BCLL.java      # Bidirectional Circular Linked List
└── README.md
```

## Bugs Fixed

| Bug | Fix |
|-----|-----|
| `newCanvas` constructor never assigned `draw`, `x`, `y` fields → `NullPointerException` | Constructor now properly initializes all fields |
| `paint()` only drew the latest point instead of the full polygon | Rewrote to iterate the entire linked list on each repaint |
| `new newCanvas(draw,xx,yy)` created an orphan object (never added to frame) | Removed; now calls `canvas.repaint()` on the existing canvas |
| `Graphics g = getGraphics()` at field level returns `null` before frame is realized | Removed unused field |
| Missing `BCLL` class (imported but not in repo) | Created `BCLL.java` with circular doubly-linked list |
| No `WindowListener` — clicking ✕ didn't close the app | Added `WindowAdapter` with `System.exit(0)` |
| Empty `catch` block silently swallowed errors | Catches `NumberFormatException` specifically, prints to stderr |
| Incorrect `package com.stabgan.java` for root-level files | Removed package declaration (default package) |

## ⚠️ Known Issues

- AWT uses absolute positioning (`setLayout(null)`), so the UI doesn't resize gracefully.
- Canvas coordinates are relative to the canvas origin (top-left of the canvas area, not the frame).
- No undo/clear functionality — restart the app to start over.
