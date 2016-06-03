package main.taskem.com.agri.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import main.taskem.com.agri.adapter.JsonArrayAdapter.BaseViewHolder;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class JsonArrayAdapter extends RecyclerView.Adapter<BaseViewHolder> {

	private JSONArray mData;
	private OnRecyclerItemClick<JSONObject> mOnRecyclerItemClick;

	public JsonArrayAdapter() {

	}

	public JsonArrayAdapter(OnRecyclerItemClick<JSONObject> onRecyclerItemClick) {
		mOnRecyclerItemClick = onRecyclerItemClick;
	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		bindDataToView(holder, mData.optJSONObject(position), position);
	}

	protected void bindDataToView(BaseViewHolder holder, JSONObject note, int position) {

	}

	@Override
	public int getItemCount() {
		return mData == null ? 0 : mData.length();
	}

	public void setList(JSONArray mData) {
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
					.onRecyclerItemClick(getLayoutPosition(), mData.optJSONObject(getLayoutPosition()));
		}
	}

	public interface OnRecyclerItemClick<T> {

		void onRecyclerItemClick(int position, T object);
	}
}
