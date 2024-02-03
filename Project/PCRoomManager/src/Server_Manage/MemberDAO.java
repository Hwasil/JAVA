package Server_Manage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import Client_Manage.Message;

public class MemberDAO {
	private ArrayList<Member> members = null;
	private Iterator<Member> it;
	private HashMap<String, Member> hMaps = new HashMap<String, Member>(); 
	
	public MemberDAO() throws IOException {
		members = new ArrayList<Member>();
		
		restore();
		
		it = members.iterator();
	}
	
	public boolean isMember(Message msg) {
		if (hMaps.containsKey(msg.getId())) {
			Member member = hMaps.get(msg.getId());
			
			if ( msg.getPw().equals(member.getPassword()) )
				return true;
			else
				return false;
		}
		else
			return false; 
	}
	
	public boolean isMember(String id) {
		if (hMaps.containsKey(id))
			return true;
		else
			return false; 
	}
	
	public void backup(String fileName) {
		FileWriter fWriter = null;
		try { 
			fWriter = new FileWriter(fileName, false); // append 여부
			
			for ( Member mem : members ) {
				if (mem != null) {
					fWriter.append(mem.toString());
				}
			}
			System.out.print("Backup Complete!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			if (fWriter != null) {
				try {
					fWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("백업파일 만들 수 없음");
				}
			}
		}
		
		
	}
	
	public void printMembers() { // console에 정보 출력
		while (it.hasNext()) {
			System.out.println(it.next().toString()); // 앞에 만든 toString이 사용됨
		}
	}
	
	public void restore() throws IOException { 
		FileReader fReader = null;
		
		try {
			fReader = new FileReader("Info/members.txt");
			BufferedReader inFile = new BufferedReader(fReader);
			
			String line = null;
			
			while ((line = inFile.readLine()) != null) {
				String tokens[] = line.split(",");
				String name = tokens[0];
				String id = tokens[1];
				String pw = tokens[2];
				String phone = tokens[3];
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				ArrayList<History> histories = new ArrayList<History>();
				
				for ( int i=4; i<tokens.length; i+=2 ) {
					LocalDateTime start = LocalDateTime.parse(tokens[i], formatter);
					LocalDateTime end = LocalDateTime.parse(tokens[i+1], formatter);
					histories.add(new History(start, end));
				}
				Member mem = new Member(name, id, pw, phone, histories);
				members.add(mem);
				hMaps.put(id, mem);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			if ( fReader != null ) {
				fReader.close();
			} else {
				System.out.println("백업파일 존재 안함");
			}
		}
	}
}
