package TYUTservice.data.msgdata;

public class KccxMsg {
	private String Mon;
	private String Tue;
	private String Wed;
	private String Thu;
	private String Fri;
	public String getMon() {
		return Mon;
	}
	public KccxMsg(){
		
	}
	public KccxMsg(String mon, String tue, String wed, String thu, String fri,
			String sat, String sun) {
		super();
		Mon = mon;
		Tue = tue;
		Wed = wed;
		Thu = thu;
		Fri = fri;
		Sat = sat;
		Sun = sun;
	}
	public void setMon(String mon) {
		Mon = mon;
	}
	public String getTue() {
		return Tue;
	}
	public void setTue(String tue) {
		Tue = tue;
	}
	public String getWed() {
		return Wed;
	}
	public void setWed(String wed) {
		Wed = wed;
	}
	public String getThu() {
		return Thu;
	}
	public void setThu(String thu) {
		Thu = thu;
	}
	public String getFri() {
		return Fri;
	}
	public void setFri(String fri) {
		Fri = fri;
	}
	public String getSat() {
		return Sat;
	}
	public void setSat(String sat) {
		Sat = sat;
	}
	public String getSun() {
		return Sun;
	}
	public void setSun(String sun) {
		Sun = sun;
	}
	private String Sat;
	private String Sun;
}
