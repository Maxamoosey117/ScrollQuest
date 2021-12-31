package scrollquest;

import java.net.URL;

import scrollquest.gfx.GameCamera;
import scrollquest.input.KeyManager;
import scrollquest.input.MouseManager;
import scrollquest.utils.Clock;
import scrollquest.worlds.World;

public class Handler {
	
	private Game game;
	private Clock clock = new Clock();
	private World world;
	private int score = 0;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public URL getRes(String path){
		return this.getClass().getResource(path);
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public Clock getClock() {
		return clock;
	}

	public void setClockTime(int hours, int minutes, int seconds) {
		clock.setTime(hours, minutes, seconds);
	}

	public int getScore() {
		return score;
	}

	public void addToScore(int amt) {
		score += amt;
	}
}
