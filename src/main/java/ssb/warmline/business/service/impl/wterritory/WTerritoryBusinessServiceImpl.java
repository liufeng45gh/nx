/*    */ package ssb.warmline.business.service.impl.wterritory;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.UUID;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
/*    */ import ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI;
/*    */ 
/*    */ @Service("wTerritoryBusinessService")
/*    */ @Transactional
/*    */ public class WTerritoryBusinessServiceImpl extends CommonServiceImpl
/*    */   implements WTerritoryBusinessServiceI
/*    */ {
/*    */   public <T> void delete(T entity)
/*    */   {
/* 16 */     super.delete(entity);
/*    */ 
/* 18 */     doDelSql((WTerritoryBusiness)entity);
/*    */   }
/*    */ 
/*    */   public <T> Serializable save(T entity) {
/* 22 */     Serializable t = super.save(entity);
/*    */ 
/* 24 */     doAddSql((WTerritoryBusiness)entity);
/* 25 */     return t;
/*    */   }
/*    */ 
/*    */   public <T> void saveOrUpdate(T entity) {
/* 29 */     super.saveOrUpdate(entity);
/*    */ 
/* 31 */     doUpdateSql((WTerritoryBusiness)entity);
/*    */   }
/*    */ 
/*    */   public boolean doAddSql(WTerritoryBusiness t)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(WTerritoryBusiness t)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(WTerritoryBusiness t)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public String replaceVal(String sql, WTerritoryBusiness t)
/*    */   {
/* 65 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/* 66 */     sql = sql.replace("#{territorycode}", String.valueOf(t.getTerritorycode()));
/* 67 */     sql = sql.replace("#{territorylevel}", String.valueOf(t.getTerritorylevel()));
/* 68 */     sql = sql.replace("#{territoryname}", String.valueOf(t.getTerritoryname()));
/* 69 */     sql = sql.replace("#{territory_pinyin}", String.valueOf(t.getTerritoryPinyin()));
/* 70 */     sql = sql.replace("#{territorysort}", String.valueOf(t.getTerritorysort()));
/* 71 */     sql = sql.replace("#{xwgs84}", String.valueOf(t.getXwgs84()));
/* 72 */     sql = sql.replace("#{ywgs84}", String.valueOf(t.getYwgs84()));
/* 73 */     sql = sql.replace("#{territoryparentid}", String.valueOf(t.getTerritoryparentid()));
/* 74 */     sql = sql.replace("#{isparent}", String.valueOf(t.getIsparent()));
/* 75 */     sql = sql.replace("#{open}", String.valueOf(t.getOpen()));
/* 76 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 77 */     return sql;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.wterritory.WTerritoryBusinessServiceImpl
 * JD-Core Version:    0.6.2
 */