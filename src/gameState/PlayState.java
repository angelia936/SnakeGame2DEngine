package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import entity.*;
import tileMap.Background;

public class PlayState extends GameState{
	
	private Snake snake;
	private Apple apple;
	private Background bg;
	private Score score;
	
	private boolean keyPressed = false;
	
	public PlayState(GameStateManager gsm){
		
		super(gsm);
		init();
		
	}
	
	public void init(){
		
		try{
			
			bg = new Background("/SnakeTitleBackground/playbg.png", 1);
			bg.setVector(0, 0);
			snake = new Snake("/SnakeTitleBackground/head.png","/SnakeTitleBackground/body.png");
			apple = new Apple("/SnakeTitleBackground/apple.gif");
			score = new Score();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void update(){
		
		bg.update();
		
		if(snake.getX() == apple.getX() && snake.getY() == apple.getY()){
			snake.eat();
			apple.isEaten();
			score.addScore();
		}
		
		snake.update();
		apple.update();
		
		if(snake.isGameOver()){gsm.setState(new EndState(gsm, score.getScore()));}
	}
	
	public void draw(Graphics2D g){
		
		bg.draw(g);
		
		snake.draw(g);
		
		apple.draw(g);
		
		score.draw(g);
		
	}
	
	public void keyPressed(int k){
		if(!keyPressed){
			if(k == KeyEvent.VK_LEFT) snake.setLeft();
			else if(k == KeyEvent.VK_RIGHT) snake.setRight();
			else if(k == KeyEvent.VK_UP) snake.setUp();
			else if(k == KeyEvent.VK_DOWN) snake.setDown();
			
			keyPressed = true;
		}
	}
	
	public void keyReleased(int k){
		
		keyPressed = false;
		
	}
	
}
