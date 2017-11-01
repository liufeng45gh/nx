/*    */ package ssb.warmline.business.service.impl.wcashaccount;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wcashaccount.WCashAccountEntity;
/*    */ import ssb.warmline.business.service.wcashaccount.WCashAccountServiceI;
/*    */ 
/*    */ @Service("wCashAccountService")
/*    */ @Transactional
/*    */ public class WCashAccountServiceImpl extends CommonServiceImpl
/*    */   implements WCashAccountServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WCashAccountEntity)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WCashAccountEntity)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WCashAccountEntity)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WCashAccountEntity t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WCashAccountEntity t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WCashAccountEntity t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WCashAccountEntity t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{user_id}", String.valueOf(t.getUserId()));
/* 67 */     sql = sql.replace("#{user_name}", String.valueOf(t.getUserName()));
/* 68 */     sql = sql.replace("#{alipay_binding_state}", String.valueOf(t.getAlipayBindingState()));
/* 69 */     sql = sql.replace("#{alipay_account}", String.valueOf(t.getAlipayAccount()));
/* 70 */     sql = sql.replace("#{wechat_state}", String.valueOf(t.getWechatState()));
/* 71 */     sql = sql.replace("#{wechat_account}", String.valueOf(t.getWechatAccount()));
/* 72 */     sql = sql.replace("#{bank_card_state}", String.valueOf(t.getBankCardState()));
/* 73 */     sql = sql.replace("#{cardholder}", String.valueOf(t.getCardholder()));
/* 74 */     sql = sql.replace("#{bank_card}", String.valueOf(t.getBankCard()));
/* 75 */     sql = sql.replace("#{card_type}", String.valueOf(t.getCardType()));
/* 76 */     sql = sql.replace("#{reserve_phone}", String.valueOf(t.getReservePhone()));
/* 77 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 78 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wcashaccount.WCashAccountServiceImpl
 * JD-Core Version:    0.6.2
 */