package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class StorePanel extends JPanel {
	BufferedImage Background;
	BufferedImage Plane1;
	BufferedImage Plane2;
	BufferedImage Plane3;

	public StorePanel() {
		Background = Tool.getImage("bg0.jpg");
		Plane1 = Tool.getImage("HeroPlane1.png");
		Plane2 = Tool.getImage("HeroPlane2.png");
		Plane3 = Tool.getImage("HeroPlane3.png");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawImage(Background, 0, 0, 512, 768, null);
		g.drawImage(Plane1, 156, 50, 200, 200, null);
		g.drawImage(Plane2, 156, 270, 200, 200, null);
		g.drawImage(Plane3, 156, 490, 200, 200, null);
	}
}
