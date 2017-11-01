package ssb.warmline.business.service.wversionupdatemanagement;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity;

public abstract interface WVersionUpdateManagementServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WVersionUpdateManagementEntity paramWVersionUpdateManagementEntity);

  public abstract boolean doUpdateSql(WVersionUpdateManagementEntity paramWVersionUpdateManagementEntity);

  public abstract boolean doDelSql(WVersionUpdateManagementEntity paramWVersionUpdateManagementEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wversionupdatemanagement.WVersionUpdateManagementServiceI
 * JD-Core Version:    0.6.2
 */