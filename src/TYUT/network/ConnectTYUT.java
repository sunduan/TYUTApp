package TYUT.network;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class ConnectTYUT {
	public String getByPost(String url ,List<BasicNameValuePair> params) {
		String response = null;
		
		//设置连接网络的参数
		int timeoutConnection = 3000;  
		int timeoutSocket = 5000;  
		HttpParams httpParameters = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);  
	    
	    //开始连接服务器
	    Log.i("a","开始连接");
	    HttpClient httpClient=new DefaultHttpClient();
	    HttpPost httpPost=new HttpPost(url);
	    Log.i("ip",url);
	    Log.i("a","连接中...");
	    if(params.size()>=0){
	    	try {
	    		Log.i("a","连接1....");   		
				httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8 ));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    try {
	    	Log.i("a","连接2...");
			HttpResponse httpResponse=httpClient.execute(httpPost);
			Log.i("a","连接3...");
			int statusCode=httpResponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				Log.i("a","连接成功");
				
				response=EntityUtils.toString(httpResponse.getEntity());
			}else{
				response="响应吗"+statusCode;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
