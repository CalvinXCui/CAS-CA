package com.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.entity.AccountingSubject;
import com.entity.BudgetSubject;

/**
 * ExportExcelUtils 
 * 名称 : 
 * 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月31日     下午2:14:51 
 * 描述 :导出excel的工具类
 */
@SuppressWarnings("deprecation")
public class ExportExcelUtils {

	/**
	 * excelExport excel导出的公共方法
	 * 
	 * @param response
	 * @param headtitle
	 *            表单的头部标题
	 * @param filename
	 *            导出excel的名称
	 * @param sheetName
	 *            工作簿的名称
	 * @param titles
	 *            列标题
	 * @param fieldMap
	 *            类的英文属性和Excel中的中文列名的对应关系
	 * @throws IOException
	 */
	public static <T> void excelExport(HttpServletResponse response, String headtitle, String filename,
			String sheetName, String[] titles, List<T> list, LinkedHashMap<String, String> fieldMap)
			throws IOException {
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			// 创建Sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);
			HSSFCellStyle headCellStyle = wb.createCellStyle();
			// 设置字体样式
			HSSFFont headFont = wb.createFont();
			headFont.setFontName("楷体");
			headFont.setFontHeightInPoints((short) 14);// 设置字体大小
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			// headFont.setItalic(true); //是否使用斜体
			// 设置单元格样式
			headCellStyle.setFont(headFont);
			headCellStyle.setWrapText(true);// 设置自动换行
			headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			headCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			headCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置前景色
			// headCellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());//
			// 设置背景色
			headCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 纯色填充
			headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED); // 下边框
			headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);// 左边框
			headCellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);// 上边框
			headCellStyle.setBorderRight(HSSFCellStyle.BORDER_DOTTED);// 右边框
			// 创建一行 (EXCEL标题行)
			HSSFRow row = null;
			if (!StringUtils.isEmpty(headtitle)) {
				HSSFRow row0 = sheet.createRow(0);
				HSSFCell bigTitlecell = row0.createCell(0);
				// 设置单元格内容
				bigTitlecell.setCellValue(headtitle);
				// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
				HSSFCellStyle headcellstyle = wb.createCellStyle();
				HSSFFont headfont = wb.createFont();
				headcellstyle.setFont(headfont);
				headfont.setFontName("楷体");
				headfont.setFontHeightInPoints((short) 26);// 设置字体大小
				headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
				headcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
				headcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直居中
				bigTitlecell.setCellStyle(headcellstyle);
				row = sheet.createRow(1);
			} else {
				row = sheet.createRow(0);
			}
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
				// 添加单元格样式
				cell.setCellStyle(headCellStyle);
			}
			HSSFCellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			style.setFont(font);
			style.setWrapText(true);// 设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直居中
			// EXCEL内容
			if (list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					// 创建第j+1行
					HSSFRow rowContent = null;
					if (!StringUtils.isEmpty(headtitle)) {
						rowContent = sheet.createRow(j + 2);
					} else {
						rowContent = sheet.createRow(j + 1);
					}
					rowContent.setRowStyle(style);
					// 未完待续。。。。。。
//					rowContent.createCell(2).setCellValue(budgetVoucher.get(j).getVAbstract());
//					rowContent.createCell(j).setCellValue(list.get(j));
					

				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * excelExport excel导出的公共方法
	 * 
	 * @param response
	 * @param headtitle
	 *            表单的头部标题
	 * @param filename
	 *            导出excel的名称
	 * @param sheetName
	 *            工作簿的名称
	 * @param titles
	 *            列标题
	 * @param fieldMap
	 *            类的英文属性和Excel中的中文列名的对应关系
	 * @throws IOException
	 */
	public static <T> void excelExportSubject(HttpServletResponse response, String headtitle, String filename,
			String sheetName, String[] titles, List<AccountingSubject> list)
			throws IOException {
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			// 创建Sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);
			HSSFCellStyle headCellStyle = wb.createCellStyle();
			// 设置字体样式
			HSSFFont headFont = wb.createFont();
			headFont.setFontName("楷体");
			headFont.setFontHeightInPoints((short) 14);// 设置字体大小
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			// headFont.setItalic(true); //是否使用斜体
			// 设置单元格样式
			headCellStyle.setFont(headFont);
			headCellStyle.setWrapText(true);// 设置自动换行
			headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			headCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			headCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置前景色
			// headCellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());//
			// 设置背景色
			//headCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 纯色填充
			headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED); // 下边框
			headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);// 左边框
			headCellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);// 上边框
			headCellStyle.setBorderRight(HSSFCellStyle.BORDER_DOTTED);// 右边框
			// 创建一行 (EXCEL标题行)
			HSSFRow row = null;
			if (!StringUtils.isEmpty(headtitle)) {
				HSSFRow row0 = sheet.createRow(0);
				HSSFCell bigTitlecell = row0.createCell(0);
				// 设置单元格内容
				bigTitlecell.setCellValue(headtitle);
				// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
				HSSFCellStyle headcellstyle = wb.createCellStyle();
				HSSFFont headfont = wb.createFont();
				headcellstyle.setFont(headfont);
				headfont.setFontName("楷体");
				headfont.setFontHeightInPoints((short) 26);// 设置字体大小
				headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
				headcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
				headcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直居中
				bigTitlecell.setCellStyle(headcellstyle);
				row = sheet.createRow(1);
			} else {
				row = sheet.createRow(0);
			}
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
				// 添加单元格样式
				cell.setCellStyle(headCellStyle);
			}
			HSSFCellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			style.setFont(font);
			style.setWrapText(true);// 设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直居中
			// EXCEL内容
			if (list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					// 创建第j+1行
					HSSFRow rowContent = null;
					if (!StringUtils.isEmpty(headtitle)) {
						rowContent = sheet.createRow(j + 2);
					} else {
						rowContent = sheet.createRow(j + 1);
					}
					rowContent.setRowStyle(style);
					// 未完待续。。。。。。
					rowContent.createCell(0).setCellValue(list.get(j).getSNo());
					rowContent.createCell(1).setCellValue(list.get(j).getSName());
					rowContent.createCell(2).setCellValue(list.get(j).getSLevel());
					rowContent.createCell(3).setCellValue(list.get(j).getSHigherLevelNo());
					rowContent.createCell(4).setCellValue(list.get(j).getSIsLastLevel());
					rowContent.createCell(5).setCellValue(list.get(j).getSType());

				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * excelExport excel导出的公共方法
	 * 
	 * @param response
	 * @param headtitle
	 *            表单的头部标题
	 * @param filename
	 *            导出excel的名称
	 * @param sheetName
	 *            工作簿的名称
	 * @param titles
	 *            列标题
	 * @param fieldMap
	 *            类的英文属性和Excel中的中文列名的对应关系
	 * @throws IOException
	 */
	public static <T> void excelExportSubjects(HttpServletResponse response, String headtitle, String filename,
			String sheetName, String[] titles, List<BudgetSubject> list)
			throws IOException {
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			// 创建Sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);
			HSSFCellStyle headCellStyle = wb.createCellStyle();
			// 设置字体样式
			HSSFFont headFont = wb.createFont();
			headFont.setFontName("楷体");
			headFont.setFontHeightInPoints((short) 14);// 设置字体大小
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			// headFont.setItalic(true); //是否使用斜体
			// 设置单元格样式
			headCellStyle.setFont(headFont);
			headCellStyle.setWrapText(true);// 设置自动换行
			headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			headCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			headCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置前景色
			//headCellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());//
			// 设置背景色
			//headCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 纯色填充
			headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED); // 下边框
			headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);// 左边框
			headCellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);// 上边框
			headCellStyle.setBorderRight(HSSFCellStyle.BORDER_DOTTED);// 右边框
			// 创建一行 (EXCEL标题行)
			HSSFRow row = null;
			if (!StringUtils.isEmpty(headtitle)) {
				HSSFRow row0 = sheet.createRow(0);
				HSSFCell bigTitlecell = row0.createCell(0);
				// 设置单元格内容
				bigTitlecell.setCellValue(headtitle);
				// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
				HSSFCellStyle headcellstyle = wb.createCellStyle();
				HSSFFont headfont = wb.createFont();
				headcellstyle.setFont(headfont);
				headfont.setFontName("楷体");
				headfont.setFontHeightInPoints((short) 26);// 设置字体大小
				headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
				headcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
				headcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直居中
				bigTitlecell.setCellStyle(headcellstyle);
				row = sheet.createRow(1);
			} else {
				row = sheet.createRow(0);
			}
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
				// 添加单元格样式
				cell.setCellStyle(headCellStyle);
			}
			HSSFCellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			style.setFont(font);
			style.setWrapText(true);// 设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直居中
			// EXCEL内容
			if (list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					// 创建第j+1行
					HSSFRow rowContent = null;
					if (!StringUtils.isEmpty(headtitle)) {
						rowContent = sheet.createRow(j + 2);
					} else {
						rowContent = sheet.createRow(j + 1);
					}
					rowContent.setRowStyle(style);
					// 未完待续。。。。。。
					rowContent.createCell(0).setCellValue(list.get(j).getSNo());
					rowContent.createCell(1).setCellValue(list.get(j).getSName());
					rowContent.createCell(2).setCellValue(list.get(j).getSLevel());
					rowContent.createCell(3).setCellValue(list.get(j).getSHigherLevelNo());
					rowContent.createCell(4).setCellValue(list.get(j).getSIsLastLevel());
					rowContent.createCell(5).setCellValue(list.get(j).getSType());

				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public static void export(Map rsMap,HttpServletResponse response){
		
		
	}
}
