package ssb.warmline.business.service.wterritory;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wterritory.WTerritoryEntity;

public abstract interface WTerritoryServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WTerritoryEntity paramWTerritoryEntity);

  public abstract boolean doUpdateSql(WTerritoryEntity paramWTerritoryEntity);

  public abstract boolean doDelSql(WTerritoryEntity paramWTerritoryEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wterritory.WTerritoryServiceI
 * JD-Core Version:    0.6.2
 */