package scrollquest.tiles;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile cobbleTile = new CobbleTile(2);
	public static Tile treeTile = new TreeTile(3);
	public static Tile housetl = new  HouseTL(4);
	public static Tile housetr = new  HouseTR(5);
	public static Tile housebl = new  HouseBL(6);
	public static Tile housebr = new  HouseBR(7);
	public static Tile bushTile = new BushTile(8);
	public static Tile boulder = new Boulder(9);
	
	//CLASS
	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected Rectangle tbounds;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
		tbounds = new Rectangle(0, 0, TILEWIDTH, TILEHEIGHT);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
