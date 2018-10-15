import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Monster implements Element {
	protected Point position;
	private int velocity;
	protected String direction;
	protected boolean isBeingAttack;
	protected boolean isPaused;
	private int lives;
	protected Rectangle2D.Double box;
	private int size;
	private Color color;

	public Monster(Point initialPoint) {
		this.position = initialPoint;
		this.velocity = 5;
		this.direction = "Right";
		this.isBeingAttack = false;
		this.isPaused = false;
		this.lives = 30;
		this.size = 25;
		this.box = new Rectangle2D.Double(this.position.x, this.position.y, this.size, this.size);
		this.color = Color.MAGENTA;
	}

	@Override
	public void updatePosition() {
		double x = this.position.x;
		double y = this.position.y;
		if (!this.isBeingAttack && !this.isPaused) {
			if (this.direction.equals("Left")) {
				x = this.position.x - this.velocity;
			}
			if (this.direction.equals("Right")) {
				x = this.position.x + this.velocity;
			}
			if (this.direction.equals("Up")) {
				y = this.position.y - this.velocity;
			}
			if (this.direction.equals("Down")) {
				y = this.position.y + this.velocity;
			}
			this.box.x = x;
			this.box.y = y;
			this.setPosition(new Point((int) x, (int) y));
		}
	}

	public void setDirection(String newDirection) {
		this.direction = newDirection;
	}

	public void reverseDirection() {
		if (this.direction.equals("Right") || this.direction.equals("Left")) {
			if (this.direction.equals("Left")) {
				this.direction = "Right";
			} else {
				this.direction = "Left";
			}
		}
		if (this.direction.equals("Up") || this.direction.equals("Down")) {
			if (this.direction.equals("Up")) {
				this.direction = "Down";
			} else {
				this.direction = "Up";
			}
		}
	}

	public void setPosition(Point newPosition) {
		this.position = newPosition;
	}

	public void setColor(Color newColor) {
		this.color = newColor;
	}

	public void resetColor() {
		this.color = Color.MAGENTA;
		if (this.lives <= 20 && this.lives > 10) {
			this.color = Color.CYAN;
		}
		if (this.lives <= 10) {
			this.color = Color.GREEN;
		}
	}

	// public boolean die() {
	// if (this.isBeingAttack) {
	// this.lives--;
	// } else {
	// this.lives = 30;
	// this.size = 25;
	// }
	// if (this.lives == 0) {
	// return true;
	// }
	// return false;
	// }

	// public void beingAttack() {
	// this.isBeingAttack = true;
	// }
	//
	// public void notBeingAttack() {
	// this.isBeingAttack = false;
	// }

	public Rectangle2D.Double getBoundingBox() {
		return this.box;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.setColor(this.color);
		g2.fill(new Rectangle2D.Double(this.box.x, this.box.y, this.size, this.size));
	}

	@Override
	public void getPaused() {
		this.isPaused = !this.isPaused;

	}

	@Override
	public void collideWith(Element other) {
		other.collideWithMonster(this);
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		boolean hitDirt = thisDirt.getBoundingBox().intersects(this.box);
		if (hitDirt) {
			this.reverseDirection();
		}
	}

	@Override
	public void collideWithHero(Hero thisHero) {
		boolean hitPump = thisHero.getPump().getBoundingBox().intersects(this.box);
		if (hitPump) {
			// this.isBeingAttack = true;
			if (thisHero.getPump().getSize() / 5 == 2) {
				this.lives -= 2;
			}
			if (thisHero.getPump().getSize() / 5 == 3) {
				this.lives -= 3;
			}
			if (thisHero.getPump().getSize() / 5 >= 4) {
				this.lives -= 4;
			}
		}
	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		// Do nothing

	}

	@Override
	public boolean shouldRemove() {
		if (this.lives == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void collideWithRock(Rock thisRock) {
		boolean hitRock = thisRock.getBoundingBox().intersects(this.box);
		if (hitRock) {
			this.reverseDirection();
		}
	}

	@Override
	public void collideWithFruit(Fruit thisFruit) {
		// TODO Auto-generated method stub.

	}

	public void rockon() {
		// TODO Auto-generated method stub.
		this.lives = 0;
	}

	@Override
	public void collideWithBoss(Boss thisBoss) {
		// TODO Auto-generated method stub.

	}

}
