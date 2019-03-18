package com.action;

import com.util.ActivitiAttach;
import com.util.AttachmentRealPathUtils;
import com.util.ReadExcel;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 名称 : 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月25日 下午4:32:31 
 * 描述 :
 */
public class ImportAction {
	/**
	 * 附件上传保存
	 * 
	 * @param request
	 * @param scfile
	 * @param formId
	 * @return
	 * @throws Exception
	 */
	public String SaveAttachment(HttpServletRequest request, MultipartFile[] scfile, String formId, String personalPath)
			throws Exception {

		if (!ObjectUtils.isEmpty(scfile)) {
			for (MultipartFile multipartFile : scfile) {
				if (multipartFile.isEmpty()) {
					return null;
				}
				String attachPath = ActivitiAttach.ATTACHMENT_PATH + File.separator + personalPath;
				;
				// 获取附件名称
				String uuid = AttachmentRealPathUtils.getAttachmentFileId(request, attachPath);
				File saveFile = AttachmentRealPathUtils.getRealFile(request, attachPath + File.separator + uuid);
				ReadExcel re = new ReadExcel();
				
				
				//String flie = "C:\\Users\\admin\\Desktop\\科目.xlsx";
				String flie = attachPath;
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
				
				// 向附件表中存入数据
				multipartFile.transferTo(saveFile);

			}
		}
		return "";
	}

}
