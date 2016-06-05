package main.taskem.com.zimmber.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.List;

import main.taskem.com.zimmber.adapter.ListArrayAdapter.BaseViewHolder;

/**
 * Created by atul.bhardwaj on 04/06/16.
 */
public class ListArrayAdapter extends RecyclerView.Adapter<BaseViewHolder> {

	private List<Integer> mData;
	private OnRecyclerItemClick<Integer> mOnRecyclerItemClick;

	public ListArrayAdapter() {

	}

	public ListArrayAdapter(OnRecyclerItemClick<Integer> onRecyclerItemClick) {
		mOnRecyclerItemClick = onRecyclerItemClick;
	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		bindDataToView(holder, mData.get(position), position);
	}

	protected void bindDataToView(BaseViewHolder holder, int note, int position) {

	}

	@Override
	public int getItemCount() {
		return mData == null ? 0 : mData.size();
	}

	public void setList(List<Integer> mData) {
		this.mData = mData;
		notifyDataSetChanged();
	}

	public void clear() {
		this.mData = null;
		notifyDataSetChanged();
	}

	public class BaseViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

		public BaseViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {

			mOnRecyclerItemClick
					.onRecyclerItemClick(getLayoutPosition(), mData.get(getLayoutPosition()));
		}
	}

	public interface OnRecyclerItemClick<T> {

		void onRecyclerItemClick(int position, T object);
	}
}
