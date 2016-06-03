package main.taskem.com.agri.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import main.taskem.com.agri.fragment.BaseFragment;
import main.taskem.com.agri.fragment.MainFragment;

/**
 * Created by atul.bhardwaj on 29/05/16.
 */
public class FragmentFactory {

	public enum FragmentName {
		MAIN_FRAGMENT, HOME
	}

	FragmentName fragmentName;

	public FragmentFactory(FragmentName fragmentName) {
		this.fragmentName = fragmentName;
	}

	public Fragment getFragment(Bundle bundle) {
		BaseFragment fragment;
		switch (fragmentName) {
			case MAIN_FRAGMENT:
				fragment = new MainFragment();
				break;
			default:
				fragment = new MainFragment();
				break;
		}
		fragment.setArguments(bundle);
		return fragment;
	}
}
