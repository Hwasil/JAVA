import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.*;

public class ChatServer extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private Server serverTA = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	
	public ChatServer() {
		setTitle("Server Chating");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField();
		inputTF.addActionListener(this);
		
		serverTA = new Server();
		serverTA.setEditable(false);
		
		add(new JScrollPane(serverTA), BorderLayout.CENTER);
		add(inputTF, BorderLayout.SOUTH);
		
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
	
	private void setupNetwork() throws IOException {
		serverSocket = new ServerSocket(999);
		socket = serverSocket.accept();
		
		serverTA.append("Client connection established");
		int pos = serverTA.getText.length();
		serverTA.setCaretPosition(pos);
		
		in = new BufferedReader(new InputStremReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	public class Server extends JTextField implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String msg = null;
			while ( true ) {
				try {
					msg = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.append("\n Client : "+msg);
				int pos = this.getText().length();
				this.setCaretPosition(pos);
			}
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == inputTF) {
			String msg = inputTF.getText();
			try {
				out.write(msg+"\n");
				out.flush();
				
				serverTA.append("\nServer : " + msg);
				int pos = serverTA.getText().length();
				serverTA.setCaretPosition(pos);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer();
	}

}
