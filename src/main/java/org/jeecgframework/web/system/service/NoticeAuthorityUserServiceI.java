package org.jeecgframework.web.system.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;

public abstract interface NoticeAuthorityUserServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSNoticeAuthorityUser paramTSNoticeAuthorityUser);

  public abstract boolean doUpdateSql(TSNoticeAuthorityUser paramTSNoticeAuthorityUser);

  public abstract boolean doDelSql(TSNoticeAuthorityUser paramTSNoticeAuthorityUser);

  public abstract boolean checkAuthorityUser(String paramString1, String paramString2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.NoticeAuthorityUserServiceI
 * JD-Core Version:    0.6.2
 */