package vo;

public class StudentStatus {
	private String idstudent;
	private String name;
	private String idcard;
	private String sex;
	private String mz;
	private String jg;
	private String csrq;
	private String zz;
	private String rxrq;
	private String xs;
	private String zy;
	private String nj;
	public StudentStatus(){
		
	}
	public StudentStatus(String idstudent, String name, String idcard,
			String sex, String mz, String jg, String csrq, String zz,
			String rxrq, String xs, String zy, String nj, String bj,
			String sfxj, String gjxj, String xq, String yd) {
		super();
		this.idstudent = idstudent;
		this.name = name;
		this.idcard = idcard;
		this.sex = sex;
		this.mz = mz;
		this.jg = jg;
		this.csrq = csrq;
		this.zz = zz;
		this.rxrq = rxrq;
		this.xs = xs;
		this.zy = zy;
		this.nj = nj;
		this.bj = bj;
		this.sfxj = sfxj;
		this.gjxj = gjxj;
		this.xq = xq;
		this.yd = yd;
	}
	private String bj;
	private String sfxj;
	private String gjxj;
	public String getIdstudent() {
		return idstudent;
	}
	public void setIdstudent(String idstudent) {
		this.idstudent = idstudent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = zz;
	}
	public String getRxrq() {
		return rxrq;
	}
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	public String getXs() {
		return xs;
	}
	public void setXs(String xs) {
		this.xs = xs;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getSfxj() {
		return sfxj;
	}
	public void setSfxj(String sfxj) {
		this.sfxj = sfxj;
	}
	public String getGjxj() {
		return gjxj;
	}
	public void setGjxj(String gjxj) {
		this.gjxj = gjxj;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
	private String xq;
	private String yd;
}
