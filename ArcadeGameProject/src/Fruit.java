import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Fruit implements Element {
	private Point Position;
	private boolean isEaten;
	private Rectangle2D.Double box;
	private int oneStep = 25;

	public Fruit(int x, int y) {
		this.Position = new Point(x, y);
		this.box = new Rectangle2D.Double(this.Position.x, this.Position.y, this.oneStep, this.oneStep);
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub.
		g2.setColor(Color.red);
		this.box.x = this.Position.x;
		this.box.y = this.Position.y;
		g2.fill(new Rectangle2D.Double(this.Position.x, this.Position.y, this.oneStep, this.oneStep));
	}

	@Override
	public void getPaused() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWith(Element other) {
		// TODO Auto-generated method stub.
		other.collideWithFruit(this);
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithHero(Hero thisHero) {
		// TODO Auto-generated method stub.
		if (thisHero.getBoundingBox().intersects(this.box)) {
			this.isEaten = true;
		}
	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithRock(Rock thisRock) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub.
		return this.isEaten;
	}

	@Override
	public void collideWithFruit(Fruit thisFruit) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithBoss(Boss thisBoss) {
		boolean hitBoss = thisBoss.getBoundingBox().intersects(this.box);
		if (hitBoss) {
			this.isEaten = true;
		}
	}

}
