package Server_Manage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

import Client_Manage.Message; // 다른 패키지에 있는 클래스 사용

class ServiceRunnable implements Runnable {
	private Socket socket = null;
	private MemberDAO members = null;
	
	public ServiceRunnable(Socket soc, MemberDAO members) { // 여기 다름
		this.socket = soc;
		this.members = members;
	}
	
	@Override
	public void run() { //한번만 받고 끝남 --> 방학 때 while 사용해 서비스가 반복되도록 구현 해보기
		// TODO Auto-generated method stub
		System.out.println("연결!");
		
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // send
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //take
			
			Message msg = (Message)in.readObject();
			if ( members.isMember(msg) == true ) {
				out.write("Login OK\n"); 
			} else {
				out.write("Login FAil\n");
			}
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

public class Server extends Thread {
	private ServerSocket listener = null;
	private MemberDAO members = null;
	
	public Server(MemberDAO members) {
		this.members = members;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			listener = new ServerSocket(9999);
			while (true) {
				System.out.println("서버 연결 기다림");
				Socket socket = listener.accept(); // 클라이언트 들어올 때까지 대기
				addServer(socket); // 새로운 분신 생성
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("서버 종료");
		} finally {
			if (listener != null) {
				try {
					listener.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addServer(Socket soc) { // 새로 생성한 분신 사용
		ServiceRunnable runnable = new ServiceRunnable(soc, members);
		Thread th = new Thread(runnable);
		
		th.start();
	}
	
}
