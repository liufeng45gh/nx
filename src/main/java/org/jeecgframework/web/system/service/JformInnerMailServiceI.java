package org.jeecgframework.web.system.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.JformInnerMailAttach;
import org.jeecgframework.web.system.pojo.base.JformInnerMailEntity;

public abstract interface JformInnerMailServiceI extends CommonService
{
  public abstract void deleteFile(JformInnerMailAttach paramJformInnerMailAttach);

  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(JformInnerMailEntity paramJformInnerMailEntity);

  public abstract boolean doUpdateSql(JformInnerMailEntity paramJformInnerMailEntity);

  public abstract boolean doDelSql(JformInnerMailEntity paramJformInnerMailEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.JformInnerMailServiceI
 * JD-Core Version:    0.6.2
 */