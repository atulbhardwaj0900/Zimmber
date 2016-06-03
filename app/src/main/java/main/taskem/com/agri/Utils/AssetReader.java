package main.taskem.com.agri.Utils;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class AssetReader {
	public static String LoadData(String inFile, Context context) {
		String mContents = "";

		try {
			InputStream stream = context.getAssets().open(inFile);

			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			mContents = new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mContents;

	}
}
