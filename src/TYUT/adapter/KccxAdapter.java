/*package TYUT.adapter;

import java.util.List;

import com.example.tyutapp.R;

import TYUT.adapter.PyfaAdapter.ViewHolder;
import TYUTservice.data.msgdata.KccxMsg;
import TYUTservice.data.msgdata.PyfaMsg;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KccxAdapter extends BaseAdapter {
	public class ViewHolder {

		TextView cone;
		TextView ctwo;
		TextView cthree;
		TextView cfour;
		TextView cfive;
		TextView cweek;

	}

	private Context context;
	private List<KccxMsg> kccxMsgs;

	public KccxAdapter(Context context, List<KccxMsg> kccxMsgs) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.kccxMsgs = kccxMsgs;
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
		ViewHolder holder = null;
		if (convertView == null) {
			Log.i("∆•≈‰", "dfd");
			convertView = LayoutInflater.from(context).inflate(
					R.layout.kccx_item, null);
			holder = new ViewHolder();

			holder.cone = (TextView) convertView.findViewById(R.id.kccx_cone);
			holder.ctwo = (TextView) convertView.findViewById(R.id.kccx_ctwo);

			holder.cthree = (TextView) convertView
					.findViewById(R.id.kccx_cthree);
			holder.cfour = (TextView) convertView.findViewById(R.id.kccx_cfour);
			holder.cfive = (TextView) convertView.findViewById(R.id.kccx_cfive);
			holder.cweek = (TextView) convertView.findViewById(R.id.kccx_week);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.cone.setText(kccxMsgs.get(position).getCone());
		holder.ctwo.setText(kccxMsgs.get(position).getCtwo());
		holder.cthree.setText(kccxMsgs.get(position).getCthree());
		holder.cfour.setText(kccxMsgs.get(position).getCfour());
		holder.cfive.setText(kccxMsgs.get(position).getCfive());
		holder.cweek.setText(kccxMsgs.get(position).getCweek());

		return convertView;
	}

}
*/