package ssb.warmline.business.service.wcomment;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wcomment.WCommentEntity;

public abstract interface WCommentServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WCommentEntity paramWCommentEntity);

  public abstract boolean doUpdateSql(WCommentEntity paramWCommentEntity);

  public abstract boolean doDelSql(WCommentEntity paramWCommentEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wcomment.WCommentServiceI
 * JD-Core Version:    0.6.2
 */