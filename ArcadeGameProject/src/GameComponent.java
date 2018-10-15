import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class GameComponent extends JComponent {
	private ArrayList<Element> monsters;
	private ArrayList<Element> heros;
	private ArrayList<Element> dirts;
	private ArrayList<Element> rocks;
	private ArrayList<Element> ghostMonsters;
	private ArrayList<Element> specialMonsters;
	private ArrayList<Element> boss;
	private ArrayList<Element> fruits;
	private int fruitNum;
	private Main m;
	private int Numfallenrocks = 0;
	private int liveremianed = 3;

	private int alertdistance = 150;
	private int numTicks;

	public GameComponent(Main m) {
		this.heros = new ArrayList<Element>();
		this.monsters = new ArrayList<Element>();
		this.dirts = new ArrayList<Element>();
		this.rocks = new ArrayList<Element>();
		this.ghostMonsters = new ArrayList<Element>();
		this.specialMonsters = new ArrayList<Element>();
		this.boss = new ArrayList<Element>();
		this.fruits = new ArrayList<Element>();
		this.m = m;
	}

	public void addFruits(Element a) {
		this.fruits.add(a);
	}

	public void addDirts(Element a) {
		this.dirts.add(a);
	}

	public void addMonsters(Element a) {
		this.monsters.add(a);
	}

	public void addhero(Element b) {
		this.heros.add(b);
		KeyboardListener keyboard = new KeyboardListener((Hero) b);
		this.m.frame.addKeyListener(keyboard);

	}

	public void addRock(Element c) {
		this.rocks.add(c);
	}

	public void addGhostMonsters(Element g) {
		this.ghostMonsters.add(g);
	}

	public void addSpecialMonsters(Element g) {
		this.specialMonsters.add(g);
	}

	public void addBoss(Element a) {
		this.boss.add(a);
	}

	public void updatePosition() {
		updateMonsters();
		this.updatePump();
		this.numTicks++;
	}

	public void updatePump() {
		for (Element h : this.heros) {
			((Hero) h).getPump().updatePosition();
		}
	}

	public boolean isOut(Element e) {
		if (e.getPosition().x >= 475 || e.getPosition().y <= 25 || e.getPosition().y >= 475 || e.getPosition().x <= 0) {
			return true;
		}
		return false;
	}

	private void updateMonsters() {
		// for (Element e : this.monsters) {
		// if(!isOut(e)){
		// e.updatePosition();
		// }else{
		// ()
		// }
		// }
		for (Element g : this.ghostMonsters) {
			if (!isOut(g)) {
				g.updatePosition();
			} else {
				((Monster) g).reverseDirection();
				g.updatePosition();
			}
		}
		for (Element s : this.specialMonsters) {
			if (!isOut(s)) {
				s.updatePosition();
			} else {
				((Monster) s).reverseDirection();
				s.updatePosition();
			}
		}
		for (Element b : this.boss) {
			b.updatePosition();
		}
	}

	public void updateGhostMonsterColor() {
		for (Element g : this.ghostMonsters) {
			((Monster) g).resetColor();
			for (Element d : this.dirts) {
				Point ghostPoint = g.getPosition();
				Point dirtPoint = d.getPosition();
				if (dirtPoint.x + 24 > ghostPoint.x && ghostPoint.x > dirtPoint.x - 24) {
					if (dirtPoint.y + 24 > ghostPoint.y && ghostPoint.y > dirtPoint.y - 24) {
						((GhostMonster) g).setColor(Color.GRAY);
					}
				}
			}
		}
	}

	public void monsterChaseHero() {
		for (Element h : this.heros) {
			for (Element g : this.ghostMonsters) {
				if (h.getPosition().x == g.getPosition().x && h.getPosition().y == g.getPosition().y) {
					((GhostMonster) g).setVelocity(0, 0);
				} else {
					if (h.getPosition().x == g.getPosition().x) {
						if (h.getPosition().y > g.getPosition().y) {
							((GhostMonster) g).setVelocity(0, 1);
						} else {
							((GhostMonster) g).setVelocity(0, -1);
						}
					}
					if (h.getPosition().y == g.getPosition().y) {
						if (h.getPosition().x > g.getPosition().x) {
							((GhostMonster) g).setVelocity(1, 0);
						} else {
							((GhostMonster) g).setVelocity(-1, 0);
						}
					}
					if (h.getPosition().x != g.getPosition().x && h.getPosition().y != g.getPosition().y) {
						if (h.getPosition().x > g.getPosition().x) {
							if (h.getPosition().y > g.getPosition().y) {
								((GhostMonster) g).setVelocity(1, 1);
							} else {
								((GhostMonster) g).setVelocity(1, -1);
							}
						} else {
							if (h.getPosition().y > g.getPosition().y) {
								((GhostMonster) g).setVelocity(-1, 1);
							} else {
								((GhostMonster) g).setVelocity(-1, -1);
							}
						}
					}
				}
			}
		}
		for (Element h : this.heros) {
			for (Element b : this.boss) {
				if (h.getPosition().x == b.getPosition().x && h.getPosition().y == b.getPosition().y) {
					((Boss) b).setVelocity(0, 0);
				} else {
					if (h.getPosition().x == b.getPosition().x) {
						if (h.getPosition().y > b.getPosition().y) {
							((Boss) b).setVelocity(0, 1);
						} else {
							((Boss) b).setVelocity(0, -1);
						}
					}
					if (h.getPosition().y == b.getPosition().y) {
						if (h.getPosition().x > b.getPosition().x) {
							((Boss) b).setVelocity(1, 0);
						} else {
							((Boss) b).setVelocity(-1, 0);
						}
					}
					if (h.getPosition().x != b.getPosition().x && h.getPosition().y != b.getPosition().y) {
						if (h.getPosition().x > b.getPosition().x) {
							if (h.getPosition().y > b.getPosition().y) {
								((Boss) b).setVelocity(1, 1);
							} else {
								((Boss) b).setVelocity(1, -1);
							}
						} else {
							if (h.getPosition().y > b.getPosition().y) {
								((Boss) b).setVelocity(-1, 1);
							} else {
								((Boss) b).setVelocity(-1, -1);
							}
						}
					}
				}
			}
		}
	}

	public void handleCollisions() {
		List<Element> allObjects = new ArrayList<>();
		allObjects.addAll(dirts);
		allObjects.addAll(rocks);
		allObjects.addAll(heros);
		allObjects.addAll(monsters);
		allObjects.addAll(ghostMonsters);
		allObjects.addAll(specialMonsters);
		allObjects.addAll(boss);
		allObjects.addAll(fruits);
		for (Element object : allObjects) {
			for (Element object2 : allObjects) {
				if (object != object2 && !object.shouldRemove() && !object2.shouldRemove()) {
					object.collideWith(object2);
				}
			}
		}
		List<Element> shouldRemove = new ArrayList<>();

		for (Element object : allObjects) {
			if (object.shouldRemove()) {
				shouldRemove.add(object);
			}
		}
		for (Element object : shouldRemove) {
			if (object.getClass().getSimpleName() == "Monster") {
				this.updateScore(400);
			}
			if (object.getClass().getSimpleName() == "Fruit") {
				this.updateScore(1000);
			}
			if (object.getClass().getSimpleName() == "Rock") {
				this.Numfallenrocks++;
			}
			if (object.getClass().getSimpleName() == "Hero") {
				((Hero) this.heros.get(0)).moveTo(250, 250);
				((Hero) this.heros.get(0)).die();
				this.newlive();
				if (((Hero) object).lives == 0) {
					this.startOver();
				}
				this.repaint();

			}
			this.fruits.remove(object);
			this.dirts.remove(object);

			this.monsters.remove(object);
			this.rocks.remove(object);
			this.ghostMonsters.remove(object);
			this.specialMonsters.remove(object);
			this.boss.remove(object);
			if (this.Numfallenrocks == 2 && this.fruitNum == 0) {
				this.fruits.add(new Fruit(250, 250));
				this.fruitNum++;
			}
			if (this.ghostMonsters.size() == 0) {
				this.m.getLevel().upLevel();
			}
		}
	}

	private void startOver() {
		// TODO Auto-generated method stub.
		this.m.getLevel().setlevel(1);

	}

	private void newlive() {
		ArrayList<Element> a = new ArrayList<Element>();
		ArrayList<Element> b = new ArrayList<Element>();
		for (Element d : this.dirts) {
			a.add(d);
		}
		for (Element r : this.rocks) {
			b.add(r);
		}
		this.m.getLevel().resetlevel();
		this.dirts = a;
		this.rocks = b;
		this.liveremianed--;

		((Hero) this.heros.get(0)).lives = this.liveremianed;
	}

	public void reset() {
		this.heros = new ArrayList<Element>();
		this.monsters = new ArrayList<Element>();
		this.dirts = new ArrayList<Element>();
		this.rocks = new ArrayList<Element>();
		this.ghostMonsters = new ArrayList<Element>();
		this.specialMonsters = new ArrayList<Element>();
		this.boss = new ArrayList<Element>();
	}

	public void pauseGame() {
		for (Element hero : this.heros) {
			hero.getPaused();
		}
		for (Element e : this.dirts) {
			e.getPaused();
		}
		for (Element e : this.monsters) {
			e.getPaused();
		}
		for (Element g : this.ghostMonsters) {
			g.getPaused();
		}
		for (Element s : this.specialMonsters) {
			s.getPaused();
		}
		for (Element b : this.boss) {
			b.getPaused();
		}
	}

	public void checkrockfall() {
		for (Element mm : this.rocks) {
			if (getDirtbyPosition(mm.getPosition().x, mm.getPosition().y + 25) == null) {
				((Rock) mm).fall();

				for (Element hh : this.heros) {
					if (Math.abs(hh.getPosition().x - mm.getPosition().x) < 25
							&& Math.abs(hh.getPosition().y - mm.getPosition().y) < 25) {
						((Hero) hh).die();
					}
				}
				for (Element mon : this.ghostMonsters) {
					if (Math.abs(mon.getPosition().x - mm.getPosition().x) < 25
							&& Math.abs(mon.getPosition().y - mm.getPosition().y) < 25) {
						((Monster) mon).rockon();
					}
				}
			}
			if (getDirtbyPosition(mm.getPosition().x, mm.getPosition().y + 25) != null
					&& ((Rock) mm).isfallen == true) {
				((Rock) mm).getFallen();
			}
		}

	}

	public Element getDirtbyPosition(int x, int y) {
		for (Element dd : this.dirts) {
			if (dd.getPosition().x == x && dd.getPosition().y == y) {
				return dd;
			}
		}
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Element e : this.dirts) {
			e.drawOn(g2);
		}
		for (Element hero : this.heros) {
			hero.drawOn(g2);
		}
		for (Element m : this.monsters) {
			m.drawOn(g2);
		}
		for (Element r : this.rocks) {
			r.drawOn(g2);
		}
		for (Element f : this.ghostMonsters) {
			f.drawOn(g2);
		}
		for (Element s : this.specialMonsters) {
			s.drawOn(g2);
		}
		for (Element b : this.boss) {
			b.drawOn(g2);
		}
		for (Element t : this.fruits) {
			t.drawOn(g2);
		}
	}

	public void drawScreen() {
		this.repaint();
	}

	public Hero getHero() {
		return (Hero) this.heros.get(0);
	}

	public void updateScore(int s) {
		for (Element hh : this.heros) {
			int a = ((Hero) hh).score += s;
			this.m.label.setText("Scores:" + ((Hero) hh).score);
		}
	}
}
