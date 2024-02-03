package Client_Manage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import Server_Manage.MemberDAO;

class ClientRunnable implements Runnable {
	private Socket socket = null;
	private MemberDAO members = null;
	
	
	public ClientRunnable(Client client, Message msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() { //한번만 받고 끝남 --> 방학 때 while 사용해 서비스가 반복되도록 구현 해보기
		// TODO Auto-generated method stub
		System.out.println("연결!");
		
		if ( socket.inConnected() == false) { //////// 얘는 위치가 어딜까.... 여기 다 놓침ㅋㅋㅋㅋ
			System.out.println("서버에 연결할 수 없습니다.");
			return;
		} else {
			System.out.println("서버에 연결되었습니다.");
		}

		
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // send
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //take
			
			Message msg = (Message)in.readObject();
			if ( members.isMember(msg) == true ) {
				out.write("Login OK\n"); 
			} else {
				out.write("Login FAil\n");
			}
			//
			out.flush();
			in.close();
			out.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if ( socket != null ) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}

public class Client {
	Message msg = null;
	private String response = null;
	private ClientRunnable runnable = null;
	
	public Client(Message msg) {
		this.msg = msg;
		runnable = new ClientRunnable(this.msg);
	}
	
	public void startClient() {
		Thread th = new Thread(runnable);
		th.start();
	}
	
	synchronized public String getResponse() {
		if (this.response == null) { 
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	synchronized public void setResponse(String response) {
		this.response = response;
		notify();
	}
}
