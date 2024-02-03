// ������ ���Ͽ��� �����͸� �о� ���Ϳ� �ִ� Ŭ����. �߰�, ������ �̷����.
import java.util.*;
import java.io.*;

public class ReadPhones {
	private Vector<Phone> vPhones = new Vector<Phone>(); // Ŭ������ ���� �ű� ������ Ŭ���� �̸��� <>�� �־��ش�
	
	public Vector<Phone> getVvPhones() {
		return vPhones;
	}
	
	public ReadPhones() { // ���� �����ͼ� �б�
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./src/phonebook.txt")); //ANSI�� ����
			// ���ڵ� ����
			//reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/phone.txt"), "UTF-8")); // UTF-8�� ����
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
	
	private boolean parsePhones(String line) { // ������?�� �����ؼ� �� �ܾ� �� �о����
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
	
	public String searchName(String name) { // �̸� ã��?
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