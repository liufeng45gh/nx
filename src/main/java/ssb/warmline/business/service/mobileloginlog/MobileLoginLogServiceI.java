package ssb.warmline.business.service.mobileloginlog;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.mobileloginlog.MobileLoginLogEntity;

public abstract interface MobileLoginLogServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(MobileLoginLogEntity paramMobileLoginLogEntity);

  public abstract boolean doUpdateSql(MobileLoginLogEntity paramMobileLoginLogEntity);

  public abstract boolean doDelSql(MobileLoginLogEntity paramMobileLoginLogEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.mobileloginlog.MobileLoginLogServiceI
 * JD-Core Version:    0.6.2
 */