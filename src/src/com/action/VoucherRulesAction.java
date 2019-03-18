package com.action;

import com.entity.VoucherRules;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.ReadExcel;
import com.util.Upload;
import org.apache.struts2.interceptor.RequestAware;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 名称 : VoucherRulesAction 
 * 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月28日 上午10:53:12 
 * 描述 :
 */
@SuppressWarnings("serial")
public class VoucherRulesAction extends ActionSupport implements RequestAware {
	
	private Map<String, Object> request;
	
	private BaseService baseService;
	
	//上传文件集合   
    private List<File> file;
	private String filename;//文件名
	private Integer msg;
	
	private List<VoucherRules> vrList;
	private VoucherRules vr;
	
	private boolean flag;
	
	private String id;
	
	/**
	 * 查询凭证转换规则
	 * @return
	 */
	public String findRulesList(){
		try {
			vrList = baseService.findObjList("from VoucherRules");
			request.put("vrList", vrList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findRulesList";
	}
	
	/**
	 * 增加或修改凭证转换规则
	 * @return
	 */
	public String saveOrUpdateRules(){
		try {
			flag = baseService.saveOrUpdate(vr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "saveOrUpdateRules";
	}
	
	/**
	 * 根据id查询凭证转换规则
	 * @return
	 */
	public String findRulesById(){
		try {
			vrList = baseService.findObjList("from VoucherRules where id = '"+id+"' ");
			if (vrList != null && vrList.size()>0) {
				vr = vrList.get(0);
			}
			request.put("vr", vr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findRulesById";
	}
	
	/**
	 * 删除凭证转换规则
	 * @return
	 */
	public String deleteRules(){
		try {
			flag = baseService.zxSql("delete from Voucher_Rules where id = '"+id+"' ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deleteRules";
	}
	
	/**
	 * 通过excel导入凭证转换规则
	 * @return
	 */
	@SuppressWarnings("unused")
	public String rulesExcelImport(){
		Upload ie=new Upload();
		try {
			//上传到本地
			//String pathName=ie.uploadFile(System.currentTimeMillis()+"_"+filename,"guize",file.get(0));
			//
			ReadExcel re = new ReadExcel();
			List<List<String>> list = re.read("C:\\Users\\Joey\\Desktop\\规则\\转换规则-有预算-0713.xlsx", 0);//忽略前5行
			// 遍历读取结果
			if (list != null && list.size() > 2) {
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
//					if (cellList.size()>=7) {
						VoucherRules vr = new VoucherRules();
						//旧科目编号
						vr.setVoldSubjectNo(cellList.get(0).toString());
						//旧科目名称
						vr.setVoldSubjectName(cellList.get(1).toString());
						//新财务科目编号
						vr.setVfinancialNo(cellList.get(2).toString());
						//新财务科目名称
						vr.setVfinancialName(cellList.get(3).toString());
						//新预算科目编号
						vr.setVbudgetaryNo(cellList.get(4).toString());
//						//新预算科目名称
						vr.setVbudgetaryName(cellList.get(5).toString());
						//科目要素类型
						//vr.setVelementsType(cellList.get(5).toString());
						baseService.saveOrUpdate(vr);
						msg = 1;
//					} else {
//						msg = 0;
//						break;
//					}
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
	}


	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<VoucherRules> getVrList() {
		return vrList;
	}

	public void setVrList(List<VoucherRules> vrList) {
		this.vrList = vrList;
	}

	public VoucherRules getVr() {
		return vr;
	}

	public void setVr(VoucherRules vr) {
		this.vr = vr;
	}

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
