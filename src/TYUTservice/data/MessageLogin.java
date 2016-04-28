package TYUTservice.data;


public class MessageLogin extends MessageTyut {
	public MessageLogin(int id, int status, String cookie) {
		super(id, status);
		this.cookie = cookie;
		// TODO Auto-generated constructor stub
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	// cookie
	private String cookie;

}
