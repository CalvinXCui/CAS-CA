package com.action;

import com.entity.AccountingVoucher;
import com.entity.BalanceReport;
import com.entity.BudgetVoucher;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.OrderedProperties;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 资产负债
 * @author 15176
 *
 */
public class AssetAction extends ActionSupport implements RequestAware{
	private BaseService baseService;

	private List<BudgetVoucher> list;

	private AccountingVoucher account;

	private Map map;

	private Properties prop;

	private List<BalanceReport> columnList;

	private Map<String, Object> request;

	private BudgetVoucher voucher;
	//固定资产原值
	private double yz;
	//固定资产累计折旧
	private double zj;
	//流动负债合计
	private double ld;
	//非流动负债合计
	private double fld;
	//受托代理负债
	private double stdl;
	//无形资产原值
	private double wxzcyz;
	//无形资产累计摊销
	private double ljtx;
	//公共基础设施原值
	private double ggyz;
	//公共基础设施累计摊销
	private double ggtx;
	//保障性住房原值
	private double bzxzfyz;
	//保障性住房折旧
	private double bzxzfzj;


	//货币资金
	private double hbzj;
	//短期投资
	private double dqtz;
	//财政应返还额度
	private double czyfhed;
	//应收票据
	private double yspj;
	//应收账款净额
	private double yszkje;
	//预付账款
	private double yfzk;
	//应收股利
	private double ysgl;
	//应收利息
	private double yslx;
	//其他应收款净额
	private double qtyskje;
	//存货
	private double ch;
	//待摊费用
	private double dtfy;
	//一年内到期的非流动资产
	private double ynndqdfldzc;



	//短期借款
	private double dqjk;
	//应交增值税
	private double yjzzs;
	//其他应交税费
	private double qtyjsf;
	//应缴财政款
	private double yjczk;
	//应付职工薪酬
	private double yfzgxc;
	//应付票据
	private double yfpj;
	//应付账款
	private double yifzk;
	//应付政府补贴款
	private double yfzfbtk;
	//应付利息
	private double yflx;
	//预收账款
	private double yszk;
	//其他应付款
	private double qtyfk;
	//预提费用
	private double ytfy;
	//一年内到期的非流动负债
	private double ynndqdfldfz;
	//其他流动负债
	private double qtldfz;




	//长期借款
	private double cqjk;
	//长期应付款
	private double cqyfk;
	//预计负债
	private double yjfz;
	//其他非流动负债
	private double qtfldfz;



	//流动负债合计
	private double ldfzhj;
	//非流动负债合计
	private double fldfzhj;
	//受托代理负债
	private double stdlfz;


	// 固定资产净值 
	private double gdzcjz;
	// 无形资产净值 
	private double wxzcjz;
	// 公共基础设施净值 
	private double ggjcssjz;
	// 政府储备物资 
	private double zfcbwz;
	// 保障性住房净值 
	private double bzxzfjz;


	// 在建工程
	private double zjgc;
	// 工程物资 
	private double gcwz;
	// 长期债券投资
	private double cqzqtz;
	//长期股权投资 
	private double cqgqtz;
	// 研发支出 
	private double yfzc;
	// 文物文化资产
	private double wwwhzc;
	// 长期待摊费用
	private double cqdtfy;
	// 待处理财产损溢
	private double dclccsy;
	// 其他非流动资产
	private double qtfldzc;


	//累计盈余
	private double ljyy;
	//专用基金
	private double zyjj;
	//权益法调整
	private double qyftz;





	//流动资产合计
	private double ldzchj;
	//非流动资产合计
	private double fldzchj;
	//受托代理资产
	private double stdlzc;

	//负债合计
	private double fzhj;


	/**
	 * 页面
	 * @return
	 * @throws Exception
	 */
	public String showAssetReport() throws Exception{
		columnList=this.getReportList();
		request.put("columnList", columnList);
		return "assetReport";

	}

