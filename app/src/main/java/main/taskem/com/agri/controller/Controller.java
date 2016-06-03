package main.taskem.com.agri.controller;


import android.content.Context;

import main.taskem.com.agri.Utils.AssetReader;
import main.taskem.com.agri.Utils.JsonParser;
import main.taskem.com.agri.models.BodyContent;
import main.taskem.com.agri.models.HeadContent;
/**
 * Created by atul.bhardwaj on 30/05/16.
 */
public class Controller {
	private String mBgImageUrl;
	private HeadContent mHeadContent;
	private BodyContent mBodyContent;
	private Context context;

	public Controller(Context context) {
		this.context = context;
	}

	public HeadContent getHeadContent() {
		return mHeadContent;
	}

	public BodyContent getBodyContent() {
		return mBodyContent;
	}

	public String getImage() {
		return mBgImageUrl;
	}

	public BodyContent getContent() {
		return new BodyContent();
	}

	public void loadContent() {
		String content = AssetReader.LoadData("event.json", context);
		JsonParser parser = new JsonParser(content);
		parser.initParsing();
		mHeadContent = parser.getHeadContent();
		mBodyContent = parser.getBodyContent();
		mBgImageUrl = parser.getBgImageUrl();
	}
}
