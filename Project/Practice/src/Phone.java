
public class Phone {
	private String name;
	private String sex;
	private int age;
	private String phoneNum;
	private String country;
	
	public String getName() {
		return name;
	}

	// 생성자
	public Phone(String name, String sex, int age, String phoneNum, String country) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phoneNum = phoneNum;
		this.country = country;
	}
	
	@Override
	public String toString() { // 출력 문자열 지우고 공백 넣어서 구분하기
		return name + " " + sex + " " + age + " " + phoneNum + " " + country;
	}
}
