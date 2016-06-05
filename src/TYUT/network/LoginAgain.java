package TYUT.network;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.database.Dbconnetc;
import TYUT.tmp.Tmp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoginAgain {
	public void loginAgain(Context context ) {
		// ���Ӷ���
		Dbconnetc dbconnetc = new Dbconnetc(context);
		final SQLiteDatabase database = dbconnetc.getReadableDatabase();

		// ����USER���ݿ���������򲻴���
		dbconnetc.onCreate(database);
		
		Cursor c = database.query("user",null,null,null,null,null,null);//��ѯ������α�
		Log.i("��С",c.getCount()+"");
		c.moveToPosition(0);//�ƶ���ָ����¼,ֻȥ��һ��
        final String username = c.getString(c.getColumnIndex("username"));
        final String password = c.getString(c.getColumnIndex("password"));
		
		ConnectTYUT connectTYUT = new ConnectTYUT();
		// ���ݽ��ն���
		//MessageFacj facj = new MessageFacj();
		// ��������
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
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
