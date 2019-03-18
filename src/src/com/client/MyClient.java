package com.client;
import com.entity.AccountingVoucher;
import com.opensymphony.xwork2.ActionContext;
import com.service.BaseService;

import java.util.*;



/**
 * Socket客户端<br>
 * @author Can
 * @Date 2018年8月8日
 * @version 1.0
 */
public class MyClient {
		
		public MyClient() {
			super();
			// TODO Auto-generated constructor stub
		}
		//构建新的字符串
		public static String queryNewVoucherGB(BaseService baseService,String... vnos) throws Exception{
			StringBuilder sendMeg=new StringBuilder();			
			// [a,b]===1#a1;a2;a31#b1;b2;b3
			String per="";
			String hql=null;
					if(vnos!=null&&vnos.length==1){  //单条凭证转预算
					hql="from AccountingVoucher a where a.VNo='"+vnos[0]+"' and a.transtype='0' and  a.VStatus='0'";
					List<AccountingVoucher> list = baseService.findObjList(hql);
					per = BootStrap.dataFormat(list);
					sendMeg.append(per);
					System.out.println(per);
					}
					else if(vnos!=null&&vnos.length!=1&&vnos.length!=0){   //批量部分凭证转预算
						for (String vno : vnos) {
							hql="from AccountingVoucher a where a.VNo='"+vno+"' and a.transtype='0' and a.VStatus='0' ";
							List<AccountingVoucher> list = baseService.findObjList(hql);
							per = BootStrap.dataFormat(list);// 构建格式化字符串
							sendMeg.append(per);   //StringBuilder拼接
							}						
						}
					else{  //一键全部转预算     vnos==null 也可以说 vnos=[a,b,c,d,e,f,g,...]
 						Threadreading td = new Threadreading();
						List<AccountingVoucher> listAll = td.executor("select  V_DATE,V_NO,V_ABSTRACT,V_DEBIT_AMOUNT,V_CREDIT_AMOUNT,T_NO,T_NAME,S_NO,S_NAME from accounting_voucher where V_STATUS='0' and TRANSTYPE='0' ");
						if(listAll.size()!=0){
							Map<String,Object> mapString = new HashMap<>();
							Map<String,List<AccountingVoucher>> map = new HashMap<>();
							
							for (AccountingVoucher accv : listAll) {
								mapString.put(accv.getVNo(),accv);
							}
							
							List<List<AccountingVoucher>> resultList=new ArrayList<>();//存放每条需要转换的凭证，将来与转换结果对比
							Set<String> vno_s = mapString.keySet();
							for (String vno : vno_s) {
								ArrayList<AccountingVoucher> accv_List = new ArrayList<>();
								for(AccountingVoucher li:listAll){
									if(vno.equals(li.getVNo())){
										accv_List.add(li);  //构建每一条凭证  
									}
									
								}
								resultList.add(accv_List);
								map.put(vno, accv_List);  
							}
							ActionContext.getContext().getSession().put("resultList", resultList);
							//遍历map 的键值 
							Set<String> VNos = map.keySet();
							System.out.println(VNos.size());
							int count=0;
							for (String vno : VNos) {
								System.out.println(count++);
								List<AccountingVoucher> list =map.get(vno); 
								System.out.println(list.size());				
								System.out.println(list.get(0).getVNo());					
								per = BootStrap.dataFormat(list);
								sendMeg.append(per);
							}
						}
					}
			return sendMeg.toString();
		}	
}

