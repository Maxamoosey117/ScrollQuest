package scrollquest.entities.creatures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scrollquest.Handler;
import scrollquest.entities.Entity;
import scrollquest.gfx.Animation;
import scrollquest.gfx.Assets;
import scrollquest.items.Item;

public class Bat extends Creature {

	private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;
	private Animation animUp;

	public Bat(Handler handler, int x, int y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 3 * 2;
		bounds.y = 8 * 2;
		bounds.width = 28 * 2;
		bounds.height = 19 * 2;
		damage = 1;
		health = 1;
		speed = 2;
		weight = 1;

		animUp = new Animation(300, Assets.bat_up);
	}

	@Override
	public void tick() {
		targetPlayer();
		move();
		attack();
		animUp.tick();
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(animUp.getCurrentFrame(), x - handler.getGameCamera().getxOffset(),
				y - handler.getGameCamera().getyOffset(), width, height, null);
		g.setColor(Color.DARK_GRAY);
		g.drawString(String.valueOf(health), x + 26 - handler.getGameCamera().getxOffset(),
				y - handler.getGameCamera().getyOffset() + 5);
	}

	@Override
	public void move() {
		if (!checkEntityCollisions(xMove - 1, 0) || !checkEntityCollisions(xMove + 1, 0))
			moveX();
		if (!checkEntityCollisions(0, yMove - 1) || !checkEntityCollisions(0, yMove + 1))
			moveY();
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.scroll.createNew(x, y));
	}

	public void attack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle art = new Rectangle();
		Rectangle arb = new Rectangle();
		Rectangle arl = new Rectangle();
		Rectangle arr = new Rectangle();

		int arSize = 64;
		art.width = arSize;
		art.height = arSize/10;
		arb.width = arSize;
		arb.height = arSize/10;
		arl.width = arSize;
		arl.height = arSize/10;
		arr.width = arSize;
		arr.height = arSize/10;

		arl.x = cb.x - arSize;
		arl.y = cb.y + cb.height / 2 - arSize / 2;
		arr.x = cb.x + cb.width;
		arr.y = cb.y + cb.height / 2 - arSize / 2;
		art.x = cb.x + cb.width / 2 - arSize / 2;
		art.y = cb.y - arSize;
		arb.x = cb.x + cb.width / 2 - arSize / 2;
		arb.y = cb.y + cb.height;
		
		attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e instanceof Bat)
				continue;
			Rectangle ecb = e.getCollisionBounds(0, 0);
			if (ecb.intersects(art) || ecb.intersects(arb) || ecb.intersects(arr) || ecb.intersects(arl)) {
				e.hurt(damage);
				return;
			}
		}

		attackTimer = 0;
	}

	public void targetPlayer() {
		xMove = 0;
		yMove = 0;

		Player player = handler.getWorld().getEntityManager().getPlayer();

		if (player.getCollisionBounds(0, 0).getY()
				+ player.getCollisionBounds(0, 0).getHeight() / 2 > getCollisionBounds(0, 0).getY()
						+ getCollisionBounds(0, 0).getHeight() / 2) {
			yMove = speed;
		}
		if (player.getCollisionBounds(0, 0).getY()
				+ player.getCollisionBounds(0, 0).getHeight() / 2 < getCollisionBounds(0, 0).getY()
						+ getCollisionBounds(0, 0).getHeight() / 2) {
			yMove = -speed;
		}
		if (player.getCollisionBounds(0, 0).getX()
				+ player.getCollisionBounds(0, 0).getWidth() / 2 < getCollisionBounds(0, 0).getX()
						+ getCollisionBounds(0, 0).getHeight() / 2) {
			xMove = -speed;
		}
		if (player.getCollisionBounds(0, 0).getX()
				+ player.getCollisionBounds(0, 0).getWidth() / 2 > getCollisionBounds(0, 0).getX()
						+ getCollisionBounds(0, 0).getHeight() / 2) {
			xMove = speed;
		}
	}

}
