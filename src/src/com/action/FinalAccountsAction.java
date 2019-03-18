package com.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月8日 下午3:51:23
* 描述 :
*/
public class FinalAccountsAction  extends ActionSupport implements RequestAware{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3318370440174464682L;
	private Map<String, Object> request;
	
	/**
	 * 
	 * @throws Exception
	 */
	public void exportAccountTable() throws Exception{
		Workbook wb = new XSSFWorkbook();						//创建工作簿
		Sheet sheet = wb.createSheet();							//创建sheet
		/*==========创建大标题==========*/			
		Row row = null;
		Cell cell = null;
		row = sheet.createRow(0);								//创建行
		row.setHeightInPoints(27f);								//设置行高
		cell = row.createCell(0);								//创建单元格
		cell.setCellValue("部门决算相关信息统计表");				//设置内容
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
		CellStyle style = wb.createCellStyle();					//创建样式对象
		Font font = wb.createFont();							//创建字体对象
		font.setFontHeightInPoints((short) 22f);				//设置字体大小
		font.setFontName("宋体");								//设置字体样式
		style.setFont(font);									//设置字体
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);			//设置字体居中
		cell.setCellStyle(style);								//设置单元格样式
		/*==========大标题结束==========*/
		/*==========设置列宽==========*/
		sheet.setColumnWidth(0, 43*256);
		sheet.setColumnWidth(1, 5*256);
		sheet.setColumnWidth(2, 17*256);
		sheet.setColumnWidth(3, 17*256);
		sheet.setColumnWidth(4, 48*256);
		sheet.setColumnWidth(5, 8*256);
		sheet.setColumnWidth(6, 17*256);
		/*==========创建小标题==========*/
		CellStyle style1 = wb.createCellStyle();				//创建居中字体样式对象
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
/*		CellStyle styleColor = wb.createCellStyle();			//创建颜色样式对象
		styleColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
		styleColor.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);*/  
		
		CellStyle styleFrame = wb.createCellStyle();			//边框样式
		styleFrame.setBorderBottom(HSSFCellStyle.BORDER_THIN); 	//下边框    
		styleFrame.setBorderLeft(HSSFCellStyle.BORDER_THIN);	//左边框    
		styleFrame.setBorderTop(HSSFCellStyle.BORDER_THIN);		//上边框    
		styleFrame.setBorderRight(HSSFCellStyle.BORDER_THIN);	//右边框 
		
		row = sheet.createRow(1);								//创建第二行
		cell = row.createCell(0);
		cell.setCellValue("编制单位");
		cell = row.createCell(3);
		cell.setCellValue("2017年度");
		cell.setCellStyle(style1);								//设置样式居中
		cell = row.createCell(6);
		cell.setCellValue("金额单位：元");
		cell.setCellStyle(style1);
		
