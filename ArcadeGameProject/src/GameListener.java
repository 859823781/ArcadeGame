import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameListener implements KeyListener {
	private Level l;
	private Main m;

	public GameListener(Main m) {
		this.l = m.getLevel();
		this.m = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("U")) {
			System.out.println("Levelup lalala");
			this.l.upLevel();
		} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {
			this.l.downLevel();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("P")) {
			this.m.pauseGame();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
