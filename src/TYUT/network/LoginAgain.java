package TYUT.network;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.tmp.Tmp;
import TYUTservice.data.MessageFacj;

public class LoginAgain {
	public void loginAgain() {
		// ���Ӷ���
		ConnectTYUT connectTYUT = new ConnectTYUT();
		// ���ݽ��ն���
		//MessageFacj facj = new MessageFacj();
		// ��������
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("username", Tmp.getUsername()));
		params.add(new BasicNameValuePair("password", Tmp.getPassword()));
		// ����
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
