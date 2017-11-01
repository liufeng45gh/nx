package org.jeecgframework.web.rank.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.rank.entity.TSTeamPersonEntity;

public abstract interface TSTeamPersonServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSTeamPersonEntity paramTSTeamPersonEntity);

  public abstract boolean doUpdateSql(TSTeamPersonEntity paramTSTeamPersonEntity);

  public abstract boolean doDelSql(TSTeamPersonEntity paramTSTeamPersonEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.rank.service.TSTeamPersonServiceI
 * JD-Core Version:    0.6.2
 */