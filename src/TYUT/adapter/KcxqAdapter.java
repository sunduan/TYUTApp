package TYUT.adapter;

import java.util.List;

import com.example.tyutapp.R;

import TYUT.adapter.FacjAdapter.ViewHolder;
import TYUTservice.data.msgdata.FacjMsg;
import TYUTservice.data.msgdata.KccxMsg;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KcxqAdapter extends BaseAdapter {
	public class ViewHolder {

		TextView kcm;
		TextView xf;
		TextView js;
		TextView jc;
		TextView xq;
		TextView zc;
		
	}
	
	private Context context;
	private List<KccxMsg> kccxMsgs;
	
	
	 public  KcxqAdapter(Context context,List<KccxMsg> kccxMsgs)  
     {  
         //根据context上下文加载布局  
		  this.kccxMsgs=kccxMsgs;
         this.context = context;  
     }


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return kccxMsgs.size();
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return kccxMsgs.get(position);
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null){
			Log.i("匹配","dfd");
			convertView=LayoutInflater.from(context).inflate(
					R.layout.kcxq_item, null);
			holder = new ViewHolder();
			holder.kcm=(TextView) convertView.findViewById(R.id.kcxq_kcm);
			holder.xf=(TextView) convertView.findViewById(R.id.kcxq_xf);
			holder.js=(TextView) convertView.findViewById(R.id.kcxq_js);
			holder.jc=(TextView) convertView.findViewById(R.id.kcxq_jc);
			holder.xq=(TextView) convertView.findViewById(R.id.kcxq_xq);
			holder.zc=(TextView) convertView.findViewById(R.id.kcxq_zc);
			
			convertView.setTag(holder); 
		}else  
        {  
            holder = (ViewHolder)convertView.getTag();  
        }  
		/*Log.i("参数",facjMsgs.get(position).getCj()+"");
		Log.i("个数",position+"");*/
		holder.kcm.setText(kccxMsgs.get(position).getMon());
		holder.xf.setText(kccxMsgs.get(position).getTue());
		holder.js.setText(kccxMsgs.get(position).getWed());
		holder.jc.setText(kccxMsgs.get(position).getThu());
		holder.xq.setText(kccxMsgs.get(position).getFri());
		holder.zc.setText(kccxMsgs.get(position).getSat());
		
		return convertView;
	}  
}
