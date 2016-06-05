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
		// 连接对象
		Dbconnetc dbconnetc = new Dbconnetc(context);
		final SQLiteDatabase database = dbconnetc.getReadableDatabase();

		// 创建USER数据库如果存在则不创建
		dbconnetc.onCreate(database);
		
		Cursor c = database.query("user",null,null,null,null,null,null);//查询并获得游标
		Log.i("大小",c.getCount()+"");
		c.moveToPosition(0);//移动到指定记录,只去第一条
        final String username = c.getString(c.getColumnIndex("username"));
        final String password = c.getString(c.getColumnIndex("password"));
		
		ConnectTYUT connectTYUT = new ConnectTYUT();
		// 数据接收对象
		//MessageFacj facj = new MessageFacj();
		// 参数对象
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
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
