import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ch10_4 extends JFrame {
	private JLabel la = new JLabel("HwaSil OH ");

	public Ch10_4() {
		setTitle("LeftŰ�� ���ڿ� �̵�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		la.addKeyListener(new MyKeyListener()); // key �̺�Ʈ ������ �ޱ�
		la.setSize(100, 20);
		la.setLocation(50, 50);

		c.add(la);

		setSize(300, 300);
		setVisible(true);

		la.setFocusable(true); // ������Ʈ focus ���� 
		la.requestFocus();
	}

	class MyKeyListener extends KeyAdapter { // Inner
		
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode(); // Ű�� �ƽ�Ű�ڵ� ��

			if (keycode == KeyEvent.VK_LEFT) {
				String name = la.getText();
				String newName = name.substring(1).concat(name.substring(0, 1)); // 1���� ������ + �տ� ����
				la.setText(newName);
				System.out.println(newName); // �ܼ� â�� ���
			}
		}
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ch10_4();
	}

}
