/*    */ package test;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jeecgframework.codegenerate.generate.onetomany.CodeGenerateOneToMany;
/*    */ import org.jeecgframework.codegenerate.pojo.onetomany.CodeParamEntity;
/*    */ import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;
/*    */ 
/*    */ public class JeecgOneToMainUtil
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 25 */     CodeParamEntity codeParamEntityIn = new CodeParamEntity();
/* 26 */     codeParamEntityIn.setTableName("jform_order_main");
/* 27 */     codeParamEntityIn.setEntityName("TestOrderMain");
/* 28 */     codeParamEntityIn.setEntityPackage("test");
/* 29 */     codeParamEntityIn.setFtlDescription("订单");
/*    */ 
/* 32 */     List subTabParamIn = new ArrayList();
/*    */ 
/* 34 */     SubTableEntity po = new SubTableEntity();
/* 35 */     po.setTableName("jform_order_customer");
/* 36 */     po.setEntityName("TestOrderCustom");
/* 37 */     po.setEntityPackage("test");
/* 38 */     po.setFtlDescription("客户明细");
/*    */ 
/* 45 */     po.setForeignKeys(new String[] { "fk_id" });
/* 46 */     subTabParamIn.add(po);
/*    */ 
/* 48 */     SubTableEntity po2 = new SubTableEntity();
/* 49 */     po2.setTableName("jform_order_ticket");
/* 50 */     po2.setEntityName("TestOrderTicket");
/* 51 */     po2.setEntityPackage("test");
/* 52 */     po2.setFtlDescription("产品明细");
/*    */ 
/* 59 */     po2.setForeignKeys(new String[] { "fck_id" });
/* 60 */     subTabParamIn.add(po2);
/* 61 */     codeParamEntityIn.setSubTabParam(subTabParamIn);
/*    */ 
/* 64 */     CodeGenerateOneToMany.oneToManyCreate(subTabParamIn, codeParamEntityIn);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.test.JeecgOneToMainUtil
 * JD-Core Version:    0.6.2
 */