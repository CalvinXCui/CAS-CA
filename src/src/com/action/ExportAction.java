package com.action;

import com.entity.BudgetVoucher;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.FreemarkerUtil;
import com.util.WordGenerator;
import freemarker.template.TemplateException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExportAction
 * 名称 : 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月28日 下午2:18:02 
 * 描述 :导出的action
 */
@SuppressWarnings("deprecation")
public class ExportAction extends ActionSupport implements RequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4978536462085483513L;

	private Map<String, Object> request;

	private BaseService baseService;

	// 财务凭证
	private List<BudgetVoucher> budgetVoucher;
	
	// 预算凭证
	private List<BudgetVoucher> budgetVouchers;

	private static WordGenerator wordGenerator = new WordGenerator();

	private static FreemarkerUtil freemarkerUtil = new FreemarkerUtil();


	/**
	 * 接入量统计数据导出
	 * 
	 * @param response
	 * @param startdata
	 * @param enddata
	 * @throws Exception
	 */
	public void getExportVoucher() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();

		budgetVoucher = baseService.findObjList("from BudgetVoucher");
		Map<Object, Object> accv = new HashMap<Object, Object>();
		accv.put("budgetVoucher", budgetVoucher);
		String ftl_name = "pzgl.ftl";

		response.setContentType("application/msexcel");
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(("凭证管理").getBytes("GBK"), "ISO8859-1") + ".xlsx");
		response.setCharacterEncoding("utf-8");
		OutputStream ouputStream = response.getOutputStream();
		wordGenerator.createXlsx(accv, ftl_name, ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 解析模板生成Excel
	 * 
	 * @param templateDir
	 *            模板目录
	 * @param templateName
	 *            模板名称
	 * @param excelPath
	 *            生成的Excel文件路径
	 * @param data
	 *            数据参数
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("static-access")
	public void getExportVoucher2() throws Exception {

		budgetVoucher = baseService.findObjList("from BudgetVoucher");
		Map<Object, Object> accv = new HashMap<Object, Object>();
		accv.put("budgetVoucher", budgetVoucher);

		String templateDir = "/static/template";
		freemarkerUtil.excleExport(templateDir, "凭证管理.ftl", "凭证管理", accv);

	} 

	public void downExcel() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		budgetVoucher= baseService.findObjList("from BudgetVoucher");
		String filename ="预算凭证.xls";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("预算凭证列表");
			// 创建Sheet
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);
			/*HSSFCellStyle headCellStyle = wb.createCellStyle();
			// 设置字体样式
			HSSFFont headFont = wb.createFont();
			headFont.setFontName("楷体");
			headFont.setFontHeightInPoints((short) 14);// 设置字体大小
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			// headFont.setItalic(true); //是否使用斜体
			// 设置单元格样式
			headCellStyle.setFont(headFont);
			headCellStyle.setWrapText(true);//设置自动换行
			headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
			headCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			
			headCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置前景色  
//			headCellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色  
			headCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); //纯色填充
			
			headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED); //下边框    
			headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);//左边框    
			headCellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);//上边框    
			headCellStyle.setBorderRight(HSSFCellStyle.BORDER_DOTTED);//右边框 
			// 创建一行 (EXCEL标题行)
			HSSFRow row0 = sheet.createRow(0);
			HSSFCell bigTitlecell = row0.createCell(0);
			// 设置单元格内容
			bigTitlecell.setCellValue("预      算      凭      证      一      览      表");*/
			// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			//sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16));
			//HSSFCellStyle headcellstyle = wb.createCellStyle();
			/*HSSFFont headfont = wb.createFont();
			headcellstyle.setFont(headfont);
			headfont.setFontName("楷体");
			headfont.setFontHeightInPoints((short) 26);//设置字体大小
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			headcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			headcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			bigTitlecell.setCellStyle(headcellstyle);*/
			
			HSSFRow row = sheet.createRow(0);
			String[] titles = new String[] {"期间名称", "凭证编号","摘要","借方金额","贷方金额","课题段代吗","课题段名称","六级科目代码","六级科目名称","课题所属部门名称"};
//			// 设置行级样式
//			row.setRowStyle(headCellStyle);
			//合并单元格
//			Region region1 = new Region(0, (short) 0, 0, (short) 6);//参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
//			CellRangeAddress region1 = new CellRangeAddress(rowNumber, rowNumber, (short) 0, (short) 11);//参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列  
			for (int i = 0; i <titles.length; i++) {
				HSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
				// 添加单元格样式
			//	cell.setCellStyle(headCellStyle);
			}
			HSSFCellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);//设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			style.setWrapText(true);//设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			// EXCEL内容
			if (budgetVoucher.size() > 0) {
				for (int j = 0; j < budgetVoucher.size(); j++) {
					// 创建第j+1行
					HSSFRow rowContent = sheet.createRow(j + 1);
					rowContent.setRowStyle(style);
					
					//期间名称
					rowContent.createCell(0).setCellValue(budgetVoucher.get(j).getVDate());
					//凭证编号
					rowContent.createCell(1).setCellValue(budgetVoucher.get(j).getVNo());
					//摘要
					rowContent.createCell(2).setCellValue(budgetVoucher.get(j).getVAbstract());
					//借方金额
					rowContent.createCell(3).setCellValue(budgetVoucher.get(j).getVDebitAmount());
					//贷方金额
					rowContent.createCell(4).setCellValue(budgetVoucher.get(j).getVCreditAmount());
					//课题段代码
					rowContent.createCell(5).setCellValue(budgetVoucher.get(j).getTNo());
					//课题段名称
					rowContent.createCell(6).setCellValue(budgetVoucher.get(j).getTName());
					//六级科目代码
					rowContent.createCell(7).setCellValue(budgetVoucher.get(j).getSNo());
					//六级科目名称
					rowContent.createCell(8).setCellValue(budgetVoucher.get(j).getSName());
					//课题所属部门名称
					rowContent.createCell(9).setCellValue(budgetVoucher.get(j).getTFundsSources());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * excle导出
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String createExcel() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("凭证管理表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("预算凭证一览表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("班级");
		row2.createCell(2).setCellValue("笔试成绩");
		row2.createCell(3).setCellValue("机试成绩");
		// 在sheet里创建第三行
		HSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		// .....省略部分代码
		// 输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		// response.setHeader("Content-disposition", "attachment; filename=凭证管理.xls");
		response.setHeader("Content-disposition", "attachment; filename=" + toUtf8String("凭证管理.xls"));
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
		return null;
	}

	/**
	 * 解决中文乱码
	 * 
	 * @param s
	 * @return
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/*-------------------------gettings  and settings 方法-----------------------------------*/

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public static WordGenerator getWordGenerator() {
		return wordGenerator;
	}

	public static void setWordGenerator(WordGenerator wordGenerator) {
		ExportAction.wordGenerator = wordGenerator;
	}

	public static FreemarkerUtil getFreemarkerUtil() {
		return freemarkerUtil;
	}

	public static void setFreemarkerUtil(FreemarkerUtil freemarkerUtil) {
		ExportAction.freemarkerUtil = freemarkerUtil;
	}

	public List<BudgetVoucher> getBudgetVoucher() {
		return budgetVoucher;
	}

	public void setBudgetVoucher(List<BudgetVoucher> budgetVoucher) {
		this.budgetVoucher = budgetVoucher;
	}

	public List<BudgetVoucher> getBudgetVouchers() {
		return budgetVouchers;
	}

	public void setBudgetVouchers(List<BudgetVoucher> budgetVouchers) {
		this.budgetVouchers = budgetVouchers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
