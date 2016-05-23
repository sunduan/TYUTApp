package TYUT.adapter;

import java.util.List;

import TYUTservice.data.msgdata.FacjMsg;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyutapp.R;

public class FacjAdapter extends BaseAdapter {
	public class ViewHolder {

		TextView kch;
		TextView kxh;
		TextView kcm;
		TextView ywkcm;
		TextView xf;
		TextView kcsx;
		TextView cj;
		TextView wtgyy;
	}
	
	private Context context;
	private List<FacjMsg> facjMsgs;
	//private Activity activity;
	
	  public  FacjAdapter(Context context,List<FacjMsg> facjMsgs)  
      {  
          //根据context上下文加载布局  
		  this.facjMsgs=facjMsgs;
          this.context = context;  
      }  
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return facjMsgs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return facjMsgs.get(position);
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
					R.layout.facj_item, null);
			holder = new ViewHolder();
			holder.kch=(TextView) convertView.findViewById(R.id.facj_kch);
			holder.kxh=(TextView) convertView.findViewById(R.id.facj_kxh);
			holder.kcm=(TextView) convertView.findViewById(R.id.facj_kcm);
			holder.ywkcm=(TextView) convertView.findViewById(R.id.facj_ywkcm);
			holder.xf=(TextView) convertView.findViewById(R.id.facj_xf);
			holder.kcsx=(TextView) convertView.findViewById(R.id.facj_kcsx);
			holder.cj=(TextView) convertView.findViewById(R.id.facj_cj);
			holder.wtgyy=(TextView) convertView.findViewById(R.id.facj_wtgyy);
			convertView.setTag(holder); 
		}else  
        {  
            holder = (ViewHolder)convertView.getTag();  
        }  
		Log.i("参数",facjMsgs.get(position).getCj()+"");
		Log.i("个数",position+"");
		holder.kch.setText(facjMsgs.get(position).getKch());
		holder.kxh.setText(facjMsgs.get(position).getKxh());
		holder.kcm.setText(facjMsgs.get(position).getKcm());
		holder.ywkcm.setText(facjMsgs.get(position).getYwkcm());
		holder.xf.setText(facjMsgs.get(position).getXf());
		holder.kcsx.setText(facjMsgs.get(position).getKcsx());
		holder.cj.setText(facjMsgs.get(position).getCj());
		holder.wtgyy.setText(facjMsgs.get(position).getWtgyy());
		return convertView;
	}

}
