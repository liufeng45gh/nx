//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.config;

import com.sun.star.uno.RuntimeException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.annotation.Ehcache;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.dao.config.CgFormFieldDao;
import org.jeecgframework.web.cgform.dao.config.CgFormVersionDao;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgSubTableVO;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
import org.jeecgframework.web.cgform.service.impl.config.util.DbTableProcess;
import org.jeecgframework.web.cgform.service.impl.config.util.DbTableUtil;
import org.jeecgframework.web.cgform.service.impl.config.util.ExtendJsonConvert;
import org.jeecgframework.web.cgform.util.PublicUtil;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgFormFieldService")
@Transactional
public class CgFormFieldServiceImpl extends CommonServiceImpl implements CgFormFieldServiceI {
    private static final Logger logger = Logger.getLogger(CgFormFieldServiceImpl.class);
    private static final String SYN_NORMAL = "normal";
    private static final String SYN_FORCE = "force";
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CgFormVersionDao cgFormVersionDao;
    @Autowired
    private CgformFtlServiceI cgformFtlService;
    @Autowired
    private CgformEnhanceJsServiceI cgformEnhanceJsService;
    @Autowired
    private CgFormFieldDao cgFormFieldDao;
    @Autowired
    private CgFormIndexServiceI cgFormIndexService;

    public CgFormFieldServiceImpl() {
    }

    public synchronized void updateTable(CgFormHeadEntity t, String sign, boolean isChange) {
        boolean databaseFieldIsChange = false;

        for(int i = 0; i < t.getColumns().size(); ++i) {
            CgFormFieldEntity column = (CgFormFieldEntity)t.getColumns().get(i);
            if(!oConvertUtils.isEmpty(column.getFieldName())) {
                column.setTable(t);
                PublicUtil.judgeCheckboxValue(column, "isNull,isShow,isShowList,isQuery,isKey");
                if(oConvertUtils.isEmpty(column.getId())) {
                    databaseFieldIsChange = true;
                    this.save(column);
                } else {
                    CgFormFieldEntity c = (CgFormFieldEntity)this.getEntity(CgFormFieldEntity.class, column.getId());
                    if(!databaseFieldIsChange && this.databaseFieldIsChange(c, column)) {
                        databaseFieldIsChange = true;
                    }

                    try {
                        MyBeanUtils.copyBeanNotNull2Bean(column, c);
                    } catch (Exception var9) {
                        var9.printStackTrace();
                        logger.error(var9);
                    }

                    this.saveOrUpdate(c);
                }
            }
        }

        t.setIsDbSynch(isChange?"N":t.getIsDbSynch());
        t.setIsDbSynch(databaseFieldIsChange?"N":t.getIsDbSynch());
        Integer newVerion = Integer.valueOf(Integer.parseInt(t.getJformVersion()) + 1);
        t.setJformVersion(newVerion.toString());
        this.saveOrUpdate(t);
    }

    private boolean databaseFieldIsChange(CgFormFieldEntity oldColumn, CgFormFieldEntity newColumn) {
        return !PublicUtil.compareValue(oldColumn.getFieldName(), newColumn.getFieldName()) || !PublicUtil.compareValue(oldColumn.getContent(), newColumn.getContent()) || !PublicUtil.compareValue(oldColumn.getLength(), newColumn.getLength()) || !PublicUtil.compareValue(oldColumn.getPointLength(), newColumn.getPointLength()) || !PublicUtil.compareValue(oldColumn.getType(), newColumn.getType()) || !PublicUtil.compareValue(oldColumn.getIsNull(), newColumn.getIsNull()) || !PublicUtil.compareValue(oldColumn.getOrderNum(), newColumn.getOrderNum()) || !PublicUtil.compareValue(oldColumn.getIsKey(), newColumn.getIsKey()) || !PublicUtil.compareValue(oldColumn.getMainTable(), newColumn.getMainTable()) || !PublicUtil.compareValue(oldColumn.getMainField(), newColumn.getMainField()) || !PublicUtil.compareValue(oldColumn.getFieldDefault(), newColumn.getFieldDefault());
    }

