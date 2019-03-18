package com.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

/**
 * 
 * @author calvin
 *
 * @param <T>
 */
@SuppressWarnings("hiding")
public class ListSubPage<T> {
	/**
	 * list 对象分页
	 * @param list  //数据
	 * @param pagesize  //每页的条数
	 * @param currentPage  第几页
	 * @return
	 */
	public List<T> fenyeObj(List<T> list,int pagesize,int currentPage){  
		List<T> subList = null;
		int totalcount = list.size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		int i = currentPage;
		if(!list.isEmpty() && list!=null) {
			if (m == 0) {
				subList = list.subList((i - 1) * pagesize, pagesize * (i));
			} else {
				if (i == pagecount) {
					subList = list.subList((i - 1) * pagesize, totalcount);
				} else {
					subList = list.subList((i - 1) * pagesize, pagesize * (i));
				}
			}
		}
        return subList;
   }  
	
	/**
	 * 分页实现
	 * @param list
	 * @param pagesize 每页几条数据
	 * @param currentPage 当前页码
	 * @return
	 */
	public List<String> fenye(List<String> list,int pagesize,int currentPage){  
		List<String> subList = null;
		int totalcount = list.size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		int i = currentPage;
		if (m == 0) {
			subList = list.subList((i - 1) * pagesize, pagesize * (i));
		} else {
			if (i == pagecount) {
				subList = list.subList((i - 1) * pagesize, totalcount);
			} else {
				subList = list.subList((i - 1) * pagesize, pagesize * (i));
			}
		}
        return subList;
   }  
	/**
	 * test main
	 * @param args
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {    
         List<String> list=new ArrayList<String>();         
         for(int i=1;i<22;i++){    
          list.add(""+i+"");       
         }    
         //每页显示10条，显示第三条
         System.out.println(new ListSubPage().fenye(list, 10,3).toString());
     }    
 
}
