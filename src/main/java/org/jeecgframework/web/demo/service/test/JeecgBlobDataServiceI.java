package org.jeecgframework.web.demo.service.test;

import org.jeecgframework.core.common.service.CommonService;
import org.springframework.web.multipart.MultipartFile;

public abstract interface JeecgBlobDataServiceI extends CommonService
{
  public abstract void saveObj(String paramString, MultipartFile paramMultipartFile);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.JeecgBlobDataServiceI
 * JD-Core Version:    0.6.2
 */