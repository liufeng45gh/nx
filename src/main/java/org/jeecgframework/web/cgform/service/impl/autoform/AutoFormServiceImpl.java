//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.autoform;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.autoform.AutoFormServiceI;
import org.jeecgframework.web.cgform.util.AutoFormTemplateParseUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssb.warmline.business.commons.utils.DateUtils;

@Service("autoFormService")
@Transactional(
        rollbackFor = {Exception.class}
)
public class AutoFormServiceImpl extends CommonServiceImpl implements AutoFormServiceI {
    public AutoFormServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((AutoFormEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        this.doAddSql((AutoFormEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        this.doUpdateSql((AutoFormEntity)entity);
    }

    public boolean doAddSql(AutoFormEntity t) {
        return true;
    }

    public boolean doUpdateSql(AutoFormEntity t) {
        return true;
    }

    public boolean doDelSql(AutoFormEntity t) {
        return true;
    }

    public String replaceVal(String sql, AutoFormEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{form_name}", String.valueOf(t.getFormName()));
        sql = sql.replace("#{form_desc}", String.valueOf(t.getFormDesc()));
        sql = sql.replace("#{form_style_id}", String.valueOf(t.getFormStyleId()));
        sql = sql.replace("#{form_content}", String.valueOf(t.getFormContent()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    public void doAddTable(String formName, Map<String, Map<String, Object>> dataMap) throws BusinessException {
        if(dataMap != null) {
            Iterator it = dataMap.entrySet().iterator();

            while(true) {
                Map data;
                List list;
                do {
                    do {
                        if(!it.hasNext()) {
                            return;
                        }

                        Entry entry1 = (Entry)it.next();
                        String dsName = (String)entry1.getKey();
                        if(dsName.indexOf("[") != -1) {
                            dsName = dsName.substring(0, dsName.indexOf("["));
                        }

                        data = (Map)entry1.getValue();
                        String hql = "select afd from AutoFormEntity af,AutoFormDbEntity afd where af.id=afd.autoFormId and af.formName=? and afd.dbName=?";
                        list = this.findHql(hql, new Object[]{formName, dsName});
                    } while(list == null);
                } while(list.size() <= 0);

                AutoFormDbEntity autoFormDbEntity = (AutoFormDbEntity)list.get(0);
                String tbDbKey = autoFormDbEntity.getTbDbKey();
                if(StringUtil.isNotEmpty(tbDbKey)) {
                    throw new BusinessException("暂不支持非平台填报数据源");
                }

                String tbDbTableName = autoFormDbEntity.getTbDbTableName();
                this.fillInsertSysVar(data);
                String comma = "";
                StringBuffer insertKey = new StringBuffer();
                StringBuffer insertValue = new StringBuffer();

                for(Iterator var16 = data.entrySet().iterator(); var16.hasNext(); comma = ", ") {
                    Entry<String, Object> entry2 = (Entry)var16.next();
                    insertKey.append(comma + (String)entry2.getKey());
                    if(entry2.getValue() != null && entry2.getValue().toString().length() > 0) {
                        insertValue.append(comma + ":" + (String)entry2.getKey());
                    } else {
                        insertValue.append(comma + "null");
                    }
                }

                try {
                    String sql = "INSERT INTO " + tbDbTableName + " (" + insertKey + ") VALUES (" + insertValue + ")";
                    this.executeSqlReturnKey(sql, data);
                } catch (DuplicateKeyException var17) {
                    throw new BusinessException("数据已存在");
                } catch (UncategorizedSQLException var18) {
                    throw new BusinessException("数据类型转换异常");
                }
            }
        }
    }

    private void fillInsertSysVar(Map<String, Object> data) {
        if(data.containsKey("create_date")) {
            data.put("create_date", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }

        if(data.containsKey("create_time")) {
            data.put("create_time", DateUtils.formatTime(new Date()));
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

    private void fillUpdateSysVar(Map<String, Object> data) {
        if(data.containsKey("update_date")) {
            data.put("update_date", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }

        if(data.containsKey("update_time")) {
            data.put("update_time", DateUtils.formatTime(new Date()));
        }

        if(data.containsKey("update_by")) {
            data.put("update_by", ResourceUtil.getUserSystemData("sysUserCode"));
        }

        if(data.containsKey("update_name")) {
            data.put("update_name", ResourceUtil.getUserSystemData("sysUserName"));
        }

    }

    public String doUpdateTable(String formName, Map<String, Map<String, Object>> dataMap, Map<String, List<Map<String, Object>>> oldDataMap) throws BusinessException {
        Set<String> listDsName = new HashSet();
        String fkid;
        if(dataMap != null) {
            Iterator it = dataMap.entrySet().iterator();

            label161:
            while(true) {
                while(true) {
                    String dsName;
                    String fkdsid;
                    Map data;
                    List list;
                    do {
                        do {
                            if(!it.hasNext()) {
                                break label161;
                            }

                            Entry entry1 = (Entry)it.next();
                            dsName = (String)entry1.getKey();
                            fkid = "";
                            fkdsid = "";
                            if(dsName.indexOf("[") != -1) {
                                dsName = dsName.substring(0, dsName.indexOf("["));
                                listDsName.add(dsName);
                                data = (Map)dataMap.get("param");
                                fkid = data.get("listctrl_fk_" + dsName) == null?"":(String)data.get("listctrl_fk_" + dsName);
                                fkdsid = data.get("listctrl_fkdsid_" + dsName) == null?"":(String)data.get("listctrl_fkdsid_" + dsName);
                            }

                            data = (Map)entry1.getValue();
                            String hql = "select afd from AutoFormEntity af,AutoFormDbEntity afd where af.id=afd.autoFormId and af.formName=? and afd.dbName=?";
                            list = this.findHql(hql, new Object[]{formName, dsName});
                        } while(list == null);
                    } while(list.size() <= 0);

                    AutoFormDbEntity autoFormDbEntity = (AutoFormDbEntity)list.get(0);
                    String tbDbKey = autoFormDbEntity.getTbDbKey();
                    if(StringUtil.isNotEmpty(tbDbKey)) {
                        throw new BusinessException("暂不支持非平台数据源");
                    }

                    String tbDbTableName = autoFormDbEntity.getTbDbTableName();
                    Object val = data.get("id");
                    if(StringUtil.isNotEmpty(val)) {
                        this.fillUpdateSysVar(data);
                    } else {
                        this.fillInsertSysVar(data);
                    }

                    String id = null;
                    String comma = "";
                    StringBuffer updateSqlBuffer = new StringBuffer();
                    updateSqlBuffer.append("update ").append(tbDbTableName).append(" set ");
                    StringBuffer insertKey = new StringBuffer();
                    StringBuffer insertValue = new StringBuffer();

                    for(Iterator var23 = data.entrySet().iterator(); var23.hasNext(); comma = ", ") {
                        Entry<String, Object> entry2 = (Entry)var23.next();
                        if(entry2.getValue() != null && entry2.getValue().toString().length() > 0) {
                            updateSqlBuffer.append(comma).append((String)entry2.getKey()).append("=:" + (String)entry2.getKey() + " ");
                        } else {
                            updateSqlBuffer.append(comma).append((String)entry2.getKey()).append("=null");
                        }

                        if("id".equalsIgnoreCase((String)entry2.getKey())) {
                            id = entry2.getValue() == null?"":(String)entry2.getValue();
                            if(id == null || id.toString().equals("")) {
                                id = UUIDGenerator.generate();
                                entry2.setValue(id);
                            }
                        }

                        insertKey.append(comma + (String)entry2.getKey());
                        if(entry2.getValue() != null && entry2.getValue().toString().length() > 0) {
                            insertValue.append(comma + ":" + (String)entry2.getKey());
                        } else if(StringUtil.isNotEmpty(fkid) && fkid.equalsIgnoreCase((String)entry2.getKey())) {
                            insertValue.append(comma + ":" + (String)entry2.getKey());
                        } else {
                            insertValue.append(comma + "null");
                        }
                    }

                    String sql = "";
                    boolean isAdd = true;
                    List<Map<String, Object>> oldData = (List)oldDataMap.get(dsName);

                    for(int i = 0; i < oldData.size(); ++i) {
                        Map<String, Object> subMap = (Map)oldData.get(i);
                        String oid = this.getId(subMap);
                        if(StringUtil.isNotEmpty(id) && id.equals(oid)) {
                            isAdd = false;
                        }
                    }

                    if(isAdd && StringUtil.isNotEmpty(fkid)) {
                        Map<String, Object> param = (Map)dataMap.get("param");
                        String fkidValue = "";
                        if(StringUtil.isEmpty(fkidValue)) {
                            fkidValue = this.getDsPropertyValue(dataMap, fkdsid);
                        }

                        if(StringUtil.isEmpty(fkidValue)) {
                            fkidValue = this.getId(param);
                        }

                        if(data.get(fkid) == null) {
                            insertKey.append(",").append(fkid);
                            insertValue.append(",").append(":").append(fkid);
                        }

                        data.put(fkid, fkidValue);
                    }

                    if(isAdd) {
                        if(id == null || id.toString().equals("")) {
                            insertKey.append(",").append("id");
                            insertValue.append(",").append("'").append(UUIDGenerator.generate()).append("'");
                        }

                        sql = "INSERT INTO " + tbDbTableName + " (" + insertKey + ") VALUES (" + insertValue + ")";
                        this.executeSqlReturnKey(sql, data);
                    } else {
                        if(id instanceof String) {
                            updateSqlBuffer.append(" where ID='").append(id).append("'");
                        } else {
                            updateSqlBuffer.append(" where ID=").append(id);
                        }

                        sql = updateSqlBuffer.toString();

                        try {
                            this.executeSql(sql, data);
                        } catch (UncategorizedSQLException var28) {
                            throw new BusinessException("数据类型转换异常");
                        }
                    }
                }
            }
        }

        this.doDeletTable(formName, dataMap, oldDataMap, listDsName);
        String id = "";
        String op = this.getDsPropertyValue(dataMap, "param.op");
        if(AutoFormTemplateParseUtil.OP_ADD.equals(op)) {
            AutoFormEntity autoFormEntity = (AutoFormEntity)this.findUniqueByProperty(AutoFormEntity.class, "formName", formName);
            fkid = autoFormEntity.getMainTableSource() + ".id";
            id = this.getDsPropertyValueNoGenerator(dataMap, fkid);
            if(StringUtil.isEmpty(id)) {
                throw new BusinessException("主数据源主键获取失败");
            }
        } else {
            id = this.getDsPropertyValue(dataMap, "param.id");
        }

        return id;
    }

    private String getDsPropertyValueNoGenerator(Map<String, Map<String, Object>> dataMap, String key) {
        String value = "";
        String[] keys = key.split("\\.");
        if(keys.length == 2) {
            Map<String, Object> data = (Map)dataMap.get(keys[0]);
            if(data == null) {
                return value;
            }

            Object obj = data.get(keys[1]);
            if(obj == null) {
                obj = data.get(keys[1].toUpperCase());
            }

            value = obj == null?"":obj.toString();
        }

        return value;
    }

    private String getDsPropertyValue(Map<String, Map<String, Object>> dataMap, String key) {
        String value = "";
        String[] keys = key.split("\\.");
        if(keys.length == 2) {
            Map<String, Object> data = (Map)dataMap.get(keys[0]);
            Object obj = data.get(keys[1]);
            if(obj != null && obj.toString().toString().length() > 0) {
                value = obj == null?"":obj.toString();
            } else {
                value = UUIDGenerator.generate();
                data.put(keys[1], value);
            }
        }

        return value;
    }

    private String getId(Map<String, Object> dateMap) {
        String id = "";
        id = dateMap.get("id") == null?"":(String)dateMap.get("id");
        if(StringUtil.isEmpty(id)) {
            id = dateMap.get("ID") == null?"":(String)dateMap.get("ID");
        }

        return id;
    }

    private void doDeletTable(String formName, Map<String, Map<String, Object>> dataMap, Map<String, List<Map<String, Object>>> oldDataMap, Set<String> listDsName) throws BusinessException {
        Iterator oldit = oldDataMap.entrySet().iterator();

        while(true) {
            List olddata;
            String dsName;
            List list;
            do {
                do {
                    if(!oldit.hasNext()) {
                        return;
                    }

                    Entry entry1 = (Entry)oldit.next();
                    olddata = (List)entry1.getValue();
                    dsName = (String)entry1.getKey();
                    Map<String, Object> param = (Map)dataMap.get("param");
                    if(!listDsName.contains(dsName) && param.get("listctrl_fk_" + dsName) == null) {
                        return;
                    }

                    String hql = "select afd from AutoFormEntity af,AutoFormDbEntity afd where af.id=afd.autoFormId and af.formName=? and afd.dbName=?";
                    list = this.findHql(hql, new Object[]{formName, dsName});
                } while(list == null);
            } while(list.size() <= 0);

            AutoFormDbEntity autoFormDbEntity = (AutoFormDbEntity)list.get(0);
            String tbDbKey = autoFormDbEntity.getTbDbKey();
            if(StringUtil.isNotEmpty(tbDbKey)) {
                throw new BusinessException("暂不支持非平台数据源");
            }

            String tbDbTableName = autoFormDbEntity.getTbDbTableName();
            Iterator var16 = olddata.iterator();

            while(var16.hasNext()) {
                Map<String, Object> item = (Map)var16.next();
                String itemId = this.getId(item);
                if(!StringUtil.isEmpty(itemId)) {
                    this.isDeleteDate(dsName, tbDbTableName, itemId, dataMap);
                }
            }
        }
    }

    private void isDeleteDate(String itemDsName, String tableName, String itemId, Map<String, Map<String, Object>> dataMap) {
        boolean exist = false;
        if(dataMap != null) {
            Iterator it = dataMap.entrySet().iterator();

            while(it.hasNext()) {
                Entry entry1 = (Entry)it.next();
                String dsName = (String)entry1.getKey();
                boolean listctrflag = false;
                if(dsName.indexOf("[") != -1) {
                    dsName = dsName.substring(0, dsName.indexOf("["));
                    listctrflag = true;
                }

                if(listctrflag && itemDsName.equals(dsName)) {
                    Map<String, Object> data = (Map)entry1.getValue();
                    String id = this.getId(data);
                    if(StringUtil.isNotEmpty(itemId) && itemId.equals(id)) {
                        exist = true;
                    }
                }
            }
        }

        if(!exist) {
            StringBuffer deleteSqlBuffer = new StringBuffer();
            deleteSqlBuffer.append("delete from ").append(tableName).append(" where ");
            deleteSqlBuffer.append(" ID = '").append(itemId).append("'");
            this.executeSql(deleteSqlBuffer.toString(), new Object[0]);
        }

    }
}
