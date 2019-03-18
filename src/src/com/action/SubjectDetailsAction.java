package com.action;

import com.entity.SubjectDetails;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.ReadExcel;

import java.util.List;

/**
* 名称 :SubjectDetailsAction
* 作者 :Calvin 
* 邮箱 :calvin_it@163.com
* 时间 :2018年5月24日 上午10:45:38
* 描述 :
*/
public class SubjectDetailsAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1343235656534994888L;
	private BaseService baseService;
	
	public void save() throws Exception {
		ReadExcel re = new ReadExcel();
		String flie = "C:\\Users\\admin\\Desktop\\科目明细2017.xlsx";
		List<List<String>> list = re.read(flie, 0);//忽略前5行
		// 遍历读取结果
		if (list != null) {
			for (int i = 1; i < list.size(); i++) {
				System.out.print("第" + (1+i) + "行"+"\t");
				List<String> cellList = list.get(i);
				SubjectDetails sd = new SubjectDetails();
				sd.setvId(cellList.get(0));
				sd.setOneSubjectName(cellList.get(1));
				sd.setSixSubjectName(cellList.get(2));
				sd.setDebitAmount(cellList.get(3));
				sd.setCreditAmount(cellList.get(4));
				sd.setAbstract_(cellList.get(5));
				sd.setSingleMan(cellList.get(6));
				sd.setBrokerageMan(cellList.get(7));
				sd.setTopicNo(cellList.get(8));
				sd.setTopicName(cellList.get(9));
				sd.setPeriodName(cellList.get(10));
				baseService.saveOrUpdate(sd);
			}
		}
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

}
