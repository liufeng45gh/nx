package org.jeecgframework.web.cgform.dao.config;

import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

@Repository("cgFormFieldDao")
public abstract interface CgFormFieldDao
{
  @Arguments({"tableName"})
  public abstract List<Map<String, Object>> getCgFormFieldByTableName(String paramString);

  @Arguments({"tableName"})
  public abstract List<Map<String, Object>> getCgFormHiddenFieldByTableName(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.dao.config.CgFormFieldDao
 * JD-Core Version:    0.6.2
 */