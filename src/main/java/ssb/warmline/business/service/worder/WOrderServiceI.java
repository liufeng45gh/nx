package ssb.warmline.business.service.worder;

import java.io.Serializable;
import java.util.List;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.worder.WOrderEntity;

public abstract interface WOrderServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WOrderEntity paramWOrderEntity);

  public abstract boolean doUpdateSql(WOrderEntity paramWOrderEntity);

  public abstract boolean doDelSql(WOrderEntity paramWOrderEntity);

  public abstract JSONObject findAllOrderAndBrokerage(WOrderEntity paramWOrderEntity, DataGrid paramDataGrid, String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract List<WOrderEntity> showTotalOrderNumber(DataGrid paramDataGrid, String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.worder.WOrderServiceI
 * JD-Core Version:    0.6.2
 */