package com.action;

public class ZZHtest extends AccountAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1072538956783982721L;

    public static void main(String[] args) {

        String A = "你好132#$%#!#!$我的天";
        String[] split = A.split("132");
        for (String arg : split) {
            System.out.println(arg);
        }
    }



}
