package main.taskem.com.agri.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONObject;

import main.taskem.com.agri.R;
import main.taskem.com.agri.adapter.JsonArrayAdapter.OnRecyclerItemClick;
import main.taskem.com.agri.controller.Controller;
import main.taskem.com.agri.models.BodyContent;
import main.taskem.com.agri.models.HeadContent;
import main.taskem.com.agri.view.EventDetailsView;
import main.taskem.com.agri.view.EventHeaderView;
import main.taskem.com.agri.view.EventImageView;

/**
 * Created by atul.bhardwaj on 30/05/16.
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements OnRecyclerItemClick<JSONObject> {
	private LinearLayout mFragmentView;

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mFragmentView =
				(LinearLayout) inflater.inflate(R.layout.main_fragment_layout, container, false);

		return mFragmentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Controller controller = new Controller(getContext());
		controller.loadContent();
		setBackgroundImg(controller.getImage());
		addHeadView(controller.getHeadContent());
		addEventDetailsView(controller.getBodyContent());
	}


	private void addEventDetailsView(BodyContent bodyContent) {
		if (bodyContent != null) {
			EventDetailsView eventDetailsView = new EventDetailsView(mBaseActivity);
			eventDetailsView.setData(bodyContent);
			mFragmentView.addView(eventDetailsView);
		}
	}


	private void setBackgroundImg(String url) {
		if (!TextUtils.isEmpty(url)) {
			EventImageView eventImageView =
					(EventImageView) mBaseActivity.findViewById(R.id.backgroundImageView);
			assert eventImageView != null;
			eventImageView.setImage(url);
		}
	}

	private void addHeadView(HeadContent headContent) {

		EventHeaderView eventHeaderView =
				(EventHeaderView) mFragmentView.findViewById(R.id.headerView);
		if (headContent == null) {
			eventHeaderView.setVisibility(View.GONE);
		} else {
			eventHeaderView.setHeading(headContent.getHeading());
			eventHeaderView.setStartDate(headContent.getStartDate());
			eventHeaderView.setEndDate(headContent.getEndDate());
			eventHeaderView.setAttendingStatus(headContent.getAttendingStatus());
		}
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onRecyclerItemClick(int position, JSONObject object) {

	}
}
