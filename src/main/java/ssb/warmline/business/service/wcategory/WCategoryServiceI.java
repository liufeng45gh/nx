package ssb.warmline.business.service.wcategory;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wcategory.WCategoryEntity;

public abstract interface WCategoryServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WCategoryEntity paramWCategoryEntity);

  public abstract boolean doUpdateSql(WCategoryEntity paramWCategoryEntity);

  public abstract boolean doDelSql(WCategoryEntity paramWCategoryEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wcategory.WCategoryServiceI
 * JD-Core Version:    0.6.2
 */