package app;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player implements Plank {
	
	private int x, y, dx;
	private boolean isVisible;

	public Player() {
		init();
	}
		
	private void init() {
		this.isVisible = true;
		x = 325;
		y = 695;
	}
	
	public void move() {
		x += dx;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDx() {
		return dx;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, this.getWidth(), this.getHeight());
	}
	
	@Override
	public int getHeight() {
		return PLANK_HEIGHT;
	}

	@Override
	public int getWidth() {
		return PLANK_WIDTH;
	}

	@Override
	public int getSpeed() {
		return PLANK_SPEED;
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -PLANK_SPEED;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = PLANK_SPEED;
        }
    }
	
	// not needed method
	public void keyReleased(KeyEvent e) {
        
//        int key = e.getKeyCode();

//        if (key == KeyEvent.VK_LEFT) {
//            dx = 0;
//        }

//        if (key == KeyEvent.VK_RIGHT) {
//            dx = 0;
//        }
    }
}
