package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

public abstract interface UserService extends CommonService
{
  public abstract TSUser checkUserExits(TSUser paramTSUser);

  public abstract String getUserRole(TSUser paramTSUser);

  public abstract void pwdInit(TSUser paramTSUser, String paramString);

  public abstract int getUsersOfThisRole(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.UserService
 * JD-Core Version:    0.6.2
 */