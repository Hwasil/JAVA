import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	// Ch11_10 과제 (~10.26)

public class Ch11_10 extends JFrame {
	private static JLabel []num = new JLabel[10]; // 0~9까지 생성할 10개의 라벨
	private static int nowindex;                // 순서대로 지워지기 위함
	
	static void setLabel(Container c) {
		for(int i=0; i<num.length; i++) {
			num[i]=new JLabel(Integer.toString(i));
			num[i].setSize(30,30);
			num[i].setFont(new Font("Arial",Font.BOLD,30)); //PLAIN
			num[i].setForeground(Color.PINK);
			
			
			int x=(int)(Math.random()*300+20);
			int y=(int)(Math.random()*300+20);
			num[i].setLocation(x,y);
			c.add(num[i]);
		}
	}
	
	static void reSetLabel(Container c) {
		nowindex=0;
		
		for(int i=0;i<num.length;i++) {
			num[i].setSize(30,30);
			int x=(int)(Math.random()*200)+20;
			int y=(int)(Math.random()*200)+20;
			num[i].setLocation(x,y);
		}
	}
	
	public Ch11_10() {
		setTitle("Ch11_10 실습문제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null); 
		
		setLabel(c);
		
		for (int i=0; i<10; i++) {
			num[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					JLabel la = (JLabel)e.getSource();
					
					if(nowindex==Integer.parseInt(la.getText())) {
						la.setSize(0,0);
						nowindex++;
						if(nowindex==10) {
							reSetLabel(c);
						}	
					}
				}
			});
		}
		
		setSize(400, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ch11_10();
	}

}
