package org.jeecgframework.web.onlinedoc.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.onlinedoc.entity.OnlineDocEntity;

public abstract interface OnlineDocServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(OnlineDocEntity paramOnlineDocEntity);

  public abstract boolean doUpdateSql(OnlineDocEntity paramOnlineDocEntity);

  public abstract boolean doDelSql(OnlineDocEntity paramOnlineDocEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.onlinedoc.service.OnlineDocServiceI
 * JD-Core Version:    0.6.2
 */