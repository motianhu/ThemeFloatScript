package com.smona.base.floatscript.action;

import java.io.File;
import java.io.IOException;

import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Constants;
import com.smona.base.floatscript.util.Logger;
import com.smona.base.floatscript.util.ZipFileAction;

public class MoveFileAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("MoveFileAction execute " + content);

		executeMove();

		if (nextAction != null) {
			nextAction.execute("");
		}
	}

	private void executeMove() {
		String source = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.UNZIP;
		String moveFile = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.MOVE;
		File themeDir = new File(source);
		String[] themes = themeDir.list();
		if (themes == null || themes.length == 0) {
			return;
		}
		ZipFileAction action = new ZipFileAction();
		for (String theme : themes) {
			try {
				action.copyFileWithDir(moveFile, source, theme);
			} catch (IOException e) {
				Logger.printReport("&&&&copy failed&&&&  theme=" + theme);
				e.printStackTrace();
			}
		}
	}

}
