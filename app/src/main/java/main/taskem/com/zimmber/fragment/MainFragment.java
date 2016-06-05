package main.taskem.com.zimmber.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import main.taskem.com.zimmber.R;
import main.taskem.com.zimmber.adapter.ListArrayAdapter.OnRecyclerItemClick;
import main.taskem.com.zimmber.adapter.MainHorizontalAdapter;
import main.taskem.com.zimmber.view.CircleContainerView;

/**
 * Created by atul.bhardwaj on 04/06/16.
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements OnRecyclerItemClick<Integer> {

	private CircleContainerView mCircleContainerView;

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<Integer> colors = new ArrayList<Integer>();
		colors.add(Color.RED);
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.CYAN);
		colors.add(Color.GRAY);
		colors.add(Color.DKGRAY);
		colors.add(Color.rgb(255, 200, 0));
		colors.add(Color.MAGENTA);
		View view = inflater.inflate(R.layout.main_fragment, container, false);
		view.findViewById(R.id.reset_btn).setOnClickListener(this);
		view.findViewById(R.id.undo_btn).setOnClickListener(this);
		mCircleContainerView = (CircleContainerView) view.findViewById(R.id.main_fragment);
		mCircleContainerView.setCircleColor(Color.RED);
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.circle_bar);
		LinearLayoutManager layoutManager =
				new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

		recyclerView.setLayoutManager(layoutManager);
		MainHorizontalAdapter mainHorizontalAdapter =
				new MainHorizontalAdapter(this, mBaseActivity, colors);
		recyclerView.setAdapter(mainHorizontalAdapter);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onBackPressed() {
		mCircleContainerView.savePointsList();
		mBaseActivity.finish();
	}

	@Override
	public void onRecyclerItemClick(int position, Integer object) {
		mCircleContainerView.setCircleColor(object);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.reset_btn: {
				mCircleContainerView.removeAllViews();
				mCircleContainerView.clearPointsList();
				break;
			}
			case R.id.undo_btn: {
				int count = mCircleContainerView.getChildCount();
				if (count > 0) {
					mCircleContainerView.removeViewAt(count - 1);
					mCircleContainerView.removePointAt(count - 1);
				}
				break;
			}
		}
	}
}
