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

class newCanvas extends Canvas{
	
	private static final long serialVersionUID = 1L;
	int x ,y;
	BCLL draw;
	
	public newCanvas(BCLL draw ,int x, int y) {  
        setBackground (Color.GRAY);
	}
	
	
	public void paint(Graphics g)  
	  {  
		if(this.draw.size == 1) {
			g.setColor(Color.red);
			g.fillOval(this.x, this.y, 3, 3);
		}
		else if(draw.size == 2){
			
			g.setColor(Color.red);
			g.fillOval(this.x, this.y, 3, 3);
			g.setColor(Color.black);
			g.drawLine(this.draw.head.prev.x, this.draw.head.prev.y, this.x, this.y);
		} 
		else {
			g.setColor(Color.red);
			g.fillOval(this.x, this.y, 3, 3);
			g.setColor(Color.black);
			g.drawLine(this.draw.head.prev.x, this.draw.head.prev.y, this.x, this.y);
			g.drawLine(this.draw.head.x, this.draw.head.y, this.x, this.y);
		}
	  }
}

public class methods extends Frame{
	

	private static final long serialVersionUID = 1L;
	BCLL draw = new BCLL();
	Canvas can  = new newCanvas(draw,0,0);
	Graphics g = getGraphics();
	
	public methods() {
		
		Label title = new Label();
		title.setText("Enter Coordinates");
		title.setBounds(95, 40, 120, 20);
		add(title);
		Button b=new Button("Draw");  
		b.setBounds(120,150,60,20);// setting button position  
		
		TextField x = new TextField("X");
		TextField y = new TextField("Y");
		x.setBounds(80, 100, 50, 20);
		y.setBounds(170, 100, 50, 20);
		x.setBackground(Color.lightGray);
		y.setBackground(Color.lightGray);
	
		add(x);
		add(y);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = x.getText();
				String s2 = y.getText();
				try {
				int xx = Integer.parseInt(s1);
				int yy = Integer.parseInt(s2);
				draw.append(xx, yy);
				new newCanvas(draw,xx,yy);
				}
				catch(Exception lol){

				}
	
			}
		});
		
		
		add(b);
		can.setBounds(0, 300, 300, 400);
		can.setBackground(Color.WHITE);
		add(can);
		setSize(300,800);//frame size 300 width and 300 height  
		setLayout(null);//no layout manager  
		setVisible(true);
		
	}

	public static void main(String[] args){
		new methods();

	}
	
}
