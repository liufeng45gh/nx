//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.autolist;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.JeecgDataAutorUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.common.CommUtils;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.QueryParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgTableService")
@Transactional
public class CgTableServiceImpl extends CommonServiceImpl implements CgTableServiceI {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;

    public CgTableServiceImpl() {
    }

    public List<Map<String, Object>> querySingle(String table, String field, Map params, int page, int rows) {
        StringBuilder sqlB = new StringBuilder();
        this.dealQuerySql(table, field, params, sqlB);
        List<Map<String, Object>> result = this.commonService.findForJdbcParam(sqlB.toString(), page, rows, new Object[0]);
        return result;
    }

    public List<Map<String, Object>> querySingle(String table, String field, Map params, String sort, String order, int page, int rows) {
        StringBuilder sqlB = new StringBuilder();
        this.dealQuerySql(table, field, params, sqlB);
        if(!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
            sqlB.append(" ORDER BY " + sort + " " + order);
        }

        List<Map<String, Object>> result = this.commonService.findForJdbcParam(sqlB.toString(), page, rows, new Object[0]);
        return result;
    }

    public boolean delete(String table, Object id) {
        try {
            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(table);
            Map<String, Object> data = this.dataBaseService.findOneForJdbc(table, id.toString());
            if(data != null) {
                Iterator it = data.entrySet().iterator();

                while(it.hasNext()) {
                    Entry entry = (Entry)it.next();
                    Object ok = entry.getKey();
                    if(entry.getValue() == null) {
                        String var10000 = "";
                    } else {
                        entry.getValue();
                    }
                }

                data = CommUtils.mapConvert(data);
                this.dataBaseService.executeSqlExtend(head.getId(), "delete", data);
                this.dataBaseService.executeJavaExtend(head.getId(), "delete", data);
            }

            StringBuilder deleteSql = new StringBuilder();
            deleteSql.append("DELETE FROM " + table + " WHERE id = ?");
            if(!QueryParamUtil.sql_inj(id.toString())) {
                this.commonService.executeSql(deleteSql.toString(), new Object[]{id});
            }

            String[] subTables = head.getSubTableStr() == null?new String[0]:head.getSubTableStr().split(",");
            String[] var10 = subTables;
            int var9 = subTables.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                String subTable = var10[var8];
                Map<String, CgFormFieldEntity> fields = this.cgFormFieldService.getAllCgFormFieldByTableName(subTable);
                String subFkField = null;
                Iterator it = fields.keySet().iterator();

                String dsql;
                while(it.hasNext()) {
                    dsql = (String)it.next();
                    CgFormFieldEntity fieldc = (CgFormFieldEntity)fields.get(dsql);
                    if(StringUtil.isNotEmpty(fieldc.getMainTable()) && table.equalsIgnoreCase(fieldc.getMainTable())) {
                        subFkField = dsql;
                    }
                }

                if(StringUtil.isNotEmpty(subFkField)) {
                    dsql = "delete from " + subTable + " " + "where " + subFkField + " = ? ";
                    this.executeSql(dsql, new Object[]{id});
                }
            }

            List<CgUploadEntity> uploadBeans = this.cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
            if(uploadBeans != null) {
                Iterator var22 = uploadBeans.iterator();

                while(var22.hasNext()) {
                    CgUploadEntity b = (CgUploadEntity)var22.next();
                    String path = ResourceUtil.getSysPath() + File.separator + b.getRealpath();
                    FileUtils.delete(path);
                    this.cgFormFieldService.deleteEntityById(CgUploadEntity.class, b.getId());
                }
            }

            return true;
        } catch (Exception var16) {
            var16.printStackTrace();
            return false;
        }
    }

    private void dealQuerySql(String table, String field, Map params, StringBuilder sqlB) {
        sqlB.append(" SELECT ");
        String[] var8;
        int var7 = (var8 = field.split(",")).length;

        String dataRuleSql;
        for(int var6 = 0; var6 < var7; ++var6) {
            dataRuleSql = var8[var6];
            sqlB.append(dataRuleSql);
            sqlB.append(",");
        }

        sqlB.deleteCharAt(sqlB.length() - 1);
        sqlB.append(" FROM " + table);
        if(params.size() >= 1) {
            sqlB.append(" WHERE 1=1 ");
            Iterator it = params.keySet().iterator();

            while(it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = String.valueOf(params.get(key));
                if(!StringUtil.isEmpty(value) && !"null".equalsIgnoreCase(value)) {
                    sqlB.append(" AND ");
                    sqlB.append(" " + key + value);
                }
            }
        }

        dataRuleSql = JeecgDataAutorUtils.loadDataSearchConditonSQLString();
        if(dataRuleSql != null && !dataRuleSql.equals("")) {
            if(params.size() == 0) {
                sqlB.append(" WHERE 1=1 ");
            }

            sqlB.append(dataRuleSql);
        }

    }

    public Long getQuerySingleSize(String table, String field, Map params) {
        StringBuilder sqlB = new StringBuilder();
        this.dealQuerySql(table, "count(*) as query_size,", params, sqlB);
        List<Map<String, Object>> result = this.commonService.findForJdbc(sqlB.toString(), new Object[0]);
        return result.size() >= 1?Long.valueOf(Long.parseLong(String.valueOf(((Map)result.get(0)).get("query_size")))):Long.valueOf(0L);
    }

    public boolean deleteBatch(String table, String[] ids) {
        try {
            String[] var6 = ids;
            int var5 = ids.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String id = var6[var4];
                this.delete(table, id);
            }

            return true;
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }
    }

    public void treeFromResultHandle(String table, String parentIdFieldName, String parentIdFieldType, List<Map<String, Object>> result) {
        if(result != null && result.size() > 0) {
            String parentIds = "";

            for(int i = 0; i < result.size(); ++i) {
                Map<String, Object> resultMap = (Map)result.get(i);
                if(parentIdFieldType.equalsIgnoreCase("String")) {
                    parentIds = parentIds + ",'" + resultMap.get("id") + "'";
                } else {
                    parentIds = parentIds + "," + resultMap.get("id");
                }
            }

            parentIds = parentIds.substring(1);
            String subSQL = "select `" + parentIdFieldName + "`, count(*) ct from " + table + " a where a.`" + parentIdFieldName + "` in" + "(" + parentIds + ") group by a.`" + parentIdFieldName + "`";
            List<Map<String, Object>> subCountResult = this.findForJdbc(subSQL, new Object[0]);
            Map<String, Object> subCountMap = new HashMap();
            Iterator var10 = subCountResult.iterator();

            Map resultMap;
            while(var10.hasNext()) {
                resultMap = (Map)var10.next();
                subCountMap.put(resultMap.get(parentIdFieldName).toString(), resultMap.get("ct"));
            }

            String state;
            for(var10 = result.iterator(); var10.hasNext(); resultMap.put("state", state)) {
                resultMap = (Map)var10.next();
                state = "closed";
                if(subCountMap.get(resultMap.get("id").toString()) == null) {
                    state = "open";
                }
            }
        }

    }
}
