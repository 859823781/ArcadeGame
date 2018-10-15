import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	private boolean running = false;
	private Level l;
	public static final int DELAY = 100;
	public JFrame frame;
	public JLabel label;
	private GameComponent g;
	/**
	 * @param args
	 */
	public Main() {
		System.out.println("Write your cool arcade game here!");
		System.out.println("Write your cool arcade game here!");
		JFrame frame = new JFrame("Our game");
		this.frame = frame;
		frame.setSize(505, 555);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Point a = new Point(250, 250);

		GameComponent co = new GameComponent(this);
		this.g = co;
		AdvanceListener advanceListener = new AdvanceListener(co);
		frame.add(co);

		Monster m = new Monster(new Point(50, 50));
		Level lll = new Level("aaa", co);
		this.l = lll;
		this.l.createLevel();
		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();
		GameListener keyboard = new GameListener(this);
		this.frame.addKeyListener(keyboard);
		JLabel score = new JLabel();
		this.label = score;
		frame.setVisible(true);
		frame.add(this.label, BorderLayout.NORTH);
		this.label.setText("Scores:" + 0);

		advanceListener.advanceOneTick();
	}

	public static void main(String[] args) {
		new Main();
	}

	public Level getLevel() {
		return this.l;
	}
	
	public void pauseGame(){
		this.g.pauseGame();
	}

}
