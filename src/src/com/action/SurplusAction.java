package com.action;

import com.entity.BudgetVoucher;
import com.entity.SurplusReport;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/***
 * 预算结转结余变动表 贷减借
 * @author 15176
 *
 */
public class SurplusAction extends ActionSupport implements RequestAware{
	private BaseService baseService;
	
	private List<BudgetVoucher> list;
	
	private Map<String, Object> request;
	
	
	public String surplusReport() throws Exception{
		Date date=new Date();
		String obj="thisYear";
		SurplusReport surplusReportData = this.getSurplusReportData(null,date);
		request.put("surplus", surplusReportData);
		SurplusReport thisData = this.getSurplusReportData(obj,date);
		request.put("thisSurplus", thisData);
		return "surplusReport";
	}
	
	
	/**
	 * 获取报表数据
	 * @param obj
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public SurplusReport getSurplusReportData(String obj,Date date) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		int year=Integer.parseInt(sdf.format(date))-1;
		if("thisYear".equals(obj)){
			year=Integer.parseInt(sdf.format(date));
		}
		SurplusReport surplus=new SurplusReport();
		//财政拨款结转结余=财政拨款结转+财政拨款结余
		List<BudgetVoucher> czbkjzjyList=new ArrayList<BudgetVoucher>();
		String hql="from BudgetVoucher where SName like '财政拨款结转%' and VDate like '"+year+"%'";
		czbkjzjyList = baseService.findObjList(hql);
		hql="from BudgetVoucher where SName like '财政拨款结余%' and VDate like '"+year+"%'";
		czbkjzjyList.addAll(baseService.findObjList(hql));
		double czbkjzjy=0.00;
		if(czbkjzjyList!=null && czbkjzjyList.size()>0){
			czbkjzjy=this.getTotalData(czbkjzjyList, null);
		}
		surplus.setCzbkjzjyData(String.valueOf(czbkjzjy));
		
		//其他资金结转结余=非财政拨款结转+非财政拨款结余+经营结余+专用结余
		List<BudgetVoucher> qtzjjzjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '非财政拨款结转%' and VDate like '"+year+"%'";
		qtzjjzjyList = baseService.findObjList(hql);
		hql="from BudgetVoucher where SName like '非财政拨款结余%' and VDate like '"+year+"%'";
		qtzjjzjyList.addAll(baseService.findObjList(hql));
		hql="from BudgetVoucher where SName like '经营结余%' and VDate like '"+year+"%'";
		qtzjjzjyList.addAll(baseService.findObjList(hql));
		hql="from BudgetVoucher where SName like '专用结余%' and VDate like '"+year+"%'";
		qtzjjzjyList.addAll(baseService.findObjList(hql));
		double qtzjjzjy=0.00;
		if(qtzjjzjyList!=null && qtzjjzjyList.size()>0){
			qtzjjzjy= this.getTotalData(qtzjjzjyList, null);
		}
		surplus.setQtzjjzjyData(String.valueOf(qtzjjzjy));
		
        //年初余额--财政拨款结转结余=财政拨款结转——年初余额调整+财政拨款结余——年初余额调整
		List<BudgetVoucher> ncyeczbkjzjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转-年初余额调整%' and VDate like '"+year+"%'";
		ncyeczbkjzjyList = baseService.findObjList(hql);
		hql="from BudgetVoucher where SName like '财政拨款结余—年初余额调整%' and VDate like '"+year+"%'";
		ncyeczbkjzjyList.addAll(baseService.findObjList(hql));
		double ncyeczbkjzjy=0.00;
		if(ncyeczbkjzjyList!=null && ncyeczbkjzjyList.size()>0){
			ncyeczbkjzjy=this.getTotalData(ncyeczbkjzjyList, null);
		}
		surplus.setNcyeczbkjzjyData(String.valueOf(ncyeczbkjzjy));
		
		//年初余额--其他资金结转结余=非财政拨款结转——年初余额调整+非财政拨款结余——年初余额调整
		List<BudgetVoucher> ncyeqtzjjzjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转-年初余额调整%' and VDate like '"+year+"%'";
		ncyeqtzjjzjyList = baseService.findObjList(hql);
		hql="from BudgetVoucher where SName like '财政拨款结余—年初余额调整%' and VDate like '"+year+"%'";
		ncyeqtzjjzjyList.addAll(baseService.findObjList(hql));
		double ncyeqtzjjzjy=0.00;
		if(ncyeqtzjjzjyList!=null && ncyeqtzjjzjyList.size()>0){
			ncyeqtzjjzjy=this.getTotalData(ncyeqtzjjzjyList, null);
		}
		surplus.setNcyeqtzjjzjyData(String.valueOf(ncyeqtzjjzjy));
		
        //本年收支差额=财政拨款结转——本年收支结转
		List<BudgetVoucher> fbnszceList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转—本年收支结转%' and VDate like '"+year+"%'";
		fbnszceList = baseService.findObjList(hql);
		double fbnszce=0.00;
		if(fbnszceList!=null && fbnszceList.size()>0){
			fbnszce=this.getTotalData(fbnszceList, null);
		}
		surplus.setFbnszceData(String.valueOf(fbnszce));
		
		
		//归集调入=财政拨款结转——归集调入 本期发生额
		List<BudgetVoucher> fgjdrList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转—归集调入本期发生额%' and VDate like '"+year+"%'";
		fgjdrList = baseService.findObjList(hql);
		double fgjdr=0.00;
		if(fgjdrList!=null && fgjdrList.size()>0){
			fgjdr= this.getTotalData(fgjdrList, null);
		}
		surplus.setFgjdrData(String.valueOf(fgjdr));
		
		//归集上缴或调出=财政拨款结转——归集上缴+财政拨款结余——归集上缴 – 财政拨款结转——归集调出
		List<BudgetVoucher> fgjsjhdcList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转—归集上缴%' and VDate like '"+year+"%'";
		fgjsjhdcList = baseService.findObjList(hql);
		hql="from BudgetVoucher where SName like '财政拨款结余—归集上缴%' and VDate like '"+year+"%'";
		fgjsjhdcList.addAll(baseService.findObjList(hql));
		
		List<BudgetVoucher> gjsjList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转—归集调出%' and VDate like '"+year+"%'";
		gjsjList = baseService.findObjList(hql);
		double gjsj=0.00;
		if(fgjsjhdcList!=null && fgjsjhdcList.size()>0){
			gjsj=this.getTotalData(fgjsjhdcList, null);
		}
		double fgjsj=0.00;
		if(gjsjList!=null && gjsjList.size()>0){
			fgjsj=this.getTotalData(gjsjList, null);
		}
		gjsj=this.sub(fgjsj,gjsj);
		surplus.setFgjsjData(String.valueOf(gjsj));
		
		//本年收支差额=非财政拨款结转——本年收支结转+其他结余+经营结余
		List<BudgetVoucher> fczbkzjList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转—归集上缴+财政拨款结余%' and VDate like '"+year+"%'";
		fczbkzjList = baseService.findObjList(hql);
		double fczbkjz=0.00;
		if(fczbkzjList!=null && fczbkzjList.size()>0){
			fczbkjz=this.getTotalData(fczbkzjList, null);
		}
		
		List<BudgetVoucher> qtjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '其他结余%' and VDate like '"+year+"%'";
		qtjyList = baseService.findObjList(hql);
		double qtjy=0.00;
		if(qtjyList!=null && qtjyList.size()>0){
			qtjy=this.getTotalData(qtjyList, null);
		}
		
		List<BudgetVoucher> jyjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '经营结余%' and VDate like '"+year+"%'";
		jyjyList = baseService.findObjList(hql);
		double jyjy=0.00;
		if(jyjyList!=null && jyjyList.size()>0){
			jyjy=this.getTotalData(jyjyList, null);
		}
		fczbkjz=this.add(fczbkjz, qtjy);
		fczbkjz=this.add(fczbkjz, jyjy);
		surplus.setFczbkjzData(String.valueOf(fczbkjz));
		
		
		//缴回资金=非财政拨款结转——缴回资金【-号填列】
		List<BudgetVoucher> jhzjList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '非财政拨款结转—缴回资金%' and VDate like '"+year+"%'";
		jhzjList = baseService.findObjList(hql);
		double jhzj=0.00;
		if(jhzjList!=null && jhzjList.size()>0){
			jhzj=this.getTotalData(jhzjList, null);
		}
		surplus.setJhzjData(String.valueOf(jhzj));
		
		//使用专用结余=专用结余 一级科目本期发生额
		List<BudgetVoucher> syzyjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '专用结余%' and VDate like '"+year+"%'";
		syzyjyList = baseService.findObjList(hql);
		double syzyjy=0.00;
		if(syzyjyList!=null && syzyjyList.size()>0){
			syzyjy= this.getTotalData(syzyjyList, null);
		}
		surplus.setSyzyjyData(String.valueOf(syzyjy));
		
		//支付所得税=非财政拨款结余——其他应交税费——所得税 借方
		List<BudgetVoucher> zfsdsList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '非财政拨款结余—其他应交税费-所得税%' and VDate like '"+year+"%'";
		zfsdsList = baseService.findObjList(hql);
		double zfsds=0.00;
		if(zfsdsList!=null && zfsdsList.size()>0){
			String type="jf";
			zfsds= this.getTotalData(zfsdsList, type);
		}
		surplus.setZfsdsData(String.valueOf(zfsds));
		
		//财政拨款结转=该科目期末余额
		List<BudgetVoucher> czbkjzList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结转%' and VDate like '"+year+"%'";
		czbkjzList = baseService.findObjList(hql);
		double czbkjz=0.00;
		if(czbkjzList!=null && czbkjzList.size()>0){
			czbkjz= this.getTotalData(czbkjzList, null);
		}
		surplus.setCzbkjzData(String.valueOf(czbkjz));
		
		//财政拨款结余=该科目期末余额
		List<BudgetVoucher> czbkjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '财政拨款结余%' and VDate like '"+year+"%'";
		czbkjyList = baseService.findObjList(hql);
		double czbkjy=0.00;
		if(czbkjyList!=null && czbkjyList.size()>0){
			czbkjy= this.getTotalData(czbkjyList, null);
		}
		surplus.setCzbkjyData(String.valueOf(czbkjy));
		
		
		//非财政拨款结转=该科目期末余额
		List<BudgetVoucher> fczbkList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '非财政拨款结转%' and VDate like '"+year+"%'";
		fczbkList = baseService.findObjList(hql);
		double fczbk=0.00;
		if(fczbkList!=null && fczbkList.size()>0){
			fczbk= this.getTotalData(fczbkList, null);
		}
		surplus.setFczbkData(String.valueOf(fczbk));
		
		//非财政拨款结余=该科目期末余额
		List<BudgetVoucher> fczbkjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '非财政拨款结转%' and VDate like '"+year+"%'";
		fczbkjyList = baseService.findObjList(hql);
		double fczbkjy=0.00;
		if(fczbkjyList!=null && fczbkjyList.size()>0){
			fczbkjy= this.getTotalData(fczbkjyList, null);
		}
		surplus.setFczbkjyData(String.valueOf(fczbkjy));
		
		//专用结余=该科目期末余额
		List<BudgetVoucher> zyjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '专用结余%' and VDate like '"+year+"%'";
		zyjyList = baseService.findObjList(hql);
		double zyjy=0.00;
		if(zyjyList!=null && zyjyList.size()>0){
			zyjy= this.getTotalData(zyjyList, null);
		}
		surplus.setZyjyData(String.valueOf(zyjy));
		
		//经营结余=该科目期末余额
		List<BudgetVoucher> tjyjyList=new ArrayList<BudgetVoucher>();
		hql="from BudgetVoucher where SName like '经营结余%' and VDate like '"+year+"%'";
		tjyjyList = baseService.findObjList(hql);
		double tjyjy=0.00;
		if(tjyjyList!=null && tjyjyList.size()>0){
			tjyjy= this.getTotalData(tjyjyList, null);
		}
		surplus.setTjyjyData(String.valueOf(tjyjy));
		//surplus.setCreateTime(sdf.format(date));
		return surplus;
		
	}
	
	
	
	
	
	
	/**
	 * 导出excel
	 * @throws Exception
	 */
	public void exportAssetExcel() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		String[] titles={"项目","本年数","上年数"};
		String[] column={"一、年初预算结转结余","一（一）财政拨款结转结余","一（二）其他资金结转结余","二、年初余额调整（减少以“-”号填列）","二（一）财政拨款结转结余","二（二）其他资金结转结余","三、本年变动金额（减少以“-”号填列）","（一）财政拨款结转结余","一1.本年收支差额","2.归集调入","3.归集上缴或调出","（二）其他资金结转结余","二1.本年收支差额","2.缴回资金","3.使用专用结余","4.支付所得税","四、年末预算结转结余","（一）财政拨款结转结余","1.财政拨款结转","2.财政拨款结余","（二）其他资金结转结余","1.非财政拨款结转","2.非财政拨款结余","3.专用结余","4.经营结余（如有余额，以“-”号填列）"};
		String filename ="预算结转结余变动表.xlsx";
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
			font.setFontHeightInPoints((short) 16);//设置字体大小
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			style.setWrapText(true);//设置自动换行
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			style.setBorderTop(XSSFCellStyle.BORDER_THIN);
			style.setBorderRight(XSSFCellStyle.BORDER_THIN);
			
