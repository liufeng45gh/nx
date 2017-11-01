package org.jeecgframework.web.sms.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.sms.entity.TSSmsTemplateSqlEntity;

public abstract interface TSSmsTemplateSqlServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSSmsTemplateSqlEntity paramTSSmsTemplateSqlEntity);

  public abstract boolean doUpdateSql(TSSmsTemplateSqlEntity paramTSSmsTemplateSqlEntity);

  public abstract boolean doDelSql(TSSmsTemplateSqlEntity paramTSSmsTemplateSqlEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.service.TSSmsTemplateSqlServiceI
 * JD-Core Version:    0.6.2
 */