package com.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * ActivitiAttach
 * 名称 : 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月31日 下午2:14:51 
 * 描述 :
 */
public class ActivitiAttach {

	static class ActivitiAttachSystem {
		private static final String ATTACHMENT_DIRECTORY = "Attachment-Directory";

		public static String getAttachPath() {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String dateSplit = dateFormat.format(new Date());
			System.out.println(File.separator + ATTACHMENT_DIRECTORY + File.separator + dateSplit);
			// new StringBuilder().append(str)
			return File.separator + ATTACHMENT_DIRECTORY + File.separator + dateSplit;

		}
	}

	/**
	 * 获取下载路径
	 * @return
	 */
	public static String getReadPath(HttpServletRequest req) {

		String path =req.getSession().getServletContext().getRealPath("/");
		path = new File(path).getParent();
		return path;
	}   
	public static String getReadPath() {

		String dateSplit = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String path = File.separator + "attachment" + ActivitiAttach.ATTACH_PATH + File.separator + dateSplit; 
		return path;
	}   
	
	/**
	 * 获取下载路径
	 * @return
	 */
	public static String getAttachsPath() {

		String path = System.getProperty("catalina.home")+File.separator
				+"attachment"+File.separator+Calendar.getInstance().get(Calendar.YEAR);
		return path;

	}
	/**
	 * 附件路径
	 */
	public static String ATTACHMENT_PATH = ActivitiAttachSystem.getAttachPath();

	/**
	 * 正文模版路径
	 */
	public static String OFFICE_PATH = File.separator + "other";
	/**
	 * 附件路径
	 */
	public static String ATTACH_PATH = "\\officefile";
}
