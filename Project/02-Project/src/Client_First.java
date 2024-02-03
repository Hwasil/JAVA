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
		setTitle("채팅 클라이언트");
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
	
	public void connect() { // 접속
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
	
	public void disconnect() { // 접속 종료 : 스레드만 종료, TA는 종료시키면 안됨
		clientTA.stop();
	}
	
	public void clearMessages() { 
		this.clientTA.setText("");
	}
	
	public void sendMessages(String msg) { // 텍스트필드에서 이름 가져오기, 함수 오버로딩 시킴
		// String msg = inputTF.getText(); -> 문자를 전달받기 때문에 필요없음
		if ( socket.isConnected() == false || msg.length() <= 0) {
			clientTA.setText("연결이 안 되었거나 메세지가 없습니다.");
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
			clientTA.setText("연결이 안되어 있거나 메세지가 없습니다.");
			return;
		}
		
		String name = clientPanel.getName(); // 정삭적이라면
		
		try {
			out.write(name.concat("/" + msg) + "\n"); // 서버에게 보내는 이름과 메시지를 구분
			out.flush();
			
			String message = String.format("%60s", msg); // 우측 정렬
			
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
		
		clientTA.append("서버 연결 완료");
		int pos = clientTA.getText().length(); 
		clientTA.setCaretPosition(pos); 
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
	}
	
	private class Client extends JTextArea implements Runnable { // 간당한 기능일 땐 사용, 복잡할 기능일 땐 runnable 별로도 제작해야함
		private final AtomicBoolean running = new AtomicBoolean(false); // 값이 변경되어도 다른 스레드 값이 변경되지 않도록 AtomicBoolean 사용
		
		public void stop() { // pause Thread
			running.set(false);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String msg = null; 
			running.set(true);
			
			while ( running.get() ) { // running 변수의 true, false 비교
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
			if ( socket != null ) { // 접속종료 눌렀을 때
				sendMessages("bye");
				try {
					socket.close();
					in.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					this.append("접속 종료 \n");
					int pos = this.getText().length(); // 여러번 중복되기 때문에 별도의 함수로 제작하면 편하다
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
