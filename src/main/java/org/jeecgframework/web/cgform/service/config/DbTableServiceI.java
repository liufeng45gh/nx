package org.jeecgframework.web.cgform.service.config;

import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract interface DbTableServiceI
{
  public abstract String createTableSQL(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract String dropTableSQL(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract String createIsExitSql(String paramString);

  public abstract String updateTableSQL(CgFormHeadEntity paramCgFormHeadEntity, JdbcTemplate paramJdbcTemplate);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.config.DbTableServiceI
 * JD-Core Version:    0.6.2
 */