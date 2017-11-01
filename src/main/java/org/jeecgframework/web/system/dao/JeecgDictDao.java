package org.jeecgframework.web.system.dao;

import java.util.List;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.springframework.stereotype.Repository;

@Repository("jeecgDictDao")
public abstract interface JeecgDictDao
{
  @Arguments({"dicTable", "dicCode", "dicText"})
  @ResultType(DictEntity.class)
  public abstract List<DictEntity> queryCustomDict(String paramString1, String paramString2, String paramString3);

  @Arguments({"dicCode"})
  @ResultType(DictEntity.class)
  public abstract List<DictEntity> querySystemDict(String paramString);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.dao.JeecgDictDao
 * JD-Core Version:    0.6.2
 */