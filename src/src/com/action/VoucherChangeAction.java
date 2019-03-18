package com.action;

import com.entity.AccountingVoucher;
import com.entity.BudgetVoucher;
import com.entity.VoucherRuleName;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.JsonUtil;
import org.apache.struts2.interceptor.RequestAware;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class VoucherChangeAction extends ActionSupport  implements RequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AccountingVoucher> accountingVouchers;
	private String jsonStr;
	private List<BudgetVoucher> budgetVouchers;
	private BaseService baseService;
	private Map<String, Object> msgMap;
	private Map<String, Object> map;
	private Map<Object, Object> jsonmap;
	private List<VoucherRuleName> voucherRuleNames;
	
	//李鑫测试使用的例子	
	public String voucherChangeToApplay1() throws Exception{
		String desc=null;
		String username=null;
		String debit=null;
		String VCreditAmount=null;
		String SNo=null;
		accountingVouchers=new ArrayList<AccountingVoucher>();
		budgetVouchers=new ArrayList<BudgetVoucher>();
		if(!"".equals(jsonStr) && jsonStr!=null){
			com.alibaba.fastjson.JSONArray array = JsonUtil.toArray(jsonStr);
			int index=array.size();
			for(int i=0;i<index;i++){
				AccountingVoucher account=new AccountingVoucher();
				com.alibaba.fastjson.JSONObject obj = (com.alibaba.fastjson.JSONObject) array.get(i);
			    String kmname = obj.getString("kmname");
				if(kmname!="" && !"".equals(kmname)){
					desc = obj.getString("desc");
				    account.setVAbstract(desc);//摘要				    
				    account.setSName(kmname);//科目名称					
				    VCreditAmount = obj.getString("lender");
				    if("".equals(VCreditAmount)){
				    	VCreditAmount="0";
				    }
				    account.setVCreditAmount(VCreditAmount);//贷方金额

				    SNo = obj.getString("SNo");
					account.setSNo(SNo);
					
				    username = obj.getString("username");
				    account.setVHandleName(username);//经手人
				    
				    debit = obj.getString("debit");
				    if("".equals(debit)){
				    	debit="0";
				    }
				    account.setVDebitAmount(debit);//借方金额
					accountingVouchers.add(account);
				}
			}
		BudgetVoucher bv=null;
		for (AccountingVoucher accv : accountingVouchers) {
			bv=new BudgetVoucher();
			if(accv.getSName().equals("业务活动费用-科研活动-非财政项目-商品和服务支出-差旅费-市内交通费")){
				bv.setSName("事业支出-科研支出-非财政项目-商品和服务支出-差旅费-市内交通费");
				bv.setVCreditAmount(accv.getVCreditAmount());//贷方金额
				bv.setVDebitAmount(accv.getVDebitAmount());//借方金额
			}else{
				bv.setSName("资金结存-货币资金-人民币-银行存款-基本户-光大银行32442");
				bv.setVCreditAmount(accv.getVCreditAmount());//贷方金额
				bv.setVDebitAmount(accv.getVDebitAmount());//借方金额
			}
			bv.setVAbstract(desc);//摘要		   		    		    
		    bv.setSNo(SNo);				   
		    bv.setVHandleName(username);//经手人		    		    		    
			budgetVouchers.add(bv);	
		}
		map=new HashMap<>();
		map.put("message", "数据转换成功");
		map.put("budgetVouchers",budgetVouchers);
	  }
		return "budget";
}
	
	public String voucherChangeToApplay() throws Exception{
		accountingVouchers=new ArrayList();
		if(!"".equals(jsonStr) && jsonStr!=null){
			com.alibaba.fastjson.JSONArray array = JsonUtil.toArray(jsonStr);
			int index=array.size();
			for(int i=0;i<index;i++){
				AccountingVoucher account=new AccountingVoucher();
				com.alibaba.fastjson.JSONObject obj = (com.alibaba.fastjson.JSONObject) array.get(i);
			    String kmname = obj.getString("kmname");
				if(kmname!="" && !"".equals(kmname)){
					String desc = obj.getString("desc");
				    account.setVAbstract(desc);//摘要
				    
				    account.setSName(kmname);//科目名称
					
				    String VCreditAmount = obj.getString("lender");
				    if("".equals(VCreditAmount)){
				    	VCreditAmount="0";
				    }
				    account.setVCreditAmount(VCreditAmount);//贷方金额

				    String SNo = obj.getString("SNo");
					account.setSNo(SNo);
					
				    String username = obj.getString("username");
				    account.setVHandleName(username);//经手人
				    
				    String debit = obj.getString("debit");
				    if("".equals(debit)){
				    	debit="0";
				    }
				    account.setVDebitAmount(debit);//借方金额
					accountingVouchers.add(account);
				}
			}
			map = this.newchangeToVoucher(accountingVouchers);							
		}
		return "budget";
	}
	
	
	/**
	 * 新版官网试用版凭证转换
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> newchangeToVoucher(List<AccountingVoucher> list) throws Exception{
		InputStream inputStream=null;		
	    OutputStream outputStream=null;	
	    Socket socket=null;
	    Map<String,Object> map=null;
	    budgetVouchers=new ArrayList<BudgetVoucher>();
		//构建要求的数据结构
	    String sendMeg="";   // [a,b]===1#a1;a2;a31#b1;b2;b3
		String flag="1#";
		sendMeg=flag;
		if(list.size()>0){
			AccountingVoucher t=null;
			Iterator<AccountingVoucher> it = list.iterator();
			while (it.hasNext())// 判断是不是最后一个
			{
				t = it.next();
				sendMeg+=(t.getDataFormat()+";");
			}
			//处理最后一条记录;
			sendMeg=sendMeg.substring(0, sendMeg.length()-1)+"#";	//多条凭证用“#”分割					
			}
		//凭证转换过程
		try{			
			// 要连接的服务端IP地址和端口
		    String host = "localhost";
		    int port = 8829;
		    // 与服务端建立连接
		    socket = new Socket();
		    socket.connect(new InetSocketAddress(host, port), 30000);
		    long startTime=System.currentTimeMillis();
		    // 建立连接后获得输出流
		     outputStream = socket.getOutputStream();	 
		    
		    if(sendMeg!=null){
		    	sendMeg=sendMeg.substring(0, sendMeg.length()-1);  //去掉最后边得“#”  	
		    }
		    System.out.println(sendMeg);		    
		    String message = sendMeg;	    
		    StringBuilder sb=null;
		    //写数据
		    outputStream.write(message.getBytes("GBK"));		    
		    //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
		    socket.shutdownOutput();
		    message = null;
		    //接收数据
		     inputStream = socket.getInputStream();
		    byte[] bytes = new byte[1024];
		    int len;
		    sb = new StringBuilder();
		    while ((len = inputStream.read(bytes)) != -1) {
		    //注意指定编码格式，发送方和接收方一定要统一
		      sb.append(new String(bytes, 0, len,"GBK"));
		    }
		    //测试用的数据    后期一定要注掉
		    System.out.println("-SB--:"+sb.toString());
		    //校验接收数据的格式
		    if(sb!=null&&sb.toString().contains("data_wrong")){
		    	message="数据格式错误";		    	 
		    }
		    if(sb!=null&&sb.toString().contains("transform_none")){
		    	message="数据不需要转换";
		    }
		    if(sb!=null&&sb.toString().contains("transform_fail")){
		    	message="数据转换失败";
		    }
		    if(sb!=null&&!sb.toString().contains("transform_")){  //转换成功，处理数据	
		    	message="数据转换成功";
		    	String[] sp = sb.toString().split("#");
		    	if(sp.length==2){
		    		String str_vou = sp[1];  //一条凭证的俩条信息
		    		String[] voucherString = str_vou.split(";");  //获取每一条凭证信息
		    		if(voucherString!=null){
		    			for (String str : voucherString) {
		    				BudgetVoucher bv = new BudgetVoucher();
							if(str!=null&&!"".equals(str)){
								String[] element = str.split(",");
								if(element!=null){
									bv.setVDate(element[0]);
									bv.setVNo(element[1]);
									bv.setVAbstract(element[2]);
									if(element[3].equals("贷")){
										bv.setVDebitAmount("0");
										bv.setVCreditAmount(element[4]);
									}else{
										bv.setVDebitAmount(element[4]);
										bv.setVCreditAmount("0");
									}
									bv.setTNo(element[5]);
									bv.setTName(element[6]);
									bv.setSNo(element[7]);
									bv.setSName(element[8]);
									}
								budgetVouchers.add(bv);
							} //手动获取经手人信息,通过可以唯一标识的借贷金额确定
							for(BudgetVoucher budget :budgetVouchers){
								//获取借贷金额信息
								String vDebitAmount = budget.getVDebitAmount();
								String vCreditAmount = budget.getVCreditAmount();
								for (AccountingVoucher accv : list) { //借贷关系同时满足
									if(accv.getVDebitAmount().equals(vDebitAmount)&&accv.getVCreditAmount().equals(vCreditAmount)){
										budget.setVHandleName(accv.getVHandleName());
									}
								}
							}
						}
		    		}
		    	}else{
		    		message="暂不支持，敬请期待";
		    	}
		    }
		    map=new HashMap<>();
		    if(budgetVouchers.size()!=0){
		    	map.put("budgetVouchers", budgetVouchers);
		    }
		    map.put("message", message);	    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(inputStream!=null)   inputStream.close();	    
			if(outputStream!=null)  outputStream.close();	   
			if(socket!=null)   		socket.close();	
		}
		//得到结果
		return map;
	}
	
	public String oldvoucherChangeToApplay() throws Exception{
		accountingVouchers=new ArrayList();
		if(!"".equals(jsonStr) && jsonStr!=null){
			com.alibaba.fastjson.JSONArray array = JsonUtil.toArray(jsonStr);
			int index=array.size();
			for(int i=0;i<index;i++){
				AccountingVoucher account=new AccountingVoucher();
				com.alibaba.fastjson.JSONObject obj = (com.alibaba.fastjson.JSONObject) array.get(i);
			    String kmname = obj.getString("kmname");
				if(kmname!="" && !"".equals(kmname)){
					String desc = obj.getString("desc");
				    account.setVAbstract(desc);//摘要
				    
				    account.setSName(kmname);//科目名称
					
				    String VCreditAmount = obj.getString("lender");
				    if("".equals(VCreditAmount)){
				    	VCreditAmount="0";
				    }
				    account.setVCreditAmount(VCreditAmount);//贷方金额

				    String SNo = obj.getString("SNo");
					account.setSNo(SNo);
					
				    String username = obj.getString("username");
				    account.setVHandleName(username);//经手人

				    String debit = obj.getString("debit");
				    if("".equals(debit)){
				    	debit="0";
				    }
				    account.setVDebitAmount(debit);//借方金额
					accountingVouchers.add(account);
				}
			}
			budgetVouchers=this.changeToVoucher(accountingVouchers);
			/*if(budgetVouchers.size()==0){
				budgetVouchers=baseService.findObjList(" from BudgetVoucher");
			}*/
			msgMap=new HashMap();
			msgMap.put("budgetVouchers", budgetVouchers);
		}
		return "budget";
	}
	
	/**
	 * 官方试用版凭证转换
	 * @author 
	 */
	public List changeToVoucher(List<AccountingVoucher> vouchers) throws Exception{
		/****************查询规则表*******************/
		voucherRuleNames = baseService.findObjList("from VoucherRuleName");
		Map<String, VoucherRuleName> vMap=new HashMap<>();
		for(VoucherRuleName vRuleName:voucherRuleNames){
			String tName=vRuleName.getFinBrrowName()+vRuleName.getFinLoanName();
			vMap.put(tName, vRuleName);
		}
		Map<String, List<AccountingVoucher>> map=groupList(vouchers);
		List<String> keys = new ArrayList<String>(map.keySet());
		List<BudgetVoucher> vourcherChangeToBudgetVoucher = vourcherChangeToBudgetVoucher(map,vMap,keys,baseService);
		return vourcherChangeToBudgetVoucher;
	}
	
	/**
	 * 官网页面凭证转换
	 * @param map
	 * @param vMap
	 * @param keys
	 * @param baseService
	 * @return
	 * @throws Exception
	 */
	public List<BudgetVoucher> vourcherChangeToBudgetVoucher(Map<String, List<AccountingVoucher>> map,Map<String, VoucherRuleName> vMap,List<String> keys,BaseService baseService) throws Exception {
		List<BudgetVoucher> budgeList=new ArrayList<BudgetVoucher>();
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
						float creditAccount=Float.parseFloat(aVoucher.getVCreditAmount());
						Float debiitMoney=Float.valueOf(aVoucher.getVDebitAmount());
						if(creditAccount>0||debiitMoney<0){
							if(aVoucher.getSName().contains("-")){
							vFinLoanName=aVoucher.getSName().substring(0, aVoucher.getSName().indexOf("-"));
							}
							else{
								vFinLoanName=aVoucher.getSName();
							}
						}else if(creditAccount<0||debiitMoney>0){
							if(aVoucher.getSName().contains("-")){
								vFinBorrowName=aVoucher.getSName().substring(0, aVoucher.getSName().indexOf("-"));
							}else{
								vFinBorrowName=aVoucher.getSName();
							}
						}
					}
					if(!vFinBorrowName.equals(vFinLoanName)){
					if(vMap.containsKey((vFinBorrowName+vFinLoanName))){
						BudgetVoucher budget=new BudgetVoucher();
						//转换规则表
						VoucherRuleName vrName=vMap.get((vFinBorrowName+vFinLoanName));
						for(AccountingVoucher accountingVoucher:list){
							//获取贷方金额
							float creditAccount=Float.parseFloat(accountingVoucher.getVCreditAmount());
							if(creditAccount>0){
								//获取预算贷款名称
								if(vrName.getBudgeLoanName()!=null){
								if(accountingVoucher.getSName().contains("-")){
									accountingVoucher.setSName(vrName.getBudgeLoanName()+accountingVoucher.getSName().substring(accountingVoucher.getSName().indexOf("-")));
								}else{
									accountingVoucher.setSName(vrName.getBudgeLoanName()+"-"+accountingVoucher.getSName());
								}
								//accountingVoucher.setSNo(vrName.getBudgeLoanNO()+accountingVoucher.getSNo().substring(4));
								budget = chageToBudget(accountingVoucher,0);
								}else{
									budget = chageToBudget(accountingVoucher,1);
								}
							}else{
								//预算借款编号
								if(vrName.getBudgeBrrowName()!=null){
								//科目名称
								if(accountingVoucher.getSName().contains("-")){
									accountingVoucher.setSName(vrName.getBudgeBrrowName()+accountingVoucher.getSName().substring(accountingVoucher.getSName().indexOf("-")));
								}else{
									accountingVoucher.setSName(vrName.getBudgeBrrowName()+"-"+accountingVoucher.getSName());
								}
								//accountingVoucher.setSNo(vrName.getBudgeBrrowNO()+accountingVoucher.getSNo().substring(4));
								budget = chageToBudget(accountingVoucher,0);
								}else{
									budget = chageToBudget(accountingVoucher,1);
								}
							}
							budgeList.add(budget);
						}
					}
					}else{
						for(AccountingVoucher aVoucher:list){
							BudgetVoucher budget = chageToBudget(aVoucher,0);
							budgeList.add(budget);
						}
					}
				}
			}
		}
		return budgeList;
	}
	/**
	 * bean转换
	 * @param accountingVoucher
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public BudgetVoucher chageToBudget(AccountingVoucher accountingVoucher,int status) throws Exception {
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
		// 经手人
		budgetVoucher.setVHandleName(accountingVoucher.getVHandleName());
		// 课题段名称
		budgetVoucher.setTName(accountingVoucher.getTName());
		// 课题所属部门名称
		budgetVoucher.setTOrganization(accountingVoucher.getTOrganization());
		budgetVoucher.setVStatus(status);
		return budgetVoucher;
	}
    public Map<String, List<AccountingVoucher>> groupList(List<AccountingVoucher> list) {
    	Map<String, List<AccountingVoucher>> skuIdMap = new HashMap<>();
        for (AccountingVoucher aVoucher : list) {
            List<AccountingVoucher> tempList = skuIdMap.get(aVoucher.getVNo());
            /*如果取不到数据,那么直接new一个空的ArrayList**/
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(aVoucher);
                skuIdMap.put(aVoucher.getVNo(), tempList);
            }
            else {
                /*某个sku之前已经存放过了,则直接追加数据到原来的List里**/
                tempList.add(aVoucher);
            }
        }

		return skuIdMap;
	}
	private List<AccountingVoucher> removeDuplicateUser(List<AccountingVoucher> accs) {
		List<AccountingVoucher> totalList=new ArrayList<>();
		Map<String, List<AccountingVoucher>> skuIdMap=groupList1(accs);
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
    public Map<String, List<AccountingVoucher>> groupList1(List<AccountingVoucher> list) {
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

	public List<AccountingVoucher> getAccountingVouchers() {
		return accountingVouchers;
	}

	public void setAccountingVouchers(List<AccountingVoucher> accountingVouchers) {
		this.accountingVouchers = accountingVouchers;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public List<BudgetVoucher> getBudgetVouchers() {
		return budgetVouchers;
	}

	public void setBudgetVouchers(List<BudgetVoucher> budgetVouchers) {
		this.budgetVouchers = budgetVouchers;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public Map<String, Object> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, Object> msgMap) {
		this.msgMap = msgMap;
	}

	public List<VoucherRuleName> getVoucherRuleNames() {
		return voucherRuleNames;
	}

	public void setVoucherRuleNames(List<VoucherRuleName> voucherRuleNames) {
		this.voucherRuleNames = voucherRuleNames;
	}


	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public Map<Object, Object> getJsonmap() {
		return jsonmap;
	}


	public void setJsonmap(Map<Object, Object> jsonmap) {
		this.jsonmap = jsonmap;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
