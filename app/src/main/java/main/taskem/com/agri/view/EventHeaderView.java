package main.taskem.com.agri.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import main.taskem.com.agri.R;
import main.taskem.com.agri.Utils.Utils;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class EventHeaderView extends RelativeLayout implements OnClickListener {
	private View mView;
	private Context mContext;

	public EventHeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();


	}

	public EventHeaderView(Context context, AttributeSet attrs, int defaultStyle) {
		super(context, attrs, defaultStyle);
		mContext = context;
		init();
	}

	public void setHeading(String heading) {
		TextView headingView = (TextView) mView.findViewById(R.id.eventHeading);
		headingView.setText(heading);
	}

	public void setStartDate(String heading) {
		TextView headingView = (TextView) mView.findViewById(R.id.startDate);
		headingView.setText(Utils.getDate(heading));
	}

	public void setEndDate(String heading) {
		TextView headingView = (TextView) mView.findViewById(R.id.endDate);
		headingView.setText(Utils.getDate(heading));
	}

	public void setAttendingStatus(String status) {
		TextView textView = null;
		switch (status) {
			case "y":
			case "Y":
				textView = (TextView) mView.findViewById(R.id.yes_txt);
				break;
			case "n":
			case "N":
				textView = (TextView) mView.findViewById(R.id.no_txt);
				break;
			case "m":
			case "M":
				textView = (TextView) mView.findViewById(R.id.maybe_txt);
				break;
		}
		if (textView != null) {
			textView.setTextColor(Color.BLUE);
		}
	}

	public void init() {
		mView = LayoutInflater.from(mContext).inflate(R.layout.event_header_view, this, true);

		mView.findViewById(R.id.going_txt).setOnClickListener(this);
		mView.findViewById(R.id.yes_txt).setOnClickListener(this);
		mView.findViewById(R.id.no_txt).setOnClickListener(this);
		mView.findViewById(R.id.maybe_txt).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view.getTag() != null) {
			String tag = view.getTag().toString();
			Toast.makeText(mContext, tag, Toast.LENGTH_SHORT).show();
		}

	}
}