	/***
	 * 获取报表数据
	 * @return
	 * @throws Exception
	 */
	public List<BalanceReport> getReportList() throws Exception{
		request=new HashMap();
		prop=this.readProperties();
		columnList=new ArrayList();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<Object> list = baseService.findObjList("from BalanceReport where createTime like '%2018-08-21 10:24:46%'");

		//遍历properties
		for (String strKey : prop.stringPropertyNames()) {
			BalanceReport bal=new BalanceReport();
			bal.setDataType(strKey);
			bal.setCreateTime(sdf.format(date));
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					BalanceReport report=(BalanceReport)list.get(i);
					if(strKey.equals(report.getDataType())){
						bal.setBeginDataOne(report.getColumnTwo());
						bal.setBeginDataTwo(report.getColumnFour());
					}
				}
			}
			//获取key对应的value
			String strValue = prop.getProperty(strKey);
			//截取字符串
			String[] arr = strValue.substring(0, strValue.indexOf("-")).split("/");
			bal.setColumnOne(arr[0]);
			if(!"“”".equals(arr[1])){
				bal.setColumnThree(arr[1]);
			}else{
				bal.setColumnThree("");
			}
			//获取第二列的值
			String[] arr1 = strValue.substring(strValue.indexOf("-")+1,strValue.indexOf("~")).split("\\+");
			double coloumTwoData=0;
			if("应收账款净额".equals(arr[0])){
				String type="jf";
				String sName="坏账准备-应收账款";
				coloumTwoData = this.getcoloumTwoData(arr1,type);
				double badAccount = this.getBadAccount(sName);
				coloumTwoData = this.sub(coloumTwoData, badAccount);
			}else if("货币资金".equals(arr[0])){
				coloumTwoData = this.getcoloumTwoData(arr1,null);
				String sName="库存现金-受托代理资产";
				double badAccount = this.getBadAccount(sName);
				sName="银行存款-受托代理资产";
				double badAccount1 = this.getBadAccount(sName);
				badAccount=this.add(badAccount, badAccount1);
				coloumTwoData = this.sub(coloumTwoData, badAccount);
				hbzj=coloumTwoData;
			}/*else if("预付账款".equals(arr[0])){
            	String type="jf";
            	String sName="坏账准备-预付账款";
            	coloumTwoData = this.getcoloumTwoData(arr1,type);
            	double badAccount = this.getBadAccount(sName);
            	coloumTwoData = this.sub(coloumTwoData, badAccount);
            }*//*else if("应收股利".equals(arr[0])){
            	String sName="坏账准备-应收股利";
            	coloumTwoData = this.getcoloumTwoData(arr1,null);
            	double badAccount = this.getBadAccount(sName);
            	coloumTwoData = this.sub(coloumTwoData, badAccount);
            }*/else if("其他应收款净额".equals(arr[0])){
				String sName="坏账准备-其他应收款";
				coloumTwoData = this.getcoloumTwoData(arr1,null);
				double badAccount = this.getBadAccount(sName);
				coloumTwoData = this.sub(coloumTwoData, badAccount);
				qtyskje=coloumTwoData;
			}else if("短期投资".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				dqtz = coloumTwoData;
			}else if("财政应返还额度".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				czyfhed = coloumTwoData;
			}else if("应收票据".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				yspj = coloumTwoData;
			}else if("应收账款净额".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				yszkje =coloumTwoData;
			}else if("预付账款".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				yfzk = coloumTwoData;
			}else if("应收股利".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				ysgl = coloumTwoData;
			}else if("应收利息".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				yslx = coloumTwoData;
			}else if("存货".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				ch = coloumTwoData;
			}else if("待摊费用".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				dtfy = coloumTwoData;
			}else if("一年内到期的非流动资产".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				ynndqdfldzc = coloumTwoData;
			}else if("其他流动资产".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
			}else if("流动资产合计".equals(arr[0])){
				coloumTwoData=this.add(hbzj, dqtz);//货币资金 +短期投资
				coloumTwoData=this.add(coloumTwoData, czyfhed);//+ 财政应返还额度
				coloumTwoData=this.add(coloumTwoData, yspj);//+ 应收票据
				coloumTwoData=this.add(coloumTwoData, yszkje);//+ 应收账款净额
				coloumTwoData=this.add(coloumTwoData, yfzk);//+ 预付账款
				coloumTwoData=this.add(coloumTwoData, ysgl);//+ 应收股利
				coloumTwoData=this.add(coloumTwoData, yslx);//+ 应收利息
				coloumTwoData=this.add(coloumTwoData, qtyskje);//+ 其他应收款净额
				coloumTwoData=this.add(coloumTwoData, ch);// + 存货
				coloumTwoData=this.add(coloumTwoData, dtfy);//+ 待摊费用
				coloumTwoData=this.add(coloumTwoData, ynndqdfldzc);//+ 一年内到期的非流动资产
				ldzchj=coloumTwoData;
			}

