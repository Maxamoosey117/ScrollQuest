package scrollquest.entities.creatures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import scrollquest.Handler;
import scrollquest.entities.Entity;
import scrollquest.gfx.Animation;
import scrollquest.gfx.Assets;
import scrollquest.inventory.Inventory;

public class Player extends Creature {

	// Animations
	private Animation animDown, animUp, animLeft, animRight, animNoneUp, animNoneDown, animNoneLeft, animNoneRight;
	// Inventory
	private Inventory inventory;

	private long lastAttackTimer, attackCooldown = 5000, attackTimer = attackCooldown;
	private boolean bombflash = false;
	private int bombFlashTimer = 5;

	// Direction Facing
	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private Direction facing;

	public Player(Handler handler, int x, int y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 7 * 2;
		bounds.y = 4 * 2;
		bounds.width = 18 * 2;
		bounds.height = 27 * 2;
		damage = 2;
		health = 50;
		speed = 4;
		weight = 5;

		// Animatons
		animNoneUp = new Animation(300, Assets.player_none_up);
		animNoneDown = new Animation(300, Assets.player_none_down);
		animNoneLeft = new Animation(300, Assets.player_none_left);
		animNoneRight = new Animation(300, Assets.player_none_right);
		animDown = new Animation(300, Assets.player_down);
		animUp = new Animation(300, Assets.player_up);
		animLeft = new Animation(300, Assets.player_left);
		animRight = new Animation(300, Assets.player_right);

		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		// Animations
		animNoneLeft.tick();
		animNoneRight.tick();
		animNoneDown.tick();
		animNoneUp.tick();
		animRight.tick();
		animLeft.tick();
		animUp.tick();
		animDown.tick();
		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		// Inventory
		inventory.tick();
	}

	private void checkAttacks() {
		/*
		 * attackTimer += System.currentTimeMillis() - lastAttackTimer; lastAttackTimer
		 * = System.currentTimeMillis(); if (attackTimer < attackCooldown) return;
		 */

		if (inventory.isActive())
			return;

/*
  		Rectangle cb = getCollisionBounds(0, 0);
 
		Rectangle ar = new Rectangle();
		int arSize = 64;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_X))
			if (getFacing() == Direction.LEFT) {
				ar.x = cb.x - arSize;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			} else if (getFacing() == Direction.RIGHT) {
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			} else if (getFacing() == Direction.UP) {
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			} else if (getFacing() == Direction.DOWN) {
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + cb.height;
			}

		// attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(damage);
			}
		}
*/
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) {
			bombflash = true;
			attackTimer = 0;

			for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
				if (e.equals(this))
					continue;
				e.hurt(999999999);
			}
		}
	}

	@Override
	public void die() {
		System.out.println("You lose");
		System.out.println("Score: " + handler.getScore());
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (inventory.isActive())
			return;

		if (handler.getKeyManager().left) {
			xMove = -speed;
			setFacing(Direction.LEFT);
		}
		if (handler.getKeyManager().right) {
			xMove = speed;
			setFacing(Direction.RIGHT);
		}
		if (handler.getKeyManager().up) {
			yMove = -speed;
			setFacing(Direction.UP);
		}
		if (handler.getKeyManager().down) {
			yMove = speed;
			setFacing(Direction.DOWN);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(bombflash) 
			if(bombFlashTimer > 0) {
			g.clearRect(0, 0, 720, 480);
			bombFlashTimer--;
			return;
			} else {
				bombflash = false;
				bombFlashTimer = 5;
			}
		
		g.setColor(Color.WHITE);
		g.drawImage(getCurrentAnimationFrame(),x - handler.getGameCamera().getxOffset(),
				y - handler.getGameCamera().getyOffset(), width, height, null);
		g.setColor(Color.RED);
		g.drawString("Health: " + Integer.toString(getHealth()), getX() + getWidth() / 2 - 5 - handler.getGameCamera().getxOffset(),
				getY() - handler.getGameCamera().getyOffset());
		g.setColor(Color.BLUE);
		long attackSeconds = 0;
		if (attackTimer > 5000) attackSeconds = 0;
		else attackSeconds = 6000 - attackTimer;
		g.drawString("Flash Timer: " + Long.toString(attackSeconds/1000), getX() + getWidth() / 2 - 5 - handler.getGameCamera().getxOffset(),
				getY() - 20 - handler.getGameCamera().getyOffset());
	}

	public void postRender(Graphics2D g) {
		inventory.render(g);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		}
		if (getFacing() == Direction.UP)
			return animNoneUp.getCurrentFrame();
		else if (getFacing() == Direction.LEFT)
			return animNoneLeft.getCurrentFrame();
		else if (getFacing() == Direction.RIGHT)
			return animNoneRight.getCurrentFrame();
		else
			return animNoneDown.getCurrentFrame();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Direction getFacing() {
		return facing;
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
	}

}
