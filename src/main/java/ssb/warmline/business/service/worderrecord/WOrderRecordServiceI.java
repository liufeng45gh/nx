package ssb.warmline.business.service.worderrecord;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;

public abstract interface WOrderRecordServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WOrderRecordEntity paramWOrderRecordEntity);

  public abstract boolean doUpdateSql(WOrderRecordEntity paramWOrderRecordEntity);

  public abstract boolean doDelSql(WOrderRecordEntity paramWOrderRecordEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.worderrecord.WOrderRecordServiceI
 * JD-Core Version:    0.6.2
 */