package Game;

import java.util.Random;

public class Enemy extends FlyingObject {
	int enemySpeed;
	int type;
	int adjustbu = 8;
	int adjusthe = 20;

	public Enemy() {

		Random rd = new Random();
		int picnum = rd.nextInt(7);
		type = picnum;
		img = Tool.getImage("ep" + picnum + ".png");
		w = img.getWidth();
		h = img.getHeight();
		x = rd.nextInt(512 - w);
		y = -h;
		enemySpeed = 7 - picnum;
		Life = picnum + 1;
	}

	public boolean overFlow() {
		if (this.y >= 768 + h / 2 || this.x <= w / 2 || this.x >= 512 + w / 2) {
			return true;
		}
		return false;
	}

	public void move() {

		if (type == 1) {
			x -= 1;
			y += enemySpeed;
		} else if (type == 2) {
			x += 1;
			y += enemySpeed;
		} else if (type == 3) {
			y += enemySpeed + 8;
		} else {
			y += enemySpeed;
		}
	}

	public boolean shootby(Bullet bullet) {

		boolean hit = x + adjustbu <= bullet.x + bullet.w - adjustbu && x - adjustbu >= bullet.x - w + adjustbu
				&& y + adjustbu <= bullet.y + bullet.h - adjustbu && y - adjustbu >= bullet.y - h + adjustbu;
		return hit;
	}

	public boolean crash(Hero hero) {

		boolean hit = x + adjusthe <= hero.x + hero.w - adjusthe && x - adjusthe >= hero.x - w + adjusthe
				&& y + adjusthe <= hero.y + hero.h - adjusthe && y - adjusthe >= hero.y - h + adjusthe;
		return hit;
	}
}
