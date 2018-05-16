package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;
import tileMap.Background;

public class EndState extends GameState {
	
	private String gameOver = "GAME OVER";
	private String []options = {"Restart", "Quit"};
	private int optionsWidth;
	
	private Color titleColor;
	private Font titleFont;
	private int titleWidth;
	private Font font;
	
	private Background bg;
	
	private int score;
	private int scoreWidth;
	private String scoreText = "Score: ";
	
	private int currentChoice = 0;
	
	public EndState(GameStateManager gsm, int score){
		
		super(gsm);
		this.score = score;
		init();
		
	}
	
	public void init(){
		
		try{
			
			bg = new Background("/SnakeTitleBackground/titleback2.png", 1);
			bg.setVector(0, 0);
			
			titleColor = Color.PINK;
			titleFont = new Font("Arial", Font.BOLD, 40);
			
			font = new Font("Arial", Font.PLAIN, 20);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void update(){}
	
	public void draw(Graphics2D g){
		
		bg.draw(g);;
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		titleWidth = g.getFontMetrics().stringWidth(gameOver);
		g.drawString(gameOver, (GamePanel.WIDTH / 2) - (titleWidth / 2), 40);
		
		//draw score
		g.setFont(font);
		g.setColor(Color.RED);
		scoreWidth = g.getFontMetrics().stringWidth(scoreText + score);
		g.drawString(scoreText + score, (GamePanel.WIDTH / 2) - (scoreWidth / 2), 131);
		
		//draw menu options
		for(int i = 0; i < options.length; i++){
			optionsWidth = g.getFontMetrics().stringWidth(options[i]);
			if(i == currentChoice){
				g.setColor(Color.WHITE);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], GamePanel.WIDTH/2 - optionsWidth/2, 195 + i * 22);
		}
		
	}
	private void select(){
		if(currentChoice == 0){
			gsm.setState(new PlayState(this.gsm));
		}
		if(currentChoice == 1){
			System.exit(0);
		}
	}
	
	public void keyPressed(int k){
		
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k){}
}
