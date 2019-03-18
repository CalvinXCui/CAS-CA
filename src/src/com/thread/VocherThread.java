package com.thread;

import java.util.List;

import com.action.VoucherThread;
import com.entity.AccountingVoucher;
import com.entity.OldAccountingVoucher;
import com.service.BaseService;

public class VocherThread extends Thread{
	
	static int i = 0;
	List<List<String>> list;
	static VoucherThread vThread;
	BaseService baseService;
	List<OldAccountingVoucher> oldAccVouchers;
	List<AccountingVoucher> accVouchers;
	@SuppressWarnings("unused")
	private static ThreadLocal<VoucherThread> threadLocal = new ThreadLocal<VoucherThread>() {
		public VoucherThread initValue() {
			return vThread;
		}
	};
	
	@SuppressWarnings("static-access")
	public VocherThread(VoucherThread vThread,List<List<String>> list,BaseService baseService,List<OldAccountingVoucher> oldAccVouchers,List<AccountingVoucher> accVouchers){
		this.vThread=vThread;
		this.list=list;
		this.baseService=baseService;
		this.oldAccVouchers=oldAccVouchers;
		this.accVouchers=accVouchers;
	}
	
	@Override
	public void run() {
		long startTime=System.currentTimeMillis()/1000;
			try {
				vThread.newexecuteList(list,baseService,oldAccVouchers,accVouchers);
			} catch (Exception e) {
				e.printStackTrace();
			}
			long endTime=System.currentTimeMillis()/1000;
			System.out.println("执行时间："+(endTime-startTime)+"秒");
	}
}
