package com.thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.action.VoucherThread;
import com.entity.AccountingVoucher;
import com.entity.OldAccountingVoucher;
import com.entity.VoucherRuleName;
import com.entity.VoucherRules;
import com.service.BaseService;

public class MyThread implements Runnable {

	List<VoucherRules> vrList;
	
	BaseService  baseService;
	
	List<OldAccountingVoucher> ocvList;

	List<OldAccountingVoucher> oldAccountingVouchers;
	
	private List<AccountingVoucher> accountingVouchers;
	
	
	private List<VoucherRuleName> voucherRuleNames;

	String msg;
    private List<List<String>> list;
    private CountDownLatch begin;
    private CountDownLatch end;

    //创建个构造函数初始化 list,和其他用到的参数
    public MyThread(List<List<String>> newlist, CountDownLatch begin, CountDownLatch end,BaseService baseService,List<OldAccountingVoucher> oldAccVouchers,List<AccountingVoucher> accv) {
        this.list = newlist;
        this.begin = begin;
        this.end = end;
        this.baseService=baseService;
        this.oldAccountingVouchers = oldAccVouchers;
        this.accountingVouchers=accv;		
    }
    @Override
    public void run() {
    	long startTime=System.currentTimeMillis()/1000;
        try {            
                //这里还要说一下，，由于在实质项目中，当处理的数据存在等待超时和出错会使线程一直处于等待状态
                //这里只是处理简单的，
                //分批 批量插入
    		this.newexecuteList2(list,baseService,oldAccountingVouchers,accountingVouchers);   	           
            //执行完让线程直接进入等待
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {		
			e.printStackTrace();
		} finally {
            //这里要主要了，当一个线程执行完 了计数要减一不然这个线程会被一直挂起
            //end.countDown()，这个方法就是直接把计数器减一的
            end.countDown();
            long endTime=System.currentTimeMillis()/1000;
			System.out.println("执行时间："+(endTime-startTime)+"秒");
        }
    }
    
    
    /**
	 * 新规则下的旧财务凭证的导入以及到新财务凭证的转换
	 * 
	 * @param list
	 * @param baseService
	 * @param oldAccVouchers
	 * @param accVouchers
	 * @return
	 * @throws Exception
	 */
	public  String newexecuteList2(List<List<String>> list, BaseService baseService,
		List<OldAccountingVoucher> oldAccVouchers, List<AccountingVoucher> accVouchers) throws Exception {
		VoucherRules voucherRules=null;
		oldAccVouchers=new ArrayList<OldAccountingVoucher>();
		accVouchers=new ArrayList<AccountingVoucher>();
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
			oldAccVouchers.add(oldaccv);
			//拿到旧财务表的科目名称
			String cName = oldaccv.getSName();
			//@can8/21新改的
			String[] sp = cName.split("-");
			//判断“-”的个数
			int num=sp.length-1;
			if(num==0){
			   AccountingVoucher accv = VoucherThread.produceAccountingVoucher(cellList);
			   VoucherThread.transRules(accVouchers, cName, map, accv);
			}
			else if(num==1){
				AccountingVoucher accv = VoucherThread.produceAccountingVoucher(cellList);
				if(!map.containsKey(cName)){
					   //二级转换规则不存在，转换构建一级规则
					   cName=sp[0];
					   VoucherThread.transRules(accVouchers, cName, map, accv);
				   }else{  //这个在规则表中有，但不确定转换规则是否完整
					   voucherRules = map.get(cName);
					   String vfinancialName = voucherRules.getVfinancialName();
					   if(vfinancialName==null){ //规则不完整，转换后得值不能为null					   
						   accVouchers.add(accv);   
					   }else{    //规则完整，正常转换
						   String target = voucherRules.getVfinancialNo();
						   String str = accv.getSNo();
						   String original = str.substring(0,6);     //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串
						   str.replace(original,target);             //把str字符串中所有的字符a都替换成字符b
						   accv.setSNo(str);
						   accv.setSName(vfinancialName);
						   accVouchers.add(accv);
					   }
				   }
			}
			else if(num==2){
				AccountingVoucher accv = VoucherThread.produceAccountingVoucher(cellList);
				if(!map.containsKey(cName)){
					   //三级转换规则不存在，构建二级转换规则
					   cName=sp[0]+"-"+sp[1];
					   if(!map.containsKey(cName)){//不存在
						   cName=sp[0];
						   VoucherThread.transRules(accVouchers, cName, map, accv);
					   }else{//存在
						   voucherRules = map.get(cName);
						   String vfinancialName = voucherRules.getVfinancialName();
						   if(vfinancialName==null){ //规则不完整，转换后得值不能为null					   
							   accVouchers.add(accv);   
						   }else{    //规则完整，正常转换
							   String target = voucherRules.getVfinancialNo();
							   String str = accv.getSNo();
							   String original = str.substring(0,6);     //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串
							   str.replace(original,target);             //把str字符串中所有的字符a都替换成字符b
							   accv.setSNo(str);
							   accv.setSName(vfinancialName);
							   accVouchers.add(accv);
						   }
					   }
					  
				   }else{  //这个在规则表中有，但不确定转换规则是否完整
					   voucherRules = map.get(cName);
					   String vfinancialName = voucherRules.getVfinancialName();
					   if(vfinancialName==null){ //规则不完整，转换后得值不能为null					   
						   accVouchers.add(accv);   
					   }else{    //规则完整，正常转换
						   String target = voucherRules.getVfinancialNo();
						   String str = accv.getSNo();
						   if(str!=null){
						   String original = str.substring(0,8);     //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串
						   str.replace(original,target);             //把str字符串中所有的字符a都替换成字符b
						   accv.setSNo(str);
						   }
						   accv.setSName(vfinancialName);
						   accVouchers.add(accv);
					   }
				   }
			}
			else{//科目大于三级的多级科目
				AccountingVoucher accv = VoucherThread.produceAccountingVoucher(cellList);
				 //说明该科目级数大于三，需要特殊处理
				for(int j = 0; j < 3; j++){       //拿到如XXXX-YYYY-ZZZZ(前边缺个“-”)
					cName = cName.substring(cName.indexOf("-")+1 );
	            }
				//构建规则中的key
				String rulebefcName=sp[0]+"-"+sp[1]+"-"+sp[2];
				voucherRules = map.get(rulebefcName);
				if(voucherRules!=null){ //根据构建的规则匹配规则对对象，若值为null，则不存在这个规则
					String rulesNamed = voucherRules.getVfinancialName();
					//判断转换规则是否完整
					if(rulesNamed==null){accVouchers.add(accv); }
					else{
					//拼串重新为cName赋值
					String target = voucherRules.getVfinancialNo();
					String str = accv.getSNo();
					if(str!=null){
					String original = str.substring(0,8);     //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串					             
					accv.setSNo(str.replaceAll(original, target));  //把str字符串中所有的字符a都替换成字符b	
					}
					cName=rulesNamed+"-"+cName;
					accv.setSName(cName);
					accVouchers.add(accv);
					}
				}else{ //三级科目规则中不存在
					rulebefcName=sp[0]+"-"+sp[1];  //构建二级科目
					if(!map.containsKey(rulebefcName)){
						//构建一级科目
						rulebefcName=sp[0];
						if(!map.containsKey(rulebefcName)){//一级科目也不包含
							accVouchers.add(accv);
						}
						else{
							voucherRules = map.get(rulebefcName);
							String vfinancialName = voucherRules.getVfinancialName();
							if(vfinancialName!=null){ //可以转换的情况
								String target = voucherRules.getVfinancialNo();
								String str = accv.getSNo();
								String original = str.substring(0,4);               //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串								
								accv.setSNo(str.replaceAll(original, target));      //把str字符串中所有的字符a都替换成字符b	
								cName=vfinancialName+"-"+sp[1]+"-"+sp[2]+"-"+cName; //%%%%$$$$$
								accv.setSName(cName);
								accVouchers.add(accv);
							}else{ //不做任何转换
								accVouchers.add(accv);
							}
						}
					}
					else{//包含二级科目  ，需要继续判断规则是否为空
						voucherRules = map.get(rulebefcName);
						String vfinancialName = voucherRules.getVfinancialName();
						if(vfinancialName!=null){    //可以转换的情况
							String target = voucherRules.getVfinancialNo();
							String str = accv.getSNo();
							String original = str.substring(0,6);               //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串								
							accv.setSNo(str.replaceAll(original, target));      //把str字符串中所有的字符a都替换成字符b
							cName=vfinancialName+"-"+sp[2]+"-"+cName;           //%%%%$$$$$
							accv.setSName(cName);
							accVouchers.add(accv);
						}else{ //判断一级科目
							rulebefcName=sp[0];
							if(map.containsKey(rulebefcName)){
								VoucherRules vr = map.get(rulebefcName);
								String vsn = vr.getVoldSubjectName();
								if(vsn!=null){
									String target = voucherRules.getVfinancialNo();
									String str = accv.getSNo();
									String original = str.substring(0,4);     //可以截取str字符串第一位字符.通过下标控制截取长度.可获得精确字符串
									accv.setSNo(str.replaceAll(original, target));      //把str字符串中所有的字符a都替换成字符b									
									cName=vsn+"-"+sp[1]+"-"+sp[2]+"-"+cName; //%%%%$$$$$
									accv.setSName(cName);
									accVouchers.add(accv);
								}else{
									accVouchers.add(accv);
								}
							}else{  //不包含
								accVouchers.add(accv);
							}
						}
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
	
/**----------------------------------get----set ------------------------------------------------**/	
	
	
	public List<VoucherRules> getVrList() {
		return vrList;
	}
	public BaseService getBaseService() {
	return baseService;
}
public void setBaseService(BaseService baseService) {
	this.baseService = baseService;
}
	public void setVrList(List<VoucherRules> vrList) {
		this.vrList = vrList;
	}
	public List<OldAccountingVoucher> getOcvList() {
		return ocvList;
	}
	public void setOcvList(List<OldAccountingVoucher> ocvList) {
		this.ocvList = ocvList;
	}
	public List<OldAccountingVoucher> getOldAccountingVouchers() {
		return oldAccountingVouchers;
	}
	public void setOldAccountingVouchers(List<OldAccountingVoucher> oldAccountingVouchers) {
		this.oldAccountingVouchers = oldAccountingVouchers;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<List<String>> getList() {
		return list;
	}
	public void setList(List<List<String>> list) {
		this.list = list;
	}
	public CountDownLatch getBegin() {
		return begin;
	}
	public void setBegin(CountDownLatch begin) {
		this.begin = begin;
	}
	public CountDownLatch getEnd() {
		return end;
	}
	public void setEnd(CountDownLatch end) {
		this.end = end;
	}	
	
}