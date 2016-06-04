package main.taskem.com.agri.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import main.taskem.com.agri.adapter.JsonArrayAdapter.OnRecyclerItemClick;
import main.taskem.com.agri.controller.Controller;
import main.taskem.com.agri.view.SimpleDrawingView;

/**
 * Created by atul.bhardwaj on 30/05/16.
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements OnRecyclerItemClick<JSONObject> {
	private SimpleDrawingView mFragmentView;

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mFragmentView = new SimpleDrawingView(mBaseActivity);

		return mFragmentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Controller controller = new Controller(getContext());
		controller.loadContent();
	}



	@Override
	public void onRecyclerItemClick(int position, JSONObject object) {

	}

	@Override
	public void onBackPressed() {
		mFragmentView.savePointsList();
		mBaseActivity.finish();
	}
}
