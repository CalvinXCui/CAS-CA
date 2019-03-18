package com.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author admin
 *
 */
public class TemplateParseUtil {

	@SuppressWarnings("deprecation")
	public static void parse(String templateDir,String templateName,String excelPath,Map<String,Object> data) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8"); 
		cfg.setNumberFormat("0.00");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template template = cfg.getTemplate(templateName,"utf-8");
		OutputStreamWriter writer = null;
		try{
			writer = new OutputStreamWriter(new FileOutputStream(excelPath),"UTF-8");
			template.process(data, writer);
			writer.flush();
		}finally{
			writer.close();
		}	
	}


	@SuppressWarnings("deprecation")
	public static byte[] parse(String templateDir,String templateName,Map<String,Object> data) throws TemplateException, IOException{
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("0.00");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template template = cfg.getTemplate(templateName,"utf-8");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(outStream,"UTF-8");
		template.process(data, out);
		return outStream.toByteArray();
	}
	
	@SuppressWarnings("deprecation")
	public static String parse(String templateStr, Map<String, Object> data)
			throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		cfg.setNumberFormat("#.##");
		//����װ��ģ��
		StringTemplateLoader stringLoader = new StringTemplateLoader();	
		stringLoader.putTemplate("myTemplate", templateStr);	
		cfg.setTemplateLoader(stringLoader);
		//����װ�ص�ģ��
		Template temp = cfg.getTemplate("myTemplate", "utf-8");
		Writer out = new StringWriter();
		temp.process(data, out);
		return out.toString();
	}
}
