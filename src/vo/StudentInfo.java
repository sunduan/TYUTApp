package vo;

public class StudentInfo {
	private String name;
	private String userper;
	private String tel;
	private String address;
	private String email;
	public StudentInfo(){
		
	}
	public StudentInfo(String name, String userper, String tel, String address,
			String email) {
		super();
		this.name = name;
		this.userper = userper;
		this.tel = tel;
		this.address = address;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserper() {
		return userper;
	}
	public void setUserper(String userper) {
		this.userper = userper;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}	
