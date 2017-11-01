package org.jeecgframework.web.cgform.service.enhance;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;

public abstract interface CgformEnhanceJsServiceI extends CommonService
{
  public abstract CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String paramString1, String paramString2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI
 * JD-Core Version:    0.6.2
 */