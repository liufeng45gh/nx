package org.jeecgframework.web.cgform.service.config;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity;

public abstract interface CgFormIndexServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(CgFormIndexEntity paramCgFormIndexEntity);

  public abstract boolean doUpdateSql(CgFormIndexEntity paramCgFormIndexEntity);

  public abstract boolean doDelSql(CgFormIndexEntity paramCgFormIndexEntity);

  public abstract boolean updateIndexes(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract void createIndexes(CgFormHeadEntity paramCgFormHeadEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI
 * JD-Core Version:    0.6.2
 */