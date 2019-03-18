package com.client;

import com.entity.AccountingVoucher;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Threadreading {
    public  static int count =1;
    public List<AccountingVoucher>  executor(String sql){
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 创建存储任务的容器
        List<Callable<List<AccountingVoucher>>> tasks = new ArrayList<Callable<List<AccountingVoucher>>>();
        // 提交10个任务
        for ( int i=0; i<1; i++ ){
            Callable<List<AccountingVoucher>> task = new Callable<List<AccountingVoucher>>(){
                public List<AccountingVoucher> call(){

                    MySQLTest mySQLTest = new MySQLTest(count);
                    List<AccountingVoucher> list = mySQLTest.readMySQL(sql);
                    count++;
                    System.out.println(count);
                    return list;
                }
            };
            executorService.submit(task);
            // 将task添加进任务队列
            tasks.add( task );
        }
        // 获取10个任务的返回结果
        List<Future<List<AccountingVoucher>>> results = null;
        try {
            results = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<AccountingVoucher> totalList=new ArrayList<AccountingVoucher>();
        // 输出结果
        for ( int i=0; i<1; i++ ) {
            // 获取包含返回结果的future对象
            Future<List<AccountingVoucher>> future = results.get(i);
            // 从future中取出执行结果（若尚未返回结果，则get方法被阻塞，直到结果被返回为止）
            List<AccountingVoucher> taskReslut=null;
            try {
                taskReslut = future.get();
                System.out.println("某"+i+"线程:"+taskReslut.size());
                if(taskReslut.size()!=0){
                    for (AccountingVoucher accv: taskReslut) {
                        totalList.add(accv);
                        //System.out.println(accv.getDataFormat());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(totalList.size());
        return totalList;
    }
}
