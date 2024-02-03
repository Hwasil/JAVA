package Client_Manage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class ClientFrame extends JFrame {
	private LoginPanel loginPanel;
	private UsingPanel usingPanel;
	
	public ClientFrame() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new LoginPanel(this);
		usingPanel = new UsingPanel(this);
		
		add(loginPanel, BorderLayout.CENTER);
		add(new JLabel("    "), BorderLayout.NORTH);
		add(new JLabel("    "), BorderLayout.SOUTH);
		add(new JLabel("    "), BorderLayout.WEST);
		add(new JLabel("    "), BorderLayout.EAST);
		
		setSize(250, 200); 
		setVisible(true);
	}
	
	public void logged() {
		remove(loginPanel);
		revalidate();
		repaint();
		add(usingPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void logouted() {
		remove(usingPanel);
		revalidate();
		repaint();
		add(loginPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClientFrame();
	}

}
