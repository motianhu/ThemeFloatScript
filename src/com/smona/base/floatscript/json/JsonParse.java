package com.smona.base.floatscript.json;

import com.alibaba.fastjson.JSON;

public class JsonParse {
	public static <T> T parseJson(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
}
