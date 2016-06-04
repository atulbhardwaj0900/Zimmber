package main.taskem.com.agri.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import main.taskem.com.agri.R;
import main.taskem.com.agri.adapter.JsonArrayAdapter.OnRecyclerItemClick;
import main.taskem.com.agri.adapter.MainHorizontalAdapter;
import main.taskem.com.agri.controller.Controller;
import main.taskem.com.agri.view.SimpleDrawingView;

/**
 * Created by atul.bhardwaj on 30/05/16.
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements OnRecyclerItemClick<JSONObject> {
	private View mFragmentView;
	private SimpleDrawingView simpleDrawingView;
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
		mFragmentView = inflater.inflate(R.layout.content_scrolling,container,false);
		Log.d("color","vol");
		simpleDrawingView = (SimpleDrawingView)mFragmentView.findViewById(R.id.main_fragment);
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
		//		LinearLayout circleBar = (LinearLayout)mFragmentView.findViewById(R.id.circle_bar);
//		for (int i = 0;i<circleBar.getChildCount();i++){
//			View view = circleBar.getChildAt(i);
//			final int color = colors[i] ;
//			GradientDrawable backgroundGradient = (GradientDrawable)view.getBackground();
//			backgroundGradient.setColor(color);
//			view.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					simpleDrawingView.setCircleColor(color);
//					Log.d("color","vol");
//				}
//			});
//		}
		return mFragmentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Controller controller = new Controller(getContext());
		controller.loadContent();
	}


	@Override
	public void onClick(View v) {

	}

	@Override
	public void onRecyclerItemClick(int position, JSONObject object) {

	}
}
