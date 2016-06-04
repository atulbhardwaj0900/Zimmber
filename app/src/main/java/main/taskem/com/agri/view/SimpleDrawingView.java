package main.taskem.com.agri.view;

import android.content.Context;
import android.graphics.Color;
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
import main.taskem.com.agri.database.DBHelper;
import main.taskem.com.agri.models.CirclePoint;

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
	private List<CirclePoint> mCirclePointList;
	private Context mContext;
	private View mCurrentView;
	private int mLastRadius;

	public SimpleDrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setupPaint(); // same as before
		mCirclePointList = new ArrayList<>();
	}

	public SimpleDrawingView(Context context) {
		super(context);
		mContext = context;
		mCirclePointList = DBHelper.getInstance(mContext).getAllPointsList();
		if (mCirclePointList == null) {
			mCirclePointList = new ArrayList<>();
		} else {
			setupPaint();
		}

	}

	private void setupPaint() {
		for (CirclePoint circlePoint : mCirclePointList) {
			drawCircle(circlePoint);
		}
	}

	public void savePointsList() {
		 DBHelper.getInstance(mContext).savePoints(mCirclePointList);
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
					mCirclePointList.add(new CirclePoint(Math.round(touchX), Math.round(touchY),
							CIRCLE_RADIUS / 2));
					mLastRadius = CIRCLE_RADIUS;
					CirclePoint pp = mCirclePointList.get(mCirclePointList.size() - 1);
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
				CirclePoint circlePoint = mCirclePointList.get(mCirclePointList.size() - 1);
				params.leftMargin = circlePoint.x - mLastRadius / 2;
				params.topMargin = circlePoint.y - mLastRadius / 2;
				circlePoint.setRadius(mLastRadius);
				mCurrentView.setLayoutParams(params);
			}
			mHandler.postDelayed(runnable, 20);
		}
	};

	private void drawCircle(CirclePoint circlePoint) {
		View view = new View(mContext);
		view.setBackground(mContext.getResources().getDrawable(R.drawable.circle));
		int radius = circlePoint.r > 0 ? circlePoint.r : CIRCLE_RADIUS;
		FrameLayout.LayoutParams params =
				new FrameLayout.LayoutParams(radius, radius);
		params.leftMargin = circlePoint.x - circlePoint.r/2;
		params.topMargin = circlePoint.y - circlePoint.r/2;
		view.setLayoutParams(params);
		addView(view);
		GradientDrawable bgShape = (GradientDrawable) view.getBackground();
		bgShape.setColor(Color.GREEN);
		mCurrentView = view;
	}

}
