package ssb.warmline.business.service.wphonecode;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wphonecode.WPhoneCodeEntity;

public abstract interface WPhoneCodeServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WPhoneCodeEntity paramWPhoneCodeEntity);

  public abstract boolean doUpdateSql(WPhoneCodeEntity paramWPhoneCodeEntity);

  public abstract boolean doDelSql(WPhoneCodeEntity paramWPhoneCodeEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wphonecode.WPhoneCodeServiceI
 * JD-Core Version:    0.6.2
 */