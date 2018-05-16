package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Apple {
	
	private int appleSize;
	private int x;
	private int y;
	
	private static final int RAND = 28;
	private Random r;
	
	private BufferedImage apple;
	
	private boolean isEaten;

	public Apple(String s){
		
		try{
			apple = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		init();
		
	}
	
	private void init(){
		
		isEaten = false;
		r = new Random();
		appleSize = Snake.PART_SIZE;
		int h = r.nextInt(RAND) + 1;
		x = h * appleSize;
		h = r.nextInt(RAND) + 1;
		y = h * appleSize;
		
	}
	
	public void update(){
		
		if(isEaten){
			int h = r.nextInt(RAND) + 1;
			x = h * appleSize;
			h = r.nextInt(RAND) + 1;
			y = h * appleSize;
			isEaten = false;
		}
		
	}
	
	public void draw(Graphics2D g){
		
		g.drawImage(apple, x, y, appleSize, appleSize, null);
		
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public void isEaten(){isEaten = true;}
	
	
}
