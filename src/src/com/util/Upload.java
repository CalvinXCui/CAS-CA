package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author admin
 *
 */
public class Upload {
	
	/**
     * 上传，返回文件名
     * @param fileName  文件名称
     * @param cdNum 文件夹名称
     * @param  file 文件
     * @return 文件路径名称
     */
	@SuppressWarnings({ "deprecation", "unused" })
	public static String uploadFile(String fileName,String cdNum,File file) {
		 try {   
             InputStream in = new FileInputStream(file);   
             String dir = ServletActionContext.getRequest().getRealPath("UPLOADDIR");  
             File fileLocation = new File(dir); 
             //此处也可以在应用根目录手动建立目标上传目录  
             if(!fileLocation.exists()){
                 boolean isCreated  = fileLocation.mkdir();  
				}else {
					  dir+="/"+cdNum;
				}
             	File file2=new File(dir);
				if (!file2.exists()) {
					file2.mkdir();
				}
             File uploadFile = new File(dir, fileName);   
             OutputStream out = new FileOutputStream(uploadFile);   
             byte[] buffer = new byte[1024 * 1024];   
             int length;   
             while ((length = in.read(buffer)) > 0) {   
                 out.write(buffer, 0, length);   
             }   
             in.close();   
             out.close();  
             System.out.println(dir+"/"+fileName);
             return dir+"/"+fileName;
         } catch (FileNotFoundException ex) {   
             System.out.println("上传失败!");  
             ex.printStackTrace();   
         } catch (IOException ex) {   
             System.out.println("上传失败!");  
             ex.printStackTrace();   
         }   
		return null;
	}

}
