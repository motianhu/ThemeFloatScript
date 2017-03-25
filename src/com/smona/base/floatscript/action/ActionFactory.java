package com.smona.base.floatscript.action;

public class ActionFactory {
	public static IAction buildDownloadAction() {
		return new DownloadAction();
	}

	public static IAction buildDBAction() {
		return new DBAction();
	}

	public static IAction buildUnzipAction() {
		return new UnzipAction();
	}

	public static IAction buildMoveFileAction() {
		return new MoveFileAction();
	}

	public static IAction buildZipAction() {
		return new ZipAction();
	}

	public static IAction buildUploadAction() {
		return new UploadAction();
	}

	public static IAction buildJsonAction() {
		return new JsonAction();
	}

}
