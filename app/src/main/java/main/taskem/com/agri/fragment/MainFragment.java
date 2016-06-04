package main.taskem.com.agri.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import main.taskem.com.agri.R;
import main.taskem.com.agri.adapter.JsonArrayAdapter.OnRecyclerItemClick;
import main.taskem.com.agri.adapter.MainHorizontalAdapter;
import main.taskem.com.agri.view.CircleContainerView;

/**
 * Created by atul.bhardwaj on 30/05/16.
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements OnRecyclerItemClick<JSONObject> {

	private CircleContainerView simpleDrawingView;
	List<Integer> colors;
	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		colors  = new ArrayList<Integer>();
		colors.add(android.graphics.Color.RED);
		colors.add(android.graphics.Color.GREEN);
		colors.add(android.graphics.Color.BLUE);
		colors.add(android.graphics.Color.YELLOW);
		colors.add(android.graphics.Color.rgb(255, 200, 0));
		colors.add(android.graphics.Color.MAGENTA);
		View mFragmentView = inflater.inflate(R.layout.main_fragment,container,false);
		simpleDrawingView = (CircleContainerView)mFragmentView.findViewById(R.id.main_fragment);
		RecyclerView recyclerView = (RecyclerView) mFragmentView.findViewById(R.id.circle_bar);
		LinearLayoutManager layoutManager
				= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
		MainHorizontalAdapter mainHorizontalAdapter = new MainHorizontalAdapter(colors){
			@Override
			public void onClick(View v) {
				simpleDrawingView.setCircleColor((Integer) v.getTag());
			}
		};
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(mainHorizontalAdapter);
		return mFragmentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}



	@Override
	public void onRecyclerItemClick(int position, JSONObject object) {

	}

	@Override
	public void onBackPressed() {
		simpleDrawingView.savePointsList();
		mBaseActivity.finish();
	}
}
