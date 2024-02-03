package Server_Manage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

import Client_Manage.Message; // �ٸ� ��Ű���� �ִ� Ŭ���� ���

class ServiceRunnable implements Runnable {
	private Socket socket = null;
	private MemberDAO members = null;
	
	public ServiceRunnable(Socket soc, MemberDAO members) { // ���� �ٸ�
		this.socket = soc;
		this.members = members;
	}
	
	@Override
	public void run() { //�ѹ��� �ް� ���� --> ���� �� while ����� ���񽺰� �ݺ��ǵ��� ���� �غ���
		// TODO Auto-generated method stub
		System.out.println("����!");
		
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
				System.out.println("���� ���� ��ٸ�");
				Socket socket = listener.accept(); // Ŭ���̾�Ʈ ���� ������ ���
				addServer(socket); // ���ο� �н� ����
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� ����");
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
	
	public void addServer(Socket soc) { // ���� ������ �н� ���
		ServiceRunnable runnable = new ServiceRunnable(soc, members);
		Thread th = new Thread(runnable);
		
		th.start();
	}
	
}
