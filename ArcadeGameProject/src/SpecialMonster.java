import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class SpecialMonster extends Monster {

	private int times;
	private Rectangle2D.Double newBox;
	private boolean isFiring;

	public SpecialMonster(Point initialPoint) {
		super(initialPoint);
		this.times = 0;
		this.newBox = new Rectangle2D.Double(this.position.x, this.position.y, 25, 25);
		this.isFiring = false;
	}

	public void firing() {
		if (this.times == 20) {
			this.times = 0;
			this.isFiring = true;
			this.isPaused = true;
			this.newBox.x = this.box.x;
			this.newBox.y = this.box.y;
			if (this.direction.equals("Left")) {
				this.newBox.x -= 25;
			}
			if (this.direction.equals("Right")) {
				this.newBox.x += 25;
			}
			if (this.direction.equals("Up")) {
				this.newBox.y -= 25;
			}
			if (this.direction.equals("Down")) {
				this.newBox.y += 25;
			}
		}
	}

	@Override
	public void updatePosition() {
		super.updatePosition();
		this.times++;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		this.setColor(Color.YELLOW);
		super.drawOn(g2);
		this.firing();
		if (this.isFiring) {
			g2.setColor(Color.RED);
			g2.fill(new Rectangle2D.Double(this.newBox.x, this.newBox.y, 25, 25));
			this.times++;
			if (this.times == 5) {
				this.times = 0;
				this.isFiring = false;
				this.isPaused = false;
			}

		}
	}

}
