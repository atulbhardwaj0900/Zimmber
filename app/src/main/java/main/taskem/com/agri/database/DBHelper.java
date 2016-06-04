package main.taskem.com.agri.database;

/**
 * Created by atul.bhardwaj on 21/05/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import main.taskem.com.agri.models.CirclePoint;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "zimmber.db";
	public static final String ZIMMBER_TABLE = "zimmber_table";
	public static final String NOTE_COLUMN_TITLE = "x";
	public static final String NOTE_COLUMN_DETAIL = "y";
	public static final String CIRCLE_RADIUS = "r";
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

	public boolean addPoint(CirclePoint  note) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(NOTE_COLUMN_TITLE, note.x);
		contentValues.put(NOTE_COLUMN_DETAIL, note.y);
		contentValues.put(NOTE_COLUMN_DETAIL, note.r);
		long i = db.insert(ZIMMBER_TABLE, null, contentValues);
		return i != -1;
	}

	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + ZIMMBER_TABLE + " where id=" + id + "", null);
		return res;
	}

	public int numberOfRows() {
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, ZIMMBER_TABLE);
		return numRows;
	}

//	public boolean editNote(Point note) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues contentValues = new ContentValues();
//		Integer id = note.getId();
//		contentValues.put(NOTE_COLUMN_TITLE, note.getTitle());
//		contentValues.put(NOTE_COLUMN_DETAIL, note.getDetail());
//		db.update(ZIMMBER_TABLE, contentValues, "id = ? ", new String[]{Integer.toString(id)});
//		return true;
//	}

	public Integer deleteNote(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(ZIMMBER_TABLE, "id = ? ", new String[]{Integer.toString(id)});
	}

//	public List<Point> getAllNotesList() {
//		List<Point> list = null;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor res = db.rawQuery("select * from " + ZIMMBER_TABLE, null);
//		if (res.moveToFirst()) {
//			list = new ArrayList<Point>();
//			Point note;
//			while (!res.isAfterLast()) {
//				note = new Point();
//				note.setTitle(res.getString(res.getColumnIndex(NOTE_COLUMN_TITLE)));
//				note.setId(res.getInt(res.getColumnIndex(NOTE_COLUMN_ID)));
//				note.setDetail(res.getString(res.getColumnIndex(NOTE_COLUMN_DETAIL)));
//				list.add(note);
//				res.moveToNext();
//			}
//		}
//		return list;
//	}
}
