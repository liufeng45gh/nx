package ssb.warmline.business.service.wip;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wip.WIpEntity;

public abstract interface WIpServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WIpEntity paramWIpEntity);

  public abstract boolean doUpdateSql(WIpEntity paramWIpEntity);

  public abstract boolean doDelSql(WIpEntity paramWIpEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wip.WIpServiceI
 * JD-Core Version:    0.6.2
 */