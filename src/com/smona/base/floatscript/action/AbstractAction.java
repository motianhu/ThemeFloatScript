package com.smona.base.floatscript.action;

import java.io.File;

public abstract class AbstractAction implements IAction {
	protected IAction nextAction;

	public void setNextAction(IAction action) {
		nextAction = action;
	}

	protected void mkdirs(String dir) {
		File filePath = new File(dir);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
	}
}
