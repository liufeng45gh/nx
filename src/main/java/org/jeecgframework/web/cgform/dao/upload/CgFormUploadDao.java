package org.jeecgframework.web.cgform.dao.upload;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

@Repository("cgFormUploadDao")
public abstract interface CgFormUploadDao
{
  @Arguments({"cgFormId", "cgFormName", "cgFormField", "fileId", "fileUrl"})
  public abstract void updateBackFileInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.dao.upload.CgFormUploadDao
 * JD-Core Version:    0.6.2
 */