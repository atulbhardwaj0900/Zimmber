package main.taskem.com.agri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import main.taskem.com.agri.R;


/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class MainHorizontalAdapter extends JsonArrayAdapter {

	public MainHorizontalAdapter(OnRecyclerItemClick<JSONObject> onRecyclerItemClick) {
		super(onRecyclerItemClick);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		super.onCreateViewHolder(parent, viewType);
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.person_view, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	protected void bindDataToView(BaseViewHolder holder, JSONObject note, int position) {
		MyViewHolder viewHolder = (MyViewHolder) holder;
		//viewHolder.title.setText(note.getTitle());
		//viewHolder.detail.setText(note.getDetail());
	}

	public class MyViewHolder extends BaseViewHolder {

		private TextView title;
		private TextView detail;

		public MyViewHolder(View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.person_img);
			detail = (TextView) itemView.findViewById(R.id.person_name_txt);
		}
	}

}
