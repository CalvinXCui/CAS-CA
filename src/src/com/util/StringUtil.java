package com.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	public  static List<String>  cutStringByBite(String str,int bite){
        List<String> strList=new ArrayList<>();
        int right=0;
        int n=1;
        for (int i = 0; i < str.length(); i++) {
            if((str.charAt(i)+"t").getBytes().length==3){
                n++;
            }
            if(n>=bite){
               //System.out.println(str.substring(right,i+1));
                strList.add(str.substring(right,i+1));
                right=i+1;
                n=1;
            }else {
                n++;
            }
            if(i==str.length()-1){
               //System.out.println(str.substring(right));
               strList.add(str.substring(right));
            }

        }
        return  strList;
    }
}
