import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatServer extends JFrame implements ActionListener {
	private JTextField inputTF = null;
	private Server serverTA = null; // 서버에 대한 레퍼런스
	private BufferedReader in  = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	
	public ChatServer() {
		setTitle("Chating Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		inputTF = new JTextField(); // 리스너 필요 , 밑에 제작 시 코드가 복잡, 따로 설계
		inputTF.addActionListener(this); // 내용물이 들어올 때 리스너 객체를 처리하는 것을 ChatServer(this)로 하겠다.
		
		serverTA = new Server();
		serverTA.setEditable(false); // 수정할 필요가 없어서 작성 
		
		add(new JScrollPane(serverTA), BorderLayout.CENTER);
		add(inputTF, BorderLayout.SOUTH);
		
		setSize(500, 300);
		setVisible(true);
		
		// TA가 만들어지고 난 뒤 네트워크 연결 해야함
		try {
			setupNetwork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Network Error");
			System.exit(0); // 시스템 정상 종료
		}
		
		Thread th = new Thread(serverTA);
		th.start();
	}
	
	private void setupNetwork() throws IOException {
		serverSocket = new ServerSocket(9999); // 포트 번호, 서버 소켓, 객체 생성
		socket = serverSocket.accept(); // 새로운 소켓(분신) = 클라이언트가 들어오길 waiting
		
		serverTA.append("Client Connection established");
		int pos = serverTA.getText().length(); // 길이 파악 이유 : 그 다음 줄에 새로운 문장을 삽입 
		serverTA.setCaretPosition(pos); // 입력 포인트 이동 : 맨 끝으로
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 쓰고 읽기 위한 객체 생성
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 스트림 생성 : 클라이언트와 서버 사이의 연결이 이루어졌다
		// 문자를 다시 버퍼링 기능을 추가 <- 문자 형태로 변환 <- 소켓이 가진 인풋스트림을 (바이너리 형태)
		// 네트워크 속도를 알 수가 없어 버퍼 기능을 추가해야함
	}
	
	private class Server extends JTextArea implements Runnable { // 일반 UI 이면서 쓰레드임 (자바는 다중상속 불가)
		// 스레드가 데어터를 기다리지 메인 JFrame 채팅서버가 기다리는 것이 아님
		// 서버는 메인프리임, 따로 제작 서버Area. 별도의 쓰레드가 데이터를 기다리고 아무것도 안함. 사용자가 데이터를 안 줘서, 중간에 멈춤. -> 2개의 쓰레드, JVM에 의해 스케줄링, CPU할당, 독자적으로 실행 가능
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String msg = null; // 클라이언트에서 받은 데이터를 저장하는 임시 스트링
			while ( true ) {
				try {
					msg = in.readLine();  // 줄 단위로 보내서 리드라인 사용, 들어오는 내용(enter)이 없을 땐 대기
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Network Error");
					System.exit(0);
				}
				this.append("\nClient >> " + msg); // 쓰레드이면서 TA이기 때문에 append 사용가능
				int pos = this.getText().length(); // 길이 파악 이유 : 그 다음 줄에 새로운 문장을 삽입 
				this.setCaretPosition(pos); // 입력 포인트 이동 : 맨 끝으로
				
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // TextFiled 리스너
		// TODO Auto-generated method stub
		if ( e.getSource() == inputTF) {
			String msg = inputTF.getText();
			try {
				out.write(msg+ "\n"); // 서버에서 읽을 때 한 줄로 읽기 때문에 뉴라인 필요
				out.flush();
				
				serverTA.append("\nServer >> " + msg);
				int pos = serverTA.getText().length(); // 길이 파악 이유 : 그 다음 줄에 새로운 문장을 삽입 
				serverTA.setCaretPosition(pos); // 입력 포인트 이동 : 맨 끝으로
				
				inputTF.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer(); // 객체 실행 레퍼런스만 넣는 것
	}
}
