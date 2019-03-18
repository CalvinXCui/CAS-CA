package com.action;

import com.entity.Topic;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.ReadExcel;
import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月6日 下午6:08:59
* 描述 :
*/
public class TopicAction extends ActionSupport implements RequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1596315851667719115L;
	
	private BaseService baseService;
	
	private List<Topic> topicList;
	
	/**
	 * 查询所有项目
	 * @return
	 */
	public String topicList() throws Exception{
		topicList = baseService.findObjList("from Topic");
		request.put("topicList", topicList);
		return "topicList";
	}
	
	/**
	 * 课题的导入
	 * @throws Exception
	 */
	public void importTopics() throws Exception {
		ReadExcel re = new ReadExcel();
		String flie = "C:\\Users\\admin\\Desktop\\财务各类文档\\课题信息.xls";
		List<List<String>> list = re.read(flie, 0);//忽略前5行
		// 遍历读取结果
		if (list != null) {
			for (int i = 1; i < list.size(); i++) {
				System.out.print("第" + (1+i) + "行"+"\t");
				List<String> cellList = list.get(i);
				Topic topic = new Topic();
				topic.settNo(cellList.get(0)); //子课题号
				topic.settResponsible(cellList.get(1));//子课题负责人
				topic.settName(cellList.get(2));//课题全称	
				topic.settState(cellList.get(3));//课题状态
				topic.settChildName(cellList.get(4));//子课题全称
				topic.settCreateTime(cellList.get(5));//创建日期
				topic.settOpeningTime(cellList.get(6));//开题日期
				topic.settConcludingTime(cellList.get(7));//结题日期  t_concluding_time
				topic.settOrganization(cellList.get(8));//所属组织  t_organization
				topic.settStayFunds(cellList.get(9));//经费来源  t_funds_sources
				topic.settStartTime(cellList.get(10));//实际开始日期  t_start_time
				topic.settEndTime(cellList.get(11));//实际完成项目日期   t_end_time
				topic.settProjectBudget(cellList.get(12));//项目预算金额  t_project_budget
				topic.settStayFunds(cellList.get(13));//项目留所经费  t_stay_funds
				topic.settCategoryType(cellList.get(14));//资助类别
				topic.settSubClass(cellList.get(15));//亚类说明  t_sub_class
				topic.settType(cellList.get(16));//课题类型  t_type
				baseService.saveOrUpdate(topic);
			}
		}
	}
	
	//上传文件集合   
//    private List<File> file;
//	private String filename;//文件名
//	private Integer msg;
//	@SuppressWarnings("static-access")
	
/*	public String SubjectExcelImport(){
		Upload ie=new Upload();
		try {
			//上传到本地
			String pathName=ie.uploadFile(System.currentTimeMillis()+"_"+filename,"kemu",file.get(0));
			
			ReadExcel re = new ReadExcel();
			List<List<String>> list = re.read(pathName, 0);//忽略前5行
			// 遍历读取结果
			if (list != null && list.size() > 2) {
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					if (cellList.size()>=16) {
						baseService.zxSql(" delete from new_subject where 1 = 1 ");
					}
				}
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					
					if (cellList.size()>=15) {
						NewSubject sj = new NewSubject();
						//类型
						sj.setSType(cellList.get(0).toString());
						//级次
						sj.setSLevel(cellList.get(1).toString());
						//科目编码
						sj.setSNo(cellList.get(2).toString());
						//上级编码
						if (cellList.get(1).toString() != null && !cellList.get(1).toString().equals("1")) {
							sj.setSHigherLevelNo(cellList.get(2).toString().substring(0, cellList.get(2).length()-2));
						}
						//科目名称
						sj.setSName(cellList.get(3).toString());
						//助记码
						sj.setSMnemonic(cellList.get(4).toString());
						//外币币种
						sj.setSCurrency(cellList.get(5).toString());
						//计量单位
						sj.setSUnit(cellList.get(6).toString());
						//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
						sj.setSAuxiliaryType(cellList.get(7).toString());
						//余额方向
						sj.setSBalanceDirection(cellList.get(8).toString());
						//是否末级
						sj.setSIsLastLevel(cellList.get(9).toString());
						//帐页格式
						sj.setSFolioFormat(cellList.get(10).toString());
						//是否项目核算
						sj.setSIsAccpunting(cellList.get(11).toString());
						//项目编码
						sj.setPNo(cellList.get(12).toString());
						//备注
						sj.setSRemarks(cellList.get(13).toString());
						//凭证类别
						sj.setSCategory(cellList.get(14).toString());
						baseService.saveOrUpdate(sj);
						msg = 1;
					} else {
						msg = 0;
						break;
					}
				}
			}else {
				msg = 0;
				//break;
			}
			return "msg";
		} catch (Exception e) {
			e.printStackTrace();
			msg=0;
			return "msg";
		}
	}*/
	
	
	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
}

