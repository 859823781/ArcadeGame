import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Dirt implements Element {
	private Point position;
	private int oneStep = 25;
	private boolean shouldRemove;
	private Rectangle2D.Double box;

	public Dirt(int x, int y) {
		this.position = new Point(x, y);
		this.box = new Rectangle2D.Double(this.position.x, this.position.y, this.oneStep, this.oneStep);
	}

	public Rectangle2D.Double getBoundingBox() {
		return this.box;
	}

	public void removeNow() {
		this.shouldRemove = true;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub.
		return this.position;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub.

		g2.setColor(Color.pink);
		g2.fill(new Rectangle2D.Double(this.box.x, this.box.y, this.oneStep, this.oneStep));
	}

	@Override
	public void getPaused() {
		// TODO Auto-generated method stub.

	}

	public void setPosition(int a, int b) {
		this.position.x = a;
		this.position.y = b;
		this.box.x = a;
		this.box.y = b;
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWith(Element other) {
		// TODO Auto-generated method stub.
		other.collideWithDirt(this);
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		// Do nothing

	}

	@Override
	public void collideWithHero(Hero thisHero) {
		// TODO Auto-generated method stub.
		boolean hitHero = thisHero.getBoundingBox().intersects(this.box);
		if (hitHero) {
			this.shouldRemove = true;
		}

	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		// Do nothing

	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub.
		return this.shouldRemove;
	}

	@Override
	public void collideWithRock(Rock thisRock) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithFruit(Fruit thisFruit) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithBoss(Boss thisBoss) {
		// do nothing
	}

}
