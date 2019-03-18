package com.client;

import com.entity.AccountingVoucher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MySQLTest {
    private Connection con = null;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/zdh_pzgl";
    private static String username = "root";
    private static String password = "123qwe@@";
    private static Statement NULL = null;
    private int taskNum;
    public MySQLTest(int i) {
        this.taskNum=i;
    }

    public Statement MysqlOpen() {
        try {
            Class.forName(driver); //加载驱动类
            con = DriverManager.getConnection(url, username, password); //连接数据库
            if (!con.isClosed())
                System.out.println("***数据库成功连接***");
            Statement state = (Statement) con.createStatement();
            return state;
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类，加载驱动失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return NULL;
    }

    public List<AccountingVoucher> readMySQL(String sqlParam) {
        ResultSet sql = null;
        Statement state = MysqlOpen();
        List<AccountingVoucher> list=new ArrayList<AccountingVoucher>();
        try {           
        	sql=state.executeQuery(sqlParam);
        	System.out.println("---------task " + taskNum + "正在执行---------");
            while (sql.next()) {
                AccountingVoucher accv = new AccountingVoucher();
                String V_DATE = sql.getString("V_DATE");
                String V_NO=sql.getString("V_NO");
                String V_ABSTRACT=sql.getString("V_ABSTRACT");
                String V_DEBIT_AMOUNT=sql.getString("V_DEBIT_AMOUNT");
                String V_CREDIT_AMOUNT=sql.getString("V_CREDIT_AMOUNT");
                String T_NO=sql.getString("T_NO");
                String T_NAME=sql.getString("T_NAME");
                String S_NO=sql.getString("S_NO");
                String S_NAME=sql.getString("S_NAME");

                accv.setVDate(V_DATE);
                accv.setVNo(V_NO);
                accv.setVAbstract(V_ABSTRACT);
                accv.setVDebitAmount(V_DEBIT_AMOUNT);
                accv.setVCreditAmount(V_CREDIT_AMOUNT);
                accv.setTNo(T_NO);
                accv.setTName(T_NAME);
                accv.setSNo(S_NO);
                accv.setSName(S_NAME);

                list.add(accv);
            }
            Iterator<AccountingVoucher> it = list.iterator();
            while(it.hasNext()){
                AccountingVoucher next = it.next();
                if(next==null){
                    list.remove(next);
                }
                if(!"".equals(next)&&next.getVNo()==null){
                    list.remove(next);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sql.close();
                state.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("---------task " + taskNum + "执行完毕---------");
        return list;
    }
}