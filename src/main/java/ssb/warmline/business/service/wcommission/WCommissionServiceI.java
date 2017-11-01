package ssb.warmline.business.service.wcommission;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wcommission.WCommissionEntity;

public abstract interface WCommissionServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WCommissionEntity paramWCommissionEntity);

  public abstract boolean doUpdateSql(WCommissionEntity paramWCommissionEntity);

  public abstract boolean doDelSql(WCommissionEntity paramWCommissionEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wcommission.WCommissionServiceI
 * JD-Core Version:    0.6.2
 */