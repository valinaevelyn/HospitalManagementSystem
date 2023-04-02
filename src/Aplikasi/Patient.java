package Aplikasi;

public class Patient {
	private String id;
	private String name;
	private int age;
	private String address;
	private String phoneNum;
	private String gender;
	private String blood;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(String id, String name, int age, String address, String phoneNum, String gender, String blood) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.blood = blood;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

}
