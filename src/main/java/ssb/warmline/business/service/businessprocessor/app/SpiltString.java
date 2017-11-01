/*    */ package ssb.warmline.business.service.businessprocessor.app;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class SpiltString
/*    */ {
/*    */   private static final int LENGTH = 8;
/*    */ 
/*    */   public static String generateNumber()
/*    */   {
/*  8 */     String no = "";
/*    */ 
/* 10 */     int[] defaultNums = new int[10];
/* 11 */     for (int i = 0; i < defaultNums.length; i++) {
/* 12 */       defaultNums[i] = i;
/*    */     }
/*    */ 
/* 15 */     Random random = new Random();
/* 16 */     int[] nums = new int[8];
/*    */ 
/* 18 */     int canBeUsed = 10;
/*    */ 
/* 20 */     for (int i = 0; i < nums.length; i++)
/*    */     {
/* 22 */       int index = random.nextInt(canBeUsed);
/* 23 */       nums[i] = defaultNums[index];
/*    */ 
/* 25 */       swap(index, canBeUsed - 1, defaultNums);
/* 26 */       canBeUsed--;
/*    */     }
/* 28 */     if (nums.length > 0) {
/* 29 */       for (int i = 0; i < nums.length; i++) {
/* 30 */         no = no + nums[i];
/*    */       }
/*    */     }
/*    */ 
/* 34 */     return no;
/*    */   }
/*    */ 
/*    */   private static void swap(int i, int j, int[] nums)
/*    */   {
/* 39 */     int temp = nums[i];
/* 40 */     nums[i] = nums[j];
/* 41 */     nums[j] = temp;
/*    */   }
/*    */ 
/*    */   public static String generateNumber2() {
/* 45 */     String no = "";
/* 46 */     int[] num = new int[8];
/* 47 */     int c = 0;
/* 48 */     for (int i = 0; i < 8; i++) {
/* 49 */       num[i] = new Random().nextInt(10);
/* 50 */       c = num[i];
/* 51 */       for (int j = 0; j < i; j++) {
/* 52 */         if (num[j] == c) {
/* 53 */           i--;
/* 54 */           break;
/*    */         }
/*    */       }
/*    */     }
/* 58 */     if (num.length > 0) {
/* 59 */       for (int i = 0; i < num.length; i++) {
/* 60 */         no = no + num[i];
/*    */       }
/*    */     }
/* 63 */     return no;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 67 */     System.out.println(generateNumber2());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.app.SpiltString
 * JD-Core Version:    0.6.2
 */