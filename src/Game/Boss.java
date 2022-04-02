package Game;

public class Boss extends FlyingObject {

	boolean LR = true;// 往右
//	boolean UD = true;//上
	int adjustbu = 8;
	int adjusthe = 15;

	public Boss() {

		img = Tool.getImage("統神端火鍋.png");
		x = 220;
		y = -200;
		w = 150;
		h = 200;
		Life = 100;

	}

	public void move(int x1, int x2) {

		if (y < h / 2) {
			y += 2;
		}
		if (x >= -30 && x <= 30) {
			LR = true;
		} else if (x >= 282 && x <= 342) {
			LR = false;
		}
		if (LR) {
			x += x1;
		} else {
			x -= x2;
		}
//		if (y==468) {
//			UD = true;
//		} else if (y==0) {
//			UD = false;
//		}
//		if (UD) {
//			y -=2 ;
//		} else {
//			y += 2;
//		}

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
