package org.jeecgframework.web.cgform.service.autolist;

import java.util.List;
import java.util.Map;

public abstract interface CgTableServiceI
{
  public abstract List querySingle(String paramString1, String paramString2, Map paramMap, int paramInt1, int paramInt2);

  public abstract List querySingle(String paramString1, String paramString2, Map paramMap, String paramString3, String paramString4, int paramInt1, int paramInt2);

  public abstract Long getQuerySingleSize(String paramString1, String paramString2, Map paramMap);

  public abstract boolean delete(String paramString, Object paramObject);

  public abstract boolean deleteBatch(String paramString, String[] paramArrayOfString);

  public abstract void treeFromResultHandle(String paramString1, String paramString2, String paramString3, List<Map<String, Object>> paramList);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.autolist.CgTableServiceI
 * JD-Core Version:    0.6.2
 */