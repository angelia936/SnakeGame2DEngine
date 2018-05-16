package entity;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Snake {
	
	//positions
	private int startX;
	private int startY;
	private static final int START_SNAKE_SIZE = 3;
	private int snakeSize;
	private int currentSnakeSize;
	private static final int MAX_SIZE = GamePanel.WIDTH * GamePanel.HEIGHT;
	private int[] x = new int[MAX_SIZE];
	private int[] y = new int[MAX_SIZE];
	
	//movement
	private boolean facingLeft;
	private boolean facingRight;
	private boolean facingUp;
	private boolean facingDown;
	
	//snake sizes
	public static final int PART_SIZE = 10;
	
	//game over
	private boolean gameOver;
	
	//timer
	private long startTime;
	private static final long DELAY = 100;
	
	//image
	private BufferedImage head;
	private BufferedImage body;
	
	public Snake(String s, String p){
			
		try{
			head = ImageIO.read(getClass().getResourceAsStream(s));
			body = ImageIO.read(getClass().getResourceAsStream(p));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		init();
		
	}
	
	private void init(){
		
		gameOver = false;
		
		snakeSize = START_SNAKE_SIZE;
		
		startX = GamePanel.WIDTH / 2;
		startY = GamePanel.HEIGHT / 2;
		
		for(int i = 0; i < START_SNAKE_SIZE; i++){
			x[i] = startX + (PART_SIZE * i);
			y[i] = startY;
		}
		this.setLeft();
		
		startTime = System.nanoTime();
		
	}
	
	public void update(){
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > DELAY){
			checkCollision();
			moveWholeSnake();
			startTime = System.nanoTime();
		}
		
	}
	
	public void draw(Graphics2D g){
		
		for(int i = 0; i < currentSnakeSize; i++){
		if(i == 0){
			g.drawImage(head, x[i], y[i], PART_SIZE, PART_SIZE, null);
		}
		else{
			g.drawImage(body, x[i], y[i], PART_SIZE, PART_SIZE, null);
		}
		}
		
	}
	
	public void eat(){
		
		snakeSize++;
		
	}
	
	private void moveWholeSnake(){
		
		for(int i = snakeSize - 1; i > 0; i--){
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		if(facingRight){
			x[0] += PART_SIZE;
		}
		else if(facingLeft){
			x[0] += -PART_SIZE;
		}
		else if(facingUp){
			y[0] += -PART_SIZE;
		}
		else if(facingDown){
			y[0] += PART_SIZE;
		}
		
		//frame fix if snake added
		currentSnakeSize = snakeSize;
	}
	
	private void checkCollision(){
		
		for(int i = snakeSize; i > 0; i--){
			
			if((i > 3) && (x[0] == x[i]) && (y[0] == y[i])){
				gameOver = true;
			}
			
		}
		
		if(x[0] > GamePanel.WIDTH - PART_SIZE){
			gameOver = true;
		}
		if(y[0] > GamePanel.HEIGHT - PART_SIZE){
			gameOver = true;
		}
		if(x[0] < 0){
			gameOver = true;
		}
		if(y[0] < 0){
			gameOver = true;
		}
		
	}
	
	public int getX(){return x[0];}
	public int getY(){return y[0];}
	
	public void setLeft(){
		if(!facingLeft && !facingRight){
			facingLeft = true;
			facingDown = false;
			facingUp = false;
		}
	}
	public void setRight(){
		if(!facingLeft && !facingRight){
			facingRight = true;
			facingDown = false;
			facingUp = false;
		}
	}
	public void setUp(){
		if(!facingUp && !facingDown){
			facingUp = true;
			facingRight = false;
			facingLeft = false;
		}
	}
	public void setDown(){
		if(!facingDown && !facingUp){
			facingDown = true;
			facingRight = false;
			facingLeft = false;
		}
	}
	
	public boolean isGameOver(){return gameOver;}
	
}
