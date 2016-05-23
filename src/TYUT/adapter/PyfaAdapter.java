package TYUT.adapter;

import java.util.List;

import com.example.tyutapp.R;

import TYUT.adapter.FacjAdapter.ViewHolder;
import TYUTservice.data.msgdata.FacjMsg;
import TYUTservice.data.msgdata.PyfaMsg;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PyfaAdapter extends BaseAdapter{
	public class ViewHolder {

		TextView mon;
		TextView tue;
		TextView wed;
		TextView thu;
		TextView fri;
		TextView sat;
		TextView sun;
		
	}
	private Context context;
	private List<PyfaMsg> pyfaMsgs;
	public  PyfaAdapter(Context context,List<PyfaMsg> pyfaMsgs)  
    {  
        //根据context上下文加载布局  
		  this.pyfaMsgs=pyfaMsgs;
        this.context = context;  
    } 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pyfaMsgs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pyfaMsgs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//final int a=position;
		ViewHolder holder=null;
		if(convertView==null){
			Log.i("匹配","dfd");
			convertView=LayoutInflater.from(context).inflate(
					R.layout.pyfa_item, null);
			holder = new ViewHolder();
			
			holder.mon=(TextView) convertView.findViewById(R.id.pyfa_xq);
			holder.tue=(TextView) convertView.findViewById(R.id.pyfa_wc);
			
			holder.wed=(TextView) convertView.findViewById(R.id.pyfa_wed);
			holder.thu=(TextView) convertView.findViewById(R.id.pyfa_thu);
			holder.fri=(TextView) convertView.findViewById(R.id.pyfa_fri);
			holder.sat=(TextView) convertView.findViewById(R.id.pyfa_sat);
			holder.sun=(TextView) convertView.findViewById(R.id.pyfa_sun);
			convertView.setTag(holder); 
		}else  
        {  
            holder = (ViewHolder)convertView.getTag();  
        }  
		//Log.i("position",a+"");
		/*if(pyfaMsgs.get(position).getMon()=="培养方案完成情况"){
			holder.mon.setText("培养方案完成情况");
			holder.tue.setText("");
			((TextView) convertView.findViewById(R.id.pyfa_zxf)).setText("培养方案总学分");
			((TextView) convertView.findViewById(R.id.pyfa_zxs)).setText("培养方案总总学时");
			((TextView) convertView.findViewById(R.id.pyfa_zms)).setText("培养方案总门数");
			((TextView) convertView.findViewById(R.id.pyfa_yms)).setText("已通过课程门数");
			((TextView) convertView.findViewById(R.id.pyfa_wms)).setText("未通过课程门数");
			
			holder.wed.setText(pyfaMsgs.get(position).getMon());
			holder.thu.setText(pyfaMsgs.get(position).getTue());
			holder.fri.setText(pyfaMsgs.get(position).getWed());
		}else{*/
			holder.mon.setText(pyfaMsgs.get(position).getMon());
			holder.tue.setText(pyfaMsgs.get(position).getTue());
			holder.wed.setText(pyfaMsgs.get(position).getWed());
			holder.thu.setText(pyfaMsgs.get(position).getThu());
			holder.fri.setText(pyfaMsgs.get(position).getFri());
			
		//}
		holder.sat.setText(pyfaMsgs.get(position).getSat());
		holder.sun.setText(pyfaMsgs.get(position).getSun());

		return convertView;
	}
}
