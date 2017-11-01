package org.jeecgframework.web.demo.service.test;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.test.TFinanceEntity;
import org.jeecgframework.web.demo.entity.test.TFinanceFilesEntity;

public abstract interface TFinanceServiceI extends CommonService
{
  public abstract void deleteFile(TFinanceFilesEntity paramTFinanceFilesEntity);

  public abstract void deleteFinance(TFinanceEntity paramTFinanceEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.TFinanceServiceI
 * JD-Core Version:    0.6.2
 */