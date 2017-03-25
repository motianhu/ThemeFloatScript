package com.smona.base.floatscript.util;

import java.io.File;

public class CommonUtils {
	public static String rootPath;

	public static void setPath(String path) {
		rootPath = path;
	}

	public static void delete(File file) {
		file.delete();
	}

	public static void mkdirs(File file) {
		file.mkdirs();
	}
}
