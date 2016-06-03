package main.taskem.com.agri.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import main.taskem.com.agri.R;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class ScrollingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scrolling);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}
}
