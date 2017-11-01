package org.jeecgframework.web.demo.dao.test;

import java.util.List;
import org.jeecgframework.minidao.annotation.MiniDao;

@MiniDao
public abstract interface JeecgProcedureDao
{
  public abstract List queryDataByProcedure(String paramString1, String paramString2, String paramString3);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.dao.test.JeecgProcedureDao
 * JD-Core Version:    0.6.2
 */