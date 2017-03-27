package com.smona.base.floatscript.action;

import java.io.File;

import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Constants;
import com.smona.base.floatscript.util.FileUtils;
import com.smona.base.floatscript.util.Logger;

public class CopyDirAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("CopyDirAction execute " + content);

		executeCopy();

		if (nextAction != null) {
			nextAction.execute("");
		}
	}

	private void executeCopy() {
		String source = CommonUtils.rootPath + Constants.DIR_SPLIT
				+ Constants.UNZIP;
		String copyFile = CommonUtils.rootPath + Constants.DIR_SPLIT
				+ Constants.COPY + Constants.DIR_SPLIT + Constants.CREATE;

		File themeDir = new File(source);
		String[] themes = themeDir.list();
		if (themes == null || themes.length == 0) {
			return;
		}
		for (String theme : themes) {
			FileUtils.copyDir(copyFile, source + Constants.DIR_SPLIT + theme);
		}
	}

}
