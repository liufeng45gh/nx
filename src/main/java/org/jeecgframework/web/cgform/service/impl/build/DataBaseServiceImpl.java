//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.common.CommUtils;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssb.warmline.business.commons.utils.DateUtils;

@Service("dataBaseService")
@Transactional(
        rollbackFor = {Exception.class}
)
public class DataBaseServiceImpl extends CommonServiceImpl implements DataBaseService {
    private static final Logger logger = Logger.getLogger(DataBaseServiceImpl.class);
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private AbstractRoutingDataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataBaseServiceImpl() {
    }

    public void insertTable(String tableName, Map<String, Object> data) throws BusinessException {
        CgFormHeadEntity cgFormHeadEntity = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
        this.fillInsertSysVar(data);
        this.keyAdapter(cgFormHeadEntity, data);
        this.dataAdapter(tableName, data);
        String comma = "";
        StringBuffer insertKey = new StringBuffer();
        StringBuffer insertValue = new StringBuffer();
        Iterator key = data.entrySet().iterator();

        while(true) {
            Entry entry;
            do {
                if(!key.hasNext()) {
                    String sql = "INSERT INTO " + tableName + " (" + insertKey + ") VALUES (" + insertValue + ")";
                    key = null;
                    Object keyIn = this.executeSqlReturnKey(sql, data);
                    if(keyIn != null && keyIn instanceof Long) {
                        data.put("id", keyIn);
                    }

                    if(cgFormHeadEntity != null) {
                        this.executeSqlExtend(cgFormHeadEntity.getId(), "add", data);
                        this.executeJavaExtend(cgFormHeadEntity.getId(), "add", data);
                    }

                    return;
                }

                entry = (Entry)key.next();
            } while(!this.isContainsFieled(tableName, (String)entry.getKey()));

            insertKey.append(comma + (String)entry.getKey());
            if(entry.getValue() != null && entry.getValue().toString().length() > 0) {
                insertValue.append(comma + ":" + (String)entry.getKey());
            } else {
                insertValue.append(comma + "null");
            }

            comma = ", ";
        }
    }

    private void keyAdapter(CgFormHeadEntity cgFormHeadEntity, Map<String, Object> data) {
        String pkType = cgFormHeadEntity.getJformPkType();
        String dbType = DBTypeUtil.getDBType();
        if(("NATIVE".equalsIgnoreCase(pkType) || "SEQUENCE".equalsIgnoreCase(pkType)) && "sqlserver".equalsIgnoreCase(dbType)) {
            data.remove("id");
        }

    }

    private Map<String, Object> dataAdapter(String tableName, Map<String, Object> data) {
        Map<String, CgFormFieldEntity> fieldConfigs = this.cgFormFieldService.getAllCgFormFieldByTableName(tableName);
        Iterator it = fieldConfigs.keySet().iterator();

        while(it.hasNext()) {
            Object key = it.next();
            Object beforeV = data.get(key.toString().toLowerCase());
            if(oConvertUtils.isNotEmpty(beforeV)) {
                CgFormFieldEntity fieldConfig = (CgFormFieldEntity)fieldConfigs.get(key);
                String type = fieldConfig.getType();
                if("date".equalsIgnoreCase(type)) {
                    Object newV = String.valueOf(beforeV);

                    try {
                        String dateType = fieldConfig.getShowType();
                        if("datetime".equalsIgnoreCase(dateType)) {
                            newV = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(String.valueOf(beforeV));
                        } else if("date".equalsIgnoreCase(dateType)) {
                            newV = (new SimpleDateFormat("yyyy-MM-dd")).parse(String.valueOf(beforeV));
                        }

                        if(data.containsKey(key)) {
                            data.put(String.valueOf(key), newV);
                        }
                    } catch (ParseException var13) {
                        var13.printStackTrace();
                    }
                } else if("int".equalsIgnoreCase(type)) {
                    Integer newV = null;

                    try {
                        newV = Integer.valueOf(Integer.parseInt(String.valueOf(beforeV)));
                    } catch (Exception var12) {
                        var12.printStackTrace();
                    }

                    if(data.containsKey(key)) {
                        data.put(String.valueOf(key), newV);
                    }
                } else if("double".equalsIgnoreCase(type)) {
                    Double newV = new Double(0.0D);

                    try {
                        newV = Double.valueOf(Double.parseDouble(String.valueOf(beforeV)));
                    } catch (Exception var11) {
                        var11.printStackTrace();
                    }

                    if(data.containsKey(key)) {
                        data.put(String.valueOf(key), newV);
                    }
                }
            } else if(oConvertUtils.isNotEmpty(((CgFormFieldEntity)fieldConfigs.get(key)).getFieldDefault())) {
                data.remove(key.toString().toLowerCase());
            }
        }

        return data;
    }

