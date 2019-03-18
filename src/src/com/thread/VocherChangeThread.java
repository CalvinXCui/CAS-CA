package com.thread;

import java.util.List;
import java.util.Map;

import com.action.VoucherThread;
import com.entity.AccountingVoucher;
import com.entity.VoucherRuleName;
import com.service.BaseService;

public class VocherChangeThread extends Thread{
	
	static int i = 0;
	//List<AccountingVoucher> list;
	List<String> list;
	static VoucherThread vThread;
	static VocherChangeThread thread;
	Map<String, List<AccountingVoucher>> map;
	Map<String, VoucherRuleName> vMap;
	BaseService baseService;
	@SuppressWarnings("unused")
	private static ThreadLocal<VocherChangeThread> threadLocal = new ThreadLocal<VocherChangeThread>() {
		public VocherChangeThread initValue() {
			return thread;
		}
	};
	
	@SuppressWarnings("static-access")
	public VocherChangeThread(VoucherThread vThread,List<String> list,Map<String, List<AccountingVoucher>> map,Map<String, VoucherRuleName> vMap,BaseService baseService){
		//public VocherChangeThread(VoucherThread vThread,List<AccountingVoucher> list,BaseService baseService){
		this.vThread=vThread;
		this.list=list;
		this.baseService=baseService;
		this.map=map;
		this.vMap=vMap;
	}
	
	@Override
	public void run() {
		    long startTime=System.currentTimeMillis()/1000;
			try {
				//vThread.vourcherChange1(map, baseService);
				vThread.vourcherChange1(map,vMap,list,baseService);
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				vThread=null;
				map=null;
				vMap=null;
				list=null;
				baseService=null;
			}
			long endTime=System.currentTimeMillis()/1000;
			System.out.println("执行时间："+(endTime-startTime)+"秒");
		
	}

	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		VocherChangeThread.i = i;
	}
	
}
