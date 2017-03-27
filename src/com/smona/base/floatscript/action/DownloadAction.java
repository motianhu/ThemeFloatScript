package com.smona.base.floatscript.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.smona.base.floatscript.db.DBDelegator;
import com.smona.base.floatscript.http.HttpDelegator;
import com.smona.base.floatscript.model.ThemeInfo;
import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Constants;
import com.smona.base.floatscript.util.Logger;

public class DownloadAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("DownloadAction execute");
		String savePath = CommonUtils.rootPath + Constants.DIR_SPLIT
				+ Constants.DOWNLOAD_PATH;
		File saveDir = new File(savePath);
		if (saveDir.exists()) {
			CommonUtils.delete(saveDir);
			CommonUtils.mkdirs(saveDir);
		}
		List<ThemeInfo> themeInfos = DBDelegator.getInstance().readDBToItem();
		if ((themeInfos == null || themeInfos.size() == 0)
				&& (content instanceof ArrayList)) {
			themeInfos = (ArrayList<ThemeInfo>) content;
		}

		for (ThemeInfo themeInfo : themeInfos) {
			boolean success = HttpDelegator.getInstance().download(
					themeInfo.url, savePath, "" + themeInfo.id);
			if (!success) {
				Logger.printReport("download failed: " + themeInfo.url);
			}
		}
		if (nextAction != null) {
			nextAction.execute(savePath);
		}
	}
}
