import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ServerChat extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private JTextArea serverTA = null; 
	MultiServer server = null; // ��¥ ����
	
	public ServerChat() {
		setTitle("ä�� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField(); 
		inputTF.addActionListener(this); 
		
		serverTA = new JTextArea();
		serverTA.setEditable(false);
		
		add(new JScrollPane(serverTA), BorderLayout.CENTER);
		add(inputTF, BorderLayout.SOUTH);
		
		setSize(500, 300);
		setVisible(true);
		
		startServer();
	}
	
	public void startServer() { // server play
		server = new MultiServer(this);
		server.start();
	}
	
	public void addMessages(String msg) {
		if ( msg != null ) {
			String tokens[] = msg.split("/");
			String name = tokens[0];
			String message = tokens[1];
			
			if ( msg.equalsIgnoreCase("bye") ) {
				serverTA.append("\n Ŭ���̾�Ʈ ����޼��� ����");
			}
			serverTA.append("\n" + name + "���κ��� �޼��� : " + message);
			int pos = serverTA.getText().length(); 
			serverTA.setCaretPosition(pos); 
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ( e.getSource() == inputTF) {
			server.sendMessages("����/" + inputTF.getText());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerChat();
	}
}

	

	
