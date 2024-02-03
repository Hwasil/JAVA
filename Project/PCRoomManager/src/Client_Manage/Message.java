package Client_Manage;

import java.io.Serializable;

public class Message implements Serializable { // 객체를 바이너리 형태로 변환해서 전송
	private String id;
	private String pw;
	
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public Message(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

}
