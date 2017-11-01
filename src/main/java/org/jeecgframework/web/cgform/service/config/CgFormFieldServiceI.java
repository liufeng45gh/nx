package org.jeecgframework.web.cgform.service.config;

import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;

public abstract interface CgFormFieldServiceI extends CommonService
{
  public abstract void updateTable(CgFormHeadEntity paramCgFormHeadEntity, String paramString, boolean paramBoolean);

  public abstract void saveTable(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract void saveTable(CgFormHeadEntity paramCgFormHeadEntity, String paramString);

  public abstract Boolean judgeTableIsExit(String paramString);

  public abstract boolean dbSynch(CgFormHeadEntity paramCgFormHeadEntity, String paramString)
    throws BusinessException;

  public abstract void deleteCgForm(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract List<Map<String, Object>> getCgFormFieldByTableName(String paramString);

  public abstract Map<String, CgFormFieldEntity> getCgFormFieldByFormId(String paramString);

  public abstract CgFormHeadEntity getCgFormHeadByTableName(String paramString);

  public abstract Map<String, CgFormFieldEntity> getAllCgFormFieldByTableName(String paramString);

  public abstract Map<String, CgFormFieldEntity> getAllCgFormFieldByTableName(String paramString1, String paramString2);

  public abstract List<CgFormFieldEntity> getHiddenCgFormFieldByTableName(String paramString);

  public abstract List<Map<String, Object>> getSubTableData(String paramString1, String paramString2, Object paramObject);

  public abstract boolean appendSubTableStr4Main(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract boolean removeSubTableStr4Main(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract void sortSubTableStr(CgFormHeadEntity paramCgFormHeadEntity);

  public abstract String getCgFormVersionByTableName(String paramString);

  public abstract String getCgFormVersionById(String paramString);

  public abstract boolean updateVersion(String paramString);

  public abstract Map<String, Object> getFtlFormConfig(String paramString1, String paramString2);

  public abstract CgFormHeadEntity getCgFormHeadByTableName(String paramString1, String paramString2);

  public abstract boolean checkTableExist(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI
 * JD-Core Version:    0.6.2
 */