			//设置单元格样式
			XSSFCellStyle styleCell = wb.createCellStyle();
			styleCell.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			styleCell.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			styleCell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			styleCell.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			styleCell.setBorderTop(XSSFCellStyle.BORDER_THIN);
			styleCell.setBorderRight(XSSFCellStyle.BORDER_THIN);
			
			// 创建Sheet
			XSSFSheet sheet = wb.createSheet("预算结转结余变动表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(35);
			
			CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,2);
			sheet.addMergedRegion(callRangeAddress);
			XSSFRow rowOne = sheet.createRow(0);
			rowOne.setHeight((short) 800);
			XSSFCell cel0 = rowOne.createCell(0);
			cel0.setCellStyle(style);
			cel0.setCellValue("预算结转结余变动表");
			
			XSSFRow row = sheet.createRow(1);
			for (int i = 0; i <titles.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(styleCell);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
			}
			
			
			Date date=new Date();
			String obj="thisYear";
			SurplusReport surplusReportData = this.getSurplusReportData(null,date);
			SurplusReport thisData = this.getSurplusReportData(obj,date);
			
			
			
			// EXCEL内容
			for (int j = 0; j < column.length; j++) {
				// 创建第j+1行
				XSSFRow rowContent = null;
				rowContent = sheet.createRow(j + 2);
				for(int m=0;m<titles.length;m++){
					XSSFCell createCell = rowContent.createCell(m);
					createCell.setCellStyle(styleCell);
					if(m==0){
						createCell.setCellValue(column[j]);
					}else if(m==1){
						if("一、年初预算结转结余".equals(column[j])){
							createCell.setCellValue("");
						}else if("一（一）财政拨款结转结余".equals(column[j])){
							createCell.setCellValue(thisData.getCzbkjzjyData());
						}else if("一（二）其他资金结转结余".equals(column[j])){
							createCell.setCellValue(thisData.getQtzjjzjyData());
						}else if("二、年初余额调整（减少以“-”号填列）".equals(column[j])){
							createCell.setCellValue("");
						}else if("二（一）财政拨款结转结余".equals(column[j])){
							createCell.setCellValue(thisData.getNcyeczbkjzjyData());
						}else if("二（二）其他资金结转结余".equals(column[j])){
							createCell.setCellValue(thisData.getNcyeqtzjjzjyData());
						}else if("三、本年变动金额（减少以“-”号填列）".equals(column[j])){
							createCell.setCellValue("");
						}else if("（一）财政拨款结转结余".equals(column[j])){
							createCell.setCellValue("");
						}else if("一1.本年收支差额".equals(column[j])){
							createCell.setCellValue(thisData.getFbnszceData());
						}else if("2.归集调入".equals(column[j])){
							createCell.setCellValue(thisData.getFgjdrData());
						}else if("3.归集上缴或调出".equals(column[j])){
							createCell.setCellValue(thisData.getFgjsjData());
						}else if("（二）其他资金结转结余	".equals(column[j])){
							createCell.setCellValue("");
						}else if("二1.本年收支差额".equals(column[j])){
							createCell.setCellValue(thisData.getFczbkjzData());
						}else if("2.缴回资金".equals(column[j])){
							createCell.setCellValue(thisData.getJhzjData());
						}else if("3.使用专用结余".equals(column[j])){
							createCell.setCellValue(thisData.getSyzyjyData());
						}else if("4.支付所得税".equals(column[j])){
							createCell.setCellValue(thisData.getZfsdsData());
						}else if("四、年末预算结转结余".equals(column[j])){
							createCell.setCellValue("");
						}else if("（一）财政拨款结转结余	".equals(column[j])){
							createCell.setCellValue("");
						}else if("1.财政拨款结转".equals(column[j])){
							createCell.setCellValue(thisData.getCzbkjzData());
						}else if("2.财政拨款结余".equals(column[j])){
							createCell.setCellValue(thisData.getCzbkjyData());
						}else if("（二）其他资金结转结余	".equals(column[j])){
							createCell.setCellValue("");
						}else if("1.非财政拨款结转".equals(column[j])){
							createCell.setCellValue(thisData.getFczbkData());
						}else if("2.非财政拨款结余".equals(column[j])){
							createCell.setCellValue(thisData.getFczbkjyData());
						}else if("3.专用结余".equals(column[j])){
							createCell.setCellValue(thisData.getZyjyData());
						}else if("4.经营结余（如有余额，以“-”号填列）".equals(column[j])){
							createCell.setCellValue(thisData.getTjyjyData());
						}
					}else if(m==2){
						if("一、年初预算结转结余".equals(column[j])){
							createCell.setCellValue("");
						}else if("一（一）财政拨款结转结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getCzbkjzjyData());
						}else if("一（二）其他资金结转结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getQtzjjzjyData());
						}else if("二、年初余额调整（减少以“-”号填列）".equals(column[j])){
							createCell.setCellValue("");
						}else if("二（一）财政拨款结转结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getNcyeczbkjzjyData());
						}else if("二（二）其他资金结转结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getNcyeqtzjjzjyData());
						}else if("三、本年变动金额（减少以“-”号填列）".equals(column[j])){
							createCell.setCellValue("");
						}else if("（一）财政拨款结转结余".equals(column[j])){
							createCell.setCellValue("");
						}else if("一1.本年收支差额".equals(column[j])){
							createCell.setCellValue(surplusReportData.getFbnszceData());
						}else if("2.归集调入".equals(column[j])){
							createCell.setCellValue(surplusReportData.getFgjdrData());
						}else if("3.归集上缴或调出".equals(column[j])){
							createCell.setCellValue(surplusReportData.getFgjsjData());
						}else if("（二）其他资金结转结余	".equals(column[j])){
							createCell.setCellValue("");
						}else if("二1.本年收支差额".equals(column[j])){
							createCell.setCellValue(surplusReportData.getFczbkjzData());
						}else if("2.缴回资金".equals(column[j])){
							createCell.setCellValue(surplusReportData.getJhzjData());
						}else if("3.使用专用结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getSyzyjyData());
						}else if("4.支付所得税".equals(column[j])){
							createCell.setCellValue(surplusReportData.getZfsdsData());
						}else if("四、年末预算结转结余".equals(column[j])){
							createCell.setCellValue("");
						}else if("（一）财政拨款结转结余	".equals(column[j])){
							createCell.setCellValue("");
						}else if("1.财政拨款结转".equals(column[j])){
							createCell.setCellValue(surplusReportData.getCzbkjzData());
						}else if("2.财政拨款结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getCzbkjyData());
						}else if("（二）其他资金结转结余	".equals(column[j])){
							createCell.setCellValue("");
						}else if("1.非财政拨款结转".equals(column[j])){
							createCell.setCellValue(surplusReportData.getFczbkData());
						}else if("2.非财政拨款结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getFczbkjyData());
						}else if("3.专用结余".equals(column[j])){
							createCell.setCellValue(surplusReportData.getZyjyData());
						}else if("4.经营结余（如有余额，以“-”号填列）".equals(column[j])){
							createCell.setCellValue(surplusReportData.getTjyjyData());
						}
					}
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取数据
	 * @param list
	 * @param type
	 * @return
	 */
	public double getTotalData(List<BudgetVoucher> list,String type){
		//借方
		double vDebitAmountTotal=0;
		//贷方
		double vCreditAmountTotal=0;
		for(int i=0;i<list.size();i++){
			BudgetVoucher budget = list.get(i);
			//贷方金额
			String VCreditAmount=budget.getVCreditAmount();
			//借方金额
			String VDebitAmount=budget.getVDebitAmount();
			if(!"0".equals(VCreditAmount) && !"".equals(VCreditAmount)){
				vCreditAmountTotal=this.add(vCreditAmountTotal, Double.parseDouble(VCreditAmount));
			}
			if(!"0".equals(VDebitAmount) && !"".equals(VDebitAmount)){
				vDebitAmountTotal=this.add(vDebitAmountTotal, Double.parseDouble(VDebitAmount));
			}
		}
		if("jf".equals(type)){
			return vDebitAmountTotal;
		}else if("df".equals(type)){
			return vCreditAmountTotal;
		}else{
			return this.sub(vCreditAmountTotal,vDebitAmountTotal);
		}
	}
	
	
	
	
	
	/**
	 * 解决double类型相加丢失精度问题
	 * @param val1
	 * @param val2
	 * @return
	 */
	public double add(double val1,double val2){
		BigDecimal a=new BigDecimal(Double.toString(val1));
		BigDecimal b=new BigDecimal(Double.toString(val2));
		return a.add(b).doubleValue();
	}
	/**  
	* 提供精确的减法运算。  
	* @param v1 被减数  
	* @param v2 减数  
	* @return 两个参数的差  
	*/  
	public static double sub(double v1,double v2){   
	BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	return b1.subtract(b2).doubleValue();   
	}






	
	
	/***********************************************/
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public List<BudgetVoucher> getList() {
		return list;
	}

	public void setList(List<BudgetVoucher> list) {
		this.list = list;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}


}
