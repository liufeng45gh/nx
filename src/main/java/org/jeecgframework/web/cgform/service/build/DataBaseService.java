package org.jeecgframework.web.cgform.service.build;

import java.util.List;
import java.util.Map;
import org.jeecgframework.web.cgform.exception.BusinessException;

public abstract interface DataBaseService
{
  public abstract void insertTable(String paramString, Map<String, Object> paramMap)
    throws BusinessException;

  public abstract int updateTable(String paramString, Object paramObject, Map<String, Object> paramMap)
    throws BusinessException;

  public abstract Map<String, Object> findOneForJdbc(String paramString1, String paramString2);

  public abstract Map<String, Object> insertTableMore(Map<String, List<Map<String, Object>>> paramMap, String paramString)
    throws BusinessException;

  public abstract boolean updateTableMore(Map<String, List<Map<String, Object>>> paramMap, String paramString)
    throws BusinessException;

  public abstract void executeSqlExtend(String paramString1, String paramString2, Map<String, Object> paramMap);

  public abstract Object getPkValue(String paramString);

  public abstract void executeJavaExtend(String paramString1, String paramString2, Map<String, Object> paramMap)
    throws BusinessException;
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.build.DataBaseService
 * JD-Core Version:    0.6.2
 */