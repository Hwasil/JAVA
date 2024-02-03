import javax.swing.*;
import java.awt.Color;
import java.awt.Container;

	//p532 6번

public class RandomLabel extends JFrame {
	public RandomLabel() {
		setTitle("RandomLabel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null); // 배치관리자 제거
		
		for (int i = 0; i<20; i++) { // 20개 생성
			JLabel la = new JLabel();
			la.setBackground(Color.green); //BLUE
			
			int x = (int)(Math.random()*200) + 50; // 50~250
			int y = (int)(Math.random()*200) + 50;
			la.setLocation(x, y); // label을 (x,y)에 배치
			la.setSize(10, 10); 
			la.setOpaque(true); // label에 배경색이 보이게 함
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
