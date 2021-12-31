package scrollquest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import scrollquest.display.Display;
import scrollquest.gfx.Assets;
import scrollquest.gfx.GameCamera;
import scrollquest.input.KeyManager;
import scrollquest.input.MouseManager;
import scrollquest.states.MenuState;
import scrollquest.states.State;

public class Game implements Runnable{

	private Display display;
	private int width, height;
	public String title;

	private BufferStrategy bs;
	private Graphics2D g;

	// States
	public State menuState;

	// Input
	private MouseManager mouseManager;
	private KeyManager keyManager;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private static Handler handler;

	// Clock
	public static int round = 1;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		handler = new Handler(this);
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		gameCamera = new GameCamera(handler, 0, 0);

		menuState = new MenuState(handler);
		State.setState(menuState);
	}

	private void tick() {
		mouseManager.tick();
		keyManager.tick();

		if (State.getState() != null)
			State.getState().tick();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!

		if (State.getState() != null)
			State.getState().render(g);
		
		if (!(State.getState() == menuState)) {
		g.setColor(Color.blue);
		g.drawString(handler.getClock().getTime(), 330, 20);
		g.drawString("Press Z to flash", 315, 37);
		}
		// End Drawing!
		bs.show();
		g.dispose();
	}

	public void run() {

		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (true) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}

			if (timer >= 1000000000) {
				timer = 0;
			}
		}

	}

	public static Handler getHandler() {
		return handler;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
