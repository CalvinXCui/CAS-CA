package com.util;

import java.util.List;

/**
 * 测试类
 * @author admin
 *
 */
public class TestReadExcel {

	public static void main(String[] args) throws Exception {

		ReadExcel re = new ReadExcel();
		//String flie = "C:\\Users\\admin\\Desktop\\科目.xlsx";
		String flie = "C:\\Users\\admin\\Desktop\\科目.xlsx";
		List<List<String>> list = re.read(flie, 0);//忽略前5行

		// 遍历读取结果
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print("第" + (1+i) + "行"+"\t");
 			
				List<String> cellList = list.get(i);
				for (int j = 0; j < cellList.size(); j++) {
					//System.out.print(" 第" + (j + 1) + "列值：");
					System.out.print(" " + cellList.get(j)+"\t");
				}
				System.out.println();
			}
		}
	}
}