package ssb.warmline.business.service.worderphotomain;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;

public abstract interface WOrderPhotoMainServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WOrderPhotoMainEntity paramWOrderPhotoMainEntity);

  public abstract boolean doUpdateSql(WOrderPhotoMainEntity paramWOrderPhotoMainEntity);

  public abstract boolean doDelSql(WOrderPhotoMainEntity paramWOrderPhotoMainEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI
 * JD-Core Version:    0.6.2
 */