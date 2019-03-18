package com.action;

import com.entity.BudgetVoucher;
import com.entity.CashReport;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CashAction extends ActionSupport
  implements RequestAware
{
  private Map<String, Object> request;
  private BaseService baseService;

  public String cashReport()
    throws Exception
  {
    Date date = new Date();

    CashReport reportData = getReportData(null, date);

    this.request.put("cash", reportData);
    return "cashReport";
  }

  public CashReport getReportData(String obj, Date date)
    throws Exception
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    int year = Integer.parseInt(sdf.format(date)) - 1;
    if ("thisYear".equals(obj)) {
      year = Integer.parseInt(sdf.format(date));
    }

    CashReport cash = new CashReport();
    String hql = "";

    List syhdczbkywxjList = new ArrayList();
    hql = "from BudgetVoucher where SName like '事业预算收入%' and VDate like '" + year + "%'";
    syhdczbkywxjList = this.baseService.findObjList(hql);
    double syhdczbkywxj = 0.0D;
    if ((syhdczbkywxjList != null) && (syhdczbkywxjList.size() > 0)) {
      String type = "df";
      syhdczbkywxj = getTotalData(syhdczbkywxjList, type);
    }
    cash.setSyhdsddcczbkywdxj(String.valueOf(syhdczbkywxj));

    List sddqtyriList = new ArrayList();
    hql = "from BudgetVoucher where SName like '上级补助预算收入%' and VDate like '" + year + "%'";
    sddqtyriList = this.baseService.findObjList(hql);

    hql = "from BudgetVoucher where SName like '附属单位上缴预算收入%' and VDate like '" + year + "%'";
    sddqtyriList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '非同级财政拨款预算收入%' and VDate like '" + year + "%'";
    sddqtyriList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '其他预算收入-捐赠收入%' and VDate like '" + year + "%'";
    sddqtyriList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '其他预算收入-利息收入%' and VDate like '" + year + "%'";
    sddqtyriList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '其他预算收入-租金收入%' and VDate like '" + year + "%'";
    sddqtyriList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '其他预算收入-其他%' and VDate like '" + year + "%'";
    sddqtyriList.addAll(this.baseService.findObjList(hql));

    double sddqtyri = 0.0D;
    if ((syhdczbkywxjList != null) && (syhdczbkywxjList.size() > 0)) {
      String type = "df";
      sddqtyri = getTotalData(syhdczbkywxjList, type);
    }
    cash.setSddqtyrchdygdxj(String.valueOf(sddqtyri));

    List gmspjslwzfList = new ArrayList();
    hql = "from BudgetVoucher where SName like '事业支出-%-资本性支出%' and VDate like '" + year + "%'";
    gmspjslwzfList = this.baseService.findObjList(hql);

    hql = "from BudgetVoucher where SName like '经营支出-%-资本性支出%' and VDate like '" + year + "%'";
    gmspjslwzfList.addAll(this.baseService.findObjList(hql));

    double gmspjslwzf = 0.0D;
    if ((gmspjslwzfList != null) && (gmspjslwzfList.size() > 0)) {
      String type = "jf";
      gmspjslwzf = getTotalData(gmspjslwzfList, type);
    }
    cash.setGmspjswzfdxj(String.valueOf(gmspjslwzf));

    List gdzcwxzcggjcssfList = new ArrayList();
    hql = "from BudgetVoucher where SName like '教育支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList = this.baseService.findObjList(hql);

    hql = "from BudgetVoucher where SName like '科研支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '医疗支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '行政管理支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '后勤保障支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '离退休支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '非科研支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '支撑业务支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '经营支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '上缴上级支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '对附属单位补助-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '投资支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '债务还本支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName like '其他支出-%-资本性支出%' and VDate like '" + year + "%'";
    gdzcwxzcggjcssfList.addAll(this.baseService.findObjList(hql));

    double gdzcwxzcggjcssf = 0.0D;
    if ((gdzcwxzcggjcssfList != null) && (gdzcwxzcggjcssfList.size() > 0)) {
      String type = "jf";
      gdzcwxzcggjcssf = getTotalData(gdzcwxzcggjcssfList, type);
    }
    cash.setGgjcssdzfdxj(String.valueOf(gdzcwxzcggjcssf));

    List czzbxxmbkList = new ArrayList();
    hql = "from BudgetVoucher where SName = '教育支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList = this.baseService.findObjList(hql);

    hql = "from BudgetVoucher where SName = '科研支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '医疗支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '行政管理支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '后勤保障支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '离退休支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '非科研支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '支撑业务支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '经营支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '上缴上级支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '对附属单位补助-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '投资支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '债务还本支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    hql = "from BudgetVoucher where SName = '其他支出-财政项目-资本性支出' and VDate like '" + year + "%'";
    czzbxxmbkList.addAll(this.baseService.findObjList(hql));

    double czzbxxmbk = 0.0D;
    if ((czzbxxmbkList != null) && (czzbxxmbkList.size() > 0)) {
      String type = "jf";
      czzbxxmbk = getTotalData(czzbxxmbkList, type);
    }
    cash.setCzzbxxmbksddxj(String.valueOf(czzbxxmbk));

    List qdjksddxjList = new ArrayList();
    hql = "from BudgetVoucher where SName like '债务预算收入%' and VDate like '" + year + "%'";
    qdjksddxjList = this.baseService.findObjList(hql);
    double qdjksddxj = 0.0D;
    if ((qdjksddxjList != null) && (qdjksddxjList.size() > 0)) {
      String type = "df";
      qdjksddxj = getTotalData(qdjksddxjList, type);
    }
    cash.setQdjksddxj(String.valueOf(qdjksddxj));

    List chjkzfdxjList = new ArrayList();
    hql = "from BudgetVoucher where SName like '债务预算支出%' and VDate like '" + year + "%'";
    qdjksddxjList = this.baseService.findObjList(hql);
    double chjkzfdxj = 0.0D;
    if ((qdjksddxjList != null) && (qdjksddxjList.size() > 0)) {
      String type = "jf";
      chjkzfdxj = getTotalData(qdjksddxjList, type);
    }
    cash.setChjkzfdxj(String.valueOf(chjkzfdxj));

    List chkxzfdxjList = new ArrayList();
    hql = "from BudgetVoucher where SName like '债务预算支出%' and VDate like '" + year + "%'";
    chkxzfdxjList = this.baseService.findObjList(hql);
    double chkxzfdxj = 0.0D;
    if ((chkxzfdxjList != null) && (chkxzfdxjList.size() > 0)) {
      String type = "jf";
      chkxzfdxj = getTotalData(chkxzfdxjList, type);
    }
    cash.setChlxzfdxj(String.valueOf(chjkzfdxj));

    List zfdqtyczhdygdxjList = new ArrayList();
    hql = "from BudgetVoucher where SName like '债务预算支出%' and VDate like '" + year + "%'";
    zfdqtyczhdygdxjList = this.baseService.findObjList(hql);
    String type = "jf";
    zfdqtyczhdygdxjList = getDataList(zfdqtyczhdygdxjList, type);

    return cash;
  }

  public void exportCashExcel()
    throws IOException
  {
    HttpServletResponse response = ServletActionContext.getResponse();
    String[] titles = { "项目", "本年金额", "上年金额" };
    String[] column = { "一、日常活动产生的现金流量：", "财政基本支出拨款收到的现金", "财政非资本性项目拨款收到的现金", "事业活动收到的除财政拨款以外的现金", "收到的其他与日常活动有关的现金", "日常活动的现金流入小计", 
      "购买商品、接受劳务支付的现金", "支付给职工以及为职工支付的现金", "支付的各项税费", "支付的其他与日常活动有关的现金", "日常活动的现金流出小计", "日常活动产生的现金流量净额", 
      "二、投资活动产生的现金流量：", "收回投资收到的现金", "取得投资收益收到的现金", "处置固定资产、无形资产、公共基础设施等收回的现金净额", "收到的其他与投资活动有关的现金", "投资活动的现金流入小计", 
      "购建固定资产、无形资产、公共基础设施等支付的现金", "对外投资支付的现金", "上缴处置固定资产、无形资产、公共基础设施等净收入支付的现金", 
      "支付的其他与投资活动有关的现金", "投资活动的现金流出小计", "投资活动产生的现金流量净额", "三、筹资活动产生的现金流量：", "财政资本性项目拨款收到的现金", 
      "取得借款收到的现金", "收到的其他与筹资活动有关的现金", "筹资活动的现金流入小计", "偿还借款支付的现金", "偿还利息支付的现金", "支付的其他与筹资活动有关的现金", "筹资活动的现金流出小计", 
      "筹资活动产生的现金流量净额", "四、汇率变动对现金的影响额", "五、现金净增加额" };
    String filename = "现金流量表.xlsx";
    OutputStream os = response.getOutputStream();
    response.reset();
    response.setHeader("Content-Disposition", 
      "attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
    response.setContentType("application/msexcel");
    try
    {
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

      XSSFSheet sheet = wb.createSheet("现金流量表");

      sheet.setDefaultColumnWidth(35);

      CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 2);
      sheet.addMergedRegion(callRangeAddress);
      XSSFRow rowOne = sheet.createRow(0);
      rowOne.setHeight((short) 800);
      XSSFCell cel0 = rowOne.createCell(0);
      cel0.setCellStyle(style);
      cel0.setCellValue("现金流量表");

      XSSFRow row = sheet.createRow(1);
      for (int i = 0; i < titles.length; ++i) {
        XSSFCell cell = row.createCell(i);
        cell.setCellStyle(styleCell);

        cell.setCellValue(titles[i]);
      }

      Date date = new Date();
      String obj = "thisYear";
      CashReport reportData = getReportData(null, date);
      CashReport thisReportData = getReportData(obj, date);

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
            if ("一、日常活动产生的现金流量：".equals(column[j]))
              createCell.setCellValue("");
            else if ("财政基本支出拨款收到的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getCzjbzcsddxj());
            else if ("财政非资本性项目拨款收到的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getCzfzbxxmbksddxj());
            else if ("事业活动收到的除财政拨款以外的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getSyhdsddcczbkywdxj());
            else if ("收到的其他与日常活动有关的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getSddqtyrchdygdxj());
            else if ("日常活动的现金流入小计".equals(column[j]))
              createCell.setCellValue(thisReportData.getRchddxjlrxj());
            else if ("购买商品、接受劳务支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getGmspjswzfdxj());
            else if ("支付给职工以及为职工支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getZfgzgyjwzgzfdxj());
            else if ("支付的各项税费".equals(column[j]))
              createCell.setCellValue(thisReportData.getZfdgxsf());
            else if ("支付的其他与日常活动有关的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getZfdqtyrchdygdxj());
            else if ("日常活动的现金流出小计".equals(column[j]))
              createCell.setCellValue(thisReportData.getRchddxjlcxj());
            else if ("日常活动产生的现金流量净额".equals(column[j]))
              createCell.setCellValue(thisReportData.getRchdcsdxjllje());
            else if ("二、投资活动产生的现金流量：".equals(column[j]))
              createCell.setCellValue("");
            else if ("收回投资收到的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getShtzsddxj());
            else if ("取得投资收益收到的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getQdtzsydxj());
            else if ("处置固定资产、无形资产、公共基础设施等收回的现金净额".equals(column[j]))
              createCell.setCellValue(thisReportData.getGgjcssshdje());
            else if ("收到的其他与投资活动有关的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getYtzhdygdxj());
            else if ("投资活动的现金流入小计".equals(column[j]))
              createCell.setCellValue(thisReportData.getTzhddxjlrxj());
            else if ("购建固定资产、无形资产、公共基础设施等支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getGgjcssdzfdxj());
            else if ("对外投资支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getDwtzzfdxj());
            else if ("上缴处置固定资产、无形资产、公共基础设施等净收入支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getGgjcssdsrzfje());
            else if ("支付的其他与投资活动有关的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getZfytzhdygdxj());
            else if ("投资活动的现金流出小计".equals(column[j]))
              createCell.setCellValue(thisReportData.getTzhdxjlcxj());
            else if ("投资活动产生的现金流量净额".equals(column[j]))
              createCell.setCellValue(thisReportData.getTzhdcsdxjllje());
            else if ("三、筹资活动产生的现金流量：".equals(column[j]))
              createCell.setCellValue("");
            else if ("财政资本性项目拨款收到的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getCzzbxxmbksddxj());
            else if ("取得借款收到的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getQdjksddxj());
            else if ("收到的其他与筹资活动有关的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getSddqtyczhdygxj());
            else if ("筹资活动的现金流入小计".equals(column[j]))
              createCell.setCellValue(thisReportData.getCzhddxjlrxj());
            else if ("偿还借款支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getChjkzfdxj());
            else if ("偿还利息支付的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getChlxzfdxj());
            else if ("支付的其他与筹资活动有关的现金".equals(column[j]))
              createCell.setCellValue(thisReportData.getZfdqtyczhdygdxj());
            else if ("筹资活动的现金流出小计".equals(column[j]))
              createCell.setCellValue(thisReportData.getCzhddxjlcxj());
            else if ("筹资活动产生的现金流量净额".equals(column[j]))
              createCell.setCellValue(thisReportData.getCzhdcsdxjllje());
            else if ("四、汇率变动对现金的影响额".equals(column[j]))
              createCell.setCellValue("");
            else if ("五、现金净增加额".equals(column[j]))
              createCell.setCellValue("");
          }
          else if (m == 2) {
            if ("一、日常活动产生的现金流量：".equals(column[j]))
              createCell.setCellValue("");
            else if ("财政基本支出拨款收到的现金".equals(column[j]))
              createCell.setCellValue(reportData.getCzjbzcsddxj());
            else if ("财政非资本性项目拨款收到的现金".equals(column[j]))
              createCell.setCellValue(reportData.getCzfzbxxmbksddxj());
            else if ("事业活动收到的除财政拨款以外的现金".equals(column[j]))
              createCell.setCellValue(reportData.getSyhdsddcczbkywdxj());
            else if ("收到的其他与日常活动有关的现金".equals(column[j]))
              createCell.setCellValue(reportData.getSddqtyrchdygdxj());
            else if ("日常活动的现金流入小计".equals(column[j]))
              createCell.setCellValue(reportData.getRchddxjlrxj());
            else if ("购买商品、接受劳务支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getGmspjswzfdxj());
            else if ("支付给职工以及为职工支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getZfgzgyjwzgzfdxj());
            else if ("支付的各项税费".equals(column[j]))
              createCell.setCellValue(reportData.getZfdgxsf());
            else if ("支付的其他与日常活动有关的现金".equals(column[j]))
              createCell.setCellValue(reportData.getZfdqtyrchdygdxj());
            else if ("日常活动的现金流出小计".equals(column[j]))
              createCell.setCellValue(reportData.getRchddxjlcxj());
            else if ("日常活动产生的现金流量净额".equals(column[j]))
              createCell.setCellValue(reportData.getRchdcsdxjllje());
            else if ("二、投资活动产生的现金流量：".equals(column[j]))
              createCell.setCellValue("");
            else if ("收回投资收到的现金".equals(column[j]))
              createCell.setCellValue(reportData.getShtzsddxj());
            else if ("取得投资收益收到的现金".equals(column[j]))
              createCell.setCellValue(reportData.getQdtzsydxj());
            else if ("处置固定资产、无形资产、公共基础设施等收回的现金净额".equals(column[j]))
              createCell.setCellValue(reportData.getGgjcssshdje());
            else if ("收到的其他与投资活动有关的现金".equals(column[j]))
              createCell.setCellValue(reportData.getYtzhdygdxj());
            else if ("投资活动的现金流入小计".equals(column[j]))
              createCell.setCellValue(reportData.getTzhddxjlrxj());
            else if ("购建固定资产、无形资产、公共基础设施等支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getGgjcssdzfdxj());
            else if ("对外投资支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getDwtzzfdxj());
            else if ("上缴处置固定资产、无形资产、公共基础设施等净收入支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getGgjcssdsrzfje());
            else if ("支付的其他与投资活动有关的现金".equals(column[j]))
              createCell.setCellValue(reportData.getZfytzhdygdxj());
            else if ("投资活动的现金流出小计".equals(column[j]))
              createCell.setCellValue(reportData.getTzhdxjlcxj());
            else if ("投资活动产生的现金流量净额".equals(column[j]))
              createCell.setCellValue(reportData.getTzhdcsdxjllje());
            else if ("三、筹资活动产生的现金流量：".equals(column[j]))
              createCell.setCellValue("");
            else if ("财政资本性项目拨款收到的现金".equals(column[j]))
              createCell.setCellValue(reportData.getCzzbxxmbksddxj());
            else if ("取得借款收到的现金".equals(column[j]))
              createCell.setCellValue(reportData.getQdjksddxj());
            else if ("收到的其他与筹资活动有关的现金".equals(column[j]))
              createCell.setCellValue(reportData.getSddqtyczhdygxj());
            else if ("筹资活动的现金流入小计".equals(column[j]))
              createCell.setCellValue(reportData.getCzhddxjlrxj());
            else if ("偿还借款支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getChjkzfdxj());
            else if ("偿还利息支付的现金".equals(column[j]))
              createCell.setCellValue(reportData.getChlxzfdxj());
            else if ("支付的其他与筹资活动有关的现金".equals(column[j]))
              createCell.setCellValue(reportData.getZfdqtyczhdygdxj());
            else if ("筹资活动的现金流出小计".equals(column[j]))
              createCell.setCellValue(reportData.getCzhddxjlcxj());
            else if ("筹资活动产生的现金流量净额".equals(column[j]))
              createCell.setCellValue(reportData.getCzhdcsdxjllje());
            else if ("四、汇率变动对现金的影响额".equals(column[j]))
              createCell.setCellValue("");
            else if ("五、现金净增加额".equals(column[j])) {
              createCell.setCellValue("");
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

  public double getTotalData(List<BudgetVoucher> list, String type)
  {
    double vDebitAmountTotal = 0.0D;

    double vCreditAmountTotal = 0.0D;
    for (int i = 0; i < list.size(); ++i) {
      BudgetVoucher budget = (BudgetVoucher)list.get(i);

      String VCreditAmount = budget.getVCreditAmount();

      String VDebitAmount = budget.getVDebitAmount();
      if ((!"0".equals(VCreditAmount)) && (!"".equals(VCreditAmount))) {
        vCreditAmountTotal = add(vCreditAmountTotal, Double.parseDouble(VCreditAmount));
      }
      if ((!"0".equals(VDebitAmount)) && (!"".equals(VDebitAmount))) {
        vDebitAmountTotal = add(vDebitAmountTotal, Double.parseDouble(VDebitAmount));
      }
    }
    if ("jf".equals(type))
      return vDebitAmountTotal;
    if ("df".equals(type)) {
      return vCreditAmountTotal;
    }
    return sub(vCreditAmountTotal, vDebitAmountTotal);
  }

  public List<BudgetVoucher> getDataList(List<BudgetVoucher> list, String type)
  {
    List budgetVoucherList = new ArrayList();
    List jfList = new ArrayList();
    List dfList = new ArrayList();

    double vDebitAmountTotal = 0.0D;

    double vCreditAmountTotal = 0.0D;
    for (int i = 0; i < list.size(); ++i) {
      BudgetVoucher budget = (BudgetVoucher)list.get(i);

      String VCreditAmount = budget.getVCreditAmount();

      String VDebitAmount = budget.getVDebitAmount();

      if ((!"0".equals(VCreditAmount)) && (!"".equals(VCreditAmount))) {
        dfList.add(budget);
      }
      if ((!"0".equals(VDebitAmount)) && (!"".equals(VDebitAmount))) {
        jfList.add(budget);
      }
    }
    if ("jf".equals(type))
      budgetVoucherList = jfList;
    else if ("df".equals(type)) {
      budgetVoucherList = dfList;
    }

    return budgetVoucherList;
  }

  public double add(double val1, double val2)
  {
    BigDecimal a = new BigDecimal(Double.toString(val1));
    BigDecimal b = new BigDecimal(Double.toString(val2));
    return a.add(b).doubleValue();
  }

  public static double sub(double v1, double v2)
  {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.subtract(b2).doubleValue();
  }

  public Map<String, Object> getRequest()
  {
    return this.request;
  }

  public void setRequest(Map<String, Object> request) {
    this.request = request;
  }

  public BaseService getBaseService() {
    return this.baseService;
  }

  public void setBaseService(BaseService baseService) {
    this.baseService = baseService;
  }
}