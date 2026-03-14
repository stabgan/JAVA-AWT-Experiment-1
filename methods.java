package com.stabgan.java;

import com.stabgan.java.BCLL;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class PolygonCanvas extends Canvas {
	
	private static final long serialVersionUID = 1L;
	BCLL draw; // Package-private for access from PolygonDrawer
	
	public PolygonCanvas(BCLL draw) {  
        this.draw = draw;
        setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (draw == null || draw.size == 0) {
			return;
		}
		
		// Get all points
		int[][] points = draw.getAllPoints();
		
		// Draw all points as red dots
		g.setColor(Color.RED);
		for (int[] point : points) {
			g.fillOval(point[0] - 2, point[1] - 2, 4, 4);
		}
		
		// Draw lines connecting points
		g.setColor(Color.BLACK);
		if (draw.size >= 2) {
			for (int i = 0; i < points.length; i++) {
				int nextIndex = (i + 1) % points.length;
				g.drawLine(points[i][0], points[i][1], 
						  points[nextIndex][0], points[nextIndex][1]);
			}
		}
		
		// If we have 3+ points, fill the polygon
		if (draw.size >= 3) {
			int[] xPoints = new int[points.length];
			int[] yPoints = new int[points.length];
			
			for (int i = 0; i < points.length; i++) {
				xPoints[i] = points[i][0];
				yPoints[i] = points[i][1];
			}
			
			g.setColor(new Color(0, 0, 255, 50)); // Semi-transparent blue
			g.fillPolygon(xPoints, yPoints, points.length);
		}
	}
}

class PolygonDrawer extends Frame {
	
	private static final long serialVersionUID = 1L;
	private BCLL draw = new BCLL();
	private PolygonCanvas canvas;
	
	public PolygonDrawer() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		setTitle("Java AWT Polygon Drawing Experiment");
		
		Label title = new Label("Enter Coordinates");
		title.setBounds(95, 40, 120, 20);
		add(title);
		
		Button drawButton = new Button("Add Point");  
		drawButton.setBounds(120, 150, 80, 25);
		
		Button clearButton = new Button("Clear");
		clearButton.setBounds(210, 150, 60, 25);
		
		TextField xField = new TextField();
		TextField yField = new TextField();
		xField.setBounds(80, 100, 50, 20);
		yField.setBounds(170, 100, 50, 20);
		xField.setBackground(Color.LIGHT_GRAY);
		yField.setBackground(Color.LIGHT_GRAY);
		
		Label xLabel = new Label("X:");
		Label yLabel = new Label("Y:");
		xLabel.setBounds(65, 100, 15, 20);
		yLabel.setBounds(155, 100, 15, 20);
		
		add(xLabel);
		add(yLabel);
		add(xField);
		add(yField);
		
		// Add point button action
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String xText = xField.getText().trim();
					String yText = yField.getText().trim();
					
					if (!xText.isEmpty() && !yText.isEmpty()) {
						int x = Integer.parseInt(xText);
						int y = Integer.parseInt(yText);
						
						// Adjust coordinates relative to canvas
						draw.append(x, y - 300);
						canvas.repaint();
						
						// Clear fields for next input
						xField.setText("");
						yField.setText("");
						xField.requestFocus();
					}
				} catch (NumberFormatException ex) {
					// Invalid input - ignore
				}
			}
		});
		
		// Clear button action
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw = new BCLL();
				canvas.draw = draw;
				canvas.repaint();
				xField.setText("");
				yField.setText("");
				xField.requestFocus();
			}
		});
		
		add(drawButton);
		add(clearButton);
		
		canvas = new PolygonCanvas(draw);
		canvas.setBounds(0, 300, 300, 400);
		add(canvas);
		
		setSize(300, 750);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		// Close operation
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new PolygonDrawer();
	}
	
}
