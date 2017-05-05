package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private Image backgroundImage;
	private Player player;
	private Enemy enemy;
	private Ball ball;
	private Timer timer;
	private boolean inGame;
	private int playerScore;
	private int enemyScore;
	private JButton menuBtn;
	private Sound sound;
	private final int DELAY = 10;
	
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		loadBackgroundImage();
		int w = backgroundImage.getWidth(this); // 800
		int h = backgroundImage.getHeight(this); // 800
		setPreferredSize(new Dimension(w, h));
		
		inGame = true;
		
		player = new Player();
		
		enemy = new Enemy();
		
		ball = new Ball();
		
		playerScore = 0;
		enemyScore = 0;
		
//		initBtn();
		sound = new Sound();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void initBtn() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menuBtn = new JButton("MENU");
		menuBtn.setOpaque(false);
		menuBtn.setContentAreaFilled(false);
		menuBtn.setBorderPainted(false);
		menuBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		this.add(menuBtn);
	}
	
	/**
	 * @param Loads background image of a board 
	 * 		  given as argument of ImageIcon object.
	 */
	private void loadBackgroundImage() {
		ImageIcon ii = new ImageIcon("image//background2.png");
		backgroundImage = ii.getImage();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 28));
		g.drawString(playerScore + "", 50, 750);
		g.drawString(enemyScore + "", 50, 70);
		
		if(inGame) {
			drawPlayer(g);
			drawEnemy(g);
			g.setColor(Color.DARK_GRAY);
			drawBall(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawPlayer(Graphics g) {
		g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
	}
	
	private void drawEnemy(Graphics g) {
		g.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
	}

	private void drawBall(Graphics g) {
		g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		inGame();
		
		updatePlayer();
		updateBall();
		updateEnemy();
		checkCollisions();
		repaint();
	}
	
	private void inGame() {
		if(!inGame) {
			player = new Player();
			enemy = new Enemy();
			ball = new Ball();
			inGame = true;
		}
	}
	
	private void updatePlayer() {
		if(player.isVisible()) {
			player.move();
		}
	}
	
	private void updateEnemy() {
		if(enemy.isVisible()) {
			if(ball.getX() > enemy.getX()) {
				enemy.setDx(Plank.PLANK_SPEED);
			} else { 
				enemy.setDx(-Plank.PLANK_SPEED);
			}
			enemy.move();

		}
	}
	
	private void updateBall() {
		if(ball.isVisible()) {
			ball.move();
		}
	}
	
	private void checkCollisions() {
		Rectangle r1 = player.getBounds();
		Rectangle r2 = enemy.getBounds();
		Rectangle r3 = ball.getBounds();
		
		Random random = new Random();
		
		if(r1.intersects(r3)) {
			try {
				sound.playSound("sound/108737__branrainey__boing.wav");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ball.setDy(-ball.getDy());
			ball.setDx(random.nextInt(2) + 1);
		}
		
		if(r2.intersects(r3)) {
			try {
				sound.playSound("sound/108737__branrainey__boing.wav");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ball.setDy(2);
			ball.setDx(-random.nextInt(2) - 1);
		}
		
		// ball with left wall
		if(r3.intersects(-1, 0, 1, 800)) {
			ball.setDx(-ball.getDx());
		}
		
		// ball with right wall
		if(r3.intersects(800, 0, 1, 800)) {
			ball.setDx(-ball.getDx());
		}
		
		// ball with upper wall
		if(r3.intersects(0 , 0 , 800 , 1)) {
			inGame = false;
			playerScore ++;
		}
		
		// ball with lower wall
		if(r3.intersects(0 , 800 , 800 , 1)) {
			inGame = false;
			enemyScore ++;
		}
		
		// player intersects with left wall
		if(r1.intersects(-1, 0, 1, 800)) {
			player.setX(0);
			player.setDx(-player.getDx());
		}
		
		// player intersects with right wall
		if(r1.intersects(800, 0, 1, 800)) {
			player.setX(650);
			player.setDx(-player.getDx());
		}
		
		// enemy intersects with left wall
		if(r2.intersects(-1, 0, 1, 800)) {
			enemy.setX(0);
			enemy.setDx(-Plank.PLANK_SPEED);
		}
				
		// enemy intersects with right wall
		if(r2.intersects(800, 0, 1, 800)) {
			enemy.setX(650);
			enemy.setDx(Plank.PLANK_SPEED);
		}
	}
	
	
	private class TAdapter extends KeyAdapter {
		
		public void keyReleased(KeyEvent e){
			player.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}
}
