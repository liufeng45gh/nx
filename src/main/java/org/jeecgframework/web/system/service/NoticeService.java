package org.jeecgframework.web.system.service;

import java.io.Serializable;
import java.util.Date;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSNotice;

public abstract interface NoticeService extends CommonService
{
  public abstract String addNotice(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate, String paramString5);

  public abstract void addNoticeAuthorityUser(String paramString1, String paramString2);

  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSNotice paramTSNotice);

  public abstract boolean doUpdateSql(TSNotice paramTSNotice);

  public abstract boolean doDelSql(TSNotice paramTSNotice);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.NoticeService
 * JD-Core Version:    0.6.2
 */