import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuActionEventEx extends JFrame {
	private JLabel imgLabel = new JLabel();
	JMenuBar mb = new JMenuBar();
	JMenuItem [] menuItem = new JMenuItem [4];
	String[] itemTitle = {"Load", "Hide", "ReShow", "Exit"};
	JMenu screenMenu = new JMenu("Screen");
	
	public MenuActionEventEx() {
		setTitle("Menu에 Action 리스너 만들기 예제");
		createMenu();
		getContentPane().add(imgLabel, BorderLayout.CENTER);
		setSize(650, 1000);
		setVisible(true);
	}
	
	private void createMenu() {
		// inner class에 사용하기 위해 local변수 이동
		
		MenuActionListener listener = new MenuActionListener();
		for (int i=0; i<menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		menuItem[1].setEnabled(false); // Hide ReShow kill
		menuItem[2].setEnabled(false);
		mb.add(screenMenu);
		setJMenuBar(mb);
	}
	
	class MenuActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			switch (cmd) {
			case "Load" :
				if (imgLabel.getIcon() != null) 
					return;
				imgLabel.setIcon(new ImageIcon("img.jpg"));
				
				menuItem[0].setEnabled(false); // Load kill
				menuItem[1].setEnabled(true);  // Hide ReShow live
				menuItem[2].setEnabled(true);
				break;
			case "Hide" :
				imgLabel.setVisible(false);
				break;
			case "ReShow" :
				imgLabel.setVisible(true);
				break;
			case "Exit" :
				System.exit(0);
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MenuActionEventEx();
	}

}
