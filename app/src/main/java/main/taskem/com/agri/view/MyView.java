package main.taskem.com.agri.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by atul.bhardwaj on 03/06/16.
 */
public class MyView extends SurfaceView {
	private final SurfaceHolder surfaceHolder;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public MyView(Context context) {
		super(context);
		surfaceHolder = getHolder();
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				canvas.drawColor(Color.WHITE);
				canvas.drawCircle(event.getX(), event.getY(), 50, paint);
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		return false;
	}
}