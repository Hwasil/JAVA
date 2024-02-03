import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.io.IOException;
import java.net.Socket;

public class ChatClient extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private Client clientTA = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	
	public ChatClient() {
		setTitle("Client Chating");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField();
		inputTF.addActionListener(this);
		
		clientTA = new Client();
		clientTA.setEditable(false);
		
		setSize(500, 300);
		setVisible(true);
		
		try {
			setupNetwork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Network Error");
			System.exit(0);
		}
		
		Thread th = new Thread(serverTA);
		th.start();	
	}
	
	public class Client extends JTextField implements Runnable {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatClient();
	}

}
