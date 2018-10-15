import java.awt.Graphics2D;
import java.awt.Point;

public interface Element {
	Point getPosition();

	void drawOn(Graphics2D g2);

	void getPaused();

	void updatePosition();

	void collideWith(Element other);

	void collideWithDirt(Dirt thisDirt);

	void collideWithHero(Hero thisHero);

	void collideWithMonster(Monster thisMonster);

	void collideWithRock(Rock thisRock);

	void collideWithFruit(Fruit thisFruit);

	void collideWithBoss(Boss thisBoss);

	boolean shouldRemove();
}
