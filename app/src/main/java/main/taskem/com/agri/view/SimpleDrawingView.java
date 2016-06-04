package main.taskem.com.agri.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import main.taskem.com.agri.R;

/**
 * Created by atul.bhardwaj on 04/06/16.
 */
public class SimpleDrawingView extends FrameLayout {
	private static final int CIRCLE_RADIUS = 100;
	// setup initial color
	private final int paintColor = Color.BLACK;
	// defines paint and canvas
	private Handler mHandler;
	// Store circles to draw each time the user touches down
	private List<Point> circlePoints;
	private Context mContext;
	private View mCurrentView;
	private int mLastRadius;
	//private

	public SimpleDrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setupPaint(); // same as before
		circlePoints = new ArrayList<Point>();
	}

	public SimpleDrawingView(Context context) {
		super(context);
		mContext = context;
		setupPaint();
		circlePoints = new ArrayList<Point>();
//		setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//				ViewGroup.LayoutParams.MATCH_PARENT));

	}



	private void setupPaint() {

	}

	final GestureDetector gestureDetector =
			new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
				public void onLongPress(MotionEvent event) {
					if (mHandler == null) {
						mHandler = new Handler();
					}
					mHandler.post(runnable);
				}


				@Override
				public boolean onDown(MotionEvent event) {

					float touchX = event.getX();
					float touchY = event.getY();
					circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
					Point pp = circlePoints.get(circlePoints.size() - 1);
					drawCircle(pp);
					return true;
				}

			}

			);

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (mHandler != null) {
				mHandler.removeCallbacks(runnable);
			}
		}
		return gestureDetector.onTouchEvent(event);
	}


	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (mCurrentView != null) {
				mLastRadius += 2;
				FrameLayout.LayoutParams params =
						new FrameLayout.LayoutParams(mLastRadius, mLastRadius);
				Point pp = circlePoints.get(circlePoints.size() - 1);
				params.leftMargin = pp.x - mLastRadius / 2;
				params.topMargin = pp.y - mLastRadius / 2;
				mCurrentView.setLayoutParams(params);
			}
			mHandler.postDelayed(runnable, 20);
		}
	};

	private void drawCircle(Point pp) {
		View view = new View(mContext);
		view.setBackground(mContext.getResources().getDrawable(R.drawable.circle));
		FrameLayout.LayoutParams params =
				new FrameLayout.LayoutParams(CIRCLE_RADIUS, CIRCLE_RADIUS);
		mLastRadius = CIRCLE_RADIUS;
		params.leftMargin = pp.x - CIRCLE_RADIUS / 2;
		params.topMargin = pp.y - CIRCLE_RADIUS / 2;
		view.setLayoutParams(params);
		addView(view);
		GradientDrawable bgShape = (GradientDrawable) view.getBackground();
		bgShape.setColor(Color.GREEN);
		mCurrentView = view;
	}

}