			else if("固定资产原值".equals(arr[0])){
				yz = this.getcoloumTwoData(arr1,null);
			}else if("减：固定资产累计折旧".equals(arr[0])){
				coloumTwoData = 0-this.getcoloumTwoData(arr1,null);
				zj=coloumTwoData;
			}else if("固定资产净值".equals(arr[0])){
				coloumTwoData = this.sub(yz,zj);
				gdzcjz=coloumTwoData;
			}else if("无形资产原值".equals(arr[0])){
				coloumTwoData = this.getcoloumTwoData(arr1,null);
				wxzcyz=coloumTwoData;
			}else if("减：无形资产累计摊销".equals(arr[0])){
				coloumTwoData = this.getcoloumTwoData(arr1,null);
				ljtx=coloumTwoData;
			}else if("无形资产净值".equals(arr[0])){
				coloumTwoData=this.sub(wxzcyz, ljtx);
				wxzcjz=coloumTwoData;
			}else if("公共基础设施原值".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				ggyz=coloumTwoData;
			}else if("减：公共基础设施累计折旧（摊销）".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				ggtx=coloumTwoData;
			}else if("公共基础设施净值".equals(arr[0])){
				coloumTwoData=this.sub(ggyz, ggtx);
				ggjcssjz=coloumTwoData;
			}else if("政府储备物资".equals(arr[0])){
				String sName="待处理财产损溢";
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				double account = this.getBadAccount(sName);
				coloumTwoData=this.sub(coloumTwoData, account);
				zfcbwz=coloumTwoData;
			}else if("保障性住房原值".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				bzxzfyz=coloumTwoData;
			}else if("减：保障性住房累计折旧".equals(arr[0])){
				coloumTwoData=this.getcoloumTwoData(arr1,null);
				bzxzfzj=coloumTwoData;
			}else if("保障性住房净值".equals(arr[0])){
				coloumTwoData=this.sub(bzxzfyz,bzxzfzj);
				bzxzfjz=coloumTwoData;
			}else if("在建工程".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				zjgc = coloumTwoData;
			}else if("工程物资".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				gcwz = coloumTwoData;
			}else if("长期债券投资".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				cqzqtz = coloumTwoData;
			}else if("长期股权投资".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				cqgqtz = coloumTwoData;
			}else if("研发支出".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				yfzc = coloumTwoData;
			}else if("文物文化资产".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				wwwhzc = coloumTwoData;
			}else if("长期待摊费用".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				cqdtfy = coloumTwoData;
			}else if("待处理财产损溢".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				dclccsy = coloumTwoData;
			}else if("其他非流动资产".equals(arr[0])){
				coloumTwoData= this.getcoloumTwoData(arr1,null);
				qtfldzc = coloumTwoData;
			}else if("非流动资产合计".equals(arr[0])){
				coloumTwoData=this.add(cqgqtz, cqzqtz);//长期股权投资 + 长期债券投资
				coloumTwoData=this.add(coloumTwoData, gdzcjz);//+ 固定资产净值
				coloumTwoData=this.add(coloumTwoData, gcwz);//+ 工程物资
				coloumTwoData=this.add(coloumTwoData, zjgc);//+ 在建工程
				coloumTwoData=this.add(coloumTwoData, wxzcjz);//+ 无形资产净值
				coloumTwoData=this.add(coloumTwoData, yfzc);//+ 研发支出
				coloumTwoData=this.add(coloumTwoData, ggjcssjz);//+ 公共基础设施净值
				coloumTwoData=this.add(coloumTwoData, zfcbwz);//+ 政府储备物资
				coloumTwoData=this.add(coloumTwoData, wwwhzc);//+ 文物文化资产
				coloumTwoData=this.add(coloumTwoData, bzxzfjz);//+ 保障性住房净值
				coloumTwoData=this.add(coloumTwoData, cqdtfy);//+ 长期待摊费用
				coloumTwoData=this.add(coloumTwoData, dclccsy);//+ 待处理财产损溢
				coloumTwoData=this.add(coloumTwoData, qtfldzc);//+ 其他非流动资产
				fldzchj=coloumTwoData;
			}else if("受托代理资产".equals(arr[0])){
				coloumTwoData = this.getcoloumTwoData(arr1,null);
				String sName="库存现金-受托代理资产";
				double badAccount = this.getBadAccount(sName);
				sName="银行存款-受托代理资产";
				double badAccount1 = this.getBadAccount(sName);
				coloumTwoData=this.add(coloumTwoData, badAccount);
				coloumTwoData=this.add(coloumTwoData, badAccount1);
				stdlzc=coloumTwoData;
			}else if("资产总计".equals(arr[0])){//流动资产合计 + 非流动资产合计 + 受托代理资产
				coloumTwoData=this.add(ldzchj, fldzchj);
				coloumTwoData=this.add(coloumTwoData, stdlzc);
			}else{
				coloumTwoData = this.getcoloumTwoData(arr1,null);
			}
			if(String.valueOf(coloumTwoData).indexOf("E")!=-1){
				String plainString = new BigDecimal(String.valueOf(coloumTwoData)).toPlainString();
				bal.setColumnTwo(plainString);
			}else{
				String totalStr=String.valueOf(coloumTwoData);
				if("流动资产：".equals(arr[0]) || "非流动资产：".equals(arr[0])){
					totalStr="";
				}
				bal.setColumnTwo(totalStr);
			}

