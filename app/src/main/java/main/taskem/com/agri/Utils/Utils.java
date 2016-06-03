package main.taskem.com.agri.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class Utils {
	public static String getDate(String dateStr) {
		TimeZone tz = TimeZone.getTimeZone("Asia/Calcutta");
		Calendar cal = Calendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		sdf.setCalendar(cal);
		try {
			cal.setTime(sdf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date date = cal.getTime();
		return date.toString();
	}
}
