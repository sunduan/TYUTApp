package TYUT.tmp;

import java.util.List;

import TYUTservice.data.msgdata.FacjMsg;

public class Tmp {
	//public static List<FacjMsg> facjMsgs;
	private static String username;
	private static String password;
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Tmp.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Tmp.password = password;
	}

	private static String cookies = "";
	private static String serverIp="192.168.0.111:8080/TyutAppService";
	//private static String serverIp = "tyutappservice-end.mybluemix.net";
	
	public static String getServerIp() {
		return serverIp;
	}

	public static void setServerIp(String serverIp) {
		Tmp.serverIp = serverIp;
	}

	public static String getCookies() {
		return cookies;
	}

	public static void setCookies(String cookies) {
		Tmp.cookies = cookies;
	}

}
