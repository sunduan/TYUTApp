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
		
		//������������Ĳ���
		int timeoutConnection = 3000;  
		int timeoutSocket = 5000;  
		HttpParams httpParameters = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);  
	    
	    //��ʼ���ӷ�����
	    Log.i("a","��ʼ����");
	    HttpClient httpClient=new DefaultHttpClient();
	    HttpPost httpPost=new HttpPost(url);
	    Log.i("ip",url);
	    Log.i("a","������...");
	    if(params.size()>=0){
	    	try {
	    		Log.i("a","����1....");   		
				httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8 ));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    try {
	    	Log.i("a","����2...");
			HttpResponse httpResponse=httpClient.execute(httpPost);
			Log.i("a","����3...");
			int statusCode=httpResponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				Log.i("a","���ӳɹ�");
				
				response=EntityUtils.toString(httpResponse.getEntity());
			}else{
				response="��Ӧ��"+statusCode;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
