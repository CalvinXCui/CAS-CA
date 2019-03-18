package com.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringResult{
    public List<String> getResultNum(String index_0) {
        String input = index_0;  //返回标志
        String regex = "\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> listNum = new ArrayList<String>();
        while (matcher.find()) {
            listNum.add(matcher.group());
        }
        return listNum;
    }
}