
public class Phone {
	private String name;
	private String sex;
	private int age;
	private String phoneNum;
	private String country;
	
	public String getName() {
		return name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}


	// ������
	public Phone(String name, String sex, int age, String phoneNum, String country) { // ������
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phoneNum = phoneNum;
		this.country = country;
	}
	
	@Override
	public String toString() { // Phone ��ü�� ���� ��Ʈ���� ����
		return name + " " + sex + " " + age + " " + phoneNum + " " + country;
	}
}
