package org.jeecgframework.web.cgreport.dao.core;

import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

@Repository("cgReportDao")
public abstract interface CgReportDao
{
  @Arguments({"configId"})
  public abstract List<Map<String, Object>> queryCgReportItems(String paramString);

  @Arguments({"id"})
  public abstract Map queryCgReportMainConfig(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.dao.core.CgReportDao
 * JD-Core Version:    0.6.2
 */