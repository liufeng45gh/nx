package ssb.warmline.business.service.wcashaccount;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wcashaccount.WCashAccountEntity;

public abstract interface WCashAccountServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WCashAccountEntity paramWCashAccountEntity);

  public abstract boolean doUpdateSql(WCashAccountEntity paramWCashAccountEntity);

  public abstract boolean doDelSql(WCashAccountEntity paramWCashAccountEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wcashaccount.WCashAccountServiceI
 * JD-Core Version:    0.6.2
 */