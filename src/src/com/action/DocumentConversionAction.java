package com.action;

import com.entity.DocumentConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.service.DocumentConversionService;
import com.util.ReadExcel;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * 名称 :DocumentConversionAction 
 * 作者 :Calvin 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月24日 上午10:45:38 
 * 描述:
 */
@SuppressWarnings("deprecation")
public class DocumentConversionAction extends ActionSupport implements RequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238173792441066723L;
	
	private BaseService baseService;
	
	private DocumentConversionService documentConversionService;
	
	private Map<String, Object> request;
	
	private DocumentConversion documentConversion; 
	
	private List<DocumentConversion> list;
	
	private List<Object[]> listData;
	
	public void save() throws Exception {
		ReadExcel re = new ReadExcel();
		String flie = "C:\\Users\\admin\\Desktop\\2017凭证.xlsx";
		List<List<String>> list = re.read(flie, 0);//忽略前5行
		// 遍历读取结果
		if (list != null) {
			for (int i = 1; i < list.size(); i++) {
				System.out.print("第" + (1+i) + "行"+"\t");
				List<String> cellList = list.get(i);
				DocumentConversion dc = new DocumentConversion();
				dc.setvId(cellList.get(0));
				dc.setdDate(cellList.get(1));
				dc.setdPeriodName(cellList.get(2));
				dc.setDebitAmount(cellList.get(3));
				dc.setCreditAmount(cellList.get(4));
				dc.setdBrokerage(cellList.get(5));
				dc.setdCheckNo(cellList.get(6));
				dc.setdWarehouse(cellList.get(7));
				dc.setdSingleMan(cellList.get(8));
				dc.setSixSubjectName(cellList.get(9));
				dc.setdTopicName(cellList.get(10));
				dc.setdTopicNo(cellList.get(11));
				baseService.saveOrUpdate(dc);
			}
		}
	}
