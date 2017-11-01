package org.jeecgframework.web.cgdynamgraph.service.core;

import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;

public abstract interface CgDynamGraphServiceI extends CommonService
{
  public abstract Map<String, Object> queryCgDynamGraphConfig(String paramString);

  public abstract Map<String, Object> queryCgDynamGraphMainConfig(String paramString);

  public abstract List<Map<String, Object>> queryCgDynamGraphItems(String paramString);

  public abstract List<Map<String, Object>> queryByCgDynamGraphSql(String paramString, Map paramMap);

  public abstract long countQueryByCgDynamGraphSql(String paramString, Map paramMap);

  public abstract List<String> getSqlFields(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphServiceI
 * JD-Core Version:    0.6.2
 */