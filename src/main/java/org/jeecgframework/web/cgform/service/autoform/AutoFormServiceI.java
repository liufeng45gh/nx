package org.jeecgframework.web.cgform.service.autoform;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;

public abstract interface AutoFormServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(AutoFormEntity paramAutoFormEntity);

  public abstract boolean doUpdateSql(AutoFormEntity paramAutoFormEntity);

  public abstract boolean doDelSql(AutoFormEntity paramAutoFormEntity);

  public abstract void doAddTable(String paramString, Map<String, Map<String, Object>> paramMap)
    throws BusinessException;

  public abstract String doUpdateTable(String paramString, Map<String, Map<String, Object>> paramMap, Map<String, List<Map<String, Object>>> paramMap1)
    throws BusinessException;
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.autoform.AutoFormServiceI
 * JD-Core Version:    0.6.2
 */