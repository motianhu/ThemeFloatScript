package com.smona.base.floatscript.action;

import java.io.File;

import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Constants;
import com.smona.base.floatscript.util.Logger;
import com.smona.base.floatscript.util.ZipFileAction;

public class ZipAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("ZipAction execute " + content);

		executeZip();

		if (nextAction != null) {
			nextAction.execute("");
		}
	}

	private void executeZip() {
		String source = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.UNZIP;
		String target = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.ZIP;
		File themeDir = new File(source);
		File[] themes = themeDir.listFiles();
		if (themes == null || themes.length == 0) {
			return;
		}
		ZipFileAction action = new ZipFileAction();
		for (File theme : themes) {
			try {
				action.zip(theme.getName(), target + theme.getName());
			} catch (Exception e) {
				Logger.printDetail("ZipAction executeZip theme=" + theme.getName());
				e.printStackTrace();
			}
		}
	}

}