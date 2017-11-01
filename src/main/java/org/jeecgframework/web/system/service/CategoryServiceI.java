package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;

public abstract interface CategoryServiceI extends CommonService
{
  public abstract void saveCategory(TSCategoryEntity paramTSCategoryEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.CategoryServiceI
 * JD-Core Version:    0.6.2
 */