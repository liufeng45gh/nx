package org.jeecgframework.web.system.service;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;

public abstract interface DynamicDataSourceServiceI extends CommonService
{
  public abstract List<DynamicDataSourceEntity> initDynamicDataSource();

  public abstract void refleshCache();

  public abstract DynamicDataSourceEntity getDynamicDataSourceEntityForDbKey(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.DynamicDataSourceServiceI
 * JD-Core Version:    0.6.2
 */