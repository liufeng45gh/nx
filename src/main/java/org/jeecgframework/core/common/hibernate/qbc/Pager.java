/*     */ package org.jeecgframework.core.common.hibernate.qbc;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class Pager
/*     */ {
/*  10 */   private int curPageNO = 1;
/*     */   private int pageSize;
/*     */   private int rowsCount;
/*     */   private int pageCount;
/*     */   private Map<String, Object> map;
/*     */ 
/*     */   public Pager(int allCount, int curPagerNo, int pageSize, Map<String, Object> map)
/*     */   {
/*  25 */     this.curPageNO = curPagerNo;
/*  26 */     this.pageSize = pageSize;
/*  27 */     this.rowsCount = allCount;
/*  28 */     this.map = map;
/*  29 */     this.pageCount = ((int)Math.ceil(allCount / pageSize));
/*     */   }
/*     */ 
/*     */   public Pager() {
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  36 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public int getRowsCount()
/*     */   {
/*  41 */     return this.rowsCount;
/*     */   }
/*     */ 
/*     */   public int getPageCount()
/*     */   {
/*  46 */     return this.pageCount;
/*     */   }
/*     */ 
/*     */   public int first()
/*     */   {
/*  51 */     return 1;
/*     */   }
/*     */ 
/*     */   public int last()
/*     */   {
/*  56 */     return this.pageCount;
/*     */   }
/*     */ 
/*     */   public int previous()
/*     */   {
/*  61 */     return this.curPageNO - 1 < 1 ? 1 : this.curPageNO - 1;
/*     */   }
/*     */ 
/*     */   public int next()
/*     */   {
/*  66 */     return this.curPageNO + 1 > this.pageCount ? this.pageCount : this.curPageNO + 1;
/*     */   }
/*     */ 
/*     */   public boolean isFirst()
/*     */   {
/*  71 */     return this.curPageNO == 1;
/*     */   }
/*     */ 
/*     */   public boolean isLast()
/*     */   {
/*  76 */     return this.curPageNO == this.pageCount;
/*     */   }
/*     */   public String toString() {
/*  79 */     return "Pager的值为  curPageNO = " + this.curPageNO + " limit = " + this.pageSize + " rowsCount = " + this.rowsCount + " pageCount = " + this.pageCount;
/*     */   }
/*     */ 
/*     */   public String getToolBar(String url)
/*     */   {
/*  89 */     String temp = "";
/*  90 */     String conditions = "";
/*  91 */     if (this.map.size() > 0)
/*     */     {
/*  93 */       for (Map.Entry entry : this.map.entrySet()) {
/*  94 */         conditions = conditions + "&" + (String)entry.getKey() + "=" + entry.getValue();
/*     */       }
/*     */     }
/*  97 */     if (url.indexOf("?") == -1)
/*  98 */       temp = "?";
/*     */     else {
/* 100 */       temp = "&";
/*     */     }
/* 102 */     String str = "";
/* 103 */     str = str;
/* 104 */     if (isFirst()) {
/* 105 */       str = str + "第" + this.curPageNO + "页&nbsp;共" + this.pageCount + "页&nbsp;首页 上一页&nbsp;";
/*     */     } else {
/* 107 */       str = str + "第" + this.curPageNO + "页&nbsp;共" + this.pageCount + "页&nbsp;<a href='" + url + temp + "curPageNO=1" + conditions + "'>首页</a>&nbsp;";
/* 108 */       str = str + "<a href='" + url + temp + "curPageNO=" + previous() + conditions + "' onMouseMove=\"style.cursor='hand'\" alt=\"上一页\">上一页</a>&nbsp;";
/*     */     }
/* 110 */     if ((isLast()) || (this.rowsCount == 0)) {
/* 111 */       str = str + "下一页 尾页&nbsp;";
/*     */     } else {
/* 113 */       str = str + "<a href='" + url + temp + "curPageNO=" + next() + conditions + "' onMouseMove=\"style.cursor='hand'\" >下一页</a>&nbsp;";
/* 114 */       str = str + "<a href='" + url + temp + "curPageNO=" + this.pageCount + conditions + "'>尾页</a>&nbsp;";
/*     */     }
/* 116 */     str = str + "&nbsp;共" + this.rowsCount + "条记录&nbsp;";
/*     */ 
/* 118 */     str = str + "&nbsp;转到<select name='page' onChange=\"location='" + url + temp + "curPageNO='+this.options[this.selectedIndex].value\">"; int begin = this.curPageNO > 10 ? this.curPageNO - 10 : 1; int end = this.pageCount - this.curPageNO > 10 ? this.curPageNO + 10 : this.pageCount; for (int i = begin; i <= end; i++) if (i == this.curPageNO) str = str + "<option value='" + i + "' selected>第" + i + "页</option>"; else str = str + "<option value='" + i + "'>第" + i + "页</option>"; 
/* 118 */     str = str + "</select>";
/*     */ 
/* 120 */     return str;
/*     */   }
/*     */ 
/*     */   public String getToolBar(String myaction, String myform)
/*     */   {
/* 129 */     String str = "";
/* 130 */     str = str + "<script language='javascript'>\n";
/* 131 */     str = str + "function commonSubmit(val){\n";
/*     */ 
/* 133 */     str = str + "var patrn=/^[0-9]{1,20}$/;\n";
/* 134 */     str = str + "if (!patrn.exec(val)){\n";
/* 135 */     str = str + " alert(\"请输入有效页号！\");\n";
/* 136 */     str = str + " return false ;\n";
/* 137 */     str = str + " }else{\n";
/* 138 */     str = str + "    document." + myform + ".action='" + myaction + "&curPageNO='+val;" + "\n";
/* 139 */     str = str + "    document." + myform + ".submit();" + "\n";
/* 140 */     str = str + "    return true ;\n";
/* 141 */     str = str + "} \n";
/* 142 */     str = str + " }\n";
/* 143 */     str = str + "</script>\n";
/* 144 */     str = str + "&nbsp;<DIV class=pageArea id=pageArea>共<b>" + this.rowsCount + "</b>条&nbsp;当前第" + this.curPageNO + "/" + this.pageCount + "页&nbsp;&nbsp;&nbsp;";
/* 145 */     if ((this.curPageNO == 1) || (this.curPageNO == 0)) {
/* 146 */       str = str + "<a class=pageFirstDisable title=首页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pagePreviousDisable title=上一页 onMouseMove=\"style.cursor='hand'\"></a>";
/*     */     } else {
/* 148 */       str = str + "<a class=pageFirst title=首页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(1)\"></a>";
/* 149 */       str = str + "<a class=pagePrevious title=上一页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + (this.curPageNO - 1) + ")\"></a>";
/*     */     }
/* 151 */     if ((this.curPageNO - this.pageCount == 0) || (this.pageCount == 0) || (this.pageCount == 1)) {
/* 152 */       str = str + "<a class=pageNextDisable  title=下一页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pageLastDisable title=尾页 onMouseMove=\"style.cursor='hand'\"></a>&nbsp;";
/*     */     } else {
/* 154 */       str = str + "<a class=pageNext title=下一页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + (this.curPageNO + 1) + ")\"></a>";
/* 155 */       str = str + "<a class=pageLast title=尾页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + this.pageCount + ")\"></a>";
/*     */     }
/*     */ 
/* 158 */     if ((this.pageCount == 1) || (this.pageCount == 0)) {
/* 159 */       str = str + " &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=5 name=\"pageroffsetll\" size=3 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
/* 160 */       str = str + "<A class=pageGoto id=pageGoto title=转到 onclick='return commonSubmit()'></A></DIV>";
/*     */     } else {
/* 162 */       str = str + " &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=5 name=\"pageroffsetll\" size=3 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
/* 163 */       str = str + "<A class=pageGoto id=pageGoto title=转到 onclick='commonSubmit(document.all.pageroffsetll.value)'></A></DIV>";
/*     */     }
/* 165 */     return str;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.hibernate.qbc.Pager
 * JD-Core Version:    0.6.2
 */