package ssb.warmline.business.service.territoryandcategory;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.territoryandcategory.WTerritoryCategoryEntity;

public abstract interface WTerritoryCategoryServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WTerritoryCategoryEntity paramWTerritoryCategoryEntity);

  public abstract boolean doUpdateSql(WTerritoryCategoryEntity paramWTerritoryCategoryEntity);

  public abstract boolean doDelSql(WTerritoryCategoryEntity paramWTerritoryCategoryEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.territoryandcategory.WTerritoryCategoryServiceI
 * JD-Core Version:    0.6.2
 */