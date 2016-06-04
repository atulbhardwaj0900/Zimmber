package main.taskem.com.agri.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import main.taskem.com.agri.R;
import main.taskem.com.agri.common.OnBackPressedListener;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class BaseActivity extends AppCompatActivity {
	protected OnBackPressedListener onBackPressedListener;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_scrolling, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
		this.onBackPressedListener = onBackPressedListener;
	}

	@Override
	public void onBackPressed() {
		if(onBackPressedListener != null) {
			onBackPressedListener.onBackPressed();
			return;
		}
		super.onBackPressed();
	}
}
