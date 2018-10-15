import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Hero implements Element {
	private Point position;
	public int score;
	public int lives;
	private Point cordPosition;
	private String direction;
	private Pump pump;
	private int oneStep;
	public boolean isPaused;
	private Rectangle2D.Double box;
	private boolean die;

	public Hero(Point initialPosition) {
		this.position = initialPosition;
		this.score = 0;
		this.lives = 3;
		this.direction = "Left";
		this.cordPosition = initialPosition;
		this.pump = new Pump(this.cordPosition);
		this.oneStep = 25;
		this.box = new Rectangle2D.Double(this.position.x, this.position.y, 25, 25);
		this.die = false;
	}

	public boolean isOut() {
		if (this.getPosition().x >= 475 || this.getPosition().y <= 25 || this.getPosition().y >= 475
				|| this.getPosition().x <= 0) {
			return true;
		}
		return false;
	}

	public void moveHero() {
		if (!this.isPaused) {
			if (this.direction.equals("Left") && this.getPosition().x != 0) {
				this.position = new Point(position.x - this.oneStep, position.y);
				this.pump.moveTo(position.x, position.y);
			}
			if (this.direction.equals("Right") && this.getPosition().x != 475) {
				this.position = new Point(position.x + this.oneStep, position.y);
				this.pump.moveTo(this.position.x, this.position.y);
			}
			if (this.direction.equals("Up") && this.getPosition().y != 0) {
				this.position = new Point(position.x, position.y - this.oneStep);
				this.pump.moveTo(this.position.x, this.position.y);
			}
			if (this.direction.equals("Down") && this.getPosition().y != 475) {
				this.position = new Point(position.x, position.y + this.oneStep);
				this.pump.moveTo(this.position.x, this.position.y);
			}
			this.box.x = this.position.x;
			this.box.y = this.position.y;
		}

	}

	public void moveBack() {
		// this.pump.changeIsAttacking(false);
		if (this.direction.equals("Left")) {
			this.position = new Point(position.x + this.oneStep, position.y);
			this.pump.moveTo(position.x, position.y);
		}
		if (this.direction.equals("Right")) {
			this.position = new Point(position.x - this.oneStep, position.y);
			this.pump.moveTo(this.position.x, this.position.y);
		}
		if (this.direction.equals("Up")) {
			this.position = new Point(position.x, position.y + this.oneStep);
			this.pump.moveTo(this.position.x, this.position.y);
		}
		if (this.direction.equals("Down")) {
			this.position = new Point(position.x, position.y - this.oneStep);
			this.pump.moveTo(this.position.x, this.position.y);
		}
		this.box.x = this.position.x;
		this.box.y = this.position.y;
	}

	public void attack() {
		this.pump.changeIsAttacking(true);
		this.pump.setDirection(this.direction);
		// if (this.direction.equals("Left")) {
		// this.pump.moveBy(-5, 0);
		// }
		// if (this.direction.equals("Right")) {
		// this.pump.moveBy(5, 0);
		// }
		// if (this.direction.equals("Up")) {
		// this.pump.moveBy(0, -5);
		// }
		// if (this.direction.equals("Down")) {
		// this.pump.moveBy(0, 5);
		// }
	}

	public void charging() {
		this.pump.isCharging();
	}

	public void stopAttacking() {
		this.pump.changeIsAttacking(false);
		this.pump.moveTo(this.position.x, this.position.y);
	}

	public void die() {
		this.lives--;
		this.die = !this.die;
	}

	public void changeDirection(String newDirection) {
		this.direction = newDirection;
	}

	public Rectangle2D.Double getBoundingBox() {
		return this.box;
	}

	public Pump getPump() {
		return this.pump;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2 = (Graphics2D) g2.create();
		this.pump.drawPump(g2);
		// g2.fillRect(this.position.x, this.position.y, 25, 25);
		// this.pump.drawPath(g2);
		g2.fill(new Rectangle2D.Double(this.box.x, this.box.y, 25, 25));
	}

	@Override
	public void getPaused() {
		this.isPaused = !this.isPaused;

	}

	public String getDirection() {
		return this.direction;
	}

	@Override
	public void updatePosition() {
	}

	@Override
	public void collideWith(Element other) {
		other.collideWithHero(this);
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		boolean hitPump = thisDirt.getBoundingBox().intersects(this.pump.getBoundingBox());
		if (hitPump) {
			this.stopAttacking();
			this.pump.resetSize();
		}
		thisDirt.collideWithHero(this);
	}

	@Override
	public void collideWithHero(Hero thisHero) {
		// do nothing

	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		boolean hitMonster = thisMonster.getBoundingBox().intersects(this.box);
		if (hitMonster) {
			this.die = true;
		}

	}

	@Override
	public boolean shouldRemove() {
		return this.die;
	}

	@Override
	public void collideWithRock(Rock thisRock) {
		boolean hitRock = thisRock.getBoundingBox().intersects(this.box);
		if (hitRock) {
			this.moveBack();
		}
		boolean hitPump = thisRock.getBoundingBox().intersects(this.pump.getBoundingBox());
		if (hitPump) {
			this.stopAttacking();
			this.pump.resetSize();
		}
	}

	@Override
	public void collideWithFruit(Fruit thisFruit) {
	}

	@Override
	public void collideWithBoss(Boss thisBoss) {
	}

	public void moveTo(int i, int j) {
		this.position = new Point(250, 250);
		this.box.x = this.position.x;
		this.box.y = this.position.y;
		this.pump.moveTo(this.position.x, this.position.y);
	}

}
