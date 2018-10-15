import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvanceListener implements ActionListener {
	private GameComponent gameComponent;

	public AdvanceListener(GameComponent Component) {
		this.gameComponent = Component;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		advanceOneTick();
	}

	public void advanceOneTick() {
		System.out.println("Current time " + System.currentTimeMillis());
		// The component uses user actions from listeners
		// to change the state of the game.

		// update screen
		this.gameComponent.drawScreen();
		this.gameComponent.updatePosition();
		this.gameComponent.handleCollisions();
		this.gameComponent.checkrockfall();
		this.gameComponent.updateGhostMonsterColor();
		this.gameComponent.monsterChaseHero();
	}
}
