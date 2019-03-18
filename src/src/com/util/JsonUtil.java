package com.util;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @author 15176
 *
 */
public class JsonUtil {
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static JSONArray toArray(String str){
		JSONArray arr = JSONArray.parseArray(str);
		return arr;
	}
}
