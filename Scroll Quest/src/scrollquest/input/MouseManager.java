package scrollquest.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean[] buttons, justPressed, cantPress;

	public MouseManager() {
		buttons = new boolean[3];
		justPressed = new boolean[buttons.length];
		cantPress = new boolean[buttons.length];
	}

	public void tick() {
		for (int i = 0; i < buttons.length; i++) {
			if (cantPress[i] && !buttons[i]) {
				cantPress[i] = false;
			} else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && buttons[i]) {
				justPressed[i] = true;
			}
		}
	}

	public boolean buttonJustPressed(int button) {
		return justPressed[button];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
