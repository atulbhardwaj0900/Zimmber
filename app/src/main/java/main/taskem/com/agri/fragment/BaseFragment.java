package main.taskem.com.agri.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;

import main.taskem.com.agri.activity.BaseActivity;
import main.taskem.com.agri.common.OnBackPressedListener;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseFragment extends Fragment implements
													OnBackPressedListener, OnClickListener {
	protected BaseActivity mBaseActivity;

	@Override
	public void onAttach(Context context) {
		mBaseActivity = (BaseActivity) context;
		mBaseActivity.setOnBackPressedListener(BaseFragment.this);
		super.onAttach(context);
	}

	public BaseFragment() {
	}

}
