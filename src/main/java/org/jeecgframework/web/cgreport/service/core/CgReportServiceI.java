package org.jeecgframework.web.cgreport.service.core;

import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;

public abstract interface CgReportServiceI extends CommonService
{
  public abstract Map<String, Object> queryCgReportConfig(String paramString);

  public abstract Map<String, Object> queryCgReportMainConfig(String paramString);

  public abstract List<Map<String, Object>> queryCgReportItems(String paramString);

  public abstract List<Map<String, Object>> queryByCgReportSql(String paramString, Map paramMap, int paramInt1, int paramInt2);

  public abstract long countQueryByCgReportSql(String paramString, Map paramMap);

  public abstract List<String> getSqlFields(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.service.core.CgReportServiceI
 * JD-Core Version:    0.6.2
 */