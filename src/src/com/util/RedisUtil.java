package com.util;

import redis.clients.jedis.Jedis;

public final class RedisUtil {


	
	 @SuppressWarnings("resource")
	public static void main(String[] args) {
		 Jedis jedis;
		 jedis = new Jedis("172.18.80.9", 10154);
	        //权限认证
	      jedis.auth("zdhtest123");
	      jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
	      System.out.println(jedis.get("name"));//执行结果：xinxin  
	 }
}
