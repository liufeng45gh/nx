package org.jeecgframework.web.cgform.service.enhance;

import java.io.Serializable;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;

public abstract interface CgformEnhanceJavaServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(CgformEnhanceJavaEntity paramCgformEnhanceJavaEntity);

  public abstract boolean doUpdateSql(CgformEnhanceJavaEntity paramCgformEnhanceJavaEntity);

  public abstract boolean doDelSql(CgformEnhanceJavaEntity paramCgformEnhanceJavaEntity);

  public abstract CgformEnhanceJavaEntity getCgformEnhanceJavaEntityByCodeFormId(String paramString1, String paramString2);

  public abstract List<CgformEnhanceJavaEntity> checkCgformEnhanceJavaEntity(CgformEnhanceJavaEntity paramCgformEnhanceJavaEntity);

  public abstract boolean checkClassOrSpringBeanIsExist(CgformEnhanceJavaEntity paramCgformEnhanceJavaEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJavaServiceI
 * JD-Core Version:    0.6.2
 */