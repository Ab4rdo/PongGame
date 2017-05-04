package app;

import java.awt.Rectangle;

public class Enemy implements Plank {

	private int x, y, dx;
	private boolean isVisible;
	
	public Enemy() {
		init();
	}
	
	private void init() {
		x = 325;
		y = 75;
		isVisible = true;
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
}
