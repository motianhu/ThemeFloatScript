package com.smona.base.floatscript.action;

import com.smona.base.floatscript.db.DBDelegator;
import com.smona.base.floatscript.json.JsonParse;
import com.smona.base.floatscript.model.JsonItem;
import com.smona.base.floatscript.util.Logger;

public class DBAction extends AbstractAction {

	@Override
	public void execute(Object content) {
		Logger.printDetail("DBAction execute ");
		JsonItem item = JsonParse.parseJson((String) content, JsonItem.class);
		if (item == null || item.data == null || item.data.size() <= 0) {
			return;
		}
		DBDelegator.getInstance().saveItemToDB(item);
		if (nextAction != null) {
			nextAction.execute("");
		}
	}

}
