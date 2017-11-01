/*     */ package org.jeecgframework.tag.core.easyui;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class PagerUtil
/*     */ {
/*  11 */   private int curPageNO = 1;
/*     */   private int rowsCount;
/*     */   private int pageCount;
/*     */   private String actionUrl;
/*     */   private Map<String, Object> map;
/*     */ 
/*     */   public PagerUtil(int curPageNo, int allCount, int pageSize, Map<String, Object> map, String actionUrl)
/*     */   {
/*  17 */     this.curPageNO = curPageNo;
/*  18 */     this.rowsCount = allCount;
/*  19 */     this.map = map;
/*  20 */     this.actionUrl = actionUrl;
/*  21 */     this.pageCount = ((int)Math.ceil(allCount / pageSize));
/*     */   }
/*     */ 
/*     */   public int first() {
/*  25 */     return 1;
/*     */   }
/*     */ 
/*     */   public int last()
/*     */   {
/*  30 */     return this.pageCount;
/*     */   }
/*     */ 
/*     */   public int previous()
/*     */   {
/*  35 */     return this.curPageNO - 1 < 1 ? 1 : this.curPageNO - 1;
/*     */   }
/*     */ 
/*     */   public int next()
/*     */   {
/*  40 */     return this.curPageNO + 1 > this.pageCount ? this.pageCount : this.curPageNO + 1;
/*     */   }
/*     */ 
/*     */   public boolean isFirst()
/*     */   {
/*  45 */     return this.curPageNO == 1;
/*     */   }
/*     */ 
/*     */   public boolean isLast()
/*     */   {
/*  50 */     return this.curPageNO == this.pageCount;
/*     */   }
/*     */   protected StringBuffer getStrByImage(StringBuffer sb) {
/*  53 */     String join = getJoin();
/*  54 */     String conditions = getConditions();
/*  55 */     sb.append("<script language='javascript'>\n");
/*  56 */     sb.append("function commonSubmit(val){\n");
/*     */ 
/*  58 */     sb.append("var patrn=/^[0-9]{1,20}$/;\n");
/*  59 */     sb.append("if (!patrn.exec(val)){\n");
/*  60 */     sb.append(" alert(\"请输入有效页号！\");\n");
/*  61 */     sb.append(" return false ;\n");
/*  62 */     sb.append(" }else{\n");
/*  63 */     sb.append("    document.getElementById('pageGoto').href='" + this.actionUrl + join + "curPageNO='+val+'" + conditions + "';" + "\n");
/*  64 */     sb.append("    return true ;\n");
/*  65 */     sb.append("} \n");
/*  66 */     sb.append(" }\n");
/*  67 */     sb.append("</script>\n");
/*  68 */     sb.append("&nbsp;<span class=pageArea id=pageArea>共<b>" + this.rowsCount + "</b>条&nbsp;当前第" + this.curPageNO + "/" + this.pageCount + "页&nbsp;&nbsp;&nbsp;");
/*  69 */     if (isFirst()) {
/*  70 */       sb.append("<a class=\"pageFirstDisable\"  title=\"首页\" onMouseMove=\"style.cursor='hand'\">&nbsp;</a><a class=\"pagePreviousDisable\" title=\"上一页\"  onMouseMove=\"style.cursor='hand'\">&nbsp;</a>");
/*     */     } else {
/*  72 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=1" + conditions + "' class=pageFirst title=首页 onMouseMove=\"style.cursor='hand'\"></a>");
/*  73 */       sb.append("<a class=\"pagePrevious\" href='" + this.actionUrl + join + "curPageNO=" + previous() + conditions + "' title=\"上一页\"  onMouseMove=\"style.cursor='hand'\")\">&nbsp;</a>");
/*     */     }
/*  75 */     if ((this.curPageNO - this.pageCount == 0) || (this.pageCount == 0) || (this.pageCount == 1)) {
/*  76 */       sb.append("<a class=pageNextDisable  title=下一页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pageLastDisable title=尾页 onMouseMove=\"style.cursor='hand'\">&nbsp;</a>&nbsp;");
/*     */     } else {
/*  78 */       sb.append("<a class=pageNext href='" + this.actionUrl + join + "curPageNO=" + next() + conditions + "' title=下一页 onMouseMove=\"style.cursor='hand'\")\">&nbsp;</a>");
/*  79 */       sb.append("<a class=pageLast href='" + this.actionUrl + join + "curPageNO=" + this.pageCount + conditions + "' title=尾页 onMouseMove=\"style.cursor='hand'\" )\">&nbsp;</a>");
/*     */     }
/*     */ 
/*  82 */     if ((this.pageCount == 1) || (this.pageCount == 0)) {
/*  83 */       sb.append(" &nbsp;转到:<input class=\"SmallInput\" type=text style=TEXT-ALIGN: center maxLength=4 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;");
/*  84 */       sb.append("<A class=pageGoto id=pageGoto title=转到 onclick='return commonSubmit()'>&nbsp;</A>&nbsp;&nbsp;</span>");
/*     */     } else {
/*  86 */       sb.append(" &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=4 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;");
/*  87 */       sb.append("<A  class=pageGoto id=pageGoto title=转到 onclick='commonSubmit(document.all.pageroffsetll.value)'>&nbsp;</A>&nbsp;</span");
/*     */     }
/*  89 */     return sb;
/*     */   }
/*     */ 
/*     */   protected StringBuffer getStr(StringBuffer sb) {
/*  93 */     String join = getJoin();
/*  94 */     String conditions = getConditions();
/*     */ 
/*  96 */     String str = "";
/*  97 */     str = str;
/*  98 */     if (isFirst()) {
/*  99 */       sb.append("第" + this.curPageNO + "页&nbsp;共" + this.pageCount + "页&nbsp;首页 ");
/*     */     } else {
/* 101 */       sb.append("第" + this.curPageNO + "页&nbsp;共" + this.pageCount + "页&nbsp;<a href='" + this.actionUrl + join + "curPageNO=1" + conditions + "'>首页</a>&nbsp;");
/* 102 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=" + previous() + conditions + "' onMouseMove=\"style.cursor='hand'\" alt=\"上一页\">上一页</a>&nbsp;");
/*     */     }
/* 104 */     if ((isLast()) || (this.rowsCount == 0)) {
/* 105 */       sb.append("尾页&nbsp;");
/*     */     } else {
/* 107 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=" + next() + conditions + "' onMouseMove=\"style.cursor='hand'\" >下一页</a>&nbsp;");
/* 108 */       sb.append("<a href='" + this.actionUrl + join + "curPageNO=" + this.pageCount + conditions + "'>尾页</a>&nbsp;");
/*     */     }
/* 110 */     sb.append("&nbsp;共" + this.rowsCount + "条记录&nbsp;");
/*     */ 
/* 112 */     str = str + "&nbsp;转到<select name='page' onChange=\"location='" + this.actionUrl + join + "curPageNO='+this.options[this.selectedIndex].value\">";
/* 113 */     int begin = this.curPageNO > 10 ? this.curPageNO - 10 : 1;
/* 114 */     int end = this.pageCount - this.curPageNO > 10 ? this.curPageNO + 10 : this.pageCount;
/* 115 */     for (int i = begin; i <= end; i++) {
/* 116 */       if (i == this.curPageNO)
/* 117 */         str = str + "<option value='" + i + "' selected>第" + i + "页</option>";
/*     */       else
/* 119 */         str = str + "<option value='" + i + "'>第" + i + "页</option>";
/*     */     }
/* 121 */     str = str + "</select>";
/* 122 */     sb.append(str);
/* 123 */     return sb;
/*     */   }
/*     */ 
/*     */   protected String getConditions()
/*     */   {
/* 131 */     String conditions = "";
/* 132 */     if (this.map != null)
/*     */     {
/* 134 */       for (Map.Entry entry : this.map.entrySet()) {
/* 135 */         conditions = conditions + "&" + (String)entry.getKey() + "=" + entry.getValue();
/*     */       }
/*     */     }
/* 138 */     return conditions;
/*     */   }
/*     */ 
/*     */   protected String getJoin()
/*     */   {
/* 148 */     String join = "";
/* 149 */     if (this.actionUrl.indexOf("?") == -1)
/* 150 */       join = "?";
/*     */     else {
/* 152 */       join = "&";
/*     */     }
/* 154 */     return join;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.PagerUtil
 * JD-Core Version:    0.6.2
 */