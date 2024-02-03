import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	//Ch10_ex4 익명클래스로 구현

public class AnonyMouseListener extends JFrame {
	
	public AnonyMouseListener() {
		setTitle("Mouse Event Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c= getContentPane();
		
		JLabel la = new JLabel("Hello");
		c.setLayout(null);
		la.setSize(50, 20);
		la.setLocation(30, 30);
		c.add(la);
		
		la.addMouseListener(new MyMouseListener() {
			public void mousePressed(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				int x = e.getX();
				int y = e.getY();
				la.setLocation(x, y); 
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		setSize(250, 250);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AnonyMouseListener();
	}

}
