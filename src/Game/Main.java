package Game;

public class Main {
	static GameFrame frame1 = new GameFrame();
	static GameFrame frame2 = new GameFrame();
	static GameFrame frame3 = new GameFrame();

	public static void main(String[] args) {

		StartPanel open = new StartPanel();
		StartButton start = new StartButton(open);
		open.action();
		frame1.add(open);
		frame1.setVisible(true);
	}
}