//	http://localhost:8080/zdh_pzgl/documentConversion!documentConversionlist
	public void documentConversionlist()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		listData = documentConversionService.selectDocumentConversion();
		
		String filename ="凭证科目整合导出.xls";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition","attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			HSSFWorkbook  wb = new HSSFWorkbook();
			// 创建Sheet
			HSSFSheet sheet = wb.createSheet("科目列表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(50);  
//			sheet.setDefaultRowHeightInPoints(20);  
			HSSFCellStyle headCellStyle = wb.createCellStyle();
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
			bigTitlecell.setCellValue("科      目      一      览      表");
			// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			HSSFCellStyle headcellstyle = wb.createCellStyle();
			HSSFFont headfont = wb.createFont();
			headcellstyle.setFont(headfont);
			headfont.setFontName("楷体");
			headfont.setFontHeightInPoints((short) 26);//设置字体大小
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			headcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			headcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			bigTitlecell.setCellStyle(headcellstyle);
			
			HSSFRow row = sheet.createRow(1);
			String[] titles = new String[] {"科目编号","科目名称", "方向","课题段名称","课题段代码"};
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
				cell.setCellStyle(headCellStyle);
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
			if (listData!=null && !listData.isEmpty()) {
				for (int j = 0; j < listData.size(); j++) {
					Object[] obj = listData.get(j);
					//科目编号
					String subjectNo=StringUtils.substringBeforeLast(obj[0].toString(),"(");
					String subject=obj[0].toString();
					//科目名称
					String subjectName=subject.substring(subject.indexOf("(") + 1, subject.lastIndexOf(")"));
					// 创建第j+1行
					HSSFRow rowContent = sheet.createRow(j + 2);
					rowContent.setRowStyle(style);
					// 设置每个sheet每一行的宽度,自动,根据需求自行确定
                    sheet.autoSizeColumn(j + 1, true);
					//科目编号
					rowContent.createCell(0).setCellValue(subjectNo);
					//科目名称
					rowContent.createCell(1).setCellValue(subjectName);
					//方向
					rowContent.createCell(2).setCellValue(obj[1].toString());
					//凭证编号
					rowContent.createCell(3).setCellValue(obj[2].toString());
					//课题段名称
					rowContent.createCell(4).setCellValue(obj[3].toString());
					//课题段代码
					rowContent.createCell(5).setCellValue(obj[4].toString());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	http://localhost:8080/zdh_pzgl/documentConversion!selectAccountsAndVouchers
	public void selectAccountsAndVouchers()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		listData = documentConversionService.selectAccountsAndVouchers();
		
		String filename ="凭证科目整合导出.xls";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition","attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			XSSFWorkbook  wb = new XSSFWorkbook();
			// 创建Sheet
			XSSFSheet sheet = wb.createSheet("凭证和科目整合列表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(50);  
//			sheet.setDefaultRowHeightInPoints(20);  
			XSSFCellStyle headCellStyle = wb.createCellStyle();
			// 设置字体样式
			XSSFFont headFont = wb.createFont();
			headFont.setFontName("楷体");
			headFont.setFontHeightInPoints((short) 14);// 设置字体大小
			headFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			// headFont.setItalic(true); //是否使用斜体
			// 设置单元格样式
			headCellStyle.setFont(headFont);
			headCellStyle.setWrapText(true);//设置自动换行    
			headCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			headCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
			
			headCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置前景色  
//			headCellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色  
			headCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND); //纯色填充
			
			headCellStyle.setBorderBottom(XSSFCellStyle.BORDER_DOTTED); //下边框    
			headCellStyle.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);//左边框    
			headCellStyle.setBorderTop(XSSFCellStyle.BORDER_DOTTED);//上边框    
			headCellStyle.setBorderRight(XSSFCellStyle.BORDER_DOTTED);//右边框 
			// 创建一行 (EXCEL标题行)
			XSSFRow row0 = sheet.createRow(0);
			XSSFCell bigTitlecell = row0.createCell(0);
			// 设置单元格内容
			bigTitlecell.setCellValue("科      目      一      览      表");
			// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			XSSFCellStyle headcellstyle = wb.createCellStyle();
			XSSFFont headfont = wb.createFont();
			headcellstyle.setFont(headfont);
			headfont.setFontName("楷体");
			headfont.setFontHeightInPoints((short) 26);//设置字体大小
			headfont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			headcellstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			headcellstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			bigTitlecell.setCellStyle(headcellstyle);
			
			XSSFRow row = sheet.createRow(1);
			String[] titles = new String[] {"凭证编号","日期", "期间名称","借方金额","贷方金额","经手人","制单人","科目编号","科目名称","课题段名称","课题段代码","摘要"};
//			// 设置行级样式
//			row.setRowStyle(headCellStyle);
			//合并单元格
//			Region region1 = new Region(0, (short) 0, 0, (short) 6);//参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
//			CellRangeAddress region1 = new CellRangeAddress(rowNumber, rowNumber, (short) 0, (short) 11);//参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列  
			for (int i = 0; i <titles.length; i++) {
				XSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
				// 添加单元格样式
				cell.setCellStyle(headCellStyle);
			}
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);//设置字体大小
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			style.setWrapText(true);//设置自动换行
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			// EXCEL内容
			if (listData!=null && !listData.isEmpty()) {
				for (int j = 0; j < listData.size(); j++) {
					Object[] obj = listData.get(j);
					
					//科目编号
					String subjectNo=StringUtils.substringBeforeLast(obj[7].toString(),"(");
					String subject=obj[7].toString();
					//科目名称
					String subjectName=subject.substring(subject.indexOf("(") + 1, subject.lastIndexOf(")"));
					// 创建第j+1行
					XSSFRow rowContent = sheet.createRow(j + 2);
					rowContent.setRowStyle(style);
					// 设置每个sheet每一行的宽度,自动,根据需求自行确定
                    sheet.autoSizeColumn(j + 1, true);
					
					//凭证号 
					rowContent.createCell(0).setCellValue(obj[0].toString());
					//日期   
					rowContent.createCell(1).setCellValue(obj[1].toString());
					//期间名称     
					rowContent.createCell(2).setCellValue(obj[2].toString());
					//借方金额 
					rowContent.createCell(3).setCellValue(obj[3].toString());
					//贷方金额        
					rowContent.createCell(4).setCellValue(obj[4].toString());
					//经手人       
					rowContent.createCell(5).setCellValue(obj[5].toString());
					//制单人       
					rowContent.createCell(6).setCellValue(obj[6].toString());
					//科目编号
					rowContent.createCell(7).setCellValue(subjectNo);
					//科目名称
					rowContent.createCell(8).setCellValue(subjectName);
					//课题段名称 
					rowContent.createCell(9).setCellValue(obj[8].toString());
					//课题段代码
					rowContent.createCell(10).setCellValue(obj[9].toString());
					//摘要
					rowContent.createCell(11).setCellValue(obj[10].toString());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串 
     *  
     * @param string 
     * @param str1 
     * @param str2 
     * @return 
     */  
    public static String subString(String str, String strStart, String strEnd) {  
  
        /* 找出指定的2个字符在 该字符串里面的 位置 */  
        int strStartIndex = str.indexOf(strStart);  
        int strEndIndex = str.indexOf(strEnd);  
  
        /* index 为负数 即表示该字符串中 没有该字符 */  
        if (strStartIndex < 0) {  
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";  
        }  
        if (strEndIndex < 0) {  
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";  
        }  
        /* 开始截取 */  
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());  
        return result;  
    }
	
/*---------------------------gettings and  settings ------------------------------------*/
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public DocumentConversion getDocumentConversion() {
		return documentConversion;
	}
	public void setDocumentConversion(DocumentConversion documentConversion) {
		this.documentConversion = documentConversion;
	}
	public List<DocumentConversion> getList() {
		return list;
	}
	public void setList(List<DocumentConversion> list) {
		this.list = list;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public DocumentConversionService getDocumentConversionService() {
		return documentConversionService;
	}
	public void setDocumentConversionService(DocumentConversionService documentConversionService) {
		this.documentConversionService = documentConversionService;
	}
	public List<Object[]> getListData() {
		return listData;
	}
	public void setListData(List<Object[]> listData) {
		this.listData = listData;
	}
}
