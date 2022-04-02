package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class StartPanel extends JPanel {

	BufferedImage Background;
	Planeflying plane = new Planeflying();
	List<Bullet> bullets = new ArrayList<Bullet>();
	Random rd = new Random();
	int r1 = 3;
	int r2 = 3;

	public void action() {

		new Thread() {

			public void run() {

				while (true) {
					move();
					HeroShoot();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}

			}
		}.start();
	}

	int bulindex = 0;

	protected void HeroShoot() {
		// 造子彈
		int power = (rd.nextInt(100) % 3 + 1);
		bulindex++;
		if (bulindex >= 15) {

			if (power == 1) {
				Bullet b2 = new Bullet(plane.x, plane.y - 10, plane.w, 1, 2);
				bullets.add(b2);
			} else if (power == 2) {
				Bullet b1 = new Bullet(plane.x - 20, plane.y, plane.w, 1, 2);
				bullets.add(b1);
				Bullet b3 = new Bullet(plane.x + 20, plane.y, plane.w, 1, 2);
				bullets.add(b3);
			} else if (power == 3) {
				Bullet b1 = new Bullet(plane.x - 20, plane.y, plane.w, 0, 1);
				bullets.add(b1);
				Bullet b2 = new Bullet(plane.x, plane.y - 10, plane.w, 1, 2);
				bullets.add(b2);
				Bullet b3 = new Bullet(plane.x + 20, plane.y, plane.w, 2, 1);
				bullets.add(b3);
			}
			bulindex = 0;
		}
		// 子彈移動
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.move();
		}
	}

	int index = 0;

	protected void move() {
		index++;
		if (index >= 50) {
			r1 = rd.nextInt(4) + 2;
			r2 = rd.nextInt(4) + 3;
			index = 0;
		}
		// System.out.println(r1);
		plane.move(r1, r2);
	}

	public StartPanel() {
		Background = Tool.getImage("bg0.jpg");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Background, 0, 0, 512, 768, null);
		g.drawImage(plane.img, plane.x, plane.y, plane.w, plane.h, null);
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			g.drawImage(bullet.img, bullet.x, bullet.y, bullet.w, bullet.h, null);
		}
		g.setColor(Color.yellow);
		g.setFont(new Font("微軟正黑體", Font.BOLD, 60));
		g.drawString("F i r e 啾 啾 機", 65, 250);
	}

}