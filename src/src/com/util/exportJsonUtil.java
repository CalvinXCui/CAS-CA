package com.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月19日 上午11:23:32
* 描述 :
*/
public class exportJsonUtil {
	
	/**
	 * path：E:/svn/05.Hospital/templatedept_uuid.json
	 */
	public static String exportJson(String path) {
	        BufferedReader reader = null;  
	        String laststr = "";  
	        try {  
	            FileInputStream fileInputStream = new FileInputStream(path);  
	            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);  
	            reader = new BufferedReader(inputStreamReader);  
	            String tempString = null;  
	            while ((tempString = reader.readLine()) != null) {  
	                laststr += tempString;  
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return laststr;  
	}
}
