import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

public class GhostMonster extends Monster {
	private int yVelocity;
	private int xVelocity;
	private int times;
	int ghostTimes;
	private boolean isGhostMode;

	public GhostMonster(Point initialPoint) {
		super(initialPoint);
		this.xVelocity = 5;
		this.yVelocity = 5;
		this.times = 0;
		Random r = new Random();
		this.ghostTimes = 5 + r.nextInt(5);
		this.isGhostMode = false;
	}

	public void setVelocity(int xDirection, int yDirection) {
		this.xVelocity = xDirection;
		this.yVelocity = yDirection;
	}

	@Override
	public void updatePosition() {
		if (this.times >= this.ghostTimes) {
			this.isGhostMode = true;
			this.times = 0;
		}
		if (!this.isGhostMode) {
			super.updatePosition();
		} else {
			double x = this.position.x;
			double y = this.position.y;
			if (!this.isBeingAttack && !this.isPaused) {
				x += this.xVelocity;
				y += this.yVelocity;
				this.box.x = x;
				this.box.y = y;
				this.setPosition(new Point((int) x, (int) y));
			}
		}
	}

	@Override
	public void drawOn(Graphics2D g2) {
		super.drawOn(g2);
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		if (!this.isGhostMode) {
			boolean hitDirt = thisDirt.getBoundingBox().intersects(this.box);
			if (hitDirt) {
				this.reverseDirection();
				this.times++;
			}
		}
	}

	@Override
	public void collideWithRock(Rock thisRock) {
		if (!this.isGhostMode) {
			boolean hitRock = thisRock.getBoundingBox().intersects(this.box);
			if (hitRock) {
				this.reverseDirection();
				this.times++;
			}
		}
	}
}
