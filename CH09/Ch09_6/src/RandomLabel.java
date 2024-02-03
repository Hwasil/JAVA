import javax.swing.*;
import java.awt.Color;
import java.awt.Container;

	//p532 6��

public class RandomLabel extends JFrame {
	public RandomLabel() {
		setTitle("RandomLabel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null); // ��ġ������ ����
		
		for (int i = 0; i<20; i++) { // 20�� ����
			JLabel la = new JLabel();
			la.setBackground(Color.green); //BLUE
			
			int x = (int)(Math.random()*200) + 50; // 50~250
			int y = (int)(Math.random()*200) + 50;
			la.setLocation(x, y); // label�� (x,y)�� ��ġ
			la.setSize(10, 10); 
			la.setOpaque(true); // label�� ������ ���̰� ��
			c.add(la);
		}
		
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RandomLabel();
	}

}
