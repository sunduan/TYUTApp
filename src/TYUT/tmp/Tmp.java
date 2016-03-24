package TYUT.tmp;

public class Tmp {
private static String cookies="";
private static String serverIp="192.168.200.155";
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
