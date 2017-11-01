package org.jeecgframework.web.onlinedocsort.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.onlinedocsort.entity.OnlineDocSortEntity;

public abstract interface OnlineDocSortServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(OnlineDocSortEntity paramOnlineDocSortEntity);

  public abstract boolean doUpdateSql(OnlineDocSortEntity paramOnlineDocSortEntity);

  public abstract boolean doDelSql(OnlineDocSortEntity paramOnlineDocSortEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.onlinedocsort.service.OnlineDocSortServiceI
 * JD-Core Version:    0.6.2
 */