    public int updateTable(String tableName, Object id, Map<String, Object> data) throws BusinessException {
        this.fillUpdateSysVar(data);
        this.dataAdapter(tableName, data);
        String comma = "";
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("update ").append(tableName).append(" set ");
        Iterator var7 = data.entrySet().iterator();

        while(true) {
            Entry entry;
            do {
                if(!var7.hasNext()) {
                    if(id instanceof String) {
                        sqlBuffer.append(" where id='").append(id).append("'");
                    } else {
                        sqlBuffer.append(" where id=").append(id);
                    }

                    CgFormHeadEntity cgFormHeadEntity = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
                    int num = this.executeSql(sqlBuffer.toString(), data).intValue();
                    if(cgFormHeadEntity != null) {
                        this.executeSqlExtend(cgFormHeadEntity.getId(), "update", data);
                        this.executeJavaExtend(cgFormHeadEntity.getId(), "update", data);
                    }

                    return num;
                }

                entry = (Entry)var7.next();
            } while(!this.isContainsFieled(tableName, (String)entry.getKey()));

            if(entry.getValue() != null && entry.getValue().toString().length() > 0) {
                sqlBuffer.append(comma).append((String)entry.getKey()).append("=:" + (String)entry.getKey() + " ");
            } else {
                sqlBuffer.append(comma).append((String)entry.getKey()).append("=null");
            }

            comma = ", ";
        }
    }

