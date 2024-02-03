// 실제로 파일에서 데이터를 읽어 벡터에 넣는 클래스. 추가, 삭제도 이루어짐.
import java.util.*;
import java.io.*;

public class ReadPhones {
	private Vector<Phone> vPhones = new Vector<Phone>(); // 클래스를 넣을 거기 때문에 클래스 이름을 <>에 넣어준다
	
	public Vector<Phone> getVvPhones() {
		return vPhones;
	}
	
	public ReadPhones() { // 파일 가져와서 읽기
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./src/phonebook.txt")); //ANSI로 저장
			// 인코딩 적용
			//reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/phone.txt"), "UTF-8")); // UTF-8로 저장
			String line = reader.readLine();
			
			while (line != null) {
				if (this.parsePhones(line) )
					System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			
		}
	}
	
	private boolean parsePhones(String line) { // 구분자?로 구분해서 한 단어 씩 읽어오기
		String [] tokens = line.split(" |\t");
		if (tokens.length < 5) 
			return false;
		
		Phone p = new Phone(tokens[0], tokens[1], 
				Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
		this.vPhones.add(p);
		
		return true;
		
	}
	public boolean addPhones(String phoneStr) { 
		if (this.parsePhones(phoneStr) ) 
			return true;
		else 
			return false;
	}
	
	public String searchName(String name) { // 이름 찾기?
		Iterator it = this.vPhones.iterator();
		
		while (it.hasNext() ) {
			Phone p = (Phone)it.next();
			if (p.getName().equals(name) )
				return p.toString();
		}
		return null;
	}
	
	public String[] getPhoneString() {
		String [] pStrings = new String[this.vPhones.size()];
		
		Iterator it = this.vPhones.iterator();
		int i = 0;
		while (it.hasNext() ) {
			pStrings[i++] = it.next().toString();
		}
		return pStrings;
	}
}