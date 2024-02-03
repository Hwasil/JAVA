import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	// Ch10_ex4 ����Ŭ������ ����

public class IndepMouseListenerEx extends JFrame {
	
	
	public IndepMouseListenerEx() {
		setTitle("Mouse Event Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c= getContentPane();
		
		JLabel la = new JLabel("Hello");
		la.addMouseListener(new MyMouseListener());
		
		c.setLayout(null);
		la.setSize(50, 20);
		la.setLocation(30, 30);
		c.add(la);
		
		setSize(250, 250);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new IndepMouseListenerEx();
	}
}
class MyMouseListener implements MouseListener {
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
}