package org.jeecgframework.web.system.service;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSAttachment;

public abstract interface DeclareService extends CommonService
{
  public abstract List<TSAttachment> getAttachmentsByCode(String paramString1, String paramString2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.DeclareService
 * JD-Core Version:    0.6.2
 */