package org.jeecgframework.web.system.service;

import java.util.List;
import java.util.Set;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;

public abstract interface SystemService extends CommonService
{
  public abstract List<DictEntity> queryDict(String paramString1, String paramString2, String paramString3);

  public abstract TSUser checkUserExits(TSUser paramTSUser)
    throws Exception;

  public abstract void addLog(String paramString, Short paramShort1, Short paramShort2);

  public abstract TSType getType(String paramString1, String paramString2, TSTypegroup paramTSTypegroup);

  public abstract TSTypegroup getTypeGroup(String paramString1, String paramString2);

  public abstract Set<String> getOperationCodesByUserIdAndFunctionId(String paramString1, String paramString2);

  public abstract Set<String> getOperationCodesByRoleIdAndFunctionId(String paramString1, String paramString2);

  public abstract TSTypegroup getTypeGroupByCode(String paramString);

  public abstract void initAllTypeGroups();

  public abstract void refleshTypesCach(TSType paramTSType);

  public abstract void refleshTypeGroupCach();

  public abstract void flushRoleFunciton(String paramString, TSFunction paramTSFunction);

  public abstract String generateOrgCode(String paramString1, String paramString2);

  public abstract Set<String> getOperationCodesByRoleIdAndruleDataId(String paramString1, String paramString2);

  public abstract Set<String> getOperationCodesByUserIdAndDataId(String paramString1, String paramString2);

  public abstract void initAllTSIcons();

  public abstract void upTSIcons(TSIcon paramTSIcon);

  public abstract void delTSIcons(TSIcon paramTSIcon);

  public abstract void addDataLog(String paramString1, String paramString2, String paramString3);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.SystemService
 * JD-Core Version:    0.6.2
 */