			//获取第四列的值
			String substr = strValue.substring(strValue.indexOf("~")+1,strValue.length());
			String[] arr2={};
			if(!"".equals(substr)){
				arr2=substr.split("\\+");
			}
			double coloumFourData=0;
            /*if("预付账款".equals(arr[0])){
            	String type="df";
            	coloumFourData = this.getcoloumTwoDataYS(arr2,type);
            }else if("非流动资产：".equals(arr[0])){
            	coloumFourData = this.getcoloumTwoDataYS(arr2,null);
            	ld=coloumFourData;
            }else if("工程物资".equals(arr[0])){
            	coloumFourData = this.getcoloumTwoDataYS(arr2,null);
            	fld=coloumFourData;
            }else if("在建工程".equals(arr[0])){
            	coloumFourData = this.getcoloumTwoDataYS(arr2,null);
            	stdl=coloumFourData;
            }else if("无形资产原值".equals(arr[0])){//负债合计
            	coloumFourData = this.add(ld,fld);
            	coloumFourData = this.add(coloumFourData,stdl);
            }else{
            	coloumFourData = this.getcoloumTwoDataYS(arr2,null);
            }*/


			if("短期借款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				dqjk=coloumFourData;
			}else if("应交增值税".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yjzzs=coloumFourData;
			}else if("其他应交税费".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				qtyjsf=coloumFourData;
			}else if("应缴财政款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yjczk=coloumFourData;
			}else if("应付职工薪酬".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yfzgxc=coloumFourData;
			}else if("应付票据".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yfpj=coloumFourData;
			}else if("应付账款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yifzk=coloumFourData;
			}else if("应付政府补贴款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yfzfbtk=coloumFourData;
			}else if("应付利息".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yflx=coloumFourData;
			}else if("预收账款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yszk=coloumFourData;
			}else if("其他应付款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				qtyfk=coloumFourData;
			}else if("预提费用".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				ytfy=coloumFourData;
			}else if("一年内到期的非流动负债".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				ynndqdfldfz=coloumFourData;
			}else if("其他流动负债".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				qtldfz=coloumFourData;
			}else if("流动负债合计".equals(arr[1])){
				coloumFourData=this.add(dqjk, yjzzs);//短期借款 +应交增值税
				coloumFourData=this.add(coloumFourData, qtyjsf);//+其他应交税费
				coloumFourData=this.add(coloumFourData, yjczk);//+应缴财政款
				coloumFourData=this.add(coloumFourData, yfzgxc);//+应付职工薪酬
				coloumFourData=this.add(coloumFourData, yfpj);//+应付票据
				coloumFourData=this.add(coloumFourData, yifzk);//+应付账款
				coloumFourData=this.add(coloumFourData, yfzfbtk);//+应付政府补贴款
				coloumFourData=this.add(coloumFourData, yflx);//+应付利息
				coloumFourData=this.add(coloumFourData, yszk);//+预收账款
				coloumFourData=this.add(coloumFourData, qtyfk);//+其他应付款
				coloumFourData=this.add(coloumFourData, ytfy);//+预提费用
				coloumFourData=this.add(coloumFourData, ynndqdfldfz);//+一年内到期的非流动负债
				coloumFourData=this.add(coloumFourData, qtldfz);//+其他流动负债
				ldfzhj=coloumFourData;
			}else if("长期借款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				cqjk=coloumFourData;
			}else if("长期应付款".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				cqyfk=coloumFourData;
			}else if("预计负债".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				yjfz=coloumFourData;
			}else if("其他非流动负债".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				qtfldfz=coloumFourData;
			}else if("非流动负债合计".equals(arr[1])){
				coloumFourData=this.add(cqjk, cqyfk);//长期借款 +长期应付款
				coloumFourData=this.add(coloumFourData, yjfz);//预计负债
				coloumFourData=this.add(coloumFourData, qtfldfz);//其他非流动负债
				fldfzhj=coloumFourData;
			}else if("受托代理负债".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				stdlfz=coloumFourData;
			}else if("负债合计".equals(arr[1])){
				coloumFourData=this.add(ldfzhj, fldfzhj);//流动负债合计 +非流动负债合计
				coloumFourData=this.add(coloumFourData, stdlfz);//受托代理负债
				fzhj=coloumFourData;
			}else if("累计盈余".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				ljyy=coloumFourData;
			}else if("专用基金".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				zyjj=coloumFourData;
			}else if("权益法调整".equals(arr[1])){
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
				qyftz=coloumFourData;
			}else if("本期盈余*	".equals(arr[1])){



			}else if("净资产合计".equals(arr[1])){
				// + 无偿调拨净资产”[月度报表] + 本期盈余[月度报表]
				coloumFourData=this.add(ljyy, zyjj);//累计盈余 + 专用基金
				coloumFourData=this.add(coloumFourData, qyftz);//+ 权益法调整

			}else if("负债和净资产总计".equals(arr[1])){
				coloumFourData=this.add(fzhj, fldfzhj);//负债合计 + 资产合计
			}else{
				coloumFourData = this.getcoloumTwoDataYS(arr2,null);
			}

