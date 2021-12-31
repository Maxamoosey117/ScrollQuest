package scrollquest.states;

import java.awt.Graphics2D;

import scrollquest.Handler;
import scrollquest.tiles.Tile;
import scrollquest.worlds.World;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "/worlds/overworld.lvl", Tile.grassTile);
		handler.setWorld(world);
		handler.getClock().start();
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics2D g) {
		world.render(g);
	}

}
