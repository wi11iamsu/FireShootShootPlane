package Game;

import java.util.Random;

public class Planeflying extends FlyingObject {

	Random rd = new Random();
	String Y = "U";
	String X = "R";
	int n = rd.nextInt(3) + 1;

	public Planeflying() {

		img = Tool.getImage("HeroPlane" + n + ".png");
		x = 220;
		y = 600;
		w = 100;// img.getWidth();
		h = 100;// img.getHeight();
	}

	public void move(int r1, int r2) {

		if (x <= 0 && x >= -10) {
			X = "R";
		} else if (x >= 409 && x <= 419) {
			X = "L";
		}
		if (y <= 0 && y >= -10) {
			Y = "D";
		} else if (y >= 600 && x <= 672) {
			Y = "U";
		}
		// ¥ª¥k
		if (X.equals("R")) {
			x += r1;
		} else {
			x -= r1;
		}
		// ¤W¤U
		if (Y.equals("U")) {
			y -= r2;
		} else {
			y += r2;
		}

	}
}
