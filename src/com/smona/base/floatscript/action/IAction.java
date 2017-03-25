package com.smona.base.floatscript.action;

public interface IAction {
	void execute(Object object);

	void setNextAction(IAction action);
}
