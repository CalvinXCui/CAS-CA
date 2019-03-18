package com.util;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
public class GetObjectClass {
	
	public static List<Object> getClass(Object object) throws NoSuchMethodException, SecurityException, Exception{
		List<Object> list=new ArrayList<Object>();
		if(object!=null){
			Class<?> clz = object.getClass();
		    Field[] fields = clz.getDeclaredFields();
		    for(int i=0;i<fields.length;i++){
		    	Field field=fields[i];
		    	String name = field.getName();
		    	if(!"id".equals(name)&&!"serialVersionUID".equals(name)){
			    	list.add(name);
		    	}
		    }
		}
		return list;
	}
	
	// 把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
