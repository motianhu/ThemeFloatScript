package com.smona.base.floatscript.action;

import java.io.File;

import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Constants;
import com.smona.base.floatscript.util.Logger;
import com.smona.base.floatscript.util.ZipFileAction;

public class UnzipAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("UnzipAction execute " + content);

		executeUnzip();

		if (nextAction != null) {
			nextAction.execute("");
		}
	}

	private void executeUnzip() {
		String source = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.DOWNLOAD_PATH;
		String unzip = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.UNZIP;

		mkdirs(unzip);

		File themeDir = new File(source);
		File[] themes = themeDir.listFiles();
		if (themes == null || themes.length == 0) {
			return;
		}
		ZipFileAction action = new ZipFileAction();
		String name;
		for (File theme : themes) {
			try {
				name = theme.getName();
				action.unZip(source + Constants.DIR_SPLIT + name,
						unzip + Constants.DIR_SPLIT + name.substring(0, name.length() - 4));
			} catch (Exception e) {
				Logger.printDetail("UnzipAction executeUnzip theme=" + theme.getName());
				e.printStackTrace();
			}
		}
	}

}
