package com.action;

import com.entity.AccountingVoucher;
import com.entity.Income;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.PaginationUtil;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * 
 * 收入费用表
 * @author admin
 *
 */
public class IncoMcostActoin extends ActionSupport implements RequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1169779443950743178L;
	private Map<String, Object> request;
	private AccountingVoucher accountingVoucher;
	private HttpServletRequest req;
	public static Income thisReportData=new Income();
	/**
	 * 收入费用表
	 * @return
	 */
	 
	public String incoMcost()throws Exception{
		
		/**
		 * 财政拨款收入= 本期发生额
		 */
		 String sql = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '财政拨款收入%'";
		 String totalCoun = PaginationUtil.scccc(sql);
		 if(totalCoun  == "null"){
			 totalCoun = "";
			 request.put("totalCoun", totalCoun);
		 }else{
			 String czbksr = totalCoun; 
			 Double czbksrf = Double.parseDouble(czbksr); 
			 DecimalFormat czbksr1 = new DecimalFormat("0.00"); 
			 String s = czbksr1.format(czbksrf); 
			 thisReportData.setCzbksr(s);
			 request.put("totalCoun", s);
		 }
		 
		 /**
		  * 政府性基金收入=一级科目财政拨款收入下的明细科目期末余额
		  */
		 String sql1 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '政府性基金收入%'";
		 String totalCoun1 = PaginationUtil.scccc(sql1);
		 if(totalCoun1  == "null"){
			 totalCoun1 = "";
			 request.put("totalCoun1", totalCoun1);
		 }else{
			 String zfxjjsr = totalCoun1; 
			 Double zfxjjsrf = Double.parseDouble(zfxjjsr); 
			 DecimalFormat zfxjjsr1 = new DecimalFormat("0.00"); 
			 String s1 = zfxjjsr1.format(zfxjjsrf); 
			 thisReportData.setZfjjsr(s1);
			 request.put("totalCoun1", s1);
		 }
		 
		 /**
		  * 事业收入= 本期发生额
		  */
		 String sql2 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '事业收入%'";
		 String totalCoun2 = PaginationUtil.scccc(sql2);
		 if(totalCoun2  == "null"){
			 totalCoun2 = "";
			 request.put("totalCoun2", totalCoun2);
		 }else{
			 String sysr = totalCoun2; 
			 Double sysrf = Double.parseDouble(sysr); 
			 DecimalFormat sysr1 = new DecimalFormat("0.00"); 
			 String s2 = sysr1.format(sysrf); 
			 thisReportData.setSysr(s2);
			 request.put("totalCoun2", s2);
		 }
		 
		 /**
		  * 上级补助收入=本期发生额
		  */
		 String sql3 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '上级补助收入%'";
		 String totalCoun3 = PaginationUtil.scccc(sql3);
		 if(totalCoun3  == "null"){
			 totalCoun3 = "";
			 request.put("totalCoun3", totalCoun3);
		 }else{
			 String sjbzsr = totalCoun3; 
			 Double sjbzsrf = Double.parseDouble(sjbzsr); 
			 DecimalFormat sjbzsr1 = new DecimalFormat("0.00"); 
			 String s3 = sjbzsr1.format(sjbzsrf);
			 thisReportData.setSjfzsr(s3);
			 request.put("totalCoun3", s3);
		 }
		 
		 /**
		  * 附属单位上缴收入=本期发生额
		  */
		 String sql4 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '附属单位上缴收入%'";
		 String totalCoun4 = PaginationUtil.scccc(sql4);
		 if(totalCoun4  == "null"){
			 totalCoun4 = "";
			 request.put("totalCoun4", totalCoun4);
		 }else{
			 String fsdwsjsr = totalCoun4; 
			 Double fsdwsjsrf = Double.parseDouble(fsdwsjsr); 
			 DecimalFormat fsdwsjsr1 = new DecimalFormat("0.00"); 
			 String s4 = fsdwsjsr1.format(fsdwsjsrf);
			 thisReportData.setFsdwsjsr(s4);
			 request.put("totalCoun4", s4);
		 }
		 
		 /**
		  * 经营收入=本期发生额
		  */
		 String sql5 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '经营收入%'";
		 String totalCoun5 = PaginationUtil.scccc(sql5);
		 if(totalCoun5  == "null"){
			 totalCoun5 = "";
			 request.put("totalCoun5", totalCoun5);
		 }else{
			 String jysr = totalCoun5; 
			 Double jysrf = Double.parseDouble(jysr); 
			 DecimalFormat jysr1 = new DecimalFormat("0.00"); 
			 String s5 = jysr1.format(jysrf); 
			 thisReportData.setJysr(s5);
			 request.put("totalCoun5", s5);
		 }
		 
		 /**
		  * 非同级财政拨款收入= 本期发生额
		  */
		 String sql6 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '非同级财政拨款收入%'";
		 String totalCoun6 = PaginationUtil.scccc(sql6);
		 if(totalCoun6  == "null"){
			 totalCoun6 = "";
			 request.put("totalCoun6", totalCoun6);
		 }else{
			 String ftjczbksr = totalCoun6; 
			 Double ftjczbksrf = Double.parseDouble(ftjczbksr); 
			 DecimalFormat ftjczbksr1 = new DecimalFormat("0.00"); 
			 String s6 = ftjczbksr1.format(ftjczbksrf);
		     thisReportData.setFtjczbksr(s6);
			 request.put("totalCoun6", s6);
		 }
		 
		 /**
		  *租金收入=本期发生额
		  */
		 String sq21 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '租金收入%'";
		 String totalCoun21 = PaginationUtil.scccc(sq21);
		 if(totalCoun21  == "null"){
			 totalCoun21 = "";
			 request.put("totalCoun21", totalCoun21);
		 }else{
			 String zjsr = totalCoun21; 
			 Double zjsrf = Double.parseDouble(zjsr); 
			 DecimalFormat zjsr1 = new DecimalFormat("0.00"); 
			 String s21 = zjsr1.format(zjsrf);
			 thisReportData.setZjsr(s21);
			 request.put("totalCoun21", s21);
		 }
		 
		 /**
		  * 投资收益 规则???
		  * （9）投资收益=本期发生额
			【备注：如为投资净损失，以“-”填列】
		  */
		 String sql7 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '投资收益%'";
		 String totalCoun7 = PaginationUtil.scccc(sql7);
		 if(totalCoun7  == "null"){
			 totalCoun7 = "";
			 request.put("totalCoun7", totalCoun7);
		 }else{
			 String tzsy = totalCoun7; 
			 Double tzsyf = Double.parseDouble(tzsy); 
			 DecimalFormat tzsy1 = new DecimalFormat("0.00"); 
			 String s7 = tzsy1.format(tzsyf); 
			 thisReportData.setTzsy(s7);
			 request.put("totalCoun7", s7);
		 }
		 
		 /**
		  * 捐赠收入=本期发生额
		  */
		 String sql8 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '捐赠收入%'";
		 String totalCoun8 = PaginationUtil.scccc(sql8);
		 if(totalCoun8  == "null"){
			 totalCoun8 = "";
			 request.put("totalCoun8", totalCoun8);
		 }else{
			 String jzsr = totalCoun8; 
			 Double jzsrf = Double.parseDouble(jzsr); 
			 DecimalFormat jzsr1 = new DecimalFormat("0.00"); 
			 String s8 = jzsr1.format(jzsrf); 
			 thisReportData.setJzsr(s8);
			 request.put("totalCoun8", s8);
		 }
		 
		 /**
		  * 利息收入=本期发生额
		  */
		 String sql9 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '利息收入%'";
		 String totalCoun9 = PaginationUtil.scccc(sql9);
		 if(totalCoun9  == "null"){
			 totalCoun9 = "";
			 request.put("totalCoun9", totalCoun9);
		 }else{
			 String lxsr = totalCoun9; 
			 Double lxsrf = Double.parseDouble(lxsr); 
			 DecimalFormat lxsrf1 = new DecimalFormat("0.00"); 
			 String s9 = lxsrf1.format(lxsrf); 
			 thisReportData.setLxsr(s9);
			 request.put("totalCoun9", s9);
		 }
		 /**
		  * 其他收入=本期发生额
		  * 
		  */
		 String sql10 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '其他收入%'";
		 String totalCoun10 = PaginationUtil.scccc(sql10);
		 if(totalCoun10  == "null"){
			 totalCoun10 = "";
			 request.put("totalCoun10", totalCoun10);
		 }else{
			 String qtsr = totalCoun10; 
			 Double qtsrf = Double.parseDouble(qtsr); 
			 DecimalFormat qtsr1 = new DecimalFormat("0.00"); 
			 String s10 = qtsr1.format(qtsrf);
			 thisReportData.setQtsr(s10);
			 request.put("totalCoun10", s10);
		 }
		 /**
		  * 本期费用：
		  */
		 /**
		  * 本期费用=（2）至（9）求和
		  */
		 /**
		  * 业务活动费用=本期发生额
		  */
		 String sql12 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '业务活动费用%'";
		 String totalCoun12 = PaginationUtil.scccc(sql12);
		 if(totalCoun12  == "null"){
			 totalCoun12 = "";
			 request.put("totalCoun12", totalCoun12);
		 }else{
			 String ywhdfy = totalCoun12; 
			 Double ywhdfyf = Double.parseDouble(ywhdfy); 
			 DecimalFormat ywhdfy1 = new DecimalFormat("0.00"); 
			 String s12 = ywhdfy1.format(ywhdfyf);
			 thisReportData.setYwhdfy(s12);
			 request.put("totalCoun12", s12);
		 }
		 /**
		  * 单位管理费用=本期发生额
		  */
		 String sql13 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '单位管理费用%'";
		 String totalCoun13 = PaginationUtil.scccc(sql13);
		 if(totalCoun13  == "null"){
			 totalCoun13 = "";
			 request.put("totalCoun13", totalCoun13);
		 }else{
			 String dwglfy = totalCoun13; 
			 Double dwglfyf = Double.parseDouble(dwglfy); 
			 DecimalFormat dwglfy1 = new DecimalFormat("0.00"); 
			 String s13 = dwglfy1.format(dwglfyf); 
			 thisReportData.setDwglfy(s13);
			 request.put("totalCoun13", s13);
		 }
		 /**
		  * 经营费用=本期发生额
		  */
		 String sql14 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '经营费用%'";
		 String totalCoun14 = PaginationUtil.scccc(sql14);
		 if(totalCoun14  == "null"){
			 totalCoun14 = "";
			 request.put("totalCoun14", totalCoun14);
		 }else{
			 String dwglfy = totalCoun14; 
			 Double dwglfyf = Double.parseDouble(dwglfy); 
			 DecimalFormat dwglfy1 = new DecimalFormat("0.00"); 
			 String s14 = dwglfy1.format(dwglfyf); 
			 thisReportData.setJyfy(s14);
			 request.put("totalCoun14", s14);
		 }
		 /**
		  * 资产处置费用=本期发生额
		  */
		 String sql15 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '资产处置费用%'";
		 String totalCoun15 = PaginationUtil.scccc(sql15);
		 if(totalCoun15  == "null"){
			 totalCoun15 = "";
			 request.put("totalCoun15", totalCoun15);
		 }else{
			 String zcczfy = totalCoun15; 
			 Double zcczfyf = Double.parseDouble(zcczfy); 
			 DecimalFormat zcczfy1 = new DecimalFormat("0.00"); 
			 String s15 = zcczfy1.format(zcczfyf);
			 thisReportData.setZcczfy(s15);
			 request.put("totalCoun15", s15);
		 }
		 /**
		  * 上缴上级费用=本期发生额
		  */
		 String sql16 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '上缴上级费用%'";
		 String totalCoun16 = PaginationUtil.scccc(sql16);
		 if(totalCoun16  == "null"){
			 totalCoun16 = "";
			 request.put("totalCoun16", totalCoun16);
		 }else{
			 String sjsjfy = totalCoun16; 
			 Double sjsjfyf = Double.parseDouble(sjsjfy); 
			 DecimalFormat sjsjfy1 = new DecimalFormat("0.00"); 
			 String s16 = sjsjfy1.format(sjsjfyf); 
			 thisReportData.setSjsjfy(s16);
			 request.put("totalCoun16", s16);
		 }
		 /**
		  * 对附属单位补助费用=本期发生额
		  */
		String sql17 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '对附属单位补助费用%'";
		 String totalCoun17 = PaginationUtil.scccc(sql17);
		 if(totalCoun17  == "null"){
			 totalCoun17 = "";
			 request.put("totalCoun17", totalCoun17);
		 }else{
			 String dwsdwbzfy = totalCoun17; 
			 Double dwsdwbzfyf = Double.parseDouble(dwsdwbzfy); 
			 DecimalFormat dwsdwbzfy1 = new DecimalFormat("0.00"); 
			 String s17 = dwsdwbzfy1.format(dwsdwbzfyf); 
			 thisReportData.setDfsdwbzfy(s17);
			 request.put("totalCoun17", s17);
		 }
		 /**
		  * 所得税费用=本期发生额
		  */
		 String sql18 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '所得税费用%'";
		 String totalCoun18 = PaginationUtil.scccc(sql18);
		 if(totalCoun18  == "null"){
			 totalCoun18 = "";
			 request.put("totalCoun18", totalCoun18);
		 }else{
			 String dwsdwbzfy = totalCoun18; 
			 Double dwsdwbzfyf = Double.parseDouble(dwsdwbzfy); 
			 DecimalFormat dwsdwbzfy1 = new DecimalFormat("0.00"); 
			 String s18 = dwsdwbzfy1.format(dwsdwbzfyf);
			 thisReportData.setSdsfy(s18);
			 request.put("totalCoun18", s18);
		 }
		/**
		 *  其他费用=本期发生额
		 */
		 String sql19 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '其他费用%'";
		 String totalCoun19 = PaginationUtil.scccc(sql19);
		 if(totalCoun19  == "null"){
			 totalCoun19 = "";
			 request.put("totalCoun19", totalCoun19);
		 }else{
			 String qtfy = totalCoun19; 
			 Double qtfyf = Double.parseDouble(qtfy); 
			 DecimalFormat qtfy1 = new DecimalFormat("0.00"); 
			 String s19 = qtfy1.format(qtfyf); 
			 thisReportData.setQtfy(s19);
			 request.put("totalCoun19", s19);
		 }
		 
		 /**
		  * 本期收入财政
		  */
		 
		 
		 String sql99 = "select sum( VCreditAmount )-sum(VDebitAmount) from AccountingVoucher where SName like '财政拨款收入%' or  SName like '政府性基金收入%' or  SName like '事业收入%' or  SName like '上级补助收入%' or SName like '附属单位上缴收入%' or SName like '经营收入%' or SName like '非同级财政拨款收入%' or SName like '投资收益%' or SName like '捐赠收入%' or SName like '利息收入%' or SName like '其他收入%' or SName like '租金收入%'";
		 String totalCoun99 = PaginationUtil.scccc(sql99);
		 if(totalCoun99  == "null"){
			 totalCoun99 = "";
			 request.put("totalCoun99", totalCoun99);
		 }else{
			 String bqsrcz = totalCoun99; 
			 Double bqsrczf = Double.parseDouble(bqsrcz); 
			 DecimalFormat bqsrcz1 = new DecimalFormat("0.00"); 
			 String s99 = bqsrcz1.format(bqsrczf); 
			 thisReportData.setBqyssr(s99);
			 request.put("totalCoun99", s99);
		 }
		 /**
		  * 本期费用
		  * 
		  */
		
		 String sql100 = " select sum( VDebitAmount )-sum(VCreditAmount) from AccountingVoucher where SName like '业务活动费用%' or  SName like '单位管理费用%' or  SName like '经营费用%' or  SName like '资产处置费用%' or SName like '上缴上级费用%' or SName like '对附属单位补助费用%' or SName like '所得税费用%' or SName like '其他费用%' ";
		 String totalCoun100 = PaginationUtil.scccc(sql100);
		 if(totalCoun100  == "null"){
			 totalCoun100 = "";
			 request.put("totalCoun100", totalCoun100);
		 }else{
			 String bqfy = totalCoun100; 
			 Double bqfyf = Double.parseDouble(bqfy); 
			 DecimalFormat bqfy1 = new DecimalFormat("0.00"); 
			 String s100 = bqfy1.format(bqfyf); 
			 thisReportData.setBqfy(s100);
			 request.put("totalCoun100", s100);
		 }
		 /**
		  * 
		  * 本期余额=本期收入-本期费用
		  */
		 	String	a = totalCoun99;
		 	String	b = totalCoun100;
			 if(totalCoun99 == "" ||  totalCoun100 == ""){
				 request.put("result", totalCoun99);
			 }else{
				 BigDecimal num1 = new BigDecimal(a);
				 BigDecimal num2 = new BigDecimal(b);
				 BigDecimal result = num1.subtract(num2);
				 String ouft = result.toString();
				 String hhhh = ouft; 
				 Double hhhhf = Double.parseDouble(hhhh); 
				 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
				 String s25 = hhhh1.format(hhhhf);
				 req = ServletActionContext.getRequest();
				 HttpSession session = req.getSession();
				 session.setAttribute("s25", s25);
				 thisReportData.setBqyy(s25);
				 ActionContext.getContext().getSession().put("mmm", s25);
			 }
		return "finbymct";
		
	}
	
	public void exportCashExcel() throws IOException{
		HttpServletResponse response= ServletActionContext.getResponse();
		 String[] titles ={"项目","本月数","本年累计数" };
		 String[] column ={"一、本期收入","(一) 财政拨款收入","其中：政府性基金收入","（二）事业收入","（三）上级补助收入","（四）附属单位上缴收入","（五）经营收入","（六）非同级财政拨款收入","（七）投资收益"
				 ,"（八）捐赠收入","（九）利息收入","（十）租金收入","（十一）其他收入","二、本期费用","（一）业务活动费用","（二）单位管理费用","（三）经营费用","（四）资产处置费用","（五）上缴上级费用","（六）对附属单位补助费用","（七）所得税费用","（八）其他费用","三、本期盈余"};
		String filename = "收入支出.xlsx";
	    OutputStream os = response.getOutputStream();
	    response.reset();
	    response.setHeader("Content-Disposition", 
	      "attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
	    response.setContentType("application/msexcel");
		try {
			XSSFWorkbook wb = new XSSFWorkbook();
		      XSSFCellStyle style = wb.createCellStyle();
		      XSSFFont font = wb.createFont();
		      font.setFontName("楷体");
		      font.setFontHeightInPoints((short) 16);
		      font.setBoldweight((short) 700);
		      style.setFont(font);
		      style.setWrapText(true);
		      style.setAlignment((short) 2);
		      style.setVerticalAlignment((short) 1);
		      style.setBorderBottom((short) 1);
		      style.setBorderLeft((short) 1);
		      style.setBorderTop((short) 1);
		      style.setBorderRight((short) 1);

		      XSSFCellStyle styleCell = wb.createCellStyle();
		      styleCell.setAlignment((short) 2);
		      styleCell.setVerticalAlignment((short) 1);
		      styleCell.setBorderBottom((short) 1);
		      styleCell.setBorderLeft((short) 1);
		      styleCell.setBorderTop((short) 1);
		      styleCell.setBorderRight((short) 1);

		      XSSFCellStyle styleLeftCell = wb.createCellStyle();
		      styleLeftCell.setAlignment((short) 1);
		      styleLeftCell.setVerticalAlignment((short) 1);
		      styleLeftCell.setBorderBottom((short) 1);
		      styleLeftCell.setBorderLeft((short) 1);
		      styleLeftCell.setBorderTop((short) 1);
		      styleLeftCell.setBorderRight((short) 1);

		      XSSFSheet sheet = wb.createSheet("收入支出表");

		      sheet.setDefaultColumnWidth(35);

		      CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 2);
		      sheet.addMergedRegion(callRangeAddress);
		      XSSFRow rowOne = sheet.createRow(0);
		      rowOne.setHeight((short) 800);
		      XSSFCell cel0 = rowOne.createCell(0);
		      cel0.setCellStyle(style);
		      cel0.setCellValue("收入费用表");

		      XSSFRow row = sheet.createRow(1);
		      for (int i = 0; i < titles.length; ++i) {
		        XSSFCell cell = row.createCell(i);
		        cell.setCellStyle(styleCell);

		        cell.setCellValue(titles[i]);
		      }
		      
		    //  Income thisReportData = new Income();
		      //Income reportData =new  Income();
		      for (int j = 0; j < column.length; ++j)
		      {
		        XSSFRow rowContent = null;
		        rowContent = sheet.createRow(j + 2);
		        for (int m = 0; m < titles.length; ++m) {
		          XSSFCell createCell = rowContent.createCell(m);
		          createCell.setCellStyle(styleCell);
		          if (m == 0) {
		            createCell.setCellValue(column[j]);
		            createCell.setCellStyle(styleLeftCell);
		          } else if (m == 1) {
		            if ("一、本期收入".equals(column[j]))
		              createCell.setCellValue(thisReportData.getBqyssr());
		            else if ("(一) 财政拨款收入".equals(column[j]))
		              createCell.setCellValue(thisReportData.getCzbksr());
		            else if ("其中：政府性基金收入".equals(column[j]))
		              createCell.setCellValue(thisReportData.getZfjjsr());
		            else if ("（三）上级补助收入".equals(column[j]))
		              createCell.setCellValue(thisReportData.getSjfzsr());
		            else if ("（四）附属单位上缴收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getFsdwsjsr());
		            else if ("（五）经营收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getJysr());
		            else if ("（六）非同级财政拨款收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getFtjczbksr());
		            else if ("（七）投资收益".equals(column[j]))
			              createCell.setCellValue(thisReportData.getTzsy());
		            else if ("（八）捐赠收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getJzsr());
		            else if ("（九）利息收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getLxsr());
		            else if ("（十）租金收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getZjsr());
		            else if ("（十一）其他收入".equals(column[j]))
			              createCell.setCellValue(thisReportData.getQtsr());
		            else if ("二、本期费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getBqfy());
		            else if ("（一）业务活动费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getYwhdfy());
		            else if ("（二）单位管理费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getDwglfy());
		            else if ("（三）经营费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getJyfy());
		            else if ("（四）资产处置费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getZcczfy());
		            else if ("（五）上缴上级费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getSjsjfy());
		            else if ("（六）对附属单位补助费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getDfsdwbzfy());
		            else if ("（七）所得税费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getSdsfy());
		            else if ("（八）其他费用".equals(column[j]))
			              createCell.setCellValue(thisReportData.getQtfy());
		            else if ("三、本期盈余".equals(column[j]))
			              createCell.setCellValue(thisReportData.getBqyy());
		          }
		          }
		      }
		   wb.write(os);
		      os.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	
	
	
	
	
	public AccountingVoucher getAccountingVoucher() {
		return accountingVoucher;
	}
	public void setAccountingVoucher(AccountingVoucher accountingVoucher) {
		this.accountingVoucher = accountingVoucher;
	}

	



	public HttpServletRequest getReq() {
		return req;
	}









	public void setReq(HttpServletRequest req) {
		this.req = req;
	}




	public Income getThisReportData() {
		return thisReportData;
	}

	public void setThisReportData(Income thisReportData) {
		this.thisReportData = thisReportData;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	

	
}
