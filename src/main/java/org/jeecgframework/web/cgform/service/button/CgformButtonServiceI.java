package org.jeecgframework.web.cgform.service.button;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;

public abstract interface CgformButtonServiceI extends CommonService
{
  public abstract List<CgformButtonEntity> getCgformButtonListByFormId(String paramString);

  public abstract List<CgformButtonEntity> checkCgformButton(CgformButtonEntity paramCgformButtonEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.button.CgformButtonServiceI
 * JD-Core Version:    0.6.2
 */