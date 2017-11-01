package org.jeecgframework.web.cgform.service.cgformftl;

import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;

public abstract interface CgformFtlServiceI extends CommonService
{
  public abstract Map<String, Object> getCgformFtlByTableName(String paramString1, String paramString2);

  public abstract Map<String, Object> getCgformFtlByTableName(String paramString);

  public abstract int getNextVarsion(String paramString);

  public abstract boolean hasActive(String paramString);

  public abstract String getUserFormFtl(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI
 * JD-Core Version:    0.6.2
 */