    public void saveTable(CgFormHeadEntity cgFormHead) {
        cgFormHead.setJformVersion("1");
        cgFormHead.setIsDbSynch("N");
        cgFormHead.setId((String)this.getSession().save(cgFormHead));

        for(int i = 0; i < cgFormHead.getColumns().size(); ++i) {
            CgFormFieldEntity column = (CgFormFieldEntity)cgFormHead.getColumns().get(i);
            PublicUtil.judgeCheckboxValue(column, "isNull,isShow,isShowList,isQuery,isKey");
            column.setTable(cgFormHead);
            this.save(column);
        }

    }

    public void saveTable(CgFormHeadEntity cgFormHead, String a) {
        cgFormHead.setId((String)this.getSession().save(cgFormHead));

        for(int i = 0; i < cgFormHead.getColumns().size(); ++i) {
            CgFormFieldEntity column = (CgFormFieldEntity)cgFormHead.getColumns().get(i);
            PublicUtil.judgeCheckboxValue(column, "isNull,isShow,isShowList,isQuery,isKey");
            column.setTable(cgFormHead);
            this.save(column);
        }

    }

    public Boolean judgeTableIsExit(String tableName) {
        Connection conn = null;
        ResultSet rs = null;
        String tableNamePattern = tableName;

        Boolean var9;
        try {
            String[] types = new String[]{"TABLE"};
            conn = SessionFactoryUtils.getDataSource(this.getSession().getSessionFactory()).getConnection();
            String dataBaseType = DbTableUtil.getDataType(this.getSession());
            if("ORACLE".equals(dataBaseType)) {
                tableNamePattern = tableName.toUpperCase();
            } else if("POSTGRESQL".equals(dataBaseType)) {
                tableNamePattern = tableName.toLowerCase();
            }

            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getTables((String)null, (String)null, tableNamePattern, types);
            if(!rs.next()) {
                var9 = Boolean.valueOf(false);
                return var9;
            }

            var9 = Boolean.valueOf(true);
        } catch (SQLException var17) {
            throw new RuntimeException();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }

                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException var16) {
                var16.printStackTrace();
            }

        }

        return var9;
    }

    public boolean dbSynch(CgFormHeadEntity cgFormHead, String synMethod) throws BusinessException {
        try {
            if("normal".equals(synMethod)) {
                if(this.judgeTableIsExit(cgFormHead.getTableName()).booleanValue()) {
                    DbTableProcess dbTableProcess = new DbTableProcess(this.getSession());
                    List<String> updateTable = dbTableProcess.updateTable(cgFormHead, this.getSession());
                    Iterator var6 = updateTable.iterator();

                    while(var6.hasNext()) {
                        String sql = (String)var6.next();
                        if(StringUtils.isNotEmpty(sql)) {
                            this.executeSql(sql, new Object[0]);
                        }
                    }
                } else {
                    try {
                        DbTableProcess.createOrDropTable(cgFormHead, this.getSession());
                    } catch (Exception var9) {
                        logger.error(var9.getMessage(), var9);
                        throw new BusinessException("同步失败:创建新表出错");
                    }
                }

                this.cgFormIndexService.createIndexes(cgFormHead);
                cgFormHead.setIsDbSynch("Y");
                this.saveOrUpdate(cgFormHead);
            } else if("force".equals(synMethod)) {
                try {
                    try {
                        String sql = this.getTableUtil().dropTableSQL(cgFormHead.getTableName());
                        this.executeSql(sql, new Object[0]);
                    } catch (Exception var7) {
                        logger.error(var7.getMessage());
                    }

                    DbTableProcess.createOrDropTable(cgFormHead, this.getSession());
                    this.cgFormIndexService.createIndexes(cgFormHead);
                    cgFormHead.setIsDbSynch("Y");
                    this.saveOrUpdate(cgFormHead);
                } catch (Exception var8) {
                    logger.error(var8.getMessage(), var8);
                    throw new BusinessException("同步失败:创建新表出错");
                }
            }

            return true;
        } catch (BusinessException var10) {
            logger.error(var10.getMessage(), var10);
            throw new BusinessException(var10.getMessage());
        } catch (Exception var11) {
            logger.error(var11.getMessage(), var11);
            throw new BusinessException("同步失败:数据库不支持本次修改,如果不需要保留数据,请尝试强制同步");
        }
    }

    public void deleteCgForm(CgFormHeadEntity cgFormHead) {
        if(this.judgeTableIsExit(cgFormHead.getTableName()).booleanValue()) {
            String sql = this.getTableUtil().dropTableSQL(cgFormHead.getTableName());
            this.executeSql(sql, new Object[0]);
        }

        this.delete(cgFormHead);
    }

    private DbTableHandleI getTableUtil() {
        return DbTableUtil.getTableHandle(this.getSession());
    }

    public List<Map<String, Object>> getCgFormFieldByTableName(String tableName) {
        List<Map<String, Object>> list = this.cgFormFieldDao.getCgFormFieldByTableName(tableName);
        return list;
    }

    private List<Map<String, Object>> getCgFormHiddenFieldByTableName(String tableName) {
        List<Map<String, Object>> list = this.cgFormFieldDao.getCgFormHiddenFieldByTableName(tableName);
        if(list != null && ((List)list).size() > 0) {
            Iterator var4 = ((List)list).iterator();

            while(var4.hasNext()) {
                Map<String, Object> map = (Map)var4.next();
                if("id".equalsIgnoreCase((String)map.get("field_name"))) {
                    ((List)list).remove(map);
                    break;
                }
            }
        } else {
            list = new ArrayList();
        }

        return (List)list;
    }

    public Map<String, CgFormFieldEntity> getCgFormFieldByFormId(String formid) {
        StringBuilder hql = new StringBuilder("");
        hql.append("from CgFormFieldEntity f");
        hql.append(" where f.table.id=? ");
        List<CgFormFieldEntity> list = this.findHql(hql.toString(), new Object[]{formid});
        Map<String, CgFormFieldEntity> map = new HashMap();
        if(list != null && list.size() > 0) {
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                CgFormFieldEntity po = (CgFormFieldEntity)var6.next();
                map.put(po.getFieldName(), po);
            }
        }

        return map;
    }

    public Map<String, CgFormFieldEntity> getAllCgFormFieldByTableName(String tableName) {
        StringBuilder hql = new StringBuilder("");
        hql.append("from CgFormFieldEntity f");
        hql.append(" where f.table.tableName=? ");
        List<CgFormFieldEntity> list = this.findHql(hql.toString(), new Object[]{tableName});
        Map<String, CgFormFieldEntity> map = new HashMap();
        if(list != null && list.size() > 0) {
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                CgFormFieldEntity po = (CgFormFieldEntity)var6.next();
                map.put(po.getFieldName(), po);
            }
        }

        return map;
    }

    //@Ehcache
    public Map<String, CgFormFieldEntity> getAllCgFormFieldByTableName(String tableName, String version) {
        StringBuilder hql = new StringBuilder("");
        hql.append("from CgFormFieldEntity f");
        hql.append(" where f.table.tableName=? ");
        List<CgFormFieldEntity> list = this.findHql(hql.toString(), new Object[]{tableName});
        Map<String, CgFormFieldEntity> map = new HashMap();
        if(list != null && list.size() > 0) {
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                CgFormFieldEntity po = (CgFormFieldEntity)var7.next();
                map.put(po.getFieldName(), po);
            }
        }

        return map;
    }

    public CgFormHeadEntity getCgFormHeadByTableName(String tableName) {
        StringBuilder hql = new StringBuilder("");
        hql.append("from CgFormHeadEntity f");
        hql.append(" where f.tableName=? ");
        List<CgFormHeadEntity> list = this.findHql(hql.toString(), new Object[]{tableName});
        return list != null && list.size() > 0?(CgFormHeadEntity)list.get(0):null;
    }

    public List<Map<String, Object>> getSubTableData(String mainTableName, String subTableName, Object mainTableId) {
        StringBuilder sql1 = new StringBuilder("");
        sql1.append("select f.* from cgform_field f ,cgform_head h");
        sql1.append(" where f.table_id = h.id ");
        sql1.append(" and h.table_name=? ");
        sql1.append(" and f.main_table=? ");
        List<Map<String, Object>> list = this.findForJdbc(sql1.toString(), new Object[]{subTableName, mainTableName});
        StringBuilder sql2 = new StringBuilder("");
        sql2.append("select sub.* from ").append(subTableName).append(" sub ");
        sql2.append(", ").append(mainTableName).append(" main ");
        sql2.append("where 1=1 ");
        if(list != null && list.size() > 0) {
            Iterator var8 = list.iterator();

            while(var8.hasNext()) {
                Map<String, Object> map = (Map)var8.next();
                if(map.get("main_field") != null) {
                    sql2.append(" and sub.").append((String)map.get("field_name")).append("=").append("main.").append((String)map.get("main_field"));
                }
            }
        }

        sql2.append(" and main.id= ? ");
        List<Map<String, Object>> subTableDataList = this.findForJdbc(sql2.toString(), new Object[]{mainTableId});
        return subTableDataList;
    }

    public boolean appendSubTableStr4Main(CgFormHeadEntity entity) {
        String thisSubTable = entity.getTableName();
        List<CgFormFieldEntity> columns = entity.getColumns();
        Iterator var5 = columns.iterator();

        while(var5.hasNext()) {
            CgFormFieldEntity fieldE = (CgFormFieldEntity)var5.next();
            String mainT = fieldE.getMainTable();
            String mainF = fieldE.getMainField();
            if(!StringUtil.isEmpty(mainT) && !StringUtil.isEmpty(mainF)) {
                CgFormHeadEntity mainE = this.getCgFormHeadByTableName(mainT);
                if(mainE != null) {
                    String subTableStr = String.valueOf(mainE.getSubTableStr() == null?"":mainE.getSubTableStr());
                    if(!subTableStr.contains(thisSubTable)) {
                        if(!StringUtil.isEmpty(subTableStr)) {
                            subTableStr = subTableStr + "," + thisSubTable;
                        } else {
                            subTableStr = subTableStr + thisSubTable;
                        }

                        mainE.setSubTableStr(subTableStr);
                        logger.info("--主表" + mainE.getTableName() + "的附表串：" + mainE.getSubTableStr());
                    }

                    this.updateTable(mainE, "sign", false);
                }
            }
        }

        return true;
    }

    public boolean removeSubTableStr4Main(CgFormHeadEntity entity) {
        if(entity == null) {
            return false;
        } else {
            String thisSubTable = entity.getTableName();
            List<CgFormFieldEntity> columns = entity.getColumns();
            Iterator var5 = columns.iterator();

            while(var5.hasNext()) {
                CgFormFieldEntity fieldE = (CgFormFieldEntity)var5.next();
                String mainT = fieldE.getMainTable();
                String mainF = fieldE.getMainField();
                if(!StringUtil.isEmpty(mainT) && !StringUtil.isEmpty(mainF)) {
                    CgFormHeadEntity mainE = this.getCgFormHeadByTableName(mainT);
                    if(mainE != null) {
                        String subTableStr = String.valueOf(mainE.getSubTableStr() == null?"":mainE.getSubTableStr());
                        if(subTableStr.contains(thisSubTable)) {
                            if(subTableStr.contains(thisSubTable + ",")) {
                                subTableStr = subTableStr.replace(thisSubTable + ",", "");
                            } else if(subTableStr.contains("," + thisSubTable)) {
                                subTableStr = subTableStr.replace("," + thisSubTable, "");
                            } else {
                                subTableStr = subTableStr.replace(thisSubTable, "");
                            }

                            mainE.setSubTableStr(subTableStr);
                            logger.info("--主表" + mainE.getTableName() + "的附表串：" + mainE.getSubTableStr());
                        }
                    }
                }
            }

            return true;
        }
    }

    public void sortSubTableStr(CgFormHeadEntity entity) {
        if(entity != null) {
            CgFormHeadEntity main = null;
            List<CgFormFieldEntity> columns = entity.getColumns();
            Iterator var5 = columns.iterator();

            String subTable;
            while(var5.hasNext()) {
                CgFormFieldEntity fieldE = (CgFormFieldEntity)var5.next();
                String mainT = fieldE.getMainTable();
                subTable = fieldE.getMainField();
                if(!StringUtil.isEmpty(mainT) && !StringUtil.isEmpty(subTable)) {
                    CgFormHeadEntity mainE = this.getCgFormHeadByTableName(mainT);
                    if(mainE != null) {
                        main = mainE;
                    }
                }
            }

            if(main != null) {
                String subTableStr = main.getSubTableStr();
                if(StringUtils.isNotEmpty(subTableStr)) {
                    String[] subTables = subTableStr.split(",");
                    if(subTables.length <= 1) {
                        return;
                    }

                    List<CgFormHeadEntity> subList = new ArrayList();
                    String[] var10 = subTables;
                    int var9 = subTables.length;

                    for(int var16 = 0; var16 < var9; ++var16) {
                        subTable = var10[var16];
                        CgFormHeadEntity sub = this.getCgFormHeadByTableName(subTable);
                        subList.add(sub);
                    }

                    Collections.sort(subList, new Comparator<CgFormHeadEntity>() {
                        public int compare(CgFormHeadEntity arg0, CgFormHeadEntity arg1) {
                            Integer tabOrder0 = arg0.getTabOrder();
                            if(tabOrder0 == null) {
                                tabOrder0 = Integer.valueOf(0);
                            }

                            Integer tabOrder1 = arg1.getTabOrder();
                            if(tabOrder1 == null) {
                                tabOrder1 = Integer.valueOf(0);
                            }

                            return tabOrder0.compareTo(tabOrder1);
                        }
                    });
                    subTableStr = "";

                    CgFormHeadEntity sub;
                    for(Iterator var17 = subList.iterator(); var17.hasNext(); subTableStr = subTableStr + sub.getTableName() + ",") {
                        sub = (CgFormHeadEntity)var17.next();
                    }

                    subTableStr = subTableStr.substring(0, subTableStr.length() - 1);
                    main.setSubTableStr(subTableStr);
                    this.updateTable(main, "sign", false);
                }

            }
        }
    }

    public String getCgFormVersionByTableName(String tableName) {
        String version = this.cgFormVersionDao.getCgFormVersionByTableName(tableName);
        return StringUtil.isEmpty(version)?"0":version;
    }

    public String getCgFormVersionById(String id) {
        String version = this.cgFormVersionDao.getCgFormVersionById(id);
        return StringUtil.isEmpty(version)?"0":version;
    }

    public Map<String, Object> getFtlFormConfig(String tableName, String version) {
        Map<String, Object> data = new HashMap();
        Map<String, Object> field = new HashMap();
        CgFormHeadEntity head = this.getCgFormHeadByTableName(tableName, version);
        data.put("head", head);
        String id;
        if(head.getJformType().intValue() == 2) {
            new CgSubTableVO();
            String subTableStr = head.getSubTableStr();
            if(StringUtils.isNotEmpty(subTableStr)) {
                String[] subTables = subTableStr.split(",");
                new ArrayList();
                new ArrayList();
                String[] var14 = subTables;
                int var13 = subTables.length;

                for(int var12 = 0; var12 < var13; ++var12) {
                    id = var14[var12];
                    List<Map<String, Object>> subTalbeFieldList = this.getCgFormFieldByTableName(id);
                    List<Map<String, Object>> subTalbeHiddenFieldList = this.getCgFormHiddenFieldByTableName(id);
                    CgFormHeadEntity subhead = this.getCgFormHeadByTableName(id);
                    CgSubTableVO subtableVo = new CgSubTableVO();
                    subtableVo.setHead(subhead);
                    subtableVo.setFieldList(subTalbeFieldList);
                    subtableVo.setHiddenFieldList(subTalbeHiddenFieldList);
                    ExtendJsonConvert.json2HtmlForList(subTalbeFieldList, "extend_json");
                    field.put(id, subtableVo);
                }
            }
        }

        data.put("field", field);
        data.put("tableName", tableName);
        List<Map<String, Object>> fieldList = null;
        if(head.getJformType().intValue() == 2) {
            fieldList = this.getCgFormFieldByTableName(tableName);
        } else {
            Map<String, Object> cgformFtlEntity = this.cgformFtlService.getCgformFtlByTableName(tableName);
            if(cgformFtlEntity == null) {
                fieldList = this.getCgFormFieldByTableName(tableName);
            }
        }

        List<Map<String, Object>> hiddenFieldList = this.getCgFormHiddenFieldByTableName(tableName);
        data.put("columnhidden", hiddenFieldList);
        Set<String> operationCodes = (Set)ContextHolderUtils.getRequest().getAttribute("operationCodes");
        Map<String, TSOperation> operationCodesMap = new HashMap();
        if(operationCodes != null) {
            Iterator var26 = operationCodes.iterator();

            while(var26.hasNext()) {
                id = (String)var26.next();
                TSOperation tsOperation = (TSOperation)this.getEntity(TSOperation.class, id);
                if(tsOperation != null && tsOperation.getStatus().shortValue() == 0) {
                    operationCodesMap.put(tsOperation.getOperationcode(), tsOperation);
                }
            }
        }

        if(fieldList != null) {
            List<Map<String, Object>> list = new ArrayList();
            List<Map<String, Object>> textareaList = new ArrayList();
            Iterator var28 = fieldList.iterator();

            while(true) {
                while(var28.hasNext()) {
                    Map<String, Object> map = (Map)var28.next();
                    if(operationCodesMap.containsKey(map.get("field_name"))) {
                        if(((TSOperation)operationCodesMap.get(map.get("field_name"))).getOperationType().shortValue() == 0) {
                            hiddenFieldList.add(map);
                            continue;
                        }

                        map.put("operationCodesReadOnly", Boolean.valueOf(true));
                    }

                    if(!"textarea".equals((String)map.get("show_type")) && !"umeditor".equals((String)map.get("show_type"))) {
                        list.add(map);
                    } else {
                        textareaList.add(map);
                    }
                }

                data.put("columns", list);
                data.put("columnsarea", textareaList);
                break;
            }
        }

        ExtendJsonConvert.json2HtmlForList(fieldList, "extend_json");
        String jsCode = "";
        CgformEnhanceJsEntity jsEnhance = this.cgformEnhanceJsService.getCgformEnhanceJsByTypeFormId("form", head.getId());
        if(jsEnhance != null) {
            jsCode = jsEnhance.getCgJsStr();
        }

        data.put("js_plug_in", jsCode);
        return data;
    }

    //@Ehcache
    public CgFormHeadEntity getCgFormHeadByTableName(String tableName, String version) {
        StringBuilder hql = new StringBuilder("");
        hql.append("from CgFormHeadEntity f");
        hql.append(" where f.tableName=? ");
        List<CgFormHeadEntity> list = this.findHql(hql.toString(), new Object[]{tableName});
        return list != null && list.size() > 0?(CgFormHeadEntity)list.get(0):null;
    }

    public synchronized boolean updateVersion(String formId) {
        try {
            int newV = Integer.parseInt(this.cgFormVersionDao.getCgFormVersionById(formId)) + 1;
            this.cgFormVersionDao.updateVersion(String.valueOf(String.valueOf(newV)), formId);
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            logger.debug(var3.getMessage());
            return false;
        }
    }

    public List<CgFormFieldEntity> getHiddenCgFormFieldByTableName(String tableName) {
        StringBuilder hql = new StringBuilder("");
        hql.append("from CgFormFieldEntity f");
        hql.append(" where f.isShow !='Y' and f.table.tableName=? ");
        List<CgFormFieldEntity> list = this.findHql(hql.toString(), new Object[]{tableName});
        if(list != null && ((List)list).size() > 0) {
            Iterator var5 = ((List)list).iterator();

            while(var5.hasNext()) {
                CgFormFieldEntity po = (CgFormFieldEntity)var5.next();
                if("id".equalsIgnoreCase(po.getFieldName())) {
                    ((List)list).remove(po);
                    break;
                }
            }
        } else {
            list = new ArrayList();
        }

        return (List)list;
    }

    public boolean checkTableExist(String tableName) {
        boolean result = true;

        try {
            this.findForJdbc("select count(*) from " + tableName, new Object[0]);
        } catch (Exception var4) {
            result = false;
        }

        return result;
    }
}
