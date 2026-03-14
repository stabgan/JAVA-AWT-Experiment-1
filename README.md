# Java AWT Polygon Drawing Experiment

A simple interactive polygon drawing application built with Java AWT that allows users to create polygons by adding coordinate points dynamically.

## 🎯 What It Does

This application provides a graphical interface where users can:
- Input X,Y coordinates through text fields
- Add points to create a polygon shape
- View real-time polygon rendering with connected lines
- Clear the canvas to start over
- See filled polygons when 3+ points are added

## 🏗️ Architecture

The application uses a **Bidirectional Circular Linked List (BCLL)** data structure to store coordinate points, enabling efficient polygon rendering and manipulation. The main components are:

- **PolygonDrawer**: Main frame with UI controls
- **PolygonCanvas**: Custom canvas for rendering polygons
- **BCLL**: Bidirectional circular linked list for coordinate storage

## 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| ☕ **Java AWT** | GUI framework and graphics rendering |
| 🔗 **Custom BCLL** | Efficient coordinate point storage |
| 🎨 **Graphics2D** | Polygon drawing and filling |

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line access

### Installation & Execution

1. **Clone the repository**
   ```bash
   git clone https://github.com/stabgan/JAVA-AWT-Experiment-1.git
   cd JAVA-AWT-Experiment-1
   ```

2. **Compile the Java files**
   ```bash
   javac *.java
   ```

3. **Run the application**
   ```bash
   java com.stabgan.java.PolygonDrawer
   ```

### Usage

1. Enter X and Y coordinates in the input fields
2. Click "Add Point" to add the coordinate to your polygon
3. Watch as points are connected with lines automatically
4. When you have 3+ points, the polygon will be filled with semi-transparent blue
5. Use "Clear" to reset and start a new polygon

## 🎨 Features

- **Real-time rendering**: See your polygon update as you add points
- **Visual feedback**: Red dots mark each coordinate point
- **Automatic polygon completion**: Lines connect all points in sequence
- **Polygon filling**: 3+ points create a filled polygon shape
- **Input validation**: Handles invalid coordinate input gracefully
- **Clean UI**: Simple, intuitive interface

## 📝 Code Structure

```
├── methods.java      # Main application with UI and canvas
├── BCLL.java        # Bidirectional circular linked list implementation
└── README.md        # This file
```

## 🔧 Technical Details

- **Data Structure**: Uses a bidirectional circular linked list for O(1) insertion and efficient traversal
- **Rendering**: Custom paint() method handles polygon drawing with Graphics API
- **Event Handling**: ActionListener implementations for button interactions
- **Memory Management**: Proper cleanup and resource management

## ⚠️ Known Limitations

- Coordinates are relative to the canvas area (adjust Y values by subtracting 300)
- No undo functionality for individual points
- Fixed window size (300x750 pixels)
- Basic error handling for invalid input

---

*This project demonstrates fundamental Java AWT concepts including custom canvas rendering, event handling, and data structure implementation for graphical applications.*