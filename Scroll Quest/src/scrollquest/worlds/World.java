package scrollquest.worlds;

import java.awt.Graphics2D;

import scrollquest.Handler;
import scrollquest.entities.EntityManager;
import scrollquest.entities.creatures.Bat;
import scrollquest.entities.creatures.Player;
import scrollquest.items.ItemManager;
import scrollquest.tiles.Tile;
import scrollquest.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Tile backgroundTile;
	// Entities
	private EntityManager entityManager;
	// Item
	private ItemManager itemManager;
	private int ticksDelta = 0;
	private int tickLimit = 120;
	private int batHealth, batDamage;
	private int upgradeTicks;

	public World(Handler handler, String path, Tile backgroundTile) {
		entityManager = new EntityManager(handler, new Player(handler, 640, 640));
		this.handler = handler;
		this.backgroundTile = backgroundTile;
		itemManager = new ItemManager(handler);
		loadWorld(handler, path);
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}

	public void tick() {
		itemManager.tick();
		entityManager.tick();
		Bat battl = new Bat(handler, Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 3);
		Bat battr = new Bat(handler, Tile.TILEWIDTH * 17, Tile.TILEHEIGHT * 3);
		Bat batbr = new Bat(handler, Tile.TILEWIDTH * 17, Tile.TILEHEIGHT * 17);
		Bat batbl = new Bat(handler, Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 17);
		if (ticksDelta >= tickLimit) {
			if (upgradeTicks >= 1800) {
				batDamage += 2;
				batHealth += 1;
				entityManager.getPlayer().setDamage(entityManager.getPlayer().getDamage() + 1);
				entityManager.getPlayer().setHealth(entityManager.getPlayer().getHealth() + 10);
				upgradeTicks = 0;
				if (!(tickLimit < 50))
					tickLimit -= 10;
			}
			battl.setDamage(battl.getDamage() + batDamage);
			battl.setHealth(battl.getHealth() + batHealth);
			battr.setDamage(battr.getDamage() + batDamage);
			battr.setHealth(battr.getHealth() + batHealth);
			batbr.setDamage(batbr.getDamage() + batDamage);
			batbr.setHealth(batbr.getHealth() + batHealth);
			batbl.setDamage(batbl.getDamage() + batDamage);
			batbl.setHealth(batbl.getHealth() + batHealth);
			entityManager.addEntity(battl);
			entityManager.addEntity(battr);
			entityManager.addEntity(batbl);
			entityManager.addEntity(batbr);
			ticksDelta = 0;
		}
		ticksDelta++;
		upgradeTicks++;
	}

	public void render(Graphics2D g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				backgroundTile.render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Items
		itemManager.render(g);
		// Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.dirtTile;
		return t;
	}

	private void loadWorld(Handler handler, String path) {
		entityManager = new EntityManager(handler, new Player(handler, 640, 640));
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}
