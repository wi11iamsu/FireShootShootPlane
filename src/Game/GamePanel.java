package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public static BufferedImage[] bgs = new BufferedImage[5];
	static {
		for (int i = 1; i <= 5; i++)
			bgs[i - 1] = Tool.getImage("bg" + i + ".jpg");
	}

	Random rd = new Random();
	int bgindex = rd.nextInt(5);
	BufferedImage Background = bgs[bgindex];
	boolean gameover;
	boolean pause;
	boolean crazy;
	int bossalive = 0;
	int score = 0;
	int power = 1;
	int y1 = 0;
	int y2 = -767;
	int x1 = 3;
	int x2 = 3;
	Hero hero = new Hero();
	List<Enemy> enemies = new ArrayList<Enemy>();
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Bullet> bossbullets = new ArrayList<Bullet>();
	List<Boss> boss = new ArrayList<Boss>();

	public void action() {

		new Thread() {

			public void run() {

				while (true) {

					if (!gameover) {
						if (!pause) {

							// System.out.println(crazy);
							EnemyGo();
							HeroShoot();
							HitEnemy();
							HitHero();
							backgroundMove();
							BossGo();
							BossShoot();
							HitBoss();
						}

					}

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

	protected void HitHero() {
		// 英雄撞桶子
		for (int i = 0; i < bossbullets.size(); i++) {
			Bullet bossbullet = bossbullets.get(i);
			if (bossbullet.crash(hero)) {

				if (bossbullet.type == 3) {
					if (!crazy) {
						hero.Life--;
						power = 1;
						score -= 3000;
					}
				}
				bossbullets.remove(bossbullet);
				if (hero.Life <= 0) {
					gameover = true;
				}
			}
		}
		// 英雄撞Boss
		for (int i = 0; i < boss.size(); i++) {
			Boss bosss = boss.get(i);
			if (bosss.crash(hero)) {
				if (!crazy) {
					hero.Life--;
					power = 1;
					score -= 5000;
				}

				if (hero.Life <= 0) {
					gameover = true;
				}
			}
		}

		// 英雄撞敵機
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			if (enemy.crash(hero)) {
				// 吃星星加火力
				if (enemy.type == 6) {
					score += 2000;
					power++;
					if (power > 3) {
						hero.Life++;
						if (hero.Life >= 3) {
							hero.Life = 3;
						}
						power = 3;
					}
				} else {
					if (!crazy) {
						hero.Life--;
						power = 1;
					}
					score += 1000;
				}
				enemies.remove(enemy);

				if (hero.Life <= 0) {
					gameover = true;
				}
			}
		}
	}

	protected void HitBoss() {
		// 射到Boss
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			for (int j = 0; j < boss.size(); j++) {
				Boss bosss = boss.get(j);
				if (bosss.shootby(bullet) || bosss.crash(hero)) {
					bosss.Life--;
					if (bosss.Life <= 0) {
						bosss.Life = 0;
						boss.remove(bosss);
						score += 10000;
						bossalive -= 1;
					}
					// System.out.println(bosss.Life);

					bullets.remove(bullet);
				}
			}

		}
	}

	protected void HitEnemy() {

		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			for (int j = 0; j < enemies.size(); j++) {
				Enemy enemy = enemies.get(j);
				if (enemy.shootby(bullet)) {
					if (enemy.type != 3 && enemy.type != 6) {
						enemy.Life--;
						if (enemy.Life <= 0) {
							// 打掉某個敵人加火力
//							if (enemy.type == 6) {
//								power++;
//								if (power > 3) {
//									hero.hLife++;
//									if (hero.hLife >= 3) {
//										hero.hLife = 3;
//									}
//									power = 3;
//								}
//							}
							enemies.remove(enemy);
							score += 1000;
						}
						bullets.remove(bullet);
					}

				}
			}
		}
	}

	int bosindex = 0;

	protected void BossShoot() {
		// 造子彈
		bosindex++;
		if (bosindex >= 100) {

			for (int i = 0; i < boss.size(); i++) {
				Boss bosss = boss.get(i);
				Bullet b2 = new Bullet(bosss.x, bosss.y - 10, bosss.w, 4, 3);
				bossbullets.add(b2);

			}
			bosindex = 0;

		}
		// 子彈移動
		for (int i = 0; i < bossbullets.size(); i++) {
			Bullet bullet = bossbullets.get(i);
			bullet.move();
		}
	}

	int bulindex = 0;

	protected void HeroShoot() {
		// 造子彈
		bulindex++;
		if (bulindex >= 20) {

			if (power == 1) {
				Bullet b2 = new Bullet(hero.x, hero.y - 10, hero.w, 1, 2);
				bullets.add(b2);
			} else if (power == 2) {
				Bullet b1 = new Bullet(hero.x - 20, hero.y, hero.w, 1, 2);
				bullets.add(b1);
				Bullet b3 = new Bullet(hero.x + 20, hero.y, hero.w, 1, 2);
				bullets.add(b3);
			} else if (power == 3) {
				Bullet b1 = new Bullet(hero.x - 20, hero.y, hero.w, 0, 1);
				bullets.add(b1);
				Bullet b2 = new Bullet(hero.x, hero.y - 10, hero.w, 1, 2);
				bullets.add(b2);
				Bullet b3 = new Bullet(hero.x + 20, hero.y, hero.w, 2, 1);
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

	int movindex = 0;

	protected void BossGo() {
		// 造敵人
		movindex++;
		if (movindex >= 50) {
			x1 = rd.nextInt(4) + 2;
			x2 = rd.nextInt(4) + 3;
			movindex = 0;
		}
		if (score % 50000 == 0 && score > 0 && bossalive == 0) {
			Boss b = new Boss();
			boss.add(b);
			bossalive += 1;

		}

		// 敵人移動
		for (int i = 0; i < boss.size(); i++) {
			Boss bosss = boss.get(i);
			bosss.move(x1, x2);
		}
	}

	int index = 0;

	protected void EnemyGo() {
		// 造敵人
		index++;
		if (index >= 40) {
			Enemy e = new Enemy();
			enemies.add(e);
			index = 0;
		}
		// 敵人移動
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();
		}
	}

	private void backgroundMove() {
		y1++;
		y2++;
		if (y1 == 0) {
			y2 = -767;
		}
		if (y2 == 0) {
			y1 = -767;
		}
	}

	public GamePanel(GameFrame frame) {
		setBackground(Color.white);

		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				if (!gameover && !pause) {
					hero.moveToMouse(mouseX, mouseY);
				}
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				pause = false;
			}

			public void mouseExited(MouseEvent e) {
				pause = true;
			}

//			public void mousePressed(MouseEvent e) {
//				crazy = true;
//			}
//			public void mouseReleased(MouseEvent e) {
//				crazy = false;
//			}
			public void mouseClicked(MouseEvent e) {
				if (gameover) {
					hero = new Hero();

					gameover = false;
					score = 0;
					power = 1;
					boss.clear();
					enemies.clear();
					bullets.clear();
					bossbullets.clear();
					Random rd = new Random();
					int bgindex = rd.nextInt(5);
					Background = bgs[bgindex];
					repaint();
				}
			}

		};
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		KeyAdapter key = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_SPACE) {
					crazy = true;
				}

//				System.out.println("Ahoj!");
//				if (keyCode == KeyEvent.VK_W) {
//					hero.moveUp();
//				} else if (keyCode == KeyEvent.VK_S) {
//					hero.moveDown();
//				} else if (keyCode == KeyEvent.VK_A) {
//					hero.moveLeft();
//				} else if (keyCode == KeyEvent.VK_D) {
//					hero.moveRight();
//				}
				repaint();
			}

			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_SPACE) {
					crazy = false;
				}
			}
		};
		frame.addKeyListener(key);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawImage(Background, 0, y1, 512, 768, null);
		g.drawImage(Background, 0, y2, 512, 768, null);

		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score : " + score, 10, 60);
		g.drawString("Life : ", 10, 30);
		for (int i = 0; i < hero.Life; i++) {
			g.drawImage(hero.img, 70 + 35 * i, 10, 30, 30, null);
		}
		g.drawImage(hero.img, hero.x, hero.y, hero.w, hero.h, null);

		for (int i = 0; i < boss.size(); i++) {
			Boss bosss = boss.get(i);
			g.drawImage(bosss.img, bosss.x, bosss.y, bosss.w, bosss.h, null);
			for (int j = 0; j < bosss.Life / 10; j++) {
				g.drawImage(bosss.img, 450, 10 + 45 * j, 40, 40, null);
			}
		}

		// 畫敵人
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			g.drawImage(enemy.img, enemy.x, enemy.y, enemy.w, enemy.h, null);
		}
		// 畫英雄子彈
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			g.drawImage(bullet.img, bullet.x, bullet.y, bullet.w, bullet.h, null);
		}
		// 畫boss子彈
		for (int i = 0; i < bossbullets.size(); i++) {
			Bullet bossbullet = bossbullets.get(i);
			g.drawImage(bossbullet.img, bossbullet.x, bossbullet.y, bossbullet.w, bossbullet.h, null);
		}

		if (gameover) {
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("GAMEOVER!!!", 80, 280);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Final Score : " + score, 100, 345);
			if (score >= 200000) {
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("Oh My God!", 100, 430);
			} else if (score >= 100000) {
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("Good Job!", 100, 430);
			} else {
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("You Suck!", 100, 430);
			}
			g.setFont(new Font("Arial", Font.BOLD, 24));
			g.drawString("Click the Screen to RESTART the game", 25, 500);
		} else if (pause) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 80));
			g.drawString("Pause", 120, 390);
		}

	}
}
