package ssb.warmline.business.service.worderphoto;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;

public abstract interface WOrderPhotoServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WOrderPhotoEntity paramWOrderPhotoEntity);

  public abstract boolean doUpdateSql(WOrderPhotoEntity paramWOrderPhotoEntity);

  public abstract boolean doDelSql(WOrderPhotoEntity paramWOrderPhotoEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI
 * JD-Core Version:    0.6.2
 */