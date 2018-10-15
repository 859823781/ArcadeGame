import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rock implements Element {
	private Point Position;
	private Rectangle2D.Double box;

	private int oneStep = 25;
	public boolean isfallen;
	private int tick;
	private boolean isOnRock;
	private boolean remove;

	public Rock(int x, int y) {
		this.Position = new Point(x, y);
		this.isfallen = false;
		this.box = new Rectangle2D.Double(this.Position.x, this.Position.y, this.oneStep, this.oneStep);
	}

	public Rectangle2D.Double getBoundingBox() {
		return this.box;
	}

	public void removeNow() {
		this.remove = true;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub.
		return this.Position;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub.
		g2.setColor(Color.orange);
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
		other.collideWithRock(this);
	}

	@Override
	public void collideWithDirt(Dirt thisDirt) {
		// TODO Auto-generated method stub.
		boolean hitDirt = thisDirt.getBoundingBox().intersects(this.box);
		// System.out.println("AAA");
		if (hitDirt) {
			this.Position.y -= this.oneStep;
			System.out.println("BBB" + this.Position);
			this.isOnRock = true;

			this.isfallen = true;

		}

	}

	@Override
	public void collideWithHero(Hero thisHero) {
		// TODO Auto-generated method stub.
		boolean hitHero = thisHero.getBoundingBox().intersects(this.box);
		if (hitHero) {
			thisHero.die();
		}
		// this.isfallen = true;
	}

	@Override
	public void collideWithMonster(Monster thisMonster) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub.
		return this.remove;
	}

	@Override
	public void collideWithRock(Rock thisRock) {
		// TODO Auto-generated method stub.
	}

	public void fall() {
		this.tick++;
		System.out.println("CCC");

		if (this.tick == 15) {
			// while(this.isOnRock ==false){
			// System.out.println("DDD");
			//
			// this.Position.y+=this.oneStep;
			//
			// }
			this.Position = new Point(this.Position.x, this.Position.y + this.oneStep);
			this.isfallen = true;
			this.tick = 0;
		}
	}

	public void getFallen() {
		this.tick++;
		if (this.tick == 3) {
			this.remove = true;
		}

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
