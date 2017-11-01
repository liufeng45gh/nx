package ssb.warmline.business.service.wcapital;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;

public abstract interface WCapitalServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WCapitalEntity paramWCapitalEntity);

  public abstract boolean doUpdateSql(WCapitalEntity paramWCapitalEntity);

  public abstract boolean doDelSql(WCapitalEntity paramWCapitalEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wcapital.WCapitalServiceI
 * JD-Core Version:    0.6.2
 */