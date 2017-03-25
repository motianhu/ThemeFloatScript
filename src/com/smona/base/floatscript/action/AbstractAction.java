package com.smona.base.floatscript.action;

public abstract class AbstractAction implements IAction {
	protected IAction nextAction;

	public void setNextAction(IAction action) {
		nextAction = action;
	}
}
