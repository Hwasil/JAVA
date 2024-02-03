package Client_Manage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class UsingPanel extends JFrame {
	private ClientFrame frame = null;
	
	public UsingPanel(ClientFrame frame) {
		
		setLayout(new GridLayout(2, 2, 5, 5));
		
		add(new JLabel("사용 시간"));
		add(new JLabel("      "));
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.logouted();
			}
			
		});
		add(logoutBtn);
		
		setSize(100, 60); 
		//setVisible(true);
	}
}
