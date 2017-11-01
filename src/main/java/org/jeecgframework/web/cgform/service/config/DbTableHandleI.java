package org.jeecgframework.web.cgform.service.config;

import org.jeecgframework.web.cgform.exception.DBException;
import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;

public abstract interface DbTableHandleI
{
  public abstract String getAddColumnSql(ColumnMeta paramColumnMeta);

  public abstract String getReNameFieldName(ColumnMeta paramColumnMeta);

  public abstract String getUpdateColumnSql(ColumnMeta paramColumnMeta1, ColumnMeta paramColumnMeta2)
    throws DBException;

  public abstract String getMatchClassTypeByDataType(String paramString, int paramInt);

  public abstract String dropTableSQL(String paramString);

  public abstract String getDropColumnSql(String paramString);

  public abstract String getCommentSql(ColumnMeta paramColumnMeta);

  public abstract String getSpecialHandle(ColumnMeta paramColumnMeta1, ColumnMeta paramColumnMeta2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.config.DbTableHandleI
 * JD-Core Version:    0.6.2
 */