package org.jeecgframework.web.cgdynamgraph.service.core;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;

public abstract interface CgDynamGraphConfigHeadServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract void addMain(CgDynamGraphConfigHeadEntity paramCgDynamGraphConfigHeadEntity, List<CgDynamGraphConfigItemEntity> paramList, List<CgDynamGraphConfigParamEntity> paramList1);

  public abstract void updateMain(CgDynamGraphConfigHeadEntity paramCgDynamGraphConfigHeadEntity, List<CgDynamGraphConfigItemEntity> paramList, List<CgDynamGraphConfigParamEntity> paramList1);

  public abstract void delMain(CgDynamGraphConfigHeadEntity paramCgDynamGraphConfigHeadEntity);

  public abstract boolean doAddSql(CgDynamGraphConfigHeadEntity paramCgDynamGraphConfigHeadEntity);

  public abstract boolean doUpdateSql(CgDynamGraphConfigHeadEntity paramCgDynamGraphConfigHeadEntity);

  public abstract boolean doDelSql(CgDynamGraphConfigHeadEntity paramCgDynamGraphConfigHeadEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphConfigHeadServiceI
 * JD-Core Version:    0.6.2
 */