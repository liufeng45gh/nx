package org.jeecgframework.web.cgform.service.autoform;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormStyleEntity;

public abstract interface AutoFormStyleServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(AutoFormStyleEntity paramAutoFormStyleEntity);

  public abstract boolean doUpdateSql(AutoFormStyleEntity paramAutoFormStyleEntity);

  public abstract boolean doDelSql(AutoFormStyleEntity paramAutoFormStyleEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.autoform.AutoFormStyleServiceI
 * JD-Core Version:    0.6.2
 */