package main.taskem.com.agri.database;

/**
 * Created by atul.bhardwaj on 21/05/16.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import main.taskem.com.agri.models.CirclePoint;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "zimmber.db";
	public static final String ZIMMBER_TABLE = "zimmber_table";
	public static final String CIRCLE_X = "x";
	public static final String CIRCLE_Y = "y";
	public static final String CIRCLE_R = "r";
	private static DBHelper mDbHelper;

	public static DBHelper getInstance(Context context) {
		if (mDbHelper == null) {
			mDbHelper = new DBHelper(context);
		}
		return mDbHelper;
	}

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + ZIMMBER_TABLE +
				"(x integer, y integer, r integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + ZIMMBER_TABLE);
		onCreate(db);
	}

	public void savePoints(List<CirclePoint> itemsList) {
		if(itemsList != null && itemsList.size() >0) {

			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(ZIMMBER_TABLE, null, null);
			try {
				db.beginTransaction();
				String sql =
						"Insert into " + ZIMMBER_TABLE + " (" + CIRCLE_X + "," + CIRCLE_Y + "," +
								CIRCLE_R + ") values(?,?,?)";
				SQLiteStatement insert = db.compileStatement(sql);
				db.beginTransaction();

				for (CirclePoint circlePoint : itemsList) {
					insert.bindLong(1, circlePoint.x);
					insert.bindLong(2, circlePoint.y);
					insert.bindLong(3, circlePoint.r);
					insert.execute();
				}
				db.setTransactionSuccessful();

			} catch (Exception ex) {
				Log.e(ZIMMBER_TABLE, ex.getLocalizedMessage());
			} finally {
				db.endTransaction();
			}
		}
	}


	public int numberOfRows() {
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, ZIMMBER_TABLE);
		Log.e("******", numRows+"");
		return numRows;
	}

	public List<CirclePoint> getAllPointsList() {
		//numberOfRows();
		List<CirclePoint> list = null;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + ZIMMBER_TABLE, null);
		if (res.moveToFirst()) {
			list = new ArrayList<CirclePoint>();
			CirclePoint note;
			while (!res.isAfterLast()) {
				note = new CirclePoint();
				note.set(res.getInt(res.getColumnIndex(CIRCLE_X)), res.getInt(res.getColumnIndex(CIRCLE_Y)), res.getInt(res.getColumnIndex(
						CIRCLE_R)));
				list.add(note);
				res.moveToNext();
			}
		}
		return list;
	}
}
