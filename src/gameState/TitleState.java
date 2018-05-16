package gameState;

import main.GamePanel;
import tileMap.Background;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TitleState extends GameState{

	private Background bg;
	
	private int currentChoice = 0;
	
	private String title = "SNAKE";
	private String[] options = { "Start", "Quit"};
	private int optionsWidth;
	private int optionsSelectWidth = 80;
	
	private Color titleColor;
	private Font titleFont;
	private int titleWidth;
	
	private BufferedImage image;
	
	private Font font;
	
	public TitleState(GameStateManager gsm){
		
		super(gsm);
		init();
	}
	
	public void init(){
		
			try{
			
			bg = new Background("/SnakeTitleBackground/titleback3.png", 1);
			bg.setVector(-0.5, 0);
			
			image = ImageIO.read(getClass().getResourceAsStream("/SnakeTitleBackground/selection.gif"));
			
			titleColor = Color.PINK;
			titleFont = new Font("Arial", Font.BOLD, 40);
			
			font = new Font("Arial", Font.PLAIN, 20);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void update(){
		
		bg.update();
		
	}
	
	public void draw(Graphics2D g){
		
		//draw background
		bg.draw(g);;
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		titleWidth = g.getFontMetrics().stringWidth(title);
		g.drawString(title, (GamePanel.WIDTH / 2) - (titleWidth / 2), 40);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			optionsWidth = g.getFontMetrics().stringWidth(options[i]);
			if(i == currentChoice){
				g.drawImage(image,  GamePanel.WIDTH/2 - optionsSelectWidth, 100 + i * 50, null);
				g.setColor(Color.WHITE);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], GamePanel.WIDTH/2 - optionsWidth/2, 135 + i * 45);
		}
		
		
	}
	
	private void select(){
		if(currentChoice == 0){
			gsm.setState(new PlayState(gsm));
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
