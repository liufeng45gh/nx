/*    */ package ssb.warmline.business.commons.utils.JMessage;
/*    */ 
/*    */ import cn.jiguang.common.resp.BaseResult;
/*    */ import com.google.gson.annotations.Expose;
/*    */ 
/*    */ public class UserStateResult extends BaseResult
/*    */ {
/*    */ 
/*    */   @Expose
/*    */   Boolean login;
/*    */ 
/*    */   @Expose
/*    */   Boolean online;
/*    */ 
/*    */   public Boolean getLogin()
/*    */   {
/* 12 */     return this.login;
/*    */   }
/*    */ 
/*    */   public Boolean getOnline() {
/* 16 */     return this.online;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JMessage.UserStateResult
 * JD-Core Version:    0.6.2
 */