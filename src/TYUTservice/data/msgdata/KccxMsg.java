package TYUTservice.data.msgdata;

public class KccxMsg {
	private String cone;
	private String ctwo;
	private String cthree;
	private String cfour;
	private String cfive;
	private String cweek;

	public KccxMsg() {

	}

	public String getCone() {
		return cone;
	}

	public void setCone(String cone) {
		this.cone = cone;
	}

	public String getCtwo() {
		return ctwo;
	}

	public void setCtwo(String ctwo) {
		this.ctwo = ctwo;
	}

	public String getCthree() {
		return cthree;
	}

	public void setCthree(String cthree) {
		this.cthree = cthree;
	}

	public String getCfour() {
		return cfour;
	}

	public void setCfour(String cfour) {
		this.cfour = cfour;
	}

	public String getCfive() {
		return cfive;
	}

	public void setCfive(String cfive) {
		this.cfive = cfive;
	}

	public KccxMsg(String cone, String ctwo, String cthree, String cfour,
			String cfive, String cweek) {
		super();
		this.cone = cone;
		this.ctwo = ctwo;
		this.cthree = cthree;
		this.cfour = cfour;
		this.cfive = cfive;
		this.cweek = cweek;
	}

	public String getCweek() {
		return cweek;
	}

	public void setCweek(String cweek) {
		this.cweek = cweek;
	}

}
