package main.taskem.com.agri.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashSet;

/**
 * Created by atul.bhardwaj on 03/06/16.
 */
public class MyView1 extends View {
	private static final String TAG = "CirclesDrawingView";
	boolean flag = false;
	/** Main bitmap */
	//private Bitmap mBitmap = null;

	//private Rect mMeasuredRect;

	/**
	 * Stores data about single circle
	 */
	private static class CircleArea {
		int radius;
		int centerX;
		int centerY;

		CircleArea(int centerX, int centerY, int radius) {
			this.radius = radius;
			this.centerX = centerX;
			this.centerY = centerY;
		}

		@Override
		public String toString() {
			return "Circle[" + centerX + ", " + centerY + ", " + radius + "]";
		}
	}

	/**
	 * Paint to draw circles
	 */
	private Paint mCirclePaint;
	Paint strokePaint;

	private static final int CIRCLES_LIMIT = 1;

	/**
	 * All available circles
	 */
	private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
	private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);

	/**
	 * Default constructor
	 *
	 * @param ct {@link android.content.Context}
	 */
	public MyView1(final Context ct) {
		super(ct);
		init(ct);
	}

	private void init(final Context ct) {
		// Generate bitmap used for background
		//mBitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.abc_ic_menu_cut_mtrl_alpha);
		mCirclePaint = new Paint();
		mCirclePaint.setColor(Color.argb(100, 50, 205, 50));
		mCirclePaint.setStrokeWidth(40);
		mCirclePaint.setStyle(Paint.Style.FILL);

		strokePaint = new Paint();
		strokePaint.setColor(Color.argb(100, 0, 100, 0));
		strokePaint.setStyle(Paint.Style.STROKE);
		strokePaint.setStrokeWidth(3);
	}

	@Override
	public void onDraw(final Canvas canv) {
		// background bitmap to cover all area
		//canv.drawBitmap(mBitmap, null, mMeasuredRect, null);

		for (CircleArea circle : mCircles) {
			canv.drawCircle(circle.centerX, circle.centerY, circle.radius, mCirclePaint);
			canv.drawCircle(circle.centerX, circle.centerY, circle.radius, strokePaint);
		}
	}

	@Override
	public boolean onTouchEvent(@Nullable final MotionEvent event) {
		boolean handled = false;
		CircleArea touchedCircle;
		int xTouch;
		int yTouch;
		int pointerId;
		int actionIndex = event.getActionIndex();

		// get touch event coordinates and make transparent circle from it
		switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				// it's the first pointer, so clear all existing pointers data
				clearCirclePointer();

				xTouch = (int) event.getX(0);
				yTouch = (int) event.getY(0);

				// check if we've touched inside some circle
				touchedCircle = obtainTouchedCircle(xTouch, yTouch);
				touchedCircle.centerX = xTouch;
				touchedCircle.centerY = yTouch;
				mCirclePointer.put(event.getPointerId(0), touchedCircle);

				invalidate();
				handled = true;
				break;
			case MotionEvent.ACTION_MOVE:
				final int pointerCount = event.getPointerCount();

				for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
					// Some pointer has moved, search it by pointer id
					pointerId = event.getPointerId(actionIndex);

					xTouch = (int) event.getX(actionIndex);
					yTouch = (int) event.getY(actionIndex);

					touchedCircle = mCirclePointer.get(pointerId);

					if (null != touchedCircle) {
						touchedCircle.centerX = xTouch;
						touchedCircle.centerY = yTouch;
					}
				}
				invalidate();
				handled = true;
				break;

			case MotionEvent.ACTION_UP:
				clearCirclePointer();
				invalidate();
				handled = true;
				break;

			case MotionEvent.ACTION_POINTER_UP:
				// not general pointer was up
				pointerId = event.getPointerId(actionIndex);

				mCirclePointer.remove(pointerId);
				invalidate();
				handled = true;
				break;

			case MotionEvent.ACTION_CANCEL:
				handled = true;
				break;

			default:
				// do nothing
				break;
		}

		return super.onTouchEvent(event) || handled;
	}

	/**
	 * Clears all CircleArea - pointer id relations
	 */
	private void clearCirclePointer() {
		mCirclePointer.clear();
	}

	/**
	 * Search and creates new (if needed) circle based on touch area
	 *
	 * @param xTouch int x of touch
	 * @param yTouch int y of touch
	 * @return obtained {@link CircleArea}
	 */
	private CircleArea obtainTouchedCircle(final int xTouch, final int yTouch) {
		CircleArea touchedCircle = getTouchedCircle(xTouch, yTouch);

		if (null == touchedCircle) {
			touchedCircle = new CircleArea(xTouch, yTouch, 50/*mRadiusGenerator.nextInt(RADIUS_LIMIT) + RADIUS_LIMIT*/);

			if (mCircles.size() == CIRCLES_LIMIT) {
			}
			if (flag == false) {
				mCircles.add(touchedCircle);
				flag = true;
			}

		}

		return touchedCircle;
	}

	/**
	 * Determines touched circle
	 *
	 * @param xTouch int x touch coordinate
	 * @param yTouch int y touch coordinate
	 * @return {@link CircleArea} touched circle or null if no circle has been touched
	 */
	private CircleArea getTouchedCircle(final int xTouch, final int yTouch) {
		CircleArea touched = null;

		for (CircleArea circle : mCircles) {
			if ((circle.centerX - xTouch) * (circle.centerX - xTouch) +
					(circle.centerY - yTouch) * (circle.centerY - yTouch) <=
					circle.radius * circle.radius) {
				touched = circle;
				break;
			}
		}

		return touched;
	}
}
