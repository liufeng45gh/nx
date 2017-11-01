package org.jeecgframework.web.cgform.service.button;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;

public abstract interface CgformButtonSqlServiceI extends CommonService
{
  public abstract List<CgformButtonSqlEntity> checkCgformButtonSql(CgformButtonSqlEntity paramCgformButtonSqlEntity);

  public abstract CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String paramString1, String paramString2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.button.CgformButtonSqlServiceI
 * JD-Core Version:    0.6.2
 */