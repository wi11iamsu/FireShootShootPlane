package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class StartButton implements ActionListener {

	JButton START = new JButton("�}�l");
	JButton EXIT = new JButton("����");

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
		int result = JOptionPane.showConfirmDialog(null, "�O�_�n�h�X�H", "�h�X�T�{", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
