import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client_First extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private Client clientTA = null; 
	private BufferedReader in  = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	ClientPanel clientPanel = null;
	
	public Client_First() {
		setTitle("ä�� Ŭ���̾�Ʈ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField(); 
		inputTF.addActionListener(this); 
		
		clientTA = new Client();
		clientTA.setEditable(false); 
		
		add(new JScrollPane(clientTA), BorderLayout.CENTER);
		add(inputTF, BorderLayout.SOUTH);
		
		clientPanel = new ClientPanel(this);
		add(clientPanel, BorderLayout.EAST);
		
		setSize(500, 300);
		setVisible(true);
	}
	
	public void addMessages(String msg) {
		this.clientTA.append(msg);
		
	}
	
	public void connect() { // ����
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
	
	public void disconnect() { // ���� ���� : �����常 ����, TA�� �����Ű�� �ȵ�
		clientTA.stop();
	}
	
	public void clearMessages() { 
		this.clientTA.setText("");
	}
	
	public void sendMessages(String msg) { // �ؽ�Ʈ�ʵ忡�� �̸� ��������, �Լ� �����ε� ��Ŵ
		// String msg = inputTF.getText(); -> ���ڸ� ���޹ޱ� ������ �ʿ����
		if ( socket.isConnected() == false || msg.length() <= 0) {
			clientTA.setText("������ �� �Ǿ��ų� �޼����� �����ϴ�.");
			return;
		}
		
		String name = clientPanel.getName();
		
		try {
			out.write(name.concat("/"+msg) + "\n"); 
			out.flush();
			
			String message = String.format("%60s", msg);
			//clientTA.append(name.concat(" / " + msg) + "\n"); 
			
			clientTA.append("\n" + message);
			int pos = clientTA.getText().length(); 
			clientTA.setCaretPosition(pos); 
			
			inputTF.setText("");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void sendMessages() {
		String msg = inputTF.getText();
		
		if (socket.isConnected() == false || msg.length() <= 0) {
			clientTA.setText("������ �ȵǾ� �ְų� �޼����� �����ϴ�.");
			return;
		}
		
		String name = clientPanel.getName(); // �������̶��
		
		try {
			out.write(name.concat("/" + msg) + "\n"); // �������� ������ �̸��� �޽����� ����
			out.flush();
			
			String message = String.format("%60s", msg); // ���� ����
			
			clientTA.append("\n"+ message);
			int pos = clientTA.getText().length();
			clientTA.setCaretPosition(pos);
			
			inputTF.setText("");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void setupNetwork() throws IOException {
		socket = new Socket("localhost", 9999); 
		
		clientTA.append("���� ���� �Ϸ�");
		int pos = clientTA.getText().length(); 
		clientTA.setCaretPosition(pos); 
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
	}
	
	private class Client extends JTextArea implements Runnable { // ������ ����� �� ���, ������ ����� �� runnable ���ε� �����ؾ���
		private final AtomicBoolean running = new AtomicBoolean(false); // ���� ����Ǿ �ٸ� ������ ���� ������� �ʵ��� AtomicBoolean ���
		
		public void stop() { // pause Thread
			running.set(false);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String msg = null; 
			running.set(true);
			
			while ( running.get() ) { // running ������ true, false ��
				try {
					msg = in.readLine();  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Network Error");
					System.exit(0);
				}
				this.append("\n Server : " + msg); 
				int pos = this.getText().length(); 
				this.setCaretPosition(pos); 
				
			}
			if ( socket != null ) { // �������� ������ ��
				sendMessages("bye");
				try {
					socket.close();
					in.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					this.append("���� ���� \n");
					int pos = this.getText().length(); // ������ �ߺ��Ǳ� ������ ������ �Լ��� �����ϸ� ���ϴ�
					this.setCaretPosition(pos); 
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client_First();
	}
}
