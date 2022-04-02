package Game;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject {
	int adjusthe = 15;
	int direction;
	int type;
	public static BufferedImage[] buls = new BufferedImage[5];
	static {
		for (int i = 1; i <= 3; i++)
			buls[i] = Tool.getImage("fire" + i + ".png");
	}

	public Bullet(int herox, int heroy, int herow, int dir, int kind) {
		img = buls[kind];
		type = kind;
		x = herox + (herow / 2) - 8;
		y = heroy;
		w = img.getWidth();
		h = img.getHeight();
		direction = dir;
	}

	public void move() {

		if (direction == 0) {
			x -= 1;
			y -= 3;
		} else if (direction == 1) {
			y -= 3;
		} else if (direction == 2) {
			x += 1;
			y -= 3;
		} else if (direction == 4) {
			y += 2;
		}

	}

	public boolean crash(Hero hero) {

		boolean hit = x + adjusthe <= hero.x + hero.w - adjusthe && x - adjusthe >= hero.x - w + adjusthe
				&& y + adjusthe <= hero.y + hero.h - adjusthe && y - adjusthe >= hero.y - h + adjusthe;
		return hit;
	}

}
