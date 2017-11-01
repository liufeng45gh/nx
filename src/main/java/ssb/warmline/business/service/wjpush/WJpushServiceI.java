package ssb.warmline.business.service.wjpush;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wjpush.WJpushEntity;

public abstract interface WJpushServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WJpushEntity paramWJpushEntity);

  public abstract boolean doUpdateSql(WJpushEntity paramWJpushEntity);

  public abstract boolean doDelSql(WJpushEntity paramWJpushEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wjpush.WJpushServiceI
 * JD-Core Version:    0.6.2
 */