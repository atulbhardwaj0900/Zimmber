package main.taskem.com.agri.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import main.taskem.com.agri.R;


/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class MainHorizontalAdapter extends ListArrayAdapter {

	private List<Integer> colorsList;

	public void setColorsList(List<Integer> colorsList) {
		this.colorsList = colorsList;
	}

	private int mSelectedIndex;
	private Context mContext;

	public MainHorizontalAdapter(OnRecyclerItemClick<Integer> onRecyclerItemClick,Context context, List<Integer> colorsList) {
		super(onRecyclerItemClick);
		mContext = context;
		this.colorsList = colorsList;
		setList(colorsList);
	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.circle_selector_layout, parent, false);
		return new CircleVH(itemView);
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		CircleVH vh = (CircleVH) holder;
		GradientDrawable backgroundGradient = (GradientDrawable) vh.circle.getBackground();
		backgroundGradient.setColor(colorsList.get(position));
		vh.container.setTag(position);
		if (mSelectedIndex == position) {
			vh.container
					.setBackground(mContext.getResources().getDrawable(R.drawable.rectange_shape));
		} else {
			vh.container.setBackground(null);
		}
	}

	public void setSelectedIndex(int selectedIndex) {
		mSelectedIndex = selectedIndex;
	}

	public int getSelectedIndex() {
		return mSelectedIndex;
	}

	private class CircleVH extends BaseViewHolder {
		private View circle;
		private View container;

		public CircleVH(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			circle = itemView.findViewById(R.id.circle);
			container = itemView.findViewById(R.id.circle_container);
		}


		@Override
		public void onClick(View view) {
			int position = (Integer) view.getTag();
			int lastSelectedIndex = getSelectedIndex();
			if (position == lastSelectedIndex) {
				return;
			}
			setSelectedIndex(position);
			notifyItemChanged(lastSelectedIndex);
			notifyItemChanged(position);
			super.onClick(view);
		}
	}

}
