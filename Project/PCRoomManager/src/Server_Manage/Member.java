package Server_Manage; 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

class History { // 시작 시간과 끝 시간 정보를 알고 있어야함
	private LocalDateTime start = null;
	private LocalDateTime end = null;
	
	public History(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) 
				+ "," + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}



	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
}

public class Member {
	private String name;
	private String id;
	private String password;
	private String phone;
	private ArrayList<History> history = new ArrayList<History>();
	
	public Member(String name, String id, String pw, String phone, ArrayList<History> his) {
		super();
		this.name = name;
		this.id = id;
		this.password = pw;
		this.phone = phone;
		this.history = his;
	}
	
	@Override
	public String toString() { // 파일에 저장될 때, 멤버만 불러도 쉽게 저장 가능
		// TODO Auto-generated method stub
		return name+","+id+","+password+","+phone+","+getHistoryString();
	}



	public String getHistoryString() {
		Iterator<History> it = this.history.iterator(); // 컬렉션에 대한 반복자가 넘어옴
		String tempStr = "";
		
		while (it.hasNext()) {
			tempStr = tempStr.concat(it.next().toString());
			if (it.hasNext()) 
				tempStr = tempStr.concat(",");
			else
				break;
		}
		return tempStr;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<History> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<History> history) {
		this.history = history;
	}
}

	
