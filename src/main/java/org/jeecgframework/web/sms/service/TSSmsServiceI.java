package org.jeecgframework.web.sms.service;

import java.io.Serializable;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.sms.entity.TSSmsEntity;

public abstract interface TSSmsServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSSmsEntity paramTSSmsEntity);

  public abstract boolean doUpdateSql(TSSmsEntity paramTSSmsEntity);

  public abstract boolean doDelSql(TSSmsEntity paramTSSmsEntity);

  public abstract void send();

  public abstract List<TSSmsEntity> getMsgsList(String paramString1, String paramString2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.service.TSSmsServiceI
 * JD-Core Version:    0.6.2
 */