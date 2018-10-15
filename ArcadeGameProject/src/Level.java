import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
	private File file;
	private String name;
	private int x;
	private int y;
	private GameComponent gg;
	private int levelnum;

	public Level(String name, GameComponent g) {
		this.file = new File("Level 1");
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.gg = g;
		this.levelnum = 1;
	}

	public void createLevel() {
		System.out.println("Creating Level");
		System.out.println(this.name);
		this.readFromFile(this.file);
	}

	public void readFromFile(File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}

		while (scanner.hasNext() == true) {
			String nextLine = scanner.next();
			for (int i = 0; i < 20; i++) {
				char nextChar = nextLine.charAt(i);
				if (nextChar == 'D') {
					Element a = new Dirt(this.x, this.y);
					this.gg.addDirts(a);

					this.x += 25;
				} else if (nextChar == 'X') {
					this.x += 25;
				} else if (nextChar == 'H') {
					this.gg.addhero(new Hero(new Point(this.x, this.y)));
					this.x += 25;
				} else if (nextChar == 'R') {
					this.gg.addRock(new Rock(this.x, this.y));
					this.x += 25;
				} else if (nextChar == 'M') {
					this.gg.addMonsters(new Monster(new Point(this.x, this.y)));
					this.x += 25;
				} else if (nextChar == 'G') {
					this.gg.addGhostMonsters(new GhostMonster(new Point(this.x, this.y)));
					this.x += 25;
				} else if (nextChar == 'S') {
					this.gg.addSpecialMonsters(new SpecialMonster(new Point(this.x, this.y)));
					this.x += 25;
				} else if (nextChar == 'B') {
					this.gg.addBoss(new Boss(new Point(this.x, this.y)));
					this.x += 25;
				}
			}
			this.x = 0;
			this.y += 25;
		}
		scanner.close();
	}

	public void upLevel() {
		if (this.levelnum <= 2) {
			this.gg.reset();
			this.x = 0;
			this.y = 0;
			this.levelnum++;
			System.out.println(this.levelnum);

			String b = new String("Level" + " " + (this.levelnum));
			this.file = new File(b);
			// System.out.println(this.file.getName());
			// this.file=new File("Level 2");
			// System.out.println(this.file.getName());
			// System.out.println(this.levelnum);
			this.createLevel();
		}
	}

	public void downLevel() {
		if (this.levelnum >= 2) {
			this.gg.reset();
			this.x = 0;
			this.y = 0;
			this.levelnum--;
			String b = new String("Level" + " " + (this.levelnum));
			this.file = new File(b);
			System.out.println(this.levelnum);
			this.createLevel();
		}
	}

	public void resetlevel() {
		this.gg.reset();
		this.x = 0;
		this.y = 0;
		String b = new String("Level" + " " + (this.levelnum));
		this.file = new File(b);
		this.createLevel();
	}

	public void setlevel(int i) {
		this.gg.reset();
		this.x = 0;
		this.y = 0;
		this.levelnum = i;
		String b = new String("Level" + " " + (this.levelnum));
		this.file = new File(b);
		this.createLevel();
	}
}
