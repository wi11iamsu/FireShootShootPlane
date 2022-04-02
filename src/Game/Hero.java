package Game;

public class Hero extends FlyingObject {

	public Hero() {

		img = Tool.getImage("HeroPlane" + StoreButton.choice + ".png");
		x = 220;
		y = 600;
		w = 100;// img.getWidth();
		h = 100;// img.getHeight();
		Life = 1;
	}

	public void moveToMouse(int mouseX, int mouseY) {
		x = mouseX - (w / 2);
		y = mouseY - (h / 2);
	}

	public void moveUp() {
		y -= 10;
	}

	public void moveDown() {
		y += 10;
	}

	public void moveLeft() {
		x -= 10;
	}

	public void moveRight() {
		x += 10;
	}

}
