import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Boss implements Element {
	private int yVelocity;
	private int xVelocity;
	private int lives;
	private Point position;
	private Rectangle2D.Double bossBox;
	private int size;
	private boolean shouldRemove;

	public Boss(Point initialPoint) {
		this.position = initialPoint;
		this.size = 75;
		this.bossBox = new Rectangle2D.Double(this.position.x, this.position.y, this.size, this.size);
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.lives = 500;
		this.shouldRemove = false;
	}

	public void setVelocity(int xDirection, int yDirection) {
		this.xVelocity = xDirection;
		this.yVelocity = yDirection;
	}

	public Rectangle2D.Double getBoundingBox() {
		return this.bossBox;
	}

	public void setPosition(Point newPosition) {
		this.position = newPosition;
	}

	@Override
	public void updatePosition() {
		double x = this.position.x;
		double y = this.position.y;
		x += this.xVelocity;
		y += this.yVelocity;
		this.bossBox.x = x;
		this.bossBox.y = y;
		this.setPosition(new Point((int) x, (int) y));
	}

	@Override
	public void drawOn(Graphics2D g2) {
		int x = 0;
		g2 = (Graphics2D) g2.create();
		g2.setColor(Color.RED);
		g2.fill(new Rectangle2D.Double(this.bossBox.x, this.bossBox.y, 75, 75));
		for (int i = 0; i < this.lives; i++) {
			g2.fillRect(x, 0, 3, 15);
			x += 1;
		}
	}

	@Override
	public void collideWithHero(Hero thisHero) {
		boolean hitPump = thisHero.getPump().getBoundingBox().intersects(this.bossBox);
		if (hitPump) {
			if (thisHero.getPump().getSize() / 5 == 5) {
				this.lives -= 1;
			}
		}
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		boolean hitDirt = thisDirt.getBoundingBox().intersects(this.bossBox);
		if (hitDirt) {
			thisDirt.removeNow();
		}
	}

	@Override
	public void collideWithRock(Rock thisRock) {
		boolean hitRock = thisRock.getBoundingBox().intersects(this.bossBox);
		if (hitRock) {
			thisRock.removeNow();
		}
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void getPaused() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWith(Element other) {
		// TODO Auto-generated method stub.
		other.collideWith(this);
	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		// do nothing

	}

	@Override
	public void collideWithFruit(Fruit thisFruit) {
		// do nothing

	}

	@Override
	public void collideWithBoss(Boss thisBoss) {
		// do nothing

	}

	@Override
	public boolean shouldRemove() {
		if (this.lives == 0) {
			this.shouldRemove = true;
		}
		return this.shouldRemove;
	}

}
