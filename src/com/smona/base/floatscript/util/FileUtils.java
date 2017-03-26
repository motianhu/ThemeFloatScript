package com.smona.base.floatscript.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileUtils {
	public static void copyDir(String sourcePath, String targetPath) {
		File sourceFile = new File(sourcePath);
		File targetFile = new File(targetPath);
		if (sourceFile.isFile()) {
			copyFile(sourceFile, targetFile);
		} else if (sourceFile.isDirectory()) {
			String desPath = targetPath + Constants.DIR_SPLIT + sourceFile.getName();
			File des = new File(desPath);
			des.mkdir();
			String[] childPathes = sourceFile.list();
			for (String child : childPathes) {
				copyDir(sourceFile.getAbsolutePath() + Constants.DIR_SPLIT + child, desPath);
			}
		}
	}

	/**
	 * 文件拷贝的方法
	 * 
	 * @throws FileNotFoundException
	 */
	private static void copyFile(File sourceFile, File targetFile) {
		try {
			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream out = new FileOutputStream(
					new File(targetFile.getPath() + Constants.DIR_SPLIT + sourceFile.getName()));

			int count = fis.available();
			byte[] data = new byte[count];
			if ((fis.read(data)) != -1) {
				out.write(data); // 复制文件内容
			}
			out.close(); // 关闭输出流
			fis.close(); // 关闭输入流
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
