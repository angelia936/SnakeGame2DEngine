package gameState;

import java.util.Stack;

public class GameStateManager {
	
	private Stack<GameState> states;
	
	public GameStateManager(){
		
		states = new Stack<GameState>();
		states.push(new TitleState(this));
		
	}
	
	public void push(GameState state){
		states.push(state);
	}
	
	public void pop(){
		states.pop();
	}
	
	public void setState(GameState state){
		states.pop();
		states.push(state);
	}
	
	public void update(){
		states.peek().update();
	}
	
	public void draw(java.awt.Graphics2D g){
		states.peek().draw(g);
	}
	
	public void keyPressed(int k){
		states.peek().keyPressed(k);
	}
	
	public void keyReleased(int k){
		states.peek().keyReleased(k);
	}
	
}
