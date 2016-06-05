package TYUTservice.data.msgdata;

public class FacjMsg {
	private String kch;
	private String kxh;
	private String kcm;
	private String ywkcm;
	private String xf;
	private String kcsx;
	private String cj;
	private String wtgyy="";
	public FacjMsg(){
		
	}
	public FacjMsg(String kch, String kxh, String kcm, String ywkcm, String xf,
			String kcsx, String cj,String wtgyy) {
		super();
		this.kch = kch;
		this.kxh = kxh;
		this.kcm = kcm;
		this.ywkcm = ywkcm;
		this.xf = xf;
		this.kcsx = kcsx;
		this.cj = cj;
		this.wtgyy=wtgyy;
	}
	public String getWtgyy() {
		return wtgyy;
	}
	public void setWtgyy(String wtgyy) {
		this.wtgyy = wtgyy;
	}
	public String getKch() {
		return kch;
	}
	public void setKch(String kch) {
		this.kch = kch;
	}
	public String getKxh() {
		return kxh;
	}
	public void setKxh(String kxh) {
		this.kxh = kxh;
	}
	public String getKcm() {
		return kcm;
	}
	public void setKcm(String kcm) {
		this.kcm = kcm;
	}
	public String getYwkcm() {
		return ywkcm;
	}
	public void setYwkcm(String ywkcm) {
		this.ywkcm = ywkcm;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getKcsx() {
		return kcsx;
	}
	public void setKcsx(String kcsx) {
		this.kcsx = kcsx;
	}
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
}
