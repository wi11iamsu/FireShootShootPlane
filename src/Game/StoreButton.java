package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StoreButton implements ActionListener {
	public static int choice=0;
	JButton Plane1 = new JButton();
	JButton Plane2 = new JButton();
	JButton Plane3 = new JButton();

	public StoreButton(StorePanel name) {
		name.setLayout(null);
		name.add(Plane1);
		name.add(Plane2);
		name.add(Plane3);
		Plane1.setBounds(156, 50, 200, 200);
		Plane2.setBounds(156, 270, 200, 200);
		Plane3.setBounds(156, 490, 200, 200);
		Plane1.setOpaque(false);				//«öÁä³z©ú
		Plane1.setContentAreaFilled(false);
		Plane2.setOpaque(false);
		Plane2.setContentAreaFilled(false);
		Plane3.setOpaque(false);
		Plane3.setContentAreaFilled(false);
		Plane1.addActionListener(this);
		Plane2.addActionListener(this);
		Plane3.addActionListener(this);

	}

	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Plane1) {
            choice=1;
        }
        else if(e.getSource() == Plane2){
            choice=2;
        }
        else {
            choice=3;
        }
        Main.frame2.dispose();
        GamePanel panel = new GamePanel(Main.frame3);
        panel.action();
        Main.frame3.add(panel);
        Main.frame3.setVisible(true);

    }
}
