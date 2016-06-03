package main.taskem.com.agri.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;

import main.taskem.com.agri.activity.BaseActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	protected BaseActivity mBaseActivity;

	@Override
	public void onAttach(Context context) {
		mBaseActivity = (BaseActivity) context;
		super.onAttach(context);
	}

	public BaseFragment() {
	}

}
