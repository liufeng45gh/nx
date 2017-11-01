package org.jeecgframework.web.cgform.service.upload;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;

public abstract interface CgUploadServiceI extends CommonService
{
  public abstract void deleteFile(CgUploadEntity paramCgUploadEntity);

  public abstract void writeBack(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.upload.CgUploadServiceI
 * JD-Core Version:    0.6.2
 */