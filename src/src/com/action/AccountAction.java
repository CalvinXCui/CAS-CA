package com.action;

import com.entity.Account;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.ReadExcel;
import com.util.Upload;

import java.io.File;
import java.util.List;

/**
 *
 * @author calvin
 *
 */
public class AccountAction extends ActionSupport{
    /**
     *
     */
    private static final long serialVersionUID = 5767551015672580712L;
    private BaseService baseService;
    @SuppressWarnings("rawtypes")
    List accountList=null;

    public String getTopName(){
        try {
            accountList = baseService.findObjList("from Account a order by a.orderId ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "srzcjsb";
    }
    /**
     * 清空
     * @return
     * @throws Exception
     */
    public String deleteTable()throws Exception{

        boolean flag = baseService.zxSql(" truncate table account ");
        accountList = baseService.findObjList("from Account a order by a.orderId ");
        if(flag)
            return "srzcjsb";
        return "删除失败";
    }

    /**
     * 导入数据
     * @return
     * @throws Exception
     */
    private List<File> file;
    private String filename;//文件名
    private Integer msg;
    @SuppressWarnings("static-access")
    public String upLoadTable()throws Exception{
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
                    if (cellList.size()>=23) {
                        baseService.zxSql("truncate table account");
                        break;
                    }
                }
                for (int i = 7; i < list.size(); i++) {
                    List<String> cellList = list.get(i);
                    if (cellList.size()>=23) {//列
                        Account account = new Account();
                        account.setProjectNum(cellList.get(0).toString());
                        account.setProjectName(cellList.get(1).toString());
                        account.setAdd1(cellList.get(2).toString());
                        account.setBasePay1(cellList.get(3).toString());
                        account.setProjectBalance1(cellList.get(4).toString());
                        account.setManageBalance1(cellList.get(5).toString());
                        account.setYearAdd(cellList.get(6).toString());
                        account.setYearPay(cellList.get(7).toString());
                        account.setAdd2(cellList.get(8).toString());
                        account.setBasePay2(cellList.get(9).toString());
                        account.setProjectBalance2(cellList.get(10).toString());
                        account.setManageBalance2(cellList.get(11).toString());
                        account.setMargin(cellList.get(12).toString());
                        account.setAdd3(cellList.get(13).toString());
                        account.setTax(cellList.get(14).toString());
                        account.setWelfare(cellList.get(15).toString());
                        account.setCareer(cellList.get(16).toString());
                        account.setOther(cellList.get(17).toString());
                        account.setAdd4(cellList.get(18).toString());
                        account.setBasePay4(cellList.get(19).toString());
                        account.setProjectBalance4(cellList.get(20).toString());
                        account.setManageBalance4(cellList.get(21).toString());
                        account.setState(cellList.get(22).toString());
                        baseService.saveOrUpdate(account);
                        setMsg(1);
                    } else {
                        setMsg(0);
                        break;
                    }
                }
            }else {
                setMsg(0);
            }
            return "msg";
        } catch (Exception e) {
            e.printStackTrace();
            setMsg(0);
            return "msg";
        }
    }


    public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    @SuppressWarnings("rawtypes")
    public List getAccountList() {
        return accountList;
    }

    @SuppressWarnings("rawtypes")
    public void setAccountList(List accountList) {
        this.accountList = accountList;
    }


    public Integer getMsg() {
        return msg;
    }


    public void setMsg(Integer msg) {
        this.msg = msg;
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


}