    public Map<String, Object> findOneForJdbc(String tableName, String id) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select * from ").append(tableName);
        sqlBuffer.append(" where id= ? ");
        Map<String, Object> map = this.commonDao.findOneForJdbc(sqlBuffer.toString(), new Object[]{id});
        return map;
    }

    public void executeSqlExtend(String formId, String buttonCode, Map<String, Object> data) {
        CgformButtonSqlEntity cgformButtonSqlVo = this.getCgformButtonSqlByCodeFormId(buttonCode, formId);
        if(cgformButtonSqlVo != null) {
            String sqlPlugin = cgformButtonSqlVo.getCgbSqlStr();
            if(StringUtils.isNotEmpty(sqlPlugin)) {
                String[] sqls = sqlPlugin.split(";");
                String[] var10 = sqls;
                int var9 = sqls.length;

                for(int var8 = 0; var8 < var9; ++var8) {
                    String sql = var10[var8];
                    logger.debug("sql plugin -------->" + sql);
                    sql = this.formateSQl(sql, data);
                    logger.debug("sql plugin -------->" + sql);
                    int num = this.executeSql(sql, new Object[0]).intValue();
                    if(num > 0) {
                        logger.debug("sql plugin --execute success------>" + sql);
                    } else {
                        logger.debug("sql plugin --execute fail------>" + sql);
                    }
                }
            }
        }

    }

    private CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode, String formId) {
        StringBuilder hql = new StringBuilder("");
        hql.append(" from CgformButtonSqlEntity t");
        hql.append(" where t.formId=?");
        hql.append(" and  t.buttonCode =?");
        List<CgformButtonSqlEntity> list = this.findHql(hql.toString(), new Object[]{formId, buttonCode});
        return list != null && list.size() > 0?(CgformButtonSqlEntity)list.get(0):null;
    }

    private String formateSQl(String sql, Map<String, Object> params) {
        sql = this.replaceExtendSqlSysVar(sql);
        if(params == null) {
            return sql;
        } else {
            if(sql.toLowerCase().indexOf("insert") != -1) {
                sql = sql.replace("#{UUID}", UUIDGenerator.generate());
            }

            String key;
            for(Iterator var4 = params.keySet().iterator(); var4.hasNext(); sql = sql.replace("#{" + key + "}", String.valueOf(params.get(key)))) {
                key = (String)var4.next();
            }

            return sql;
        }
    }

    public Map<String, Object> insertTableMore(Map<String, List<Map<String, Object>>> mapMore, String mainTableName) throws BusinessException {
        Map<String, Object> mainMap = (Map)((List)mapMore.get(mainTableName)).get(0);
        String[] filterName = new String[]{"tableName", "saveOrUpdateMore"};
        mainMap = CommUtils.attributeMapFilter(mainMap, filterName);
        if(mainMap.get("id") == null || "".equals((String)mainMap.get("id"))) {
            Object pkValue = this.getPkValue(mainTableName);
            mainMap.put("id", pkValue);
        }

        this.insertTable(mainTableName, mainMap);
        String[] filterMainTable = new String[]{mainTableName};
        mapMore = CommUtils.attributeMapFilter(mapMore, filterMainTable);
        Iterator it = mapMore.entrySet().iterator();

        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            String ok = (String)entry.getKey();
            List<Map<String, Object>> ov = (List)entry.getValue();
            Iterator var11 = ov.iterator();

            while(var11.hasNext()) {
                Map<String, Object> fieldMap = (Map)var11.next();
                List<Map<String, Object>> fkFieldList = this.getFKField(mainTableName, ok);
                Object subPkValue = this.getPkValue(ok);
                fieldMap.put("id", subPkValue);
                fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
                this.insertTable(ok, fieldMap);
            }
        }

        return mainMap;
    }

    public boolean updateTableMore(Map<String, List<Map<String, Object>>> mapMore, String mainTableName) throws BusinessException {
        Map<String, Object> mainMap = (Map)((List)mapMore.get(mainTableName)).get(0);
        Object mainTableId = mainMap.get("id");
        String[] filterName = new String[]{"tableName", "saveOrUpdateMore", "id"};
        mainMap = CommUtils.attributeMapFilter(mainMap, filterName);
        this.updateTable(mainTableName, mainTableId, mainMap);
        mainMap.put("id", mainTableId);
        String[] filterMainTable = new String[]{mainTableName};
        mapMore = CommUtils.attributeMapFilter(mapMore, filterMainTable);
        Iterator it = mapMore.entrySet().iterator();

        while(true) {
            String ok;
            Map subTableDateMap;
            Object subId;
            label40:
            do {
                if(!it.hasNext()) {
                    return true;
                }

                Entry entry = (Entry)it.next();
                ok = (String)entry.getKey();
                List<Map<String, Object>> ov = (List)entry.getValue();
                List<Map<String, Object>> fkFieldList = this.getFKField(mainTableName, ok);
                subTableDateMap = this.getSubTableData(fkFieldList, mainTableName, ok, mainTableId);
                Iterator var14 = ov.iterator();

                while(true) {
                    while(true) {
                        if(!var14.hasNext()) {
                            continue label40;
                        }

                        Map<String, Object> fieldMap = (Map)var14.next();
                        subId = fieldMap.get("id") == null?"":fieldMap.get("id");
                        if(subId != null && !"".equals(String.valueOf(subId))) {
                            fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
                            String[] subFilterName = new String[]{"id"};
                            fieldMap = CommUtils.attributeMapFilter(fieldMap, subFilterName);
                            this.updateTable(ok, subId, fieldMap);
                            if(subTableDateMap.containsKey(subId)) {
                                subTableDateMap.remove(subId);
                            }
                        } else {
                            fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
                            fieldMap.put("id", this.getPkValue(ok));
                            this.insertTable(ok, fieldMap);
                        }
                    }
                }
            } while(subTableDateMap.size() <= 0);

            Iterator itSub = subTableDateMap.entrySet().iterator();

            while(itSub.hasNext()) {
                Entry entrySub = (Entry)itSub.next();
                subId = entrySub.getKey();
                this.deleteSubTableDataById(subId, ok);
            }
        }
    }

    private List<Map<String, Object>> getFKField(String mainTableName, String subTableName) {
        StringBuilder sql1 = new StringBuilder("");
        sql1.append("select f.* from cgform_field f ,cgform_head h");
        sql1.append(" where f.table_id = h.id ");
        sql1.append(" and h.table_name=? ");
        sql1.append(" and f.main_table=? ");
        List<Map<String, Object>> list = this.findForJdbc(sql1.toString(), new Object[]{subTableName, mainTableName});
        return list;
    }

    private Map<Object, Map<String, Object>> getSubTableData(List<Map<String, Object>> fkFieldList, String mainTableName, String subTableName, Object mainTableId) {
        StringBuilder sql2 = new StringBuilder("");
        sql2.append("select sub.* from ").append(subTableName).append(" sub ");
        sql2.append(", ").append(mainTableName).append(" main ");
        sql2.append("where 1=1 ");
        if(fkFieldList != null && fkFieldList.size() > 0) {
            Iterator var7 = fkFieldList.iterator();

            while(var7.hasNext()) {
                Map<String, Object> map = (Map)var7.next();
                if(map.get("main_field") != null) {
                    sql2.append(" and sub.").append((String)map.get("field_name")).append("=").append("main.").append((String)map.get("main_field"));
                }
            }
        }

        sql2.append(" and main.id= ? ");
        List<Map<String, Object>> subTableDataList = this.findForJdbc(sql2.toString(), new Object[]{mainTableId});
        Map<Object, Map<String, Object>> dataMap = new HashMap();
        if(subTableDataList != null) {
            Iterator var9 = subTableDataList.iterator();

            while(var9.hasNext()) {
                Map<String, Object> map = (Map)var9.next();
                dataMap.put(map.get("id"), map);
            }
        }

        return dataMap;
    }

    public Object getPkValue(String tableName) {
        Object pkValue = null;
        CgFormHeadEntity cghead = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
        String dbType = DBTypeUtil.getDBType();
        String pkType = cghead.getJformPkType();
        String pkSequence = cghead.getJformPkSequence();
        if(StringUtil.isNotEmpty(pkType) && "UUID".equalsIgnoreCase(pkType)) {
            pkValue = UUIDGenerator.generate();
        } else {
            DataFieldMaxValueIncrementer incr;
            //OracleSequenceMaxValueIncrementer incr = null;
            if(StringUtil.isNotEmpty(pkType) && "NATIVE".equalsIgnoreCase(pkType)) {
                if(StringUtil.isNotEmpty(dbType) && "oracle".equalsIgnoreCase(dbType)) {
                    incr = new OracleSequenceMaxValueIncrementer(this.dataSource, "HIBERNATE_SEQUENCE");

                    try {
                        pkValue = Long.valueOf(incr.nextLongValue());
                    } catch (Exception var12) {
                        logger.error(var12, var12);
                    }
                } else if(StringUtil.isNotEmpty(dbType) && "postgres".equalsIgnoreCase(dbType)) {
                    incr = new PostgreSQLSequenceMaxValueIncrementer(this.dataSource, "HIBERNATE_SEQUENCE");

                    try {
                        pkValue = Long.valueOf(incr.nextLongValue());
                    } catch (Exception var11) {
                        logger.error(var11, var11);
                    }
                } else {
                    pkValue = null;
                }
            } else if(StringUtil.isNotEmpty(pkType) && "SEQUENCE".equalsIgnoreCase(pkType)) {
                if(StringUtil.isNotEmpty(dbType) && "oracle".equalsIgnoreCase(dbType)) {
                    incr = new OracleSequenceMaxValueIncrementer(this.dataSource, pkSequence);

                    try {
                        pkValue = Long.valueOf(incr.nextLongValue());
                    } catch (Exception var10) {
                        logger.error(var10, var10);
                    }
                } else if(StringUtil.isNotEmpty(dbType) && "postgres".equalsIgnoreCase(dbType)) {
                    incr = new PostgreSQLSequenceMaxValueIncrementer(this.dataSource, pkSequence);

                    try {
                        pkValue = Long.valueOf(incr.nextLongValue());
                    } catch (Exception var9) {
                        logger.error(var9, var9);
                    }
                } else {
                    pkValue = null;
                }
            } else {
                pkValue = UUIDGenerator.generate();
            }
        }

        return pkValue;
    }

    private void deleteSubTableDataById(Object subId, String subTableName) {
        StringBuilder sql = new StringBuilder("");
        sql.append(" delete from ").append(subTableName).append(" where id = ? ");
        this.executeSql(sql.toString(), new Object[]{subId});
    }

    private void fillUpdateSysVar(Map<String, Object> data) {
        if(data.containsKey("update_date")) {
            data.put("update_date", DateUtils.formatDate());
        }

        if(data.containsKey("update_time")) {
            data.put("update_time", DateUtils.formatTime());
        }

        if(data.containsKey("update_by")) {
            data.put("update_by", ResourceUtil.getUserSystemData("sysUserCode"));
        }

        if(data.containsKey("update_name")) {
            data.put("update_name", ResourceUtil.getUserSystemData("sysUserName"));
        }

    }

    private void fillInsertSysVar(Map<String, Object> data) {
        if(data.containsKey("create_date")) {
            data.put("create_date", DateUtils.formatDate());
        }

        if(data.containsKey("create_time")) {
            data.put("create_time", DateUtils.formatTime());
        }

        if(data.containsKey("create_by")) {
            data.put("create_by", ResourceUtil.getUserSystemData("sysUserCode"));
        }

        if(data.containsKey("create_name")) {
            data.put("create_name", ResourceUtil.getUserSystemData("sysUserName"));
        }

        if(data.containsKey("sys_company_code")) {
            data.put("sys_company_code", ResourceUtil.getUserSystemData("sysCompanyCode"));
        }

        if(data.containsKey("sys_org_code")) {
            data.put("sys_org_code", ResourceUtil.getUserSystemData("sysOrgCode"));
        }

        if(data.containsKey("sys_user_code")) {
            data.put("sys_user_code", ResourceUtil.getUserSystemData("sysUserCode"));
        }

    }

    private String replaceExtendSqlSysVar(String sql) {
        sql = sql.replace("{sys_user_code}", ResourceUtil.getUserSystemData("sysUserCode")).replace("{sys_user_name}", ResourceUtil.getUserSystemData("sysUserName")).replace("{sys_org_code}", ResourceUtil.getUserSystemData("sysOrgCode")).replace("{sys_company_code}", ResourceUtil.getUserSystemData("sysCompanyCode")).replace("{sys_date}", DateUtils.formatDate()).replace("{sys_time}", DateUtils.formatTime());
        return sql;
    }

    private Map<String, CgFormFieldEntity> getAllFieldByTableName(String tableName) {
        String version = this.cgFormFieldService.getCgFormVersionByTableName(tableName);
        Map<String, CgFormFieldEntity> map = this.cgFormFieldService.getAllCgFormFieldByTableName(tableName, version);
        return map;
    }

    private boolean isContainsFieled(String tableName, String fieledName) {
        boolean flag = false;
        if(this.getAllFieldByTableName(tableName).containsKey(fieledName)) {
            flag = true;
        }

        return flag;
    }

    public void executeJavaExtend(String formId, String buttonCode, Map<String, Object> data) throws BusinessException {
        CgformEnhanceJavaEntity cgformEnhanceJavaEntity = this.getCgformEnhanceJavaEntityByCodeFormId(buttonCode, formId);
        if(cgformEnhanceJavaEntity != null) {
            String cgJavaType = cgformEnhanceJavaEntity.getCgJavaType();
            String cgJavaValue = cgformEnhanceJavaEntity.getCgJavaValue();
            if(StringUtil.isNotEmpty(cgJavaValue)) {
                Object obj = null;

                try {
                    if("class".equals(cgJavaType)) {
                        obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
                    } else if("spring".equals(cgJavaType)) {
                        obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                    }

                    if(obj instanceof CgformEnhanceJavaInter) {
                        CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter)obj;
                        javaInter.execute(data);
                    }
                } catch (Exception var9) {
                    logger.error(var9.getMessage());
                    var9.printStackTrace();
                    throw new BusinessException("执行JAVA增强出现异常！");
                }
            }
        }

    }

    public CgformEnhanceJavaEntity getCgformEnhanceJavaEntityByCodeFormId(String buttonCode, String formId) {
        StringBuilder hql = new StringBuilder("");
        hql.append(" from CgformEnhanceJavaEntity t");
        hql.append(" where t.formId='").append(formId).append("'");
        hql.append(" and  t.buttonCode ='").append(buttonCode).append("'");
        List<CgformEnhanceJavaEntity> list = this.findHql(hql.toString(), new Object[0]);
        return list != null && list.size() > 0?(CgformEnhanceJavaEntity)list.get(0):null;
    }
}
