package entity;

import java.awt.*;
import java.awt.Graphics2D;

public class Score {
	
	private static final int MULTIPLIER = 10;
	
	private int score;
	private Color color;
	private Font font;
	
	public Score(){
		
		init();
		
	}
	
	private void init(){
		
		score = 0;
		
		color =Color.BLACK;
		font = new Font("Arial", Font.PLAIN, 15);
		
	}
	
	public void update(){
		
		
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(color);
		g.setFont(font);
		g.drawString("Score: " +score, 3, 15);
		
	}
	
	public void addScore(){
		score += MULTIPLIER;
	}
	
	public int getScore(){return score;}
	
	
}
