package com.smona.base.floatscript.action;

import com.smona.base.floatscript.http.HttpDelegator;

public class JsonAction extends AbstractAction {

	@Override
	public void execute(Object path) {
		executeDownload();
	}

	private void executeDownload() {
		String jsonContent = HttpDelegator.getInstance().requestJson();
		if (jsonContent == null) {
			return;
		} else {
			if (nextAction != null) {
				nextAction.execute(jsonContent);
			}
		}
	}

}
