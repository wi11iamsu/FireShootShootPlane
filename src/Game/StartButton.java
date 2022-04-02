package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class StartButton implements ActionListener {

	JButton START = new JButton("開始");
	JButton EXIT = new JButton("結束");

	public StartButton(StartPanel open) {
		open.setLayout(null);
		EXIT.setBounds(156, 600, 200, 50);
		START.setBounds(156, 500, 200, 50);
		open.add(EXIT);
		open.add(START);
		START.addActionListener(this);
		EXIT.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == START) {
			Main.frame1.dispose();
			StorePanel choice = new StorePanel();
			StoreButton store = new StoreButton(choice);
			Main.frame2.add(choice);
			Main.frame2.setVisible(true);
		} else {
			closeFrame();
		}
	}

	private void closeFrame() {
		int result = JOptionPane.showConfirmDialog(null, "是否要退出？", "退出確認", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