			if(String.valueOf(coloumFourData).indexOf("E")!=-1){
				String plainString = new BigDecimal(String.valueOf(coloumFourData)).toPlainString();
				bal.setColumnFour(plainString);
			}else{
				String totalStr=String.valueOf(coloumFourData);
				if("流动负债：".equals(arr[1]) || "非流动负债：".equals(arr[1]) || "“”".equals(arr[1]) || "净资产：".equals(arr[1])){
					totalStr="";
				}
				bal.setColumnFour(totalStr);
			}
			baseService.saveOrUpdate(bal);
			columnList.add(bal);
		}
		return columnList;
	}
	public void exportAssetExcel() throws Exception{
		List<BalanceReport> list = this.getReportList();
		HttpServletResponse response=ServletActionContext.getResponse();
		String[] titles={"资产","期末余额","年初余额","负债和净资产","期末余额","年初余额"};
		String filename ="资产负债表.xlsx";
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
			XSSFSheet sheet = wb.createSheet("资产负债表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);

			CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,5);
			sheet.addMergedRegion(callRangeAddress);
			XSSFRow rowOne = sheet.createRow(0);
			rowOne.setHeight((short) 800);
			XSSFCell cel0 = rowOne.createCell(0);
			cel0.setCellStyle(style);
			cel0.setCellValue("资产负债表");

			XSSFRow row = sheet.createRow(1);
			for (int i = 0; i <titles.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(styleCell);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
			}
			// EXCEL内容
			if (list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					// 创建第j+1行
					XSSFRow rowContent = null;
					rowContent = sheet.createRow(j + 2);
					for(int m=0;m<titles.length;m++){
						XSSFCell createCell = rowContent.createCell(m);
						createCell.setCellStyle(styleCell);
						if(m==0){
							createCell.setCellValue(list.get(j).getColumnOne());
						}else if(m==1){
							createCell.setCellValue(list.get(j).getColumnTwo());
						}else if(m==2){
							createCell.setCellValue(list.get(j).getBeginDataOne());
						}else if(m==3){
							createCell.setCellValue(list.get(j).getColumnThree());
						}else if(m==4){
							createCell.setCellValue(list.get(j).getColumnFour());
						}else if(m==5){
							createCell.setCellValue(list.get(j).getBeginDataTwo());
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
	 * 获取财务凭证数据
	 * @param array
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public double getcoloumTwoData(String[] array,String type) throws Exception{
		double total=0;
		double getkjpzTotal=0;
		List list=new ArrayList();
		for(int j=0;j<array.length;j++){
			String str = array[j];
			if(!"".equals(str)){
				String hql="from AccountingVoucher where SName like '"+array[j]+"%'";
				list = baseService.findObjList(hql);
			}
			if(list!=null && list.size()>0){
				getkjpzTotal = this.getkjpzTotal(list,type);
			}
			total=this.add(total,getkjpzTotal);
		}
		return total;
	}
	/**
	 * 获取预算凭证数据
	 * @param array
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public double getcoloumTwoDataYS(String[] array,String type) throws Exception{
		double total=0;
		double getkjpzTotal=0;
		List list=new ArrayList();
		for(int j=0;j<array.length;j++){
			String hql="from BudgetVoucher where SName like '"+array[j]+"%'";
			list = baseService.findObjList(hql);
			if(list!=null && list.size()>0){
				getkjpzTotal = this.getyspzTotal(list,type);
			}
		}
		total=this.add(total,getkjpzTotal);
		return total;

	}

	/**
	 * 读取properties
	 * @return
	 */
	public Properties readProperties(){
		Resource resource = new ClassPathResource("asset.properties");
		Properties prop = new OrderedProperties();
		InputStream is;
		try {
			is = resource.getInputStream();
			BufferedReader bf;
			try {
				bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				prop.load(bf);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				is.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
	/**
	 * 获取坏账
	 * @throws Exception
	 */
	public double getBadAccount(String sName) throws Exception{
		double getkjpzTotal=0;
		String hql="from AccountingVoucher where SName like '"+sName+"%'";
		list = baseService.findObjList(hql);
		if(list!=null && list.size()>0){
			getkjpzTotal = this.getkjpzTotal(list,null);
		}
		return getkjpzTotal;
	}
	/**
	 * 计算预算凭证
	 * @param list
	 * @return
	 */
	public double getyspzTotal(List list,String type){
		//借方
		double vDebitAmountTotal=0;
		//贷方
		double vCreditAmountTotal=0;
		for(int i=0;i<list.size();i++){
			voucher = (BudgetVoucher)list.get(i);
			//贷方金额
			String VCreditAmount=voucher.getVCreditAmount();
			//借方金额
			String VDebitAmount=voucher.getVDebitAmount();
			if(!"0".equals(VCreditAmount) && !"".equals(VCreditAmount)){
				vCreditAmountTotal=this.add(vCreditAmountTotal, Double.parseDouble(VCreditAmount));
			}
			if(!"0".equals(VDebitAmount) && !"".equals(VDebitAmount)){
				vDebitAmountTotal=this.add(vDebitAmountTotal, Double.parseDouble(VDebitAmount));
			}
		}
		System.out.println(vDebitAmountTotal+"------------"+vCreditAmountTotal);
		if("jf".equals(type)){
			return vDebitAmountTotal;
		}else if("df".equals(type)){
			return vCreditAmountTotal;
		}else{
			return this.sub(vDebitAmountTotal, vCreditAmountTotal);
		}
	}
	/**
	 * 计算会计凭证
	 * @param list
	 * @return
	 */
	public double getkjpzTotal(List list,String type){
		//借方
		double vDebitAmountTotal=0;
		//贷方
		double vCreditAmountTotal=0;
		for(int i=0;i<list.size();i++){
			account = (AccountingVoucher)list.get(i);
			//贷方金额
			String VCreditAmount=account.getVCreditAmount();
			//借方金额
			String VDebitAmount=account.getVDebitAmount();
			if(!"0".equals(VCreditAmount) && !"".equals(VCreditAmount)){
				vCreditAmountTotal=this.add(vCreditAmountTotal, Double.parseDouble(VCreditAmount));
			}
			if(!"0".equals(VDebitAmount) && !"".equals(VDebitAmount)){
				vDebitAmountTotal=this.add(vDebitAmountTotal, Double.parseDouble(VDebitAmount));
			}
		}
		System.out.println(vDebitAmountTotal+"------------"+vCreditAmountTotal);
		if("jf".equals(type)){
			return vDebitAmountTotal;
		}else if("df".equals(type)){
			return vCreditAmountTotal;
		}else{
			return this.sub(vDebitAmountTotal, vCreditAmountTotal);
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

















	/**********************************************************************/
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

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

	public AccountingVoucher getAccount() {
		return account;
	}

	public void setAccount(AccountingVoucher account) {
		this.account = account;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public List getColumnList() {
		return columnList;
	}

	public void setColumnList(List columnList) {
		this.columnList = columnList;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public BudgetVoucher getVoucher() {
		return voucher;
	}

	public void setVoucher(BudgetVoucher voucher) {
		this.voucher = voucher;
	}

	public double getYz() {
		return yz;
	}

	public void setYz(double yz) {
		this.yz = yz;
	}

	public double getZj() {
		return zj;
	}

	public void setZj(double zj) {
		this.zj = zj;
	}

}
