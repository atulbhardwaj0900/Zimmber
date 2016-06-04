package main.taskem.com.agri.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

import main.taskem.com.agri.R;


/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public abstract class MainHorizontalAdapter extends RecyclerView.Adapter implements View.OnClickListener {

	private List<Integer> colorsList;
	public MainHorizontalAdapter(List<Integer> colorsList){
		this.colorsList = colorsList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.circle_selector_layout, parent, false);
		return new CircleVH(itemView);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		CircleVH vh = (CircleVH)holder;
		GradientDrawable backgroundGradient = (GradientDrawable)vh.circle.getBackground();
			backgroundGradient.setColor(colorsList.get(position));
		vh.circle.setTag(colorsList.get(position));
		vh.circle.setOnClickListener(this);
	}

	@Override
	public int getItemCount() {
		return colorsList.size();
	}

	private class CircleVH extends  RecyclerView.ViewHolder{
		View circle;
		public CircleVH(View itemView) {
			super(itemView);
			circle = itemView.findViewById(R.id.circle);
		}
	}
}
