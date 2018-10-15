import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	private Hero h;

	public KeyboardListener(Hero h) {
		this.h = h;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		String a = KeyEvent.getKeyText(e.getKeyCode());
		if (a.equals("Left") || a.equals("Right") || a.equals("Up") || a.equals("Down")) {
			this.h.changeDirection(a);
			// System.out.println(this.h.getDirection());
			this.h.moveHero();
		}
		// System.out.println(this.h.getPosition());
		else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Z")) {
			System.out.println("AAAAA");
			this.h.charging();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("Z")) {
			// System.out.println("AAAAA");
			this.h.attack();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
