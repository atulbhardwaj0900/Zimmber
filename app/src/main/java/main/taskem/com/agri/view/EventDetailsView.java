package main.taskem.com.agri.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import main.taskem.com.agri.R;
import main.taskem.com.agri.adapter.JsonArrayAdapter.OnRecyclerItemClick;
import main.taskem.com.agri.models.BodyContent;


/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class EventDetailsView extends LinearLayout implements OnRecyclerItemClick<JSONObject> {
	private Context mContext;
	private View mView;

	public EventDetailsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public EventDetailsView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	private void init() {
		mView = LayoutInflater.from(mContext).inflate(R.layout.event_details_view, this, true);

	}

	public void setData(BodyContent bodyContent) {
		TextView address = (TextView) mView.findViewById(R.id.address_txt);
		address.setText(bodyContent.getName());
		EventImageView eventImageView = (EventImageView) mView.findViewById(R.id.address_img_view);
		eventImageView.setImage(bodyContent.getUrl());
	}

	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	@Override
	public void onRecyclerItemClick(int position, JSONObject object) {

	}
}
