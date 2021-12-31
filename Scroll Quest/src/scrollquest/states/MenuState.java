package scrollquest.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import scrollquest.Handler;
import scrollquest.gfx.Assets;

public class MenuState extends State{

	private Handler handler;
	private int selected = 0;
	
	public MenuState(Handler handler) {
		super(handler);
		this.handler = handler;
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
			selected--;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
			selected++;
		}
		
		if (selected > 1) selected = 0;
		if (selected < 0) selected = 1;
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
			if (selected == 0) {
				State.setState(new GameState(handler));
			}
			if (selected == 1) System.exit(0);
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(Assets.startup, 0, 0, null);
		g.drawImage(Assets.title, 225, 15, null);
		if (selected == 0) {
			g.drawImage(Assets.playh, 275, 150, null);
			g.drawImage(Assets.quit, 275, 275, null);
		}
		else {
			g.drawImage(Assets.play, 275, 150, null);
			g.drawImage(Assets.quith, 275, 275, null);
		}
		}

}
