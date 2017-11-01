package org.jeecgframework.web.sms.service;

import java.io.Serializable;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.sms.entity.TSSmsSqlEntity;

public abstract interface TSSmsSqlServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSSmsSqlEntity paramTSSmsSqlEntity);

  public abstract boolean doUpdateSql(TSSmsSqlEntity paramTSSmsSqlEntity);

  public abstract boolean doDelSql(TSSmsSqlEntity paramTSSmsSqlEntity);

  public abstract Map<String, Object> getMap(String paramString, Map<String, Object> paramMap);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.service.TSSmsSqlServiceI
 * JD-Core Version:    0.6.2
 */