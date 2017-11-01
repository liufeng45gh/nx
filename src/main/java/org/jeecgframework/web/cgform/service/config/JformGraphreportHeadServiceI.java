package org.jeecgframework.web.cgform.service.config;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportHeadEntity;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportItemEntity;

public abstract interface JformGraphreportHeadServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract void addMain(JformGraphreportHeadEntity paramJformGraphreportHeadEntity, List<JformGraphreportItemEntity> paramList);

  public abstract void updateMain(JformGraphreportHeadEntity paramJformGraphreportHeadEntity, List<JformGraphreportItemEntity> paramList);

  public abstract void delMain(JformGraphreportHeadEntity paramJformGraphreportHeadEntity);

  public abstract boolean doAddSql(JformGraphreportHeadEntity paramJformGraphreportHeadEntity);

  public abstract boolean doUpdateSql(JformGraphreportHeadEntity paramJformGraphreportHeadEntity);

  public abstract boolean doDelSql(JformGraphreportHeadEntity paramJformGraphreportHeadEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.config.JformGraphreportHeadServiceI
 * JD-Core Version:    0.6.2
 */