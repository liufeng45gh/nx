package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.MutiLangEntity;

public abstract interface MutiLangServiceI extends CommonService
{
  public abstract void initAllMutiLang();

  public abstract String getLang(String paramString);

  public abstract String getLang(String paramString1, String paramString2);

  public abstract void refleshMutiLangCach();

  public abstract void putMutiLang(MutiLangEntity paramMutiLangEntity);

  public abstract void putMutiLang(String paramString1, String paramString2, String paramString3);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.MutiLangServiceI
 * JD-Core Version:    0.6.2
 */