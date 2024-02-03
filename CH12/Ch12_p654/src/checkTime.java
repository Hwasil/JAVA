import javax.swing.*;
import java.awt.*;

public class checkTime extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public checkTime() {
		setTitle("fillXXXX Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(300, 300);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// (1) Line
			g.setColor(Color.yellow);
			g.drawLine(10,10, 30, 30);
			// (2) Oval
			g.drawOval(10, 15, 20, 10); // width, high
			// (3) triangle
			g.setColor(Color.red);
			int []x = {10, 5, 15};
			int []y = {10, 15, 20};
			g.fillPolygon(x, y, 3);
			// (4) 
			g.setColor(Color.yellow);
			g.fillArc(100, 100, 80, 80, -30, 120);
			g.setColor(Color.red);
			g.fillArc(100, 100, 80, 80, 90, 210);
			g.setColor(Color.blue);
			g.fillArc(100, 100, 80, 80, -30, -120);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new checkTime();
	}

}
