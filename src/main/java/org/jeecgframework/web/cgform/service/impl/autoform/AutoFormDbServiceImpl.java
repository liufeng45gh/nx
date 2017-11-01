//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.autoform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbFieldEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormParamEntity;
import org.jeecgframework.web.cgform.service.autoform.AutoFormDbServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("autoFormDbService")
@Transactional
public class AutoFormDbServiceImpl extends CommonServiceImpl implements AutoFormDbServiceI {
    @Autowired
    private JdbcDao jdbcDao;

    public AutoFormDbServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((AutoFormDbEntity)entity);
    }

    public void addMain(AutoFormDbEntity autoFormDb, List<AutoFormDbFieldEntity> autoFormDbFieldList, List<AutoFormParamEntity> autoFormParamList) {
        this.save(autoFormDb);
        Iterator var5 = autoFormDbFieldList.iterator();

        while(var5.hasNext()) {
            AutoFormDbFieldEntity autoFormDbField = (AutoFormDbFieldEntity)var5.next();
            autoFormDbField.setAutoFormDbId(autoFormDb.getId());
            this.save(autoFormDbField);
        }

        var5 = autoFormParamList.iterator();

        while(var5.hasNext()) {
            AutoFormParamEntity autoFormParam = (AutoFormParamEntity)var5.next();
            autoFormParam.setAutoFormDbId(autoFormDb.getId());
            this.save(autoFormParam);
        }

        this.doAddSql(autoFormDb);
    }

    public void updateMain(AutoFormDbEntity autoFormDb, List<AutoFormDbFieldEntity> autoFormDbFieldList, List<AutoFormParamEntity> autoFormParamList) {
        this.saveOrUpdate(autoFormDb);
        Object id0 = autoFormDb.getId();
        Object id1 = autoFormDb.getId();
        String hql0 = "from AutoFormDbFieldEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";
        List<AutoFormDbFieldEntity> autoFormDbFieldOldList = this.findHql(hql0, new Object[]{id0});
        Iterator var9 = autoFormDbFieldOldList.iterator();
        Iterator var21;
        {
            AutoFormDbFieldEntity oldE;
            while (var9.hasNext()) {
                oldE = (AutoFormDbFieldEntity) var9.next();
                boolean isUpdate = false;
                Iterator var12 = autoFormDbFieldList.iterator();

                while (var12.hasNext()) {
                    AutoFormDbFieldEntity sendE = (AutoFormDbFieldEntity) var12.next();
                    if (oldE.getId().equals(sendE.getId())) {
                        try {
                            MyBeanUtils.copyBeanNotNull2Bean(sendE, oldE);
                            this.saveOrUpdate(oldE);
                        } catch (Exception var17) {
                            var17.printStackTrace();
                            throw new BusinessException(var17.getMessage());
                        }

                        isUpdate = true;
                        break;
                    }
                }

                if (!isUpdate) {
                    super.delete(oldE);
                }
            }

            var9 = autoFormDbFieldList.iterator();

            while (var9.hasNext()) {
                oldE = (AutoFormDbFieldEntity) var9.next();
                if (oConvertUtils.isEmpty(oldE.getId())) {
                    oldE.setAutoFormDbId(autoFormDb.getId());
                    this.save(oldE);
                }
            }

            String hql1 = "from AutoFormParamEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";
            List<AutoFormParamEntity> autoFormParamOldList = this.findHql(hql1, new Object[]{id1});
            var21 = autoFormParamOldList.iterator();
        }
        AutoFormParamEntity oldE;
        while(var21.hasNext()) {
            oldE = (AutoFormParamEntity)var21.next();
            boolean isUpdate = false;
            Iterator var14 = autoFormParamList.iterator();

            while(var14.hasNext()) {
                AutoFormParamEntity sendE = (AutoFormParamEntity)var14.next();
                if(oldE.getId().equals(sendE.getId())) {
                    try {
                        MyBeanUtils.copyBeanNotNull2Bean(sendE, oldE);
                        this.saveOrUpdate(oldE);
                    } catch (Exception var16) {
                        var16.printStackTrace();
                        throw new BusinessException(var16.getMessage());
                    }

                    isUpdate = true;
                    break;
                }
            }

            if(!isUpdate) {
                super.delete(oldE);
            }
        }

        var21 = autoFormParamList.iterator();

        while(var21.hasNext()) {
            oldE = (AutoFormParamEntity)var21.next();
            if(oConvertUtils.isEmpty(oldE.getId())) {
                oldE.setAutoFormDbId(autoFormDb.getId());
                this.save(oldE);
            }
        }

        this.doUpdateSql(autoFormDb);
    }

    public void delMain(AutoFormDbEntity autoFormDb) {
        this.delete(autoFormDb);
        Object id0 = autoFormDb.getId();
        Object id1 = autoFormDb.getId();
        String hql0 = "from AutoFormDbFieldEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";
        List<AutoFormDbFieldEntity> autoFormDbFieldOldList = this.findHql(hql0, new Object[]{id0});
        this.deleteAllEntitie(autoFormDbFieldOldList);
        String hql1 = "from AutoFormParamEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";
        List<AutoFormParamEntity> autoFormParamOldList = this.findHql(hql1, new Object[]{id1});
        this.deleteAllEntitie(autoFormParamOldList);
    }

    public boolean doAddSql(AutoFormDbEntity t) {
        return true;
    }

    public boolean doUpdateSql(AutoFormDbEntity t) {
        return true;
    }

    public boolean doDelSql(AutoFormDbEntity t) {
        return true;
    }

    public String replaceVal(String sql, AutoFormDbEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{db_name}", String.valueOf(t.getDbName()));
        sql = sql.replace("#{db_type}", String.valueOf(t.getDbType()));
        sql = sql.replace("#{db_table_name}", String.valueOf(t.getDbTableName()));
        sql = sql.replace("#{db_dyn_sql}", String.valueOf(t.getDbDynSql()));
        sql = sql.replace("#{db_dyn_sql}", String.valueOf(t.getDbDynSql()));
        sql = sql.replace("#{auto_form_id}", String.valueOf(t.getAutoFormId()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    public List<String> getSqlFields(String sql) {
        if(oConvertUtils.isEmpty(sql)) {
            return null;
        } else {
            sql = this.getSql(sql);
            List<Map<String, Object>> result = this.jdbcDao.findForJdbc(sql, 1, 1);
            if(result.size() < 1) {
                throw new BusinessException("该报表sql没有数据");
            } else {
                Set fieldsSet = ((Map)result.get(0)).keySet();
                List<String> fileds = new ArrayList(fieldsSet);
                return fileds;
            }
        }
    }

    private String getSql(String sql) {
        String regex = "\\$\\{\\w+\\}";
        Pattern p = Pattern.compile(regex);

        for(Matcher m = p.matcher(sql); m.find(); sql = sql.replace("'''", "''")) {
            String whereParam = m.group();
            sql = sql.replace(whereParam, "'' or 1=1 or 1=''");
        }

        return sql;
    }

    public List<String> getSqlParams(String sql) {
        if(oConvertUtils.isEmpty(sql)) {
            return null;
        } else {
            List<String> params = new ArrayList();
            String regex = "\\$\\{\\w+\\}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(sql);

            while(m.find()) {
                String whereParam = m.group();
                params.add(whereParam.substring(whereParam.indexOf("{") + 1, whereParam.indexOf("}")));
            }

            return params;
        }
    }

    public List<String> getField(String sql, String dbKey) {
        List<String> fields = null;
        sql = this.getSql(sql);
        if(StringUtils.isNotBlank(dbKey)) {
            List<Map<String, Object>> dataList = DynamicDBUtil.findList(dbKey, SqlUtil.jeecgCreatePageSql(dbKey, sql, (Map)null, 1, 1), (Object[])null);
            if(dataList.size() < 1) {
                throw new BusinessException("当前数据源没有查到数据");
            }

            Set fieldsSet = ((Map)dataList.get(0)).keySet();
            fields = new ArrayList(fieldsSet);
        } else {
            fields = this.getSqlFields(sql);
        }

        return (List)fields;
    }
}
