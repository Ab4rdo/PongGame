package app;

import java.awt.Rectangle;

public class Ball {
	
	private int x, y, dx, dy;
	private boolean isVisible;
	private final int SIZE = 30;
	
	public Ball() {
		init();
	}
	
	private void init() {
		isVisible = true;
		x = 400;
		y = 400;
		dy = 3;
	}
	
	public void move() {
		x += dx;
		y += dy;
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

	public int getDy() {
		return dy;
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x , y , SIZE , SIZE);
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

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
}
