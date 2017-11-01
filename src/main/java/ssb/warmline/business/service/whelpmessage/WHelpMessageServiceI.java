package ssb.warmline.business.service.whelpmessage;

import java.io.Serializable;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;

public abstract interface WHelpMessageServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WHelpMessageEntity paramWHelpMessageEntity);

  public abstract boolean doUpdateSql(WHelpMessageEntity paramWHelpMessageEntity);

  public abstract boolean doDelSql(WHelpMessageEntity paramWHelpMessageEntity);

  public abstract JSONObject applicantNumberDatagrid(WHelpMessageEntity paramWHelpMessageEntity, DataGrid paramDataGrid, String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI
 * JD-Core Version:    0.6.2
 */