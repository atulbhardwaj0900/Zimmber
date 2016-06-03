package main.taskem.com.agri.Utils;


import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import main.taskem.com.agri.models.BodyContent;
import main.taskem.com.agri.models.HeadContent;
/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class JsonParser {
	String content;
	private HeadContent mHeadContent;
	private BodyContent mBodyContent;
	private String mBgImageUrl;

	public String getBgImageUrl() {
		return mBgImageUrl;
	}

	public void setBgImageUrl(String bgImageUrl) {
		this.mBgImageUrl = bgImageUrl;
	}

	public BodyContent getBodyContent() {
		return mBodyContent;
	}


	public JsonParser(String string) {
		content = string;
	}

	public JSONObject getJson() {
		if (content != null) {
			try {
				return new JSONObject(content);
			} catch (JSONException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public void initParsing() {
		JSONObject object = getJson();
		if (object != null) {
			mBgImageUrl = object.optString("public_url");
			String eventHeaderName = object.optString("name");
			if (!TextUtils.isEmpty(eventHeaderName)) {
				mHeadContent = new HeadContent();
				mHeadContent.setHeading(eventHeaderName);
				mHeadContent.setStartDate(object.optString("start_time"));
				mHeadContent.setEndDate(object.optString("end_time"));
				mHeadContent.setAttendingStatus(object.optString("attending_status"));
			}
			JSONObject list = object.optJSONObject("list");
			if (list != null) {
				String name = list.optString("name");
				if (!TextUtils.isEmpty(name)) {
					mBodyContent = new BodyContent();
					mBodyContent.setName(name);
					mBodyContent.setUrl(list.optString("picture_url"));
				}
			}

		}
	}

	public HeadContent getHeadContent() {
		return mHeadContent;
	}

}
