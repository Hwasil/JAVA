package Client_Manage;

import java.io.Serializable;

public class Message implements Serializable { // ��ü�� ���̳ʸ� ���·� ��ȯ�ؼ� ����
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
