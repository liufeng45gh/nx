package ssb.warmline.business.service.wquestionanswer;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import ssb.warmline.business.entity.wquestionanswer.WQuestionAnswerEntity;

public abstract interface WQuestionAnswerServiceI extends CommonService
{
  public abstract <T> void delete(T paramT);

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract boolean doAddSql(WQuestionAnswerEntity paramWQuestionAnswerEntity);

  public abstract boolean doUpdateSql(WQuestionAnswerEntity paramWQuestionAnswerEntity);

  public abstract boolean doDelSql(WQuestionAnswerEntity paramWQuestionAnswerEntity);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.wquestionanswer.WQuestionAnswerServiceI
 * JD-Core Version:    0.6.2
 */