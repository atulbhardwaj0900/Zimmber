package main.taskem.com.agri.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class EventImageView extends ImageView {
	private Context context;

	public EventImageView(Context context) {
		super(context);
		this.context = context;
	}

	public EventImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public EventImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
	}

	public void setImage(String url) {

		Picasso.with(context).load(url).into(this);
	}

}
