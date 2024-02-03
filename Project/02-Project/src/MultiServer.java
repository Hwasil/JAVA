import java.io.*;
import java.net.*;
import java.util.*;

class ServiceRunnable implements Runnable {
	private BufferedReader in  = null;
	private BufferedWriter out = null;
	Socket socket = null;
	ServerChat frame = null;
	MultiServer server = null;
	int id = 0; // ������ Ŭ���̾�Ʈ ����
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ServiceRunnable(ServerChat frame, Socket socket, MultiServer server) throws IOException {
		this.frame = frame;
		this.socket = socket;
		this.server = server;
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
		
		frame.addMessages("Ŭ���̾�Ʈ ���� �Ϸ�");
	}
	
	public void sendMessages(String msg) {
		try {
			out.write(msg + "\n");
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
			String tokens[] = msg.split("/");
			String name = tokens[0];
			String message = tokens[1];
			
			if ( message != null && message.equalsIgnoreCase("bye")) {
				frame.addMessages("����/Ŭ���̾�Ʈ ���� ����");
				break;
			} else {
				frame.addMessages(msg);
				server.sendMessages(message); // ����Ŭ���̾�Ʈ ����
			}
			
			if( socket.isConnected() == false )
				break;
		}
		if ( socket != null ) { // 
			try {
				socket.close();
				in.close();
				out.close();
				server.removeServer(id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class MultiServer extends Thread {
	ServerChat frame = null;
	private ServerSocket serverSocket = null;
	ArrayList<ServiceRunnable> clients = new ArrayList<ServiceRunnable>();
	int id = 0;
	
	public MultiServer(ServerChat frame) {
		this.frame = frame;	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(9999);
			while ( true ) {
				Socket socket = serverSocket.accept(); // local 
				addServer(socket); 
				System.out.println("Server init");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if ( serverSocket != null ) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void addServer(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		ServiceRunnable runnable = new ServiceRunnable(frame, socket, this);
		runnable.setId(id++);
		
		this.clients.add(runnable);
		Thread th = new Thread(runnable);
		th.start();
	}
	
	public void removeServer(int id) {
		// TODO Auto-generated method stub
		Iterator it = clients.iterator();
		int index = -1;
		
		// ArrayList�� ���� �������� ����Ǿ�����
		while ( it.hasNext() ) { // iterator�� ��ȸ
			ServiceRunnable runnable = (ServiceRunnable) it.next();
			
			if ( runnable != null && runnable.getId() == id ) { // ���̵�� ���� ���� 
				index = clients.indexOf(runnable);
			}
		}
		if (index > 0) // ���ʺ��� �ش�ȴٴ� �ǹ�
			clients.remove(index);
	}

	public void sendMessages(String msg) { // 
		// TODO Auto-generated method stub
		Iterator it = clients.iterator();
		
		while ( it.hasNext() ) {
			ServiceRunnable runnable = (ServiceRunnable) it.next();
			if ( runnable.getId() != id ) { 
				runnable.sendMessages(msg); 
			}
		}
	}
	
}
