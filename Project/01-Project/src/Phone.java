
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


	// 생성자
	public Phone(String name, String sex, int age, String phoneNum, String country) { // 생성자
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phoneNum = phoneNum;
		this.country = country;
	}
	
	@Override
	public String toString() { // Phone 객체로 부터 스트링을 만듦
		return name + " " + sex + " " + age + " " + phoneNum + " " + country;
	}
}
