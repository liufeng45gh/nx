package org.jeecgframework.web.cgreport.service.core;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigHeadEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigParamEntity;

public abstract interface CgreportConfigHeadServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract void addMain(CgreportConfigHeadEntity paramCgreportConfigHeadEntity, List<CgreportConfigItemEntity> paramList, List<CgreportConfigParamEntity> paramList1);

  public abstract void updateMain(CgreportConfigHeadEntity paramCgreportConfigHeadEntity, List<CgreportConfigItemEntity> paramList, List<CgreportConfigParamEntity> paramList1);

  public abstract void delMain(CgreportConfigHeadEntity paramCgreportConfigHeadEntity);

  public abstract boolean doAddSql(CgreportConfigHeadEntity paramCgreportConfigHeadEntity);

  public abstract boolean doUpdateSql(CgreportConfigHeadEntity paramCgreportConfigHeadEntity);

  public abstract boolean doDelSql(CgreportConfigHeadEntity paramCgreportConfigHeadEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.service.core.CgreportConfigHeadServiceI
 * JD-Core Version:    0.6.2
 */