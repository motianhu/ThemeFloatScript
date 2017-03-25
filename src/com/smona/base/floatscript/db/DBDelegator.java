package com.smona.base.floatscript.db;

import java.util.ArrayList;
import java.util.List;

import com.smona.base.floatscript.model.JsonItem;
import com.smona.base.floatscript.model.ThemeInfo;

public class DBDelegator {
	private static DBDelegator sInstance;

	private DBDelegator() {
	};

	public static DBDelegator getInstance() {
		if (sInstance == null) {
			sInstance = new DBDelegator();
		}
		return sInstance;
	}

	public void saveItemToDB(JsonItem items) {

	}

	public List<ThemeInfo> readDBToItem() {
		return new ArrayList<ThemeInfo>();
	}
}
