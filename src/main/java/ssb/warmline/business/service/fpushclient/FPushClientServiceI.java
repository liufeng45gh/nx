package ssb.warmline.business.service.fpushclient;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.fpushclient.FPushClientEntity;

public abstract interface FPushClientServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(FPushClientEntity paramFPushClientEntity);

  public abstract boolean doUpdateSql(FPushClientEntity paramFPushClientEntity);

  public abstract boolean doDelSql(FPushClientEntity paramFPushClientEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.fpushclient.FPushClientServiceI
 * JD-Core Version:    0.6.2
 */