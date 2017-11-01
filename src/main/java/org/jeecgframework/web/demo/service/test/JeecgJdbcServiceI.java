package org.jeecgframework.web.demo.service.test;

import net.sf.json.JSONObject;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.test.JeecgJdbcEntity;

public abstract interface JeecgJdbcServiceI extends CommonService
{
  public abstract void getDatagrid1(JeecgJdbcEntity paramJeecgJdbcEntity, DataGrid paramDataGrid);

  public abstract void getDatagrid2(JeecgJdbcEntity paramJeecgJdbcEntity, DataGrid paramDataGrid);

  public abstract JSONObject getDatagrid3(JeecgJdbcEntity paramJeecgJdbcEntity, DataGrid paramDataGrid);

  public abstract void listAllByJdbc(DataGrid paramDataGrid);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.JeecgJdbcServiceI
 * JD-Core Version:    0.6.2
 */