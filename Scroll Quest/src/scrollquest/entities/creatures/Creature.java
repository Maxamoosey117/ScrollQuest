package scrollquest.entities.creatures;

import scrollquest.Handler;
import scrollquest.entities.Entity;
import scrollquest.tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_SPEED = 3;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	protected int weight, damage;
	protected int speed;
	protected int xMove, yMove;

	public Creature(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (!(e instanceof Creature))
				continue;
			Bat b = (Bat) e;
			if (b.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xMove, 0))) {
				if (b.getWeight() < getWeight()) {
					b.setX(b.getX() + xMove);
				}
			}
			if (b.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0, yMove))) {
				if (b.getWeight() < getWeight()) {
					b.setY(b.getY() + yMove);
				}
			}
		}
		if (!checkEntityCollisions(xMove, 0))
			moveX();
		if (!checkEntityCollisions(0, yMove))
			moveY();

	}

	public void moveX() {
		if (xMove < 0) {// Up
			int tx = (int) (x + yMove + bounds.x) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tx)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tx)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.x;
			}

		} else if (xMove > 0) {// Down
			int tx = (int) (x + xMove + bounds.x + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tx)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tx)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEHEIGHT - bounds.x - bounds.height - 1;
			}

		}
	}

	public void moveY() {
		if (yMove < 0) {// Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}

		} else if (yMove > 0) {// Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}

		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// GETTERS SETTERS

	public int getxMove() {
		return xMove;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
