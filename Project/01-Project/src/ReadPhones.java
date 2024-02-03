import java.util.*;
import java.io.*;

public class ReadPhones { // ��� ��� ����
	private Vector<Phone> vPhones = new Vector<Phone>(); 
	
	public Vector<Phone> getVvPhones() {
		return vPhones;
	}
	
	public ReadPhones() { // ���� �����ͼ� �б�
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./src/phonebook.txt")); 
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
	
	private boolean parsePhones(String line) { 
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
	
	public String searchName(String name) { // �̸� �˻�
		Iterator it = this.vPhones.iterator();
		
		while (it.hasNext() ) {
			Phone p = (Phone)it.next();
			if (p.getName().equals(name) )
				return p.toString();
		}
		return null;
	}
	
	public String searchPhoneNumber(String phoneNum) { // ��ȭ��ȣ �˻�
		Iterator it = this.vPhones.iterator();
		
		while (it.hasNext() ) {
			Phone p = (Phone)it.next();
			if ( p.getPhoneNum().equals(phoneNum) )
				return p.toString();
		}
		return null;
	}
	
	public String[] getPhoneString() { // ��Ʈ���� ���� ���۷��� ��ȯ
		String [] pStrings = new String[this.vPhones.size()];
		
		Iterator it = this.vPhones.iterator();
		int i = 0;
		while (it.hasNext() ) {
			pStrings[i++] = it.next().toString();
		}
		return pStrings;
	}
	
	public boolean editPhone(String phone, int index) {
		String [] tokens = phone.split(" |\t");
		if (tokens.length < 5) 
			return false;
		
		Phone p = new Phone(tokens[0], tokens[1], // phone ��ü
				Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
		
		this.vPhones.set(index, p);
		return true;
	}
	
	public Phone removePhone(int index) {
		Phone p = this.vPhones.remove(index);
		if ( p != null )
			return p;
		else 
			return null;
	}
	
	public boolean savePhone() {
		String [] pStrings = this.getPhoneString();
		FileWriter fout = null;
		try {
			fout = new FileWriter("./src/phonebook.txt");
			for (String line : pStrings) {
				if (line.length() == 0)
					break;
				fout.write(line, 0, line.length());
				fout.write("\r\n", 0, 2);
			}
			fout.close();
		} catch (IOException e) {
			System.out.print("���� ����");
			return false;
		}
		return true;
	}
}