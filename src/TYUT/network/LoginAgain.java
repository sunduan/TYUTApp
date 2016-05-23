package TYUT.network;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.tmp.Tmp;
import TYUTservice.data.MessageFacj;

public class LoginAgain {
	public void loginAgain() {
		// 连接对象
		ConnectTYUT connectTYUT = new ConnectTYUT();
		// 数据接收对象
		//MessageFacj facj = new MessageFacj();
		// 参数对象
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("username", Tmp.getUsername()));
		params.add(new BasicNameValuePair("password", Tmp.getPassword()));
		// 连接
		String getcookie =connectTYUT.getByPost("http://" + Tmp.getServerIp() + "/login.action", params);
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(getcookie);
			Tmp.setCookies(jsonObject.getString("cookie"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
