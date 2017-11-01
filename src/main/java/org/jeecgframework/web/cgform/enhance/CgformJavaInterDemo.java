/*    */ package org.jeecgframework.web.cgform.enhance;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.jeecgframework.core.common.exception.BusinessException;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("cgformJavaInterDemo")
/*    */ public class CgformJavaInterDemo
/*    */   implements CgformEnhanceJavaInter
/*    */ {
/*    */   public void execute(Map map)
/*    */     throws BusinessException
/*    */   {
/* 16 */     LogUtil.info("============调用[java增强]成功!==============");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.enhance.CgformJavaInterDemo
 * JD-Core Version:    0.6.2
 */