		row = sheet.createRow(2);								//创建第三行
		cell = row.createCell(0);
		cell.setCellValue("项目");
		cell.setCellStyle(style1);								//设置样式居中
	/*	cell.setCellStyle(styleColor);	*/						//设置灰色填充色
		
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 1));//合并单元格
		cell = row.createCell(1);
		cell.setCellValue("行次");
		/*	cell.setCellStyle(styleColor);	*/		
		
		cell = row.createCell(2);
		cell.setCellValue("预算数");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(3);
		cell.setCellValue("统计数");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("项目");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 5, 5));
		cell = row.createCell(5);
		cell.setCellValue("行次");
		/*	cell.setCellStyle(styleColor);	*/		
		
		cell = row.createCell(6);
		cell.setCellValue("统计数");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		
		row = sheet.createRow(3);							//创建第四行
		cell = row.createCell(0);
		cell.setCellValue("栏次");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue(1);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(3);
		cell.setCellValue(2);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("栏次");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(6);
		cell.setCellValue(3);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		
		row = sheet.createRow(4);							//创建第五行
		cell = row.createCell(0);
		cell.setCellValue("一、“三公”经费支出");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell.setCellStyle(style1);
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(3);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(4);
		cell.setCellValue("二、机关运行经费");
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(22);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		

		row = sheet.createRow(5);							//创建第六行
		cell = row.createCell(0);
		cell.setCellValue("（一）支出合计");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(1);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("（一）行政单位");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(23);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		
		row = sheet.createRow(6);							//创建第七行
		cell = row.createCell(0);
		cell.setCellValue("  1．因公出国（境）费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(3);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("（二）参照公务员法管理事业单位");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(24);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		
		
		row = sheet.createRow(7);							//创建第八行
		cell = row.createCell(0);
		cell.setCellValue("  2．公务用车购置及运行维护费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(4);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(25);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		
		row = sheet.createRow(8);							//创建第九行
		cell = row.createCell(0);
		cell.setCellValue("    （1）公务用车购置费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(5);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("三、国有资产占用情况");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(26);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		cell = row.createCell(6);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		
		row = sheet.createRow(9);							//创建第十行
		cell = row.createCell(0);
		cell.setCellValue("    （2）公务用车运行维护费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(6);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("（一）车辆数合计（辆）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(27);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(10);							//创建第十一行
		cell = row.createCell(0);
		cell.setCellValue("  3．公务接待费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(7);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("  1．部级领导干部用车");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(28);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(11);							//创建第十二行
		cell = row.createCell(0);
		cell.setCellValue("    （1）国内接待费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(8);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("  2．一般公务用车");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(29);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(12);							//创建第十三行
		cell = row.createCell(0);
		cell.setCellValue("         其中：外事接待费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(9);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("  3．一般执法执勤用车");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(30);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(13);							//创建第十四行
		cell = row.createCell(0);
		cell.setCellValue("    （2）国（境）外接待费");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(10);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(4);
		cell.setCellValue("  4．特种专业技术用车");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(31);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(14);							//创建第十五行
		cell = row.createCell(0);
		cell.setCellValue("（二）相关统计数");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(11);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(3);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(4);
		cell.setCellValue("  5．其他用车");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(32);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(15);							//创建第十六行
		cell = row.createCell(0);
		cell.setCellValue("  1．因公出国（境）团组数（个）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(12);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(4);
		cell.setCellValue("（二）单价50万元以上通用设备（台，套）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(33);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(16);							//创建第十七行
		cell = row.createCell(0);
		cell.setCellValue("  2．因公出国（境）人次数（人）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(13);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(4);
		cell.setCellValue("（三）单价100万元以上专用设备（台，套）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(5);
		cell.setCellValue(34);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		row = sheet.createRow(17);							//创建第十八行
		cell = row.createCell(0);
		cell.setCellValue("  3．公务用车购置数（辆）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(14);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(35);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(18);							//创建第十九行
		cell = row.createCell(0);
		cell.setCellValue("  4．公务用车保有量（辆）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(15);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(36);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(19);							//创建第二十行
		cell = row.createCell(0);
		cell.setCellValue("  5．国内公务接待批次（个）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(16);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(37);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(20);							//创建第二十一行
		cell = row.createCell(0);
		cell.setCellValue("     其中：外事接待批次（个）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(17);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(38);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(21);							//创建第二十二行
		cell = row.createCell(0);
		cell.setCellValue("  6．国内公务接待人次（人）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(18);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(39);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(22);							//创建第二十三行
		cell = row.createCell(0);
		cell.setCellValue("     其中：外事接待人次（人）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(19);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(40);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(23);							//创建第二十四行
		cell = row.createCell(0);
		cell.setCellValue("  7．国（境）外公务接待批次（个）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(20);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(41);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		
		row = sheet.createRow(24);							//创建第二十五行
		cell = row.createCell(0);
		cell.setCellValue("  8．国（境）外公务接待人次（人）");
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(1);
		cell.setCellValue(21);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/		
		cell = row.createCell(2);
		cell.setCellValue("一");
		cell.setCellStyle(style1);
		cell = row.createCell(5);
		cell.setCellValue(42);
		cell.setCellStyle(style1);
		/*	cell.setCellStyle(styleColor);	*/	
		
		/*==========小标题结束==========*/
		
		/*==========备注部分==========*/
		/*备注部分样式*/
		sheet.addMergedRegion(new CellRangeAddress(26, 26, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(27, 27, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(28, 28, 0, 6));
		sheet.addMergedRegion(new CellRangeAddress(29, 29, 0, 6));
		CellStyle styleLine = wb.createCellStyle();
		
		/*备注部分内容*/
		row = sheet.createRow(25);							//创建第二十六行
		cell = row.createCell(0);
		cell.setCellValue("注：1．本表反映部门决算中\"三公\"经费、机关运行经费和国有资产占用情况等相关统计指标。");
		
		row = sheet.createRow(26);							//创建第二十七行
		cell = row.createCell(0);
		row.setHeightInPoints(46.25f);
		cell.setCellValue("    2．“三公”经费填列单位使用一般公共预算财政拨款安排的支出，其中：中央单位不包括教学科研人员因公出国（境）费及相关团组和人次，地方单位按照本级部门预算口径填报。预算数填列年初预算数，支出统计数应与财决08表保持衔接。“三公”经费相关统计数是指使用一般公共预算财政拨款负担费用的相关批次、人次及车辆情况。");
		styleLine.setWrapText(true);
		cell.setCellStyle(styleLine);
		
		row = sheet.createRow(27);							//创建第二十八行
		cell = row.createCell(0);
		row.setHeightInPoints(30.75f);
		cell.setCellValue("    3．“机关运行经费”填列行政单位和参照公务员法管理的事业单位使用一般公共预算财政拨款安排的基本支出中的日常公用经费支出，相关数据应与财决07表保持一致。");
		
		row = sheet.createRow(28);							//创建第二十九行
		cell = row.createCell(0);
		row.setHeightInPoints(46.25f);
		cell.setCellValue("    4．“国有资产占用情况”填列单位用各类资金购置的车辆、设备等固定资产数量情况，相关数据应与财决附01表保持一致。");
		
		/*==========备注部分结束==========*/
		
		/*==========输出==========*/
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String("部门决算相关信息统计表.xls".getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");
		wb.write(os);
		os.close();
			
	}

	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
