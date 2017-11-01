package ssb.warmline.business.service.tsbaseuser;

import java.io.Serializable;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;

public abstract interface TSBaseUserServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(TSBaseUser paramTSBaseUser);

  public abstract boolean doUpdateSql(TSBaseUser paramTSBaseUser);

  public abstract boolean doDelSql(TSBaseUser paramTSBaseUser);

  public abstract JSONObject tsBaseUserDatagrid(TSBaseUser paramTSBaseUser, DataGrid paramDataGrid);

  public abstract JSONObject tsBaseUserJupsh(TSBaseUser paramTSBaseUser, DataGrid paramDataGrid);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI
 * JD-Core Version:    0.6.2
 */