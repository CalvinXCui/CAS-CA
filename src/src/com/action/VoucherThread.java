package com.action;

import com.entity.*;
import com.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class VoucherThread {

	List<VoucherRules> vrList;
	
	List<OldAccountingVoucher> ocvList;

	List<OldAccountingVoucher> oldAccountingVouchers;
	
	private List<AccountingVoucher> accountingVouchers;
	
	
	private List<VoucherRuleName> voucherRuleNames;

	String msg;

	/**
	 * 旧财务凭证的导入以及到新财务凭证的转换
	 * 
	 * @param list
	 * @param baseService
	 * @param oldAccVouchers
	 * @param accVouchers
	 * @return
	 * @throws Exception
	 */
	public String executeList(List<List<String>> list, BaseService baseService,
			List<OldAccountingVoucher> oldAccVouchers, List<AccountingVoucher> accVouchers) throws Exception {
		vrList = baseService.findObjList("from VoucherRules");
		ocvList=baseService.findObjList("from OldAccountingVoucher");
		Map<String, VoucherRules> map=new HashMap<>();
		for(VoucherRules voucherRules:vrList){
			String sNo=voucherRules.getVoldSubjectNo();
			String sName=voucherRules.getVoldSubjectName();
			map.put(sNo+sName, voucherRules);
		}
		List<String> abstract_list = new ArrayList<String>();
		abstract_list.add("付汇款手续费");
		abstract_list.add("审稿费");
		abstract_list.add("汇款手续费");
		abstract_list.add("专家咨询费");
		abstract_list.add("临时劳务");
		abstract_list.add("专家费");
		abstract_list.add("劳务费");
		abstract_list.add("财政部授权支付额度");
		abstract_list.add("银行手续费");
		abstract_list.add("遗嘱补助");
		abstract_list.add("伙食补助");
		abstract_list.add("补交电费");
		abstract_list.add("工资成本调整");
		abstract_list.add("调整课题号");
		abstract_list.add("购支票手续费");
		abstract_list.add("更正凭证会计科目");
		abstract_list.add("劳务派遣人员工资及工会会费");
		abstract_list.add("收保险及代缴社会保险及公积金");
		abstract_list.add("调整课题支出 ");
		abstract_list.add("调整科目串户");
		abstract_list.add("调整科目贷方");
		abstract_list.add("专家评审费 ");
		abstract_list.add("项目聘用人员保险及公积金课题转账");
		oldAccountingVouchers=baseService.findObjList("from OldAccountingVoucher");
		Map<String, OldAccountingVoucher> aMap=new HashMap<>();
		for(OldAccountingVoucher oac:oldAccountingVouchers){
			String tname=oac.getVNo()+oac.getVAbstract()+oac.getVDebitAmount()+oac.getVCreditAmount()+oac.getTNo()+oac.getSNo();
			aMap.put((tname), oac);
		}
		
		for (int i = 1; i < list.size(); i++) {  //有标题情况下
			List<String> cellList = list.get(i);
			// if (cellList.size() >= 11) {// 列数
			OldAccountingVoucher oldaccv = new OldAccountingVoucher();
			// 获取旧的凭证编号
			String oldVNo = cellList.get(1).toString();
			// 摘要
			String oldVAbstract = cellList.get(2).toString();
			// 借方金额
			String oldVDebitAmount = cellList.get(3).toString();
			// 贷方金额
			String oldVCreditAmount = cellList.get(4).toString();
			// 课题编号
			String oldTNo = cellList.get(5).toString();
			// 科目名称
			String oldSNo = cellList.get(7).toString();
			String cName=oldVNo +  oldVAbstract + oldVDebitAmount + oldVCreditAmount + oldTNo + oldSNo ;
			if (!map.containsKey(cName)) {
				aMap.put(cName, oldaccv);
				AccountingVoucher acc01 = this.importandchage(oldaccv, cellList, baseService, oldAccVouchers, accVouchers, map);
				oldAccVouchers.add(oldaccv);
				accVouchers.add(acc01);
				
			} else {
				for (int y = 0; y < abstract_list.size(); y++) {
					if (oldVAbstract.contains((abstract_list.get(y)))) {
						AccountingVoucher acc01 = this.importandchage(oldaccv, cellList, baseService, oldAccVouchers, accVouchers, map);
						oldAccVouchers.add(oldaccv);
						accVouchers.add(acc01);
					}
				}
			}
	}
	java.util.Iterator<OldAccountingVoucher> it = oldAccVouchers.iterator();
	while (it.hasNext())// 不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator方式
	{
		OldAccountingVoucher t = it.next();
		if (t.getVNo() ==null)
			it.remove();
	}
	java.util.Iterator<AccountingVoucher> its = accVouchers.iterator();
	while (its.hasNext())// 不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator方式
	{
		AccountingVoucher t = its.next();
		if (t.getVNo() ==null)
			its.remove();
	}
	//批量插入操作
	baseService.batchInsert(null, oldAccVouchers);
	
	baseService.batchInsert(null, accVouchers);
	return msg;
}

	/**
	 * 旧财务凭证的导入以及到新财务凭证的转换
	 * 
	 * @param oldaccv
	 * @param cellList
	 * @param baseService
	 * @param oldAccVouchers
	 * @param accVouchers
	 * @throws Exception
	 */
	public AccountingVoucher importandchage(OldAccountingVoucher oldaccv, List<String> cellList, BaseService baseService,
			List<OldAccountingVoucher> oldAccVouchers, List<AccountingVoucher> accVouchers, Map<String, VoucherRules> map)
			throws Exception {
		
		// 获取旧的凭证编号
		String oldVNo = cellList.get(1).toString();
		// 摘要
		String oldVAbstract = cellList.get(2).toString();
		// 借方金额
		String oldVDebitAmount = cellList.get(3).toString();
		// 贷方金额
		String oldVCreditAmount = cellList.get(4).toString();
		// 课题编号
		String oldTNo = cellList.get(5).toString();
		// 科目名称
		String oldSNo = cellList.get(7).toString();
		// 日期
		oldaccv.setVDate(cellList.get(0).toString());
		// 凭证编号
		oldaccv.setVNo(oldVNo);
		// 摘要
		oldaccv.setVAbstract(oldVAbstract);
		// 借方金额
		oldaccv.setVDebitAmount(oldVDebitAmount);
		// 贷方金额
		oldaccv.setVCreditAmount(oldVCreditAmount);
		// 课题段代码
		oldaccv.setTNo(oldTNo);
		// 课题段名称
		oldaccv.setTName(cellList.get(6).toString());
		// 六级科目代码
		oldaccv.setSNo(oldSNo);
		// 六级科目名称
		oldaccv.setSName(cellList.get(8).toString());
		// 课题所属部门名称
		oldaccv.setTOrganization(cellList.get(9).toString());
	
		String vOldSubjectNo = oldaccv.getSNo();
		String vOldSubjectName = oldaccv.getSName();
		AccountingVoucher accv=new AccountingVoucher();
		if (map.size()>0 && map != null) {

				if ( StringUtils.isNotEmpty(vOldSubjectNo) && StringUtils.isNotEmpty(vOldSubjectName)) {
					if (map.containsKey((vOldSubjectNo+vOldSubjectName))) {
						
						VoucherRules rules=map.get((vOldSubjectNo+vOldSubjectName));
						String SNo = rules.getVfinancialNo();
						String SName = rules.getVfinancialName();
						
						if (StringUtils.isNotEmpty(SNo) || StringUtils.isNotEmpty(SName)) {
							// 六级科目代码
							accv.setSNo(SNo);
							// 六级科目名称
							accv.setSName(SName);
							/*-----以下内容保持不变------*/
							// 日期
							accv.setVDate(cellList.get(0).toString());
							// 凭证编号
							accv.setVNo(cellList.get(1).toString());
							// 摘要
							accv.setVAbstract(cellList.get(2).toString());
							// 借方金额
							accv.setVDebitAmount(cellList.get(3).toString());
							// 贷方金额
							accv.setVCreditAmount(cellList.get(4).toString());
							// 课题段代码
							accv.setTNo(cellList.get(5).toString());
							// 课题段名称
							accv.setTName(cellList.get(6).toString());
							// 课题所属部门名称
							accv.setTOrganization(cellList.get(9).toString());
							
							accv.setVStatus(0);	
							try{
								//baseService.save(accv);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
		}
		return accv;
	}
	
	/**
	 * 新规则下的旧财务凭证的导入以及到新财务凭证的转换   新
	 * 
	 * @param list
	 * @param baseService
	 * @param oldAccVouchers
	 * @param accVouchers
	 * @return
	 * @throws Exception
	 */
	public String newexecuteList(List<List<String>> list, BaseService baseService,
		List<OldAccountingVoucher> oldAccVouchers, List<AccountingVoucher> accVouchers) throws Exception {
		VoucherRules voucherRules=null;		
		vrList = baseService.findObjList("from VoucherRules");
		ocvList=baseService.findObjList("from  OldAccountingVoucher");
		Map<String, VoucherRules> map=new HashMap<>();
		for(VoucherRules voucherRule:vrList){
			//String sNo=voucherRules.getVoldSubjectNo();
			String sName=voucherRule.getVoldSubjectName();
			map.put(sName, voucherRule);
		}
		List<String> abstract_list = new ArrayList<String>();
		abstract_list.add("付汇款手续费");
		abstract_list.add("审稿费");
		abstract_list.add("汇款手续费");
		abstract_list.add("专家咨询费");
		abstract_list.add("临时劳务");
		abstract_list.add("专家费");
		abstract_list.add("劳务费");
		abstract_list.add("财政部授权支付额度");
		abstract_list.add("银行手续费");
		abstract_list.add("遗嘱补助");
		abstract_list.add("伙食补助");
		abstract_list.add("补交电费");
		abstract_list.add("工资成本调整");
		abstract_list.add("调整课题号");
		abstract_list.add("购支票手续费");
		abstract_list.add("更正凭证会计科目");
		abstract_list.add("劳务派遣人员工资及工会会费");
		abstract_list.add("收保险及代缴社会保险及公积金");
		abstract_list.add("调整课题支出 ");
		abstract_list.add("调整科目串户");
		abstract_list.add("调整科目贷方");
		abstract_list.add("专家评审费 ");
		abstract_list.add("项目聘用人员保险及公积金课题转账");

		//遍历cellList(有标题，从i=1开始)
		for (int i = 1; i < list.size(); i++) { 			
			List<String> cellList = list.get(i);
			//创建旧财务表对象，存放旧财务信息
			OldAccountingVoucher oldaccv=VoucherThread.produceOldAccountingVoucher(cellList);
			AccountingVoucher accv = VoucherThread.produceAccountingVoucher(cellList);
			oldAccVouchers.add(oldaccv);
			VoucherThread.transRulesfuction(oldaccv, accVouchers, map, accv);  //转换核心代码
		}			
	java.util.Iterator<OldAccountingVoucher> it = oldAccVouchers.iterator();
	while (it.hasNext())// 不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator方式
	{
		OldAccountingVoucher t = it.next();
		if (t.getVNo() ==null)
			it.remove();
	}
	java.util.Iterator<AccountingVoucher> its = accVouchers.iterator();
  	while (its.hasNext())// 不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator方式
	{
     		AccountingVoucher t = its.next();
    		if (t.getVNo() ==null)
			its.remove();
	}
	//批量插入操作
	baseService.batchInsert(null, oldAccVouchers);	
	baseService.batchInsert(null, accVouchers);
	return msg;
}	
	

	/**
	 * 新财务转新预算方法
	 * @param list
	 * @param baseService
	 * @return
	 * @throws Exception
	 */
	public String vourcherChange(List<AccountingVoucher> list, BaseService baseService) throws Exception {
		vrList = baseService.findObjList("from VoucherRules");

		for (AccountingVoucher accountingVoucher : list) {
			String accvSNo = accountingVoucher.getSNo();// 科目编码
			String accvSName = accountingVoucher.getSName();// 科目名称
			if (!vrList.isEmpty() && vrList != null) {
				for (int i = 0; i < vrList.size(); i++) {
					VoucherRules vRules = vrList.get(i);
					if (accvSNo.equals(vRules.getVfinancialNo()) && accvSName.equals(vRules.getVfinancialName())) {
						BudgetVoucher budgetVoucher = new BudgetVoucher();
						String SNo = vRules.getVbudgetaryNo();
						String SName = vRules.getVbudgetaryName();
						// String FirstAccvSName = accvSName.substring(0,accvSName.indexOf("-",
						// accvSName.indexOf("-") + 1));
						if (SNo.equals(vRules.getVfinancialNo()) && SName.equals(vRules.getVfinancialName())) {
							String newRulesBudSName = "";
							boolean falg = SName.startsWith(newRulesBudSName);
							if (!falg) {
								continue;
							}
						}
						if (StringUtils.isNotEmpty(SNo) && StringUtils.isNotEmpty(SName)) {
							// 六级科目代码
							budgetVoucher.setSNo(SNo);
							// 六级科目名称
							budgetVoucher.setSName(SName);
							/*-----以下内容保持不变------*/
							// 日期
							budgetVoucher.setVDate(accountingVoucher.getVDate());
							// 凭证编号
							budgetVoucher.setVNo(accountingVoucher.getVNo());
							// 摘要
							budgetVoucher.setVAbstract(accountingVoucher.getVAbstract());
							// 借方金额
							budgetVoucher.setVDebitAmount(accountingVoucher.getVDebitAmount());
							// 贷方金额
							budgetVoucher.setVCreditAmount(accountingVoucher.getVCreditAmount());
							// 课题段代码
							budgetVoucher.setTNo(accountingVoucher.getTNo());
							// 课题段名称
							budgetVoucher.setTName(accountingVoucher.getTName());
							// 课题所属部门名称
							budgetVoucher.setTOrganization(accountingVoucher.getTOrganization());
							try {
								baseService.saveOrUpdate(budgetVoucher);
							} catch (Exception e) {
								e.printStackTrace();
							}
							msg = "转化成功";
						} else {
							msg = "新财务凭证科目编号为" + accvSNo + "的凭证不存在对应的预算凭证";
						}
					}
				}

			}
		}
		return msg;
	}

	public String vourcherChange1(Map<String, List<AccountingVoucher>> map,Map<String, VoucherRuleName> vMap,List<String> keys,BaseService baseService) throws Exception {
		for(String acc:keys){
			List<AccountingVoucher> list=map.get(acc);
			if(list.size()>2){
			 list=removeDuplicateUser(list);
			}
			if(list!=null&&list.size()==2){
				String vCreditMoney=list.get(0).getVCreditAmount();
				String vCreditMoney1=list.get(1).getVCreditAmount();
				if(!vCreditMoney.equals(vCreditMoney1)){   
					String vFinLoanName="";
					String vFinBorrowName="";
					for(AccountingVoucher aVoucher:list){
						float creditAccount=Float.parseFloat(aVoucher.getVCreditAmount());    //贷方金额
						Float debiitMoney=Float.valueOf(aVoucher.getVDebitAmount());          //借方金额
						if(creditAccount>0||debiitMoney<0){
							if(aVoucher.getSName().contains("-")){
							vFinLoanName=aVoucher.getSName().substring(0, aVoucher.getSName().indexOf("-"));//拿到第一级科目
							}
							else{
								vFinLoanName=aVoucher.getSName(); // 银行存款
							}
						}else if(creditAccount<0||debiitMoney>0){
							if(aVoucher.getSName().contains("-")){
								vFinBorrowName=aVoucher.getSName().substring(0, aVoucher.getSName().indexOf("-"));  //固定资产
							}else{
								vFinBorrowName=aVoucher.getSName();
							}
						}
					}
					if(!vFinBorrowName.equals(vFinLoanName)){
					if(vMap.containsKey((vFinBorrowName+vFinLoanName))){
						VoucherRuleName vrName=vMap.get((vFinBorrowName+vFinLoanName));
						for(AccountingVoucher accountingVoucher:list){
							float creditAccount=Float.parseFloat(accountingVoucher.getVCreditAmount());
							if(creditAccount>0){
								if(vrName.getBudgeLoanName()!=null){
								if(accountingVoucher.getSName().contains("-")){
									accountingVoucher.setSName(vrName.getBudgeLoanName()+accountingVoucher.getSName().substring(accountingVoucher.getSName().indexOf("-")));
								}else{
									accountingVoucher.setSName(vrName.getBudgeLoanName()+"-"+accountingVoucher.getSName());
								}
								accountingVoucher.setSNo(vrName.getBudgeLoanNO()+accountingVoucher.getSNo().substring(4));   //转换
								saveBudget(accountingVoucher,baseService,0);
								saveAccount(accountingVoucher, baseService,1);//转换成功
								}else{
									saveBudget(accountingVoucher,baseService,1);
									saveAccount(accountingVoucher, baseService,1);//如果预借的名字为空 
								}
							}else{
								if(vrName.getBudgeBrrowName()!=null){
								if(accountingVoucher.getSName().contains("-")){
									accountingVoucher.setSName(vrName.getBudgeBrrowName()+accountingVoucher.getSName().substring(accountingVoucher.getSName().indexOf("-")));
								}else{
									accountingVoucher.setSName(vrName.getBudgeBrrowName()+"-"+accountingVoucher.getSName());
								}
								accountingVoucher.setSNo(vrName.getBudgeBrrowNO()+accountingVoucher.getSNo().substring(4));   //转换
								saveBudget(accountingVoucher,baseService,0);
								saveAccount(accountingVoucher, baseService,1);//转换成功
								}else{
									saveBudget(accountingVoucher,baseService,1);
									saveAccount(accountingVoucher, baseService,1);//如果预借的名字为空 
								}
							}
						}
					}
					}else{
						for(AccountingVoucher aVoucher:list){
							saveBudget(aVoucher,baseService,0);
							saveAccount(aVoucher, baseService,1);//转换成功
						}
					}
				}
			}
		}
		return msg;
	}
	
	public void saveBudget(AccountingVoucher accountingVoucher,BaseService baseService ,int status) throws Exception {
		BudgetVoucher budgetVoucher=new BudgetVoucher();
		// 六级科目代码
		budgetVoucher.setSNo(accountingVoucher.getSNo());
		// 六级科目名称
		budgetVoucher.setSName(accountingVoucher.getSName());
		/*-----以下内容保持不变------*/
		budgetVoucher.setVDate(accountingVoucher.getVDate());
		// 凭证编号
		budgetVoucher.setVNo(accountingVoucher.getVNo());
		// 摘要
		budgetVoucher.setVAbstract(accountingVoucher.getVAbstract());
		// 借方金额
		budgetVoucher.setVDebitAmount(accountingVoucher.getVDebitAmount());
		// 贷方金额
		budgetVoucher.setVCreditAmount(accountingVoucher.getVCreditAmount());
		// 课题段代码
		budgetVoucher.setTNo(accountingVoucher.getTNo());
		// 课题段名称
		budgetVoucher.setTName(accountingVoucher.getTName());
		// 课题所属部门名称
		budgetVoucher.setTOrganization(accountingVoucher.getTOrganization());
		budgetVoucher.setVStatus(status);
		baseService.saveOrUpdate(budgetVoucher);
	}
	
	public void saveAccount(AccountingVoucher accountingVoucher,BaseService baseService,int status) throws Exception {
		String hql="update accounting_voucher set V_STATUS="+status+" WHERE ID='"+accountingVoucher.getId()+"'";
		baseService.zxSql(hql);
		//accountingVoucher.setVStatus(status);
		//baseService.saveOrUpdate(accountingVoucher);
		/*AccountingVoucherNotransfer notransfer=new AccountingVoucherNotransfer();
		// 六级科目代码
		notransfer.setSNo(accountingVoucher.getSNo());
		// 六级科目名称
		notransfer.setSName(accountingVoucher.getSName());
		-----以下内容保持不变------
		// 日期
		notransfer.setVDate(accountingVoucher.getVDate());
		// 凭证编号
		notransfer.setVNo(accountingVoucher.getVNo());
		// 摘要
		notransfer.setVAbstract(accountingVoucher.getVAbstract());
		// 借方金额
		notransfer.setVDebitAmount(accountingVoucher.getVDebitAmount());
		// 贷方金额
		notransfer.setVCreditAmount(accountingVoucher.getVCreditAmount());
		// 课题段代码
		notransfer.setTNo(accountingVoucher.getTNo());
		// 课题段名称
		notransfer.setTName(accountingVoucher.getTName());
		// 课题所属部门名称
		notransfer.setTOrganization(accountingVoucher.getTOrganization());
				//ConvertUtil.convert(accountingVoucher, AccountingVoucherNotransfer.class);
		baseService.save(notransfer);*/
	}
	
	public Map<String, List<AccountingVoucher>> groupList(List<AccountingVoucher> list) {
    	Map<String, List<AccountingVoucher>> skuIdMap = new HashMap<>();
    	List<AccountingVoucher> bList=new ArrayList<>();
    	List<AccountingVoucher> cList=new ArrayList<>();
    	int i=0;
        for (AccountingVoucher aVoucher : list) {
        	i++;
        	Float creditMoney=Float.valueOf(aVoucher.getVCreditAmount());
        	Float debiitMoney=Float.valueOf(aVoucher.getVDebitAmount());
        	//System.out.println(i+"+"+creditMoney+"+"+aVoucher.getVNo());
            if (creditMoney>0||debiitMoney<0) {
            	cList.add(aVoucher);
                skuIdMap.put("loan", cList);
            }else if(creditMoney<0||debiitMoney>0){
            	bList.add(aVoucher);
            	skuIdMap.put("brrow", bList);
            }
        }

		return skuIdMap;
	}

	private List<AccountingVoucher> removeDuplicateUser(List<AccountingVoucher> accs) {
		List<AccountingVoucher> totalList=new ArrayList<>();
		Map<String, List<AccountingVoucher>> skuIdMap=groupList(accs);
		Set<AccountingVoucher> set = new TreeSet<AccountingVoucher>(new Comparator<AccountingVoucher>() {
			@Override
			public int compare(AccountingVoucher o1, AccountingVoucher o2) {
				// 字符串,则按照asicc码升序排列
				if(o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(!o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(o1.getSName().contains("-")&&!o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName());
				}else{
					return o1.getSName().compareTo(o2.getSName());
				}
			}
		});
		if(skuIdMap.containsKey("brrow")){
		set.addAll(skuIdMap.get("brrow"));
		totalList.addAll(new ArrayList<AccountingVoucher>(set));
		}
		Set<AccountingVoucher> set1 = new TreeSet<AccountingVoucher>(new Comparator<AccountingVoucher>() {
			@Override
			public int compare(AccountingVoucher o1, AccountingVoucher o2) {
				// 字符串,则按照asicc码升序排列
				if(o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(!o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(o1.getSName().contains("-")&&!o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName());
				}else{
					return o1.getSName().compareTo(o2.getSName());
				}
			}
		});
		if(skuIdMap.containsKey("loan")){
		set1.addAll(skuIdMap.get("loan"));
		totalList.addAll(new ArrayList<AccountingVoucher>(set1));
		}
		skuIdMap=null;
		return totalList;
	}
	public static AccountingVoucher produceAccountingVoucher(List<String> cellList){
		AccountingVoucher accv=new AccountingVoucher();
		
		 // 获取旧的凭证编号
		String oldVNo = cellList.get(1).toString();
		// 摘要
		String oldVAbstract = cellList.get(2).toString();
		// 借方金额
		String oldVDebitAmount = cellList.get(3).toString();
		// 贷方金额
		String oldVCreditAmount = cellList.get(4).toString();
		// 课题编号
		String oldTNo = cellList.get(5).toString();
		// 科目名称
		String oldSNo = cellList.get(7).toString();
		// 日期
		accv.setVDate(cellList.get(0).toString());
		// 凭证编号
		accv.setVNo(oldVNo);
		// 摘要
		accv.setVAbstract(oldVAbstract);
		// 借方金额
		accv.setVDebitAmount(oldVDebitAmount);
		// 贷方金额
		accv.setVCreditAmount(oldVCreditAmount);
		// 课题段代码
		accv.setTNo(oldTNo);
		// 课题段名称
		accv.setTName(cellList.get(6).toString());
		// 六级科目代码
		accv.setSNo(oldSNo);
		// 六级科目名称
		accv.setSName(cellList.get(8).toString());
		// 课题所属部门名称
		accv.setTOrganization(cellList.get(9).toString());
		
		accv.setVStatus(0);	
					
		return accv;
	}
	
	public static OldAccountingVoucher produceOldAccountingVoucher(List<String> cellList){
		        // 获取旧的凭证编号
				String oldVNo = cellList.get(1).toString();
				// 摘要
				String oldVAbstract = cellList.get(2).toString();
				// 借方金额
				String oldVDebitAmount = cellList.get(3).toString();
				// 贷方金额
				String oldVCreditAmount = cellList.get(4).toString();
				// 课题编号
				String oldTNo = cellList.get(5).toString();
				// 科目名称
				String oldSNo = cellList.get(7).toString();
				// 日期
				OldAccountingVoucher oldaccv=new OldAccountingVoucher();
				oldaccv.setVDate(cellList.get(0).toString());
				// 凭证编号
				oldaccv.setVNo(oldVNo);
				// 摘要
				oldaccv.setVAbstract(oldVAbstract);
				// 借方金额
				oldaccv.setVDebitAmount(oldVDebitAmount);
				// 贷方金额
				oldaccv.setVCreditAmount(oldVCreditAmount);
				// 课题段代码
				oldaccv.setTNo(oldTNo);
				// 课题段名称
				oldaccv.setTName(cellList.get(6).toString());
				// 六级科目代码
				oldaccv.setSNo(oldSNo);
				// 六级科目名称
				oldaccv.setSName(cellList.get(8).toString());
				// 课题所属部门名称
				oldaccv.setTOrganization(cellList.get(9).toString());
							
				return oldaccv;
			}
	
	
	//转换细节逻辑
	public static void  transRules(List<AccountingVoucher> accVouchers,String cName, Map<String,VoucherRules> map,AccountingVoucher accv,String... sp){
		if(!map.containsKey(cName)){
			 //一级转换规则不存在，数据不做任何转换	
			 	//记录信息
			   accv.setVAccountInformation(cName);  //记录不能转换的，后续 需要优化算法
			   accVouchers.add(accv);
		   }else{
			 //这个在规则表中有，但不确定转换规则是否完整
			   VoucherRules voucherRules = map.get(cName);
			   String vfinancialName = voucherRules.getVfinancialName();
			   if(vfinancialName==null){ //规则不完整，转换后得值不能为null					   
				   accVouchers.add(accv);   
			   }else{    //规则完整，正常转换
				   String target = voucherRules.getVfinancialNo();
				   accv.setSNo(target);
				   accv.setSName(vfinancialName);
				   accVouchers.add(accv);   
				   
			   }
		   }		
	}
	
	
	//转换规则细节逻辑
    public static void  transRulesfuction(OldAccountingVoucher oldaccv,List<AccountingVoucher> accVouchers, Map<String,VoucherRules> map,AccountingVoucher accv){
        VoucherRules voucherRules=null;
        String targetPart_Name="";
        String targetPart_No="";
        String ys_sNo="";
        String ys_sName="";
        //拿到旧财务表的科目名称
        String sName = oldaccv.getSName();
        ys_sName=sName;
        //@can8/21新改的
        String[] sp = sName.split("-");
        //判断“-”的个数
        int num=sp.length-1;
        Set<String> keySet = map.keySet();
        if(num==0){//只有一级目录
            boolean isConstain=false;
            for (String string : keySet) {
                if(string.equals(sName)){
                    isConstain=true;
                    voucherRules = map.get(sName);
                    targetPart_Name = voucherRules.getVfinancialName();
                    targetPart_No = voucherRules.getVfinancialNo();                    
                    //截取原始科目编号，科目名称  并完成拼接                   
                    accv.setSName(targetPart_Name);
                    accv.setSNo(targetPart_No);
                    accVouchers.add(accv);
                    break;
                }
            }
            if(!isConstain){
            	accVouchers.add(accv);
            }
        }
        else if(num==1){ //只有两级科目
        	boolean isConstain=false;
        	for (String string : keySet) {
        		if(string.equals(sName)){//判断二级科目
        			isConstain=true;
        			voucherRules = map.get(sName);
                    targetPart_Name = voucherRules.getVfinancialName();
                    targetPart_No = voucherRules.getVfinancialNo();                    
                    //截取原始科目编号，科目名称  并完成拼接                   
                    accv.setSName(targetPart_Name);
                    accv.setSNo(targetPart_No);
                    accVouchers.add(accv);
                    break;
        		}
        	}
        	if(!isConstain){//判断一级科目
        		boolean isNext=false;
        		sName=sp[0];
        		for (String string : keySet) {
            		if(string.equals(sName)){//判断第一级科目
            			isNext=true;
            			voucherRules = map.get(sName);
                        targetPart_Name = voucherRules.getVfinancialName();
                        targetPart_No = voucherRules.getVfinancialNo();                    
                        //截取原始科目编号，科目名称  并完成拼接                          
                        targetPart_Name=ys_sName.replaceAll(sName, targetPart_Name);
                        accv.setSName(targetPart_Name);
                        //获取规则中要转换的凭证号 
                        ys_sNo = oldaccv.getSNo();
                        targetPart_No=ys_sNo.replaceAll(voucherRules.getVoldSubjectNo(), voucherRules.getVfinancialNo());  //获取规则中要转换的凭证号替换
                        accv.setSNo(targetPart_No);
                        accVouchers.add(accv);
                        break;
            		}
            	}
        		if(!isNext){
        			accVouchers.add(accv);
        		}
        	}
        }
        else if(num==2){//判断三级目录
        	boolean isConstain=false;
        	for (String string : keySet) {
        		if(string.equals(sName)){//判断二级科目
        			isConstain=true;
        			voucherRules = map.get(sName);
                    targetPart_Name = voucherRules.getVfinancialName();
                    targetPart_No = voucherRules.getVfinancialNo();                    
                    //截取原始科目编号，科目名称  并完成拼接                   
                    accv.setSName(targetPart_Name);
                    accv.setSNo(targetPart_No);
                    accVouchers.add(accv);
                    break;
        		}
        	}
        	if(!isConstain){
        		boolean isNext=false;
        		sName=sp[0]+"-"+sp[1];
            	for (String string : keySet) {
            		if(string.equals(sName)){//判断二级科目
            			isNext=true;
            			voucherRules = map.get(sName);
                        targetPart_Name = voucherRules.getVfinancialName();
                        targetPart_No = voucherRules.getVfinancialNo();                    
                        //截取原始科目编号，科目名称  并完成拼接                          
                        targetPart_Name=ys_sName.replaceAll(sName, targetPart_Name);
                        accv.setSName(targetPart_Name);
                        //获取规则中要转换的凭证号 
                        ys_sNo = oldaccv.getSNo();
                        targetPart_No=ys_sNo.replaceAll(voucherRules.getVoldSubjectNo(), voucherRules.getVfinancialNo());  //获取规则中要转换的凭证号替换
                        accv.setSNo(targetPart_No);
                        accVouchers.add(accv);
                        break;
            		}
            	}
            	if(!isNext){
            		boolean isOk=false;
            		sName=sp[0];
            		for (String string : keySet) {
                		if(string.equals(sName)){//判断二级科目
                			isOk=true;
                			voucherRules = map.get(sName);
                            targetPart_Name = voucherRules.getVfinancialName();
                            targetPart_No = voucherRules.getVfinancialNo();                    
                            //截取原始科目编号，科目名称  并完成拼接                          
                            targetPart_Name=ys_sName.replaceAll(sName, targetPart_Name);
                            accv.setSName(targetPart_Name);
                            //获取规则中要转换的凭证号 
                            ys_sNo = oldaccv.getSNo();
                            targetPart_No=ys_sNo.replaceAll(voucherRules.getVoldSubjectNo(), voucherRules.getVfinancialNo());  //获取规则中要转换的凭证号替换
                            accv.setSNo(targetPart_No);
                            accVouchers.add(accv);
                            break;
                		}
            		}
            		if(!isOk){
            			accVouchers.add(accv);
            		}
            	}
            }
        }
        else{ // 判断前三级目录
        	sName=sp[0]+"-"+sp[1]+"-"+sp[2];
        	boolean isConstain=false;
        	for (String string : keySet) {
        		if(string.equals(sName)){//判断二级科目
        			isConstain=true;
        			voucherRules = map.get(sName);
                    targetPart_Name = voucherRules.getVfinancialName();
                    targetPart_No = voucherRules.getVfinancialNo();                    
                    //截取原始科目编号，科目名称  并完成拼接                          
                    targetPart_Name=ys_sName.replaceAll(sName, targetPart_Name);
                    accv.setSName(targetPart_Name);
                    //获取规则中要转换的凭证号 
                    ys_sNo = oldaccv.getSNo();
                    targetPart_No=ys_sNo.replaceAll(voucherRules.getVoldSubjectNo(), voucherRules.getVfinancialNo());  //获取规则中要转换的凭证号替换
                    accv.setSNo(targetPart_No);
                    accVouchers.add(accv);
                    break;
        		}
        	}
        	if(!isConstain){
        		boolean isNext=false;
        		sName=sp[0]+"-"+sp[1];
            	for (String string : keySet) {
            		if(string.equals(sName)){//判断二级科目
            			isNext=true;
            			voucherRules = map.get(sName);
                        targetPart_Name = voucherRules.getVfinancialName();
                        targetPart_No = voucherRules.getVfinancialNo();                    
                        //截取原始科目编号，科目名称  并完成拼接                          
                        targetPart_Name=ys_sName.replaceAll(sName, targetPart_Name);
                        accv.setSName(targetPart_Name);
                        //获取规则中要转换的凭证号 
                        ys_sNo = oldaccv.getSNo();
                        targetPart_No=ys_sNo.replaceAll(voucherRules.getVoldSubjectNo(), voucherRules.getVfinancialNo());  //获取规则中要转换的凭证号替换
                        accv.setSNo(targetPart_No);
                        accVouchers.add(accv);
                        break;
            		}
            	}
            	if(!isNext){
            		boolean isOk=false;
            		sName=sp[0];
            		for (String string : keySet) {
                		if(string.equals(sName)){//判断二级科目
                			isOk=true;
                			voucherRules = map.get(sName);
                            targetPart_Name = voucherRules.getVfinancialName();
                            targetPart_No = voucherRules.getVfinancialNo();                    
                            //截取原始科目编号，科目名称  并完成拼接                          
                            targetPart_Name=ys_sName.replaceAll(sName, targetPart_Name);
                            accv.setSName(targetPart_Name);
                            //获取规则中要转换的凭证号 
                            ys_sNo = oldaccv.getSNo();
                            targetPart_No=ys_sNo.replaceAll(voucherRules.getVoldSubjectNo(), voucherRules.getVfinancialNo());  //获取规则中要转换的凭证号替换
                            accv.setSNo(targetPart_No);
                            accVouchers.add(accv);
                            break;
                		}
            		}
            		if(!isOk){
            			accVouchers.add(accv);
            		}
            	}
            }	
        }
        
    }
    
	
	public List<VoucherRules> getVrList() {
		return vrList;
	}

	public void setVrList(List<VoucherRules> vrList) {
		this.vrList = vrList;
	}

	public List<OldAccountingVoucher> getOldAccountingVouchers() {
		return oldAccountingVouchers;
	}

	public void setOldAccountingVouchers(List<OldAccountingVoucher> oldAccountingVouchers) {
		this.oldAccountingVouchers = oldAccountingVouchers;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<OldAccountingVoucher> getOcvList() {
		return ocvList;
	}

	public void setOcvList(List<OldAccountingVoucher> ocvList) {
		this.ocvList = ocvList;
	}

	public List<AccountingVoucher> getAccountingVouchers() {
		return accountingVouchers;
	}

	public void setAccountingVouchers(List<AccountingVoucher> accountingVouchers) {
		this.accountingVouchers = accountingVouchers;
	}

	public List<VoucherRuleName> getVoucherRuleNames() {
		return voucherRuleNames;
	}

	public void setVoucherRuleNames(List<VoucherRuleName> voucherRuleNames) {
		this.voucherRuleNames = voucherRuleNames;
	}

}
