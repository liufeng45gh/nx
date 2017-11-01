package org.jeecgframework.web.cgform.service.autoform;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbFieldEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormParamEntity;

public abstract interface AutoFormDbServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract void addMain(AutoFormDbEntity paramAutoFormDbEntity, List<AutoFormDbFieldEntity> paramList, List<AutoFormParamEntity> paramList1);

  public abstract void updateMain(AutoFormDbEntity paramAutoFormDbEntity, List<AutoFormDbFieldEntity> paramList, List<AutoFormParamEntity> paramList1);

  public abstract void delMain(AutoFormDbEntity paramAutoFormDbEntity);

  public abstract boolean doAddSql(AutoFormDbEntity paramAutoFormDbEntity);

  public abstract boolean doUpdateSql(AutoFormDbEntity paramAutoFormDbEntity);

  public abstract boolean doDelSql(AutoFormDbEntity paramAutoFormDbEntity);

  public abstract List<String> getSqlFields(String paramString);

  public abstract List<String> getSqlParams(String paramString);

  public abstract List<String> getField(String paramString1, String paramString2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.autoform.AutoFormDbServiceI
 * JD-Core Version:    0.6.2
 */