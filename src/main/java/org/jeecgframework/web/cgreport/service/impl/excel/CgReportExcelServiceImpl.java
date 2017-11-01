//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgreport.service.impl.excel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.cgreport.service.excel.CgReportExcelServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgReportExcelService")
@Transactional
public class CgReportExcelServiceImpl extends CommonServiceImpl implements CgReportExcelServiceI {
    public CgReportExcelServiceImpl() {
    }

    public HSSFWorkbook exportExcel(String title, Collection<?> titleSet, Collection<?> dataSet) {
        HSSFWorkbook workbook = null;

        try {
            if(titleSet == null || titleSet.size() == 0) {
                throw new Exception("读取表头失败！");
            }

            if(title == null) {
                title = "";
            }

            workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(title);
            int rindex = 0;
            int cindex = 0;
            Row row = sheet.createRow(rindex);
            row.setHeight((short) 450);
            CellStyle titleStyle = getTitleStyle(workbook);
            List<Map> titleList = (List)titleSet;
            Iterator itData = dataSet.iterator();

            for(Iterator var13 = titleList.iterator(); var13.hasNext(); ++cindex) {
                Map titleM = (Map)var13.next();
                String titleContent = (String)titleM.get("field_txt");
                Cell cell = row.createCell(cindex);
                RichTextString text = new HSSFRichTextString(titleContent);
                cell.setCellValue(text);
                cell.setCellStyle(titleStyle);
            }

            HSSFCellStyle bodyStyle = getOneStyle(workbook);

            while(itData.hasNext()) {
                cindex = 0;
                ++rindex;
                row = sheet.createRow(rindex);
                Map dataM = (Map)itData.next();

                for(Iterator var25 = titleList.iterator(); var25.hasNext(); ++cindex) {
                    Map titleM = (Map)var25.next();
                    String field = (String)titleM.get("field_name");
                    String content = dataM.get(field) == null?"":dataM.get(field).toString();
                    Cell cell = row.createCell(cindex);
                    RichTextString text = new HSSFRichTextString(content);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(text);
                }
            }

            for(int i = 0; i < titleList.size(); ++i) {
                sheet.autoSizeColumn(i);
            }
        } catch (Exception var20) {
            var20.printStackTrace();
        }

        return workbook;
    }

    public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setBorderLeft((short)1);
        titleStyle.setBorderRight((short)1);
        titleStyle.setBorderBottom((short) 1);
        titleStyle.setBorderTop((short)1);
        titleStyle.setAlignment((short)2);
        titleStyle.setFillForegroundColor((short)22);
        titleStyle.setFillPattern((short)1);
        return titleStyle;
    }

    public static void setBlankRows(int rows, int columns, HSSFWorkbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        CellStyle cellStyle = getOneStyle(workbook);

        for(int i = 1; i <= rows; ++i) {
            Row row = sheet.createRow(i);

            for(int j = 0; j < columns; ++j) {
                row.createCell(j).setCellStyle(cellStyle);
            }
        }

    }

    public static HSSFCellStyle getTwoStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderBottom((short)1);
        style.setBorderTop((short)1);
        style.setFillForegroundColor((short)41);
        style.setFillPattern((short)1);
        return style;
    }

    public static HSSFCellStyle getOneStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderBottom((short)1);
        style.setBorderTop((short)1);
        return style;
    }
}
