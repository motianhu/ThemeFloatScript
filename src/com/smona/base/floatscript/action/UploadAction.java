package com.smona.base.floatscript.action;

import java.io.File;

import com.smona.base.floatscript.http.HttpDelegator;
import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Constants;
import com.smona.base.floatscript.util.Logger;

public class UploadAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("UploadAction execute " + content);

		executeUpload();

		if (nextAction != null) {
			nextAction.execute("");
		}

	}

	private void executeUpload() {
		String source = CommonUtils.rootPath + Constants.DIR_SPLIT + Constants.ZIP;
		File themeDir = new File(source);
		String[] themes = themeDir.list();
		if (themes == null || themes.length == 0) {
			return;
		}

		for (String theme : themes) {
			boolean success = HttpDelegator.getInstance().upload(theme);
			if (!success) {
				Logger.printReport("===Upload failed===:  theme=" + theme);
			}
		}
	}

}
