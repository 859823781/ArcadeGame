import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Pump implements Element {
	private Point position;
	private boolean isAttacking;
	private Rectangle2D.Double box;
	private int size;
	private int velocity;
	private String direction;

	public Pump(Point initialPosition) {
		this.position = initialPosition;
		this.isAttacking = false;
		this.size = 10;
		this.velocity = 5;
		this.box = new Rectangle2D.Double(this.position.x, this.position.y, this.size, this.size);
		this.direction = null;
	}

	public void drawPump(Graphics2D g2) {
		g2.fill(new Rectangle2D.Double(this.box.x, this.box.y, this.size, this.size));
	}

	public void resetSize() {
		this.size = 10;
	}

	public int getSize() {
		return this.size;
	}

	public void isCharging() {
		if (!this.isAttacking) {
			this.size++;
			if (this.size >= 25) {
				this.size = 25;
			}
		}
	}

	public void setDirection(String Direction) {
		this.direction = Direction;
	}

	public void moveBy(int dx, int dy) {
		this.position = new Point(position.x + dx, position.y + dy);
		this.box.x = this.position.x;
		this.box.y = this.position.y;
		System.out.println(this.position.x + "a" + this.position.y);
	}

	public void moveTo(int dx, int dy) {
		if (!this.isAttacking) {
			this.position = new Point(dx, dy);
			this.box.x = dx;
			this.box.y = dy;
		}
	}

	public void changeIsAttacking(boolean newSituation) {
		this.isAttacking = newSituation;
	}

	public Rectangle2D.Double getBoundingBox() {
		return this.box;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub.
		return this.position;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void getPaused() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void updatePosition() {
		if (this.isAttacking) {
			if (this.direction.equals("Right")) {
				this.box.x += this.velocity;
			}
			if (this.direction.equals("Left")) {
				this.box.x -= this.velocity;
			}
			if (this.direction.equals("Down")) {
				this.box.y += this.velocity;
			}
			if (this.direction.equals("Up")) {
				this.box.y -= this.velocity;
			}
		}
	}

	@Override
	public void collideWith(Element other) {
		// do nothing
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithHero(Hero thisHero) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub.
		return false;
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
		// TODO Auto-generated method stub.

	}
}
