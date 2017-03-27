package com.smona.base.floatscript.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.smona.base.floatscript.util.FileUtils;
import com.smona.base.floatscript.util.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpDelegator {
	private static HttpDelegator sInstance;

	private OkHttpClient mOkHttpClient;

	private HttpDelegator() {
		mOkHttpClient = new OkHttpClient().newBuilder().build();
	}

	public static HttpDelegator getInstance() {
		if (sInstance == null) {
			sInstance = new HttpDelegator();
		}
		return sInstance;
	}

	public String requestJson() {
		String url = (String) FileUtils.getPropertiesValue("themeUrl",
				"config.properties");
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.printDetail("requestJson failed");
		return null;

	}

	public boolean download(String url, String savePath, String fileName) {
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			byte[] buf = new byte[2048];
			int len = 0;
			if (response.isSuccessful()) {
				InputStream is = null;
				FileOutputStream fos = null;

				try {
					is = response.body().byteStream();
					File file = new File(savePath, fileName + ".gnz");
					fos = new FileOutputStream(file);
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
					}
					fos.flush();
					Logger.printDetail("文件下载成功: " + url);
					return true;
				} catch (Exception e) {
					Logger.printDetail("***文件下载失败***: " + url);
					e.printStackTrace();
				} finally {
					try {
						if (is != null)
							is.close();
					} catch (IOException e) {
					}
					try {
						if (fos != null)
							fos.close();
					} catch (IOException e) {
					}
				}
			}
		} catch (IOException e) {
			Logger.printDetail("++++请求下载失败++++: " + url);
			e.printStackTrace();
		}
		return false;
	}

	public boolean upload(String filePath) {
		File file = new File(filePath);
		RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"),
				file);
		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("file", "head_image", fileBody).build();
		Request request = new Request.Builder().url("http://xxxxx")
				.post(requestBody).build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			response.body().string();
			if (response.isSuccessful()) {
				return true;
			}
		} catch (IOException e) {
			Logger.printDetail("upload IOException " + e);
			e.printStackTrace();
		}
		return false;
	}

}
