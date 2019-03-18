package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class JsonConvert {
	
	/**
	 * 读取文件
	 * @param filePath
	 * @return
	 */
	 public static String reader(String filePath) {
		 File file = new File(filePath);
	        BufferedReader reader = null;
	        String laststr = "";
	        try {
	            // System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                System.out.println("line " + line + ": " + tempString);
	                laststr = laststr + tempString.getBytes("UTF-8");
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	        return laststr;
	    }
	 
	 public static JSONArray process(StringBuilder txtStr) {
	       JSONObject json = JSONObject.fromObject(txtStr.toString());
	        JSONArray datas = json.getJSONArray("记账凭证");
	        return datas;
	    }
	 
	 public static void main(String[] args) {
	        String filePath = "D:\\QQdownload\\300条凭证-老制度.json";//E:\\wugang\\data\\weiboyi\\wechat.txt
	        StringBuilder txtStr = FileUtils.readFile(filePath,"GBK");
	        if (txtStr != null) {
	            process(txtStr);
	        } else {
	            System.out.println("Read the content is empty!");
	        }
	        System.out.println("--- end ---");
		// 读取nameID.txt文件中的NAMEID字段（key）对应值（value）并存储  
	       /* ArrayList<String> list = new ArrayList<String>();  
	        BufferedReader brname;  
	        try {  
	            brname = new BufferedReader(new FileReader("D:\\QQdownload\\300条凭证-老制度.json"));// 读取NAMEID对应值  
	            String sname = null;  
	            while ((sname = brname.readLine()) != null) {  
	                // System.out.println(sname);  
	                list.add(sname);// 将对应value添加到链表存储  
	            }  
	            brname.close();  
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }  
	        // 读取原始json文件并进行操作和输出  
	        try {  
	            BufferedReader br = new BufferedReader(new FileReader(  
	                    "D:\\QQdownload\\300条凭证-老制度.json"));// 读取原始json文件  
	            BufferedWriter bw = new BufferedWriter(new FileWriter(  
	                    "D:\\QQdownload\\300条凭证-老制度.json"));// 输出新的json文件  
	            String s = null, ws = null;  
	            while ((s = br.readLine()) != null) {  
	                // System.out.println(s);  
	                try {  
	                    JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象  
	                    JSONArray features = dataJson.getJSONArray("features");// 找到features的json数组  
	                    for (int i = 0; i < features.length(); i++) {  
	                        JSONObject info = features.getJSONObject(i);// 获取features数组的第i个json对象  
	                        JSONObject properties = info.getJSONObject("properties");// 找到properties的json对象  
	                        String name = properties.getString("name");// 读取properties对象里的name字段值  
	                        System.out.println(name);  
	                        properties.put("NAMEID", list.get(i));// 添加NAMEID字段  
	                        // properties.append("name", list.get(i));  
	                        System.out.println(properties.getString("NAMEID"));  
	                        properties.remove("ISO");// 删除ISO字段  
	                    }  
	                    ws = dataJson.toString();  
	                    System.out.println(ws);  
	                } catch (JSONException e) {  
	                    // TODO Auto-generated catch block  
	                    e.printStackTrace();  
	                }  
	            }  
	  
	            bw.write(ws);  
	            // bw.newLine();  
	  
	            bw.flush();  
	            br.close();  
	            bw.close();  
	  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  */
	    }
	 
	 

}
