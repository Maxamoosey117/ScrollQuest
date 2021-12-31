package scrollquest.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import scrollquest.Handler;

public abstract class Entity {

	protected Handler handler;
	protected int x, y;
	protected int width, height;
	protected int health = 0;
	protected int weight;
	protected boolean active = true;
	protected Rectangle bounds;

	public Entity(Handler handler, int x, int y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics2D g);

	public abstract void die();

	public boolean canBePushed() {
		return false;
	}

	public void hurt(int amt) {
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}

	public void push(Entity e, int xAmt, int yAmt) {
		e.setX(e.getX() + xAmt);
		e.setY(e.getY() + yAmt);
	}

	public boolean checkEntityCollisions(int xOffset, int yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
