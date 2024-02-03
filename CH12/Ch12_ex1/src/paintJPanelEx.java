import javax.swing.*;
import java.awt.*;

public class paintJPanelEx extends JFrame {
	private MyPanel panel =  new MyPanel();
	
	public paintJPanelEx() {
		setTitle("JPanel's paintComponent() Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(250, 250);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GREEN);
			g.drawRect(20, 20, 60, 60);
			g.drawRect(40, 40, 60, 60);
			g.setColor(Color.yellow);
			g.drawRect(100, 100, 60, 60);
			//Ch12_ex3
			Font f = new Font("Arial", Font.ITALIC, 30);
			g.setFont(f);
			//g.setColor(Color.black);
			//Ch12_ex2
			g.drawString("Yellow Runjunny~~", 40, 40);
			g.drawString("Beatbox", 80, 80);
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new paintJPanelEx();
	}

}
