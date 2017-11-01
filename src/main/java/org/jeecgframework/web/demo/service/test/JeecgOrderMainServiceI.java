package org.jeecgframework.web.demo.service.test;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
import org.jeecgframework.web.demo.entity.test.JeecgOrderMainEntity;
import org.jeecgframework.web.demo.entity.test.JeecgOrderProductEntity;

public abstract interface JeecgOrderMainServiceI extends CommonService
{
  public abstract void addMain(JeecgOrderMainEntity paramJeecgOrderMainEntity, List<JeecgOrderProductEntity> paramList, List<JeecgOrderCustomEntity> paramList1);

  public abstract void updateMain(JeecgOrderMainEntity paramJeecgOrderMainEntity, List<JeecgOrderProductEntity> paramList, List<JeecgOrderCustomEntity> paramList1, boolean paramBoolean);

  public abstract void delMain(JeecgOrderMainEntity paramJeecgOrderMainEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.test.JeecgOrderMainServiceI
 * JD-Core Version:    0.6.2
 */