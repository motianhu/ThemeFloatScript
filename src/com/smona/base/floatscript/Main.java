package com.smona.base.floatscript;

import com.smona.base.floatscript.action.ActionFactory;
import com.smona.base.floatscript.action.IAction;
import com.smona.base.floatscript.util.CommonUtils;
import com.smona.base.floatscript.util.Logger;

public class Main {

	public static void main(String[] args) {
		String encode = System.getProperty("file.encoding");
		Logger.init();
		Logger.printDetail(encode);
		String path = System.getProperty("user.dir");
		Logger.printDetail(path);
		CommonUtils.setPath(path);
		action();
	}

	private static void action() {
		IAction jsonAction = ActionFactory.buildJsonAction();
		IAction dbAction = ActionFactory.buildDBAction();
		IAction downloadAction = ActionFactory.buildDownloadAction();
		IAction unZipAction = ActionFactory.buildUnzipAction();
		IAction moveFileAction = ActionFactory.buildMoveFileAction();
		IAction zipAction = ActionFactory.buildZipAction();
		IAction uploadAction = ActionFactory.buildUploadAction();

		jsonAction.setNextAction(dbAction);
		dbAction.setNextAction(downloadAction);
		downloadAction.setNextAction(unZipAction);
		unZipAction.setNextAction(moveFileAction);
		moveFileAction.setNextAction(zipAction);
		zipAction.setNextAction(uploadAction);

		jsonAction.execute("start");
	}

}
