import javax.swing.*;
import java.awt.*;

public class TabbedPaneEx extends JFrame {
	public TabbedPaneEx() {
		setTitle("TebbenPane Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		JTabbedPane pane = createTabbedPane();
		c.add(pane, BorderLayout.CENTER);
		setSize(250, 250);
		setVisible(true);
		
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("tab1", new JLabel(new ImageIcon("1.jpg")));
		pane.addTab("tab2", new JLabel(new ImageIcon("Peach.jpg")));
		pane.addTab("tab3", new MyPanel());
		return pane;
	}
	class MyPanel extends Panel {
		public MyPanel() {
			this.setBackground(Color.YELLOW);
		}
		public void paintComponent(Graphics g) { //tab 3 
			super.paintComponents(g); //// s가 있고 없고의 차이는 뭘까...?
			g.setColor(Color.RED);
			g.fillRect(10, 10, 50, 50);
			g.setColor(Color.BLUE);
			g.fillOval(10, 70, 50, 50);
			g.setColor(Color.BLACK);
			g.drawString("tab3에 들어가는 JPanel", 30, 50);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TabbedPaneEx();
	}

}
