package org.jeecgframework.web.cgform.dao.config;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.springframework.stereotype.Repository;

@Repository("cgFormVersionDao")
public abstract interface CgFormVersionDao
{
  @Arguments({"tableName"})
  public abstract String getCgFormVersionByTableName(String paramString);

  @Arguments({"id"})
  public abstract String getCgFormVersionById(String paramString);

  @Arguments({"newVersion", "formId"})
  public abstract void updateVersion(String paramString1, String paramString2);

  @Arguments({"id"})
  public abstract CgFormHeadEntity getCgFormById(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.dao.config.CgFormVersionDao
 * JD-Core Version:    0.6.2
 */