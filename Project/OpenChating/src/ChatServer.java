import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatServer extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private Server serverTA = null; // ������ ���� ���۷���
	private BufferedReader in  = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	
	public ChatServer() {
		setTitle("Chating Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField(); // ������ �ʿ� , �ؿ� ���� �� �ڵ尡 ����, ���� ����
		inputTF.addActionListener(this); // ���빰�� ���� �� ������ ��ü�� ó���ϴ� ���� ChatServer(this)�� �ϰڴ�.
		
		serverTA = new Server();
		serverTA.setEditable(false); // ������ �ʿ䰡 ��� �ۼ� 
		
		add(new JScrollPane(serverTA), BorderLayout.CENTER);
		add(inputTF, BorderLayout.SOUTH);
		
		setSize(500, 300);
		setVisible(true);
		
		// TA�� ��������� �� �� ��Ʈ��ũ ���� �ؾ���
		try {
			setupNetwork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Network Error");
			System.exit(0); // �ý��� ���� ����
		}
		
		Thread th = new Thread(serverTA);
		th.start();
	}
	
	private void setupNetwork() throws IOException {
		serverSocket = new ServerSocket(9999); // ��Ʈ ��ȣ, ���� ����, ��ü ����
		socket = serverSocket.accept(); // ���ο� ����(�н�) = Ŭ���̾�Ʈ�� ������ waiting
		
		serverTA.append("Client Connection established");
		int pos = serverTA.getText().length(); // ���� �ľ� ���� : �� ���� �ٿ� ���ο� ������ ���� 
		serverTA.setCaretPosition(pos); // �Է� ����Ʈ �̵� : �� ������
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ���� �б� ���� ��ü ����
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // ��Ʈ�� ���� : Ŭ���̾�Ʈ�� ���� ������ ������ �̷������
		// ���ڸ� �ٽ� ���۸� ����� �߰� <- ���� ���·� ��ȯ <- ������ ���� ��ǲ��Ʈ���� (���̳ʸ� ����)
		// ��Ʈ��ũ �ӵ��� �� ���� ���� ���� ����� �߰��ؾ���
	}
	
	private class Server extends JTextArea implements Runnable { // �Ϲ� UI �̸鼭 �������� (�ڹٴ� ���߻�� �Ұ�)
		// �����尡 �����͸� ��ٸ��� ���� JFrame ä�ü����� ��ٸ��� ���� �ƴ�
		// ������ ����������, ���� ���� ����Area. ������ �����尡 �����͸� ��ٸ��� �ƹ��͵� ����. ����ڰ� �����͸� �� �༭, �߰��� ����. -> 2���� ������, JVM�� ���� �����ٸ�, CPU�Ҵ�, ���������� ���� ����
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String msg = null; // Ŭ���̾�Ʈ���� ���� �����͸� �����ϴ� �ӽ� ��Ʈ��
			while ( true ) {
				try {
					msg = in.readLine();  // �� ������ ������ ������� ���, ������ ����(enter)�� ���� �� ���
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Network Error");
					System.exit(0);
				}
				this.append("\nClient >> " + msg); // �������̸鼭 TA�̱� ������ append ��밡��
				int pos = this.getText().length(); // ���� �ľ� ���� : �� ���� �ٿ� ���ο� ������ ���� 
				this.setCaretPosition(pos); // �Է� ����Ʈ �̵� : �� ������
				
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // TextFiled ������
		// TODO Auto-generated method stub
		if ( e.getSource() == inputTF) {
			String msg = inputTF.getText();
			try {
				out.write(msg+ "\n"); // �������� ���� �� �� �ٷ� �б� ������ ������ �ʿ�
				out.flush();
				
				serverTA.append("\nServer >> " + msg);
				int pos = serverTA.getText().length(); // ���� �ľ� ���� : �� ���� �ٿ� ���ο� ������ ���� 
				serverTA.setCaretPosition(pos); // �Է� ����Ʈ �̵� : �� ������
				
				inputTF.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer(); // ��ü ���� ���۷����� �ִ� ��
	}
}
