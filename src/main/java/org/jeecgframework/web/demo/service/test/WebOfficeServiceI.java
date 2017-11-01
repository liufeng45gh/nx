package org.jeecgframework.web.demo.service.test;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.test.WebOfficeEntity;
import org.springframework.web.multipart.MultipartFile;

public abstract interface WebOfficeServiceI extends CommonService
{
  public abstract void saveObj(WebOfficeEntity paramWebOfficeEntity, MultipartFile paramMultipartFile);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.WebOfficeServiceI
 * JD-Core Version:    0.6.2
 */