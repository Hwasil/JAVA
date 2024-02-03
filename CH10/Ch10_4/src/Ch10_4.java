import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ch10_4 extends JFrame {
	private JLabel la = new JLabel("HwaSil OH ");

	public Ch10_4() {
		setTitle("Left키로 문자열 이동");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		la.addKeyListener(new MyKeyListener()); // key 이벤트 리스너 달기
		la.setSize(100, 20);
		la.setLocation(50, 50);

		c.add(la);

		setSize(300, 300);
		setVisible(true);

		la.setFocusable(true); // 컴포넌트 focus 강제 
		la.requestFocus();
	}

	class MyKeyListener extends KeyAdapter { // Inner
		
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode(); // 키의 아스키코드 값

			if (keycode == KeyEvent.VK_LEFT) {
				String name = la.getText();
				String newName = name.substring(1).concat(name.substring(0, 1)); // 1부터 끝까지 + 앞에 글자
				la.setText(newName);
				System.out.println(newName); // 콘솔 창에 출력
			}
		}
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ch10_4();
	}

}
