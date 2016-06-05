package main.taskem.com.zimmber.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import main.taskem.com.zimmber.R;
import main.taskem.com.zimmber.database.DBHelper;
import main.taskem.com.zimmber.models.CirclePoint;

/**
 * Created by atul.bhardwaj on 04/06/16.
 * This View is used as container for Circle
 */
public class CircleContainerView extends FrameLayout {
	private static final int CIRCLE_RADIUS = 70;
	// setup initial color
	private int mPaintColor;
	private Handler mHandler;
	// Store circles to draw each time the user touches down
	private List<CirclePoint> mCirclePointList;
	private Context mContext;
	private View mCurrentView;
	private int mLastRadius;
	private int mLongPressedIncrementer;

	public CircleContainerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setupPaint();
	}

	public void clearPointsList() {
		mCirclePointList.clear();
	}

	public void removePointAt(int index) {
		mCirclePointList.remove(index);
	}

	/**
	 * This method initialise view
	 */
	private void setupPaint() {
		mCirclePointList = DBHelper.getInstance(mContext).getAllPointsList();
		if (mCirclePointList == null) {
			mCirclePointList = new ArrayList<>();
		} else {
			for (CirclePoint circlePoint : mCirclePointList) {
				drawCircle(circlePoint);
			}
		}


	}

	/**
	 * This method saves PointList to Database
	 */
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
							CIRCLE_RADIUS / 2, mPaintColor));
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
				mLongPressedIncrementer = 0;

			}
		}
		return gestureDetector.onTouchEvent(event);
	}


	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (mCurrentView != null) {
				mLastRadius += 1 + mLongPressedIncrementer;
				mLongPressedIncrementer += 1;

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

	/**
	 * This method is responsible for drawing circle
	 *
	 * @param circlePoint
	 */
	private void drawCircle(CirclePoint circlePoint) {
		View view = new View(mContext);
		view.setBackground(mContext.getResources().getDrawable(R.drawable.circle));
		int radius = circlePoint.r > 0 ? circlePoint.r : CIRCLE_RADIUS;
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(radius, radius);
		params.leftMargin = circlePoint.x - circlePoint.r / 2;
		params.topMargin = circlePoint.y - circlePoint.r / 2;
		view.setLayoutParams(params);
		GradientDrawable bgShape = (GradientDrawable) view.getBackground();
		bgShape.setColor(circlePoint.color);
		addView(view);
		mCurrentView = view;
	}

	/**
	 * This method change color of Circle
	 *
	 * @param color
	 */
	public void setCircleColor(int color) {
		mPaintColor = color;
	}

}
