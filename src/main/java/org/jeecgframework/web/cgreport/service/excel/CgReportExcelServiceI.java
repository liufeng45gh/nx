package org.jeecgframework.web.cgreport.service.excel;

import java.util.Collection;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.service.CommonService;

public abstract interface CgReportExcelServiceI extends CommonService
{
  public abstract HSSFWorkbook exportExcel(String paramString, Collection<?> paramCollection1, Collection<?> paramCollection2);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.service.excel.CgReportExcelServiceI
 * JD-Core Version:    0.6.2
 */