package org.jeecgframework.web.demo.service.goods;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.goods.GoodsEntity;

public abstract interface GoodsServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(GoodsEntity paramGoodsEntity);

  public abstract boolean doUpdateSql(GoodsEntity paramGoodsEntity);

  public abstract boolean doDelSql(GoodsEntity paramGoodsEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.goods.GoodsServiceI
 * JD-Core Version:    0.6.2
 */