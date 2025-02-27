package com.util;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * WordGenerator
 * 名称 : 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月31日 下午2:14:51 
 * 描述 :
 */
public class WordGenerator {

	private Configuration configuration = null;

	private String directoryForTemplatePath = "classpath:com/template";
	
	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	public WordGenerator() {
		configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setClassForTemplateLoading(this.getClass(), "com/template");
		configuration.setTemplateLoader(new SpringTemplateLoader(resourceLoader, directoryForTemplatePath));
	}
	//导出周报
	public void createXlsx(Map<Object, Object> dataMap ,String ftl_name, OutputStream outputStream) throws Exception {
		// dataMap 要填入模本的数据文件
		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		// 这里我们的模板是放在/com/kanq/platform/supervision/statistics/ftl包下面
		// fzmj.ftl为要装载的模板  抛出异常
		Template t = configuration.getTemplate(ftl_name);
		t.process(dataMap, new OutputStreamWriter(outputStream));
	}
	/*// 将图片转换成BASE64字符串
	public String getImageString() throws Exception {
		// String imagesfile="";
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader
				.getResource("classpath:com/kanq/platform/supervision/statistics/ftl/fzmjtj.png");

		byte[] srcBuffer = null;
		if (resource.exists()) {
			InputStream input = resource.getInputStream();
			srcBuffer = new byte[input.available()];
			IOUtils.read(input, srcBuffer);
			IOUtils.closeQuietly(input);
		}
		return Base64Utils.encodeToString(srcBuffer);
	}*/
}