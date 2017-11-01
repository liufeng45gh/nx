package org.jeecgframework.web.cgform.service.template;

import java.io.Serializable;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;

public abstract interface CgformTemplateServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(CgformTemplateEntity paramCgformTemplateEntity);

  public abstract boolean doUpdateSql(CgformTemplateEntity paramCgformTemplateEntity);

  public abstract boolean doDelSql(CgformTemplateEntity paramCgformTemplateEntity);

  public abstract CgformTemplateEntity findByCode(String paramString);

  public abstract List<CgformTemplateEntity> getTemplateListByType(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI
 * JD-Core Version:    0.6.2
 */