package ssb.warmline.business.service.wterritory;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;

public abstract interface WTerritoryBusinessServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WTerritoryBusiness paramWTerritoryBusiness);

  public abstract boolean doUpdateSql(WTerritoryBusiness paramWTerritoryBusiness);

  public abstract boolean doDelSql(WTerritoryBusiness paramWTerritoryBusiness);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI
 * JD-Core Version:    0.6.2
 */