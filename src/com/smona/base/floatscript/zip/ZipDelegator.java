package com.smona.base.floatscript.zip;

public class ZipDelegator {
	private static ZipDelegator sInstance;

	private ZipDelegator() {
	};

	public static ZipDelegator getInstance() {
		if (sInstance == null) {
			sInstance = new ZipDelegator();
		}
		return sInstance;
	}

	public void unZipTheme(String path) {

	}

	public void zipTheme(String path) {

	}
}
