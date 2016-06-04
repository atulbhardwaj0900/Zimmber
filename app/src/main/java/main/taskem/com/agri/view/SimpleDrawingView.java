package main.taskem.com.agri.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul.bhardwaj on 04/06/16.
 */
public class SimpleDrawingView extends View {
	// setup initial color
	private final int paintColor = Color.BLACK;
	// defines paint and canvas
	private Paint drawPaint;
	// Store circles to draw each time the user touches down
	private List<Point> circlePoints;
	private Context mContext;

	public SimpleDrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		drawPaint = new Paint();
		setupPaint(); // same as before
		circlePoints = new ArrayList<Point>();
	}

	public SimpleDrawingView(Context context) {
		super(context);
		mContext = context;
		drawPaint = new Paint();
		setupPaint(); // same as before
		circlePoints = new ArrayList<Point>();

	}

	// Draw each circle onto the view
	@Override
	protected void onDraw(Canvas canvas) {
		for (Point p : circlePoints) {
			canvas.drawCircle(p.x, p.y, 50, drawPaint);
		}
	}


//	// Append new circle each time user presses on screen
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getActionMasked()) {
//			case MotionEvent.ACTION_DOWN:
//				float touchX = event.getX();
//				float touchY = event.getY();
//				circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
//				// indicate view should be redrawn
//				postInvalidate();
//		}
//		return true;
//	}
	public void setCircleColor(int circleColor){
		drawPaint.setColor(circleColor);
	}


	private void setupPaint() {
		// same as before
		drawPaint.setStyle(Paint.Style.FILL); // change to fill
		// ...
	}

	final GestureDetector gestureDetector =
			new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
				public void onLongPress(MotionEvent e) {
					Log.e("", "Longpress detected");
					Toast.makeText(mContext, "L", Toast.LENGTH_SHORT).show();
				}

				@Override
				public boolean onDown(MotionEvent event) {
					float touchX = event.getX();
					float touchY = event.getY();
					circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
					// indicate view should be redrawn
					postInvalidate();
					return true;
				}
			});

		public boolean onTouchEvent(MotionEvent event) {
			return gestureDetector.onTouchEvent(event);
		};
}
