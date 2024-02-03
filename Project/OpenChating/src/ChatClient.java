import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private Client clientTA = null; 
	private BufferedReader in  = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	
	public ChatClient() {
		setTitle("Chating Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField(); 
		inputTF.addActionListener(this); 
		
		clientTA = new Client();
		clientTA.setEditable(false); 
		
		add(new JScrollPane(clientTA), BorderLayout.CENTER);
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
		
		Thread th = new Thread(clientTA); 
		th.start();
	}
	
	private void setupNetwork() throws IOException {
		socket = new Socket("localhost", 9999); // 클라이언트 연결. (ip주소, 포트 번호), 서버로 연결을 시도
		
		clientTA.append("Server Connection established");
		int pos = clientTA.getText().length(); 
		clientTA.setCaretPosition(pos); 
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
	}
	
	private class Client extends JTextArea implements Runnable { 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String msg = null; 
			while ( true ) {
				try {
					msg = in.readLine();  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Network Error");
					System.exit(0);
				}
				this.append("\nServer >> " + msg); 
				int pos = this.getText().length(); 
				this.setCaretPosition(pos); 
				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ( e.getSource() == inputTF) {
			String msg = inputTF.getText();
			try {
				out.write(msg+"\n"); 
				out.flush();
				
				// 위치 조정
				clientTA.append("\nClient >> " + msg); 
				int pos = clientTA.getText().length(); 
				clientTA.setCaretPosition(pos); 
				
				inputTF.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatClient(); 
	}
}
