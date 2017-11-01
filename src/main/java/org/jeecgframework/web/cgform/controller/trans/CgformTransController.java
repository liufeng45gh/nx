//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.trans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.codegenerate.pojo.Columnt;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cgformTransController"})
public class CgformTransController {
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;

    public CgformTransController() {
    }

    @RequestMapping(
            params = {"trans"}
    )
    public ModelAndView trans(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/trans/transList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String tableName = request.getParameter("id");
        Object list = new ArrayList();

        try {
            list = (new JeecgReadTable()).readAllTableNames();
        } catch (SQLException var22) {
            var22.printStackTrace();
        }

        String html = "";
        Collections.sort((List)list, new CgformTransController.StringSort(dataGrid.getOrder()));
        List<String> tables = this.cgFormFieldService.findByQueryString("select tableName from CgFormHeadEntity");
        ((List)list).removeAll(tables);
        List<String> index = new ArrayList();
        if(StringUtil.isNotEmpty(tableName)) {
            for(int i = 0; i < ((List)list).size(); ++i) {
                if(((String)((List)list).get(i)).contains(tableName)) {
                    index.add((String)((List)list).get(i));
                }
            }

            html = getJson(index, Integer.valueOf(index.size()));
        } else {
            html = getJson((List)list, Integer.valueOf(((List)list).size()));
        }

        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var20) {
            var20.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var19) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"transEditor"}
    )
    @ResponseBody
    public AjaxJson transEditor(HttpServletRequest request, String id) throws Exception {
        AjaxJson j = new AjaxJson();
        String[] ids = id.split(",");
        String no = "";
        String yes = "";

        for(int i = 0; i < ids.length; ++i) {
            if(StringUtil.isNotEmpty(ids[i])) {
                List<CgFormHeadEntity> cffList = this.cgFormFieldService.findByProperty(CgFormHeadEntity.class, "tableName", ids[i]);
                if(cffList.size() > 0) {
                    if(no != "") {
                        no = no + ",";
                    }

                    no = no + ids[i];
                } else {
                    List<Columnt> list = (new JeecgReadTable()).readOriginalTableColumn(ids[i]);
                    CgFormHeadEntity cgFormHead = new CgFormHeadEntity();
                    cgFormHead.setJformType(Integer.valueOf(1));
                    cgFormHead.setIsCheckbox("Y");
                    cgFormHead.setIsDbSynch("Y");
                    cgFormHead.setIsTree("N");
                    cgFormHead.setQuerymode("group");
                    cgFormHead.setTableName(ids[i].toLowerCase());
                    cgFormHead.setIsPagination("Y");
                    cgFormHead.setContent(ids[i]);
                    cgFormHead.setJformVersion("1");
                    List<CgFormFieldEntity> columnsList = new ArrayList();

                    for(int k = 0; k < list.size(); ++k) {
                        Columnt columnt = (Columnt)list.get(k);
                        String fieldName = columnt.getFieldDbName();
                        CgFormFieldEntity cgFormField = new CgFormFieldEntity();
                        cgFormField.setFieldName(columnt.getFieldDbName().toLowerCase());
                        if(StringUtil.isNotEmpty(columnt.getFiledComment())) {
                            cgFormField.setContent(columnt.getFiledComment());
                        } else {
                            cgFormField.setContent(columnt.getFieldName());
                        }

                        cgFormField.setIsKey("N");
                        cgFormField.setIsShow("Y");
                        cgFormField.setIsShowList("Y");
                        cgFormField.setOrderNum(Integer.valueOf(k + 2));
                        cgFormField.setQueryMode("group");
                        cgFormField.setLength(Integer.valueOf(0));
                        cgFormField.setFieldLength(Integer.valueOf(120));
                        cgFormField.setPointLength(Integer.valueOf(0));
                        cgFormField.setShowType("text");
                        cgFormField.setIsNull(columnt.getNullable());
                        if("id".equalsIgnoreCase(fieldName)) {
                            String[] pkTypeArr = new String[]{"java.lang.Integer", "java.lang.Long"};
                            String idFiledType = columnt.getFieldType();
                            if(Arrays.asList(pkTypeArr).contains(idFiledType)) {
                                cgFormHead.setJformPkType("NATIVE");
                            } else {
                                cgFormHead.setJformPkType("UUID");
                            }

                            cgFormField.setIsKey("Y");
                            cgFormField.setIsShow("N");
                            cgFormField.setIsShowList("N");
                        }

                        if("java.lang.Integer".equalsIgnoreCase(columnt.getFieldType())) {
                            cgFormField.setType("int");
                        } else if("java.lang.Long".equalsIgnoreCase(columnt.getFieldType())) {
                            cgFormField.setType("int");
                        } else if("java.util.Date".equalsIgnoreCase(columnt.getFieldType())) {
                            cgFormField.setType("Date");
                            cgFormField.setShowType("date");
                        } else if(!"java.lang.Double".equalsIgnoreCase(columnt.getFieldType()) && !"java.lang.Float".equalsIgnoreCase(columnt.getFieldType())) {
                            if("java.math.BigDecimal".equalsIgnoreCase(columnt.getFieldType())) {
                                cgFormField.setType("BigDecimal");
                            } else if(columnt.getFieldType().contains("blob")) {
                                cgFormField.setType("Blob");
                                columnt.setCharmaxLength((String)null);
                            } else {
                                cgFormField.setType("string");
                            }
                        } else {
                            cgFormField.setType("double");
                        }

                        if(StringUtil.isNotEmpty(columnt.getCharmaxLength())) {
                            if(Long.valueOf(columnt.getCharmaxLength()).longValue() >= 3000L) {
                                cgFormField.setType("Text");
                                cgFormField.setShowType("textarea");

                                try {
                                    cgFormField.setLength(Integer.valueOf(columnt.getCharmaxLength()));
                                } catch (Exception var18) {
                                    ;
                                }
                            } else {
                                cgFormField.setLength(Integer.valueOf(columnt.getCharmaxLength()));
                            }
                        } else {
                            if(StringUtil.isNotEmpty(columnt.getPrecision())) {
                                cgFormField.setLength(Integer.valueOf(columnt.getPrecision()));
                            } else if(cgFormField.getType().equals("int")) {
                                cgFormField.setLength(Integer.valueOf(10));
                            }

                            if(StringUtil.isNotEmpty(columnt.getScale())) {
                                cgFormField.setPointLength(Integer.valueOf(columnt.getScale()));
                            }
                        }

                        columnsList.add(cgFormField);
                    }

                    cgFormHead.setColumns(columnsList);
                    this.cgFormFieldService.saveTable(cgFormHead, "");
                    if(yes != "") {
                        yes = yes + ",";
                    }

                    yes = yes + ids[i];
                }
            }
        }

        Map<String, String> map = new HashMap();
        map.put("no", no);
        map.put("yes", yes);
        j.setObj(map);
        return j;
    }

    public static String getJson(List<String> result, Integer size) {
        JSONObject main = new JSONObject();
        JSONArray rows = new JSONArray();
        main.put("total", size);
        Iterator var5 = result.iterator();

        while(var5.hasNext()) {
            String m = (String)var5.next();
            JSONObject item = new JSONObject();
            item.put("id", m);
            rows.add(item);
        }

        main.put("rows", rows);
        return main.toString();
    }

    private class StringSort implements Comparator<String> {
        private SortDirection sortOrder;

        public StringSort(SortDirection sortDirection) {
            this.sortOrder = sortDirection;
        }

        public int compare(String prev, String next) {
            return this.sortOrder.equals(SortDirection.asc)?prev.compareTo(next):next.compareTo(prev);
        }
    }
}
