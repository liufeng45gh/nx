/*    */ package test;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ class UDPClient
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws IOException
/*    */   {
/* 17 */     System.out.println("循环没有开始");
/*    */ 
/* 19 */     System.out.println("现在开始测试continue");
/* 20 */     for (int i = 0; i < 3; i++) {
/* 21 */       System.out.println("开始第" + i + "次for循环开始");
/* 22 */       if (i != 1)
/*    */       {
/* 25 */         System.out.println("开始第" + i + "次for循环结束");
/*    */       }
/*    */     }
/* 27 */     System.out.println("continue测试完毕\n***********************");
/*    */ 
/* 29 */     System.out.println("现在开始测试break");
/* 30 */     for (int i = 0; i < 3; i++) {
/* 31 */       System.out.println("开始第" + i + "次for循环");
/* 32 */       if (i == 1)
/*    */       {
/*    */         break;
/*    */       }
/* 36 */       System.out.println("结束第" + i + "次for循环");
/*    */     }
/* 38 */     System.out.println("break测试完毕\n***********************");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.test.UDPClient
 * JD-Core Version:    0.6.2
 */