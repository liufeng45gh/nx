//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.config.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.exception.DBException;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public class DbTableProcess {
    private static final Logger logger = Logger.getLogger(DbTableProcess.class);
    private static DbTableHandleI dbTableHandle;

    public DbTableProcess(Session session) {
        dbTableHandle = DbTableUtil.getTableHandle(session);
    }

    public static void createOrDropTable(CgFormHeadEntity table, Session session) throws IOException, TemplateException, HibernateException, SQLException, DBException {
        Template t = getConfig("/org/jeecgframework/web/cgform/engine/hibernate").getTemplate("tableTemplate.ftl");
        Writer out = new StringWriter();
        t.setNumberFormat("0.#####################");
        t.process(getRootMap(table, DbTableUtil.getDataType(session)), out);
        String xml = out.toString();
        logger.info(xml);
        createTable(xml, table, session);
    }

    private static Object getRootMap(CgFormHeadEntity table, String dataType) {
        Map map = new HashMap();
        Iterator var4 = table.getColumns().iterator();

        while(var4.hasNext()) {
            CgFormFieldEntity field = (CgFormFieldEntity)var4.next();
            field.setFieldDefault(judgeIsNumber(field.getFieldDefault()));
        }

        map.put("entity", table);
        map.put("dataType", dataType);
        return map;
    }

    public List<String> updateTable(CgFormHeadEntity table, Session session) throws DBException {
        String tableName = DbTableUtil.getDataType(session).equals("ORACLE")?table.getTableName().toUpperCase():table.getTableName();
        String alterTable = "alter table  " + tableName + " ";
        ArrayList strings = new ArrayList();

        try {
            Map<String, ColumnMeta> dataBaseColumnMetaMap = getColumnMetadataFormDataBase((String)null, tableName, session);
            Map<String, ColumnMeta> cgFormColumnMetaMap = getColumnMetadataFormCgForm(table);
            Map<String, String> newAndOldFieldMap = getNewAndOldFieldName(table);
            Iterator var10 = cgFormColumnMetaMap.keySet().iterator();

            label64:
            while(true) {
                String columnName;
                if(!var10.hasNext()) {
                    var10 = dataBaseColumnMetaMap.keySet().iterator();

                    while(true) {
                        if(!var10.hasNext()) {
                            break label64;
                        }

                        columnName = (String)var10.next();
                        if(!cgFormColumnMetaMap.containsKey(columnName.toLowerCase()) && !newAndOldFieldMap.containsValue(columnName.toLowerCase())) {
                            strings.add(alterTable + getDropColumnSql(columnName));
                        }
                    }
                }

                columnName = (String)var10.next();
                ColumnMeta cgFormColumnMeta;
                ColumnMeta dataColumnMeta;
                if(!dataBaseColumnMetaMap.containsKey(columnName)) {
                    cgFormColumnMeta = (ColumnMeta)cgFormColumnMetaMap.get(columnName);
                    if(newAndOldFieldMap.containsKey(columnName) && dataBaseColumnMetaMap.containsKey(newAndOldFieldMap.get(columnName))) {
                        dataColumnMeta = (ColumnMeta)dataBaseColumnMetaMap.get(newAndOldFieldMap.get(columnName));
                        if(DbTableUtil.getDataType(session).equals("SQLSERVER")) {
                            strings.add(getReNameFieldName(cgFormColumnMeta));
                        } else {
                            strings.add(alterTable + getReNameFieldName(cgFormColumnMeta));
                        }

                        this.updateFieldName(columnName, cgFormColumnMeta.getColumnId(), session);
                        if(!dataColumnMeta.equals(cgFormColumnMeta)) {
                            strings.add(alterTable + getUpdateColumnSql(cgFormColumnMeta, dataColumnMeta));
                            if(DbTableUtil.getDataType(session).equals("POSTGRESQL")) {
                                strings.add(alterTable + getUpdateSpecialSql(cgFormColumnMeta, dataColumnMeta));
                            }
                        }

                        if(!dataColumnMeta.equalsComment(cgFormColumnMeta)) {
                            strings.add(this.getCommentSql(cgFormColumnMeta));
                        }
                    } else {
                        strings.add(alterTable + getAddColumnSql(cgFormColumnMeta));
                        if(StringUtils.isNotEmpty(cgFormColumnMeta.getComment())) {
                            strings.add(this.getCommentSql(cgFormColumnMeta));
                        }
                    }
                } else {
                    cgFormColumnMeta = (ColumnMeta)dataBaseColumnMetaMap.get(columnName);
                    dataColumnMeta = (ColumnMeta)cgFormColumnMetaMap.get(columnName);
                    if(!cgFormColumnMeta.equals(dataColumnMeta)) {
                        strings.add(alterTable + getUpdateColumnSql(dataColumnMeta, cgFormColumnMeta));
                    }

                    if(!cgFormColumnMeta.equalsComment(dataColumnMeta)) {
                        strings.add(this.getCommentSql(dataColumnMeta));
                    }
                }
            }
        } catch (SQLException var13) {
            throw new RuntimeException();
        }

        logger.info(strings.toString());
        return strings;
    }

    private static void createTable(String xml, CgFormHeadEntity table, Session session) throws HibernateException, SQLException, DBException {
        Configuration newconf = new Configuration();
        newconf.addXML(xml).setProperty("hibernate.dialect", ((SessionImpl)session).getFactory().getDialect().getClass().getName());
        SchemaExport dbExport = new SchemaExport(newconf, SessionFactoryUtils.getDataSource(session.getSessionFactory()).getConnection());
        dbExport.execute(true, true, false, true);
        List<Exception> exceptionList = dbExport.getExceptions();
        Iterator var7 = exceptionList.iterator();
        if(var7.hasNext()) {
            Exception exception = (Exception)var7.next();
            throw new DBException(exception.getMessage());
        }
    }

    private static freemarker.template.Configuration getConfig(String resource) {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(DbTableProcess.class, resource);
        return cfg;
    }

    public static Map<String, ColumnMeta> getColumnMetadataFormDataBase(String schemaName, String tableName, Session session) throws SQLException {
        Connection conn = SessionFactoryUtils.getDataSource(session.getSessionFactory()).getConnection();
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet rs = dbMetaData.getColumns((String)null, schemaName, tableName, "%");
        HashMap columnMap = new HashMap();

        while(rs.next()) {
            ColumnMeta columnMeta = new ColumnMeta();
            columnMeta.setTableName(rs.getString("COLUMN_NAME").toLowerCase());
            columnMeta.setColumnName(rs.getString("COLUMN_NAME").toLowerCase());
            columnMeta.setColunmType(dbTableHandle.getMatchClassTypeByDataType(rs.getString("TYPE_NAME"), rs.getInt("DECIMAL_DIGITS")));
            columnMeta.setColumnSize(rs.getInt("COLUMN_SIZE"));
            columnMeta.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
            columnMeta.setIsNullable(rs.getInt("NULLABLE") == 1?"Y":"N");
            columnMeta.setComment(rs.getString("REMARKS"));
            columnMeta.setFieldDefault(judgeIsNumber(rs.getString("COLUMN_DEF")) == null?"":judgeIsNumber(rs.getString("COLUMN_DEF")));
            logger.info("getColumnMetadataFormDataBase --->COLUMN_NAME:" + rs.getString("COLUMN_NAME") + " TYPE_NAME :" + rs.getString("TYPE_NAME") + " DECIMAL_DIGITS:" + rs.getInt("DECIMAL_DIGITS") + " COLUMN_SIZE:" + rs.getInt("COLUMN_SIZE"));
            columnMap.put(rs.getString("COLUMN_NAME").toLowerCase(), columnMeta);
        }

        return columnMap;
    }

    public static Map<String, ColumnMeta> getColumnMetadataFormCgForm(CgFormHeadEntity table) {
        Map<String, ColumnMeta> map = new HashMap();
        List<CgFormFieldEntity> cgFormFieldEntities = table.getColumns();
        Iterator var5 = cgFormFieldEntities.iterator();

        while(var5.hasNext()) {
            CgFormFieldEntity cgFormFieldEntity = (CgFormFieldEntity)var5.next();
            ColumnMeta columnMeta = new ColumnMeta();
            columnMeta.setTableName(table.getTableName().toLowerCase());
            columnMeta.setColumnId(cgFormFieldEntity.getId());
            columnMeta.setColumnName(cgFormFieldEntity.getFieldName().toLowerCase());
            columnMeta.setColumnSize(cgFormFieldEntity.getLength().intValue());
            columnMeta.setColunmType(cgFormFieldEntity.getType().toLowerCase());
            columnMeta.setIsNullable(cgFormFieldEntity.getIsNull());
            columnMeta.setComment(cgFormFieldEntity.getContent());
            columnMeta.setDecimalDigits(cgFormFieldEntity.getPointLength().intValue());
            columnMeta.setFieldDefault(judgeIsNumber(cgFormFieldEntity.getFieldDefault()));
            columnMeta.setPkType(table.getJformPkType() == null?"UUID":table.getJformPkType());
            columnMeta.setOldColumnName(cgFormFieldEntity.getOldFieldName() != null?cgFormFieldEntity.getOldFieldName().toLowerCase():null);
            logger.info("getColumnMetadataFormCgForm ---->COLUMN_NAME:" + cgFormFieldEntity.getFieldName().toLowerCase() + " TYPE_NAME:" + cgFormFieldEntity.getType().toLowerCase() + " DECIMAL_DIGITS:" + cgFormFieldEntity.getPointLength() + " COLUMN_SIZE:" + cgFormFieldEntity.getLength());
            map.put(cgFormFieldEntity.getFieldName().toLowerCase(), columnMeta);
        }

        return map;
    }

    public static Map<String, String> getNewAndOldFieldName(CgFormHeadEntity table) {
        Map<String, String> map = new HashMap();
        List<CgFormFieldEntity> cgFormFieldEntities = table.getColumns();
        Iterator var4 = cgFormFieldEntities.iterator();

        while(var4.hasNext()) {
            CgFormFieldEntity cgFormFieldEntity = (CgFormFieldEntity)var4.next();
            map.put(cgFormFieldEntity.getFieldName(), cgFormFieldEntity.getOldFieldName());
        }

        return map;
    }

    private static String getDropColumnSql(String fieldName) {
        return dbTableHandle.getDropColumnSql(fieldName);
    }

    private static String getUpdateColumnSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta) throws DBException {
        return dbTableHandle.getUpdateColumnSql(cgformcolumnMeta, datacolumnMeta);
    }

    private static String getUpdateSpecialSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta) {
        return dbTableHandle.getSpecialHandle(cgformcolumnMeta, datacolumnMeta);
    }

    private static String getReNameFieldName(ColumnMeta columnMeta) {
        return dbTableHandle.getReNameFieldName(columnMeta);
    }

    private static String getAddColumnSql(ColumnMeta columnMeta) {
        return dbTableHandle.getAddColumnSql(columnMeta);
    }

    private String getCommentSql(ColumnMeta columnMeta) {
        return dbTableHandle.getCommentSql(columnMeta);
    }

    private int updateFieldName(String columnName, String id, Session session) {
        return session.createSQLQuery("update cgform_field set old_field_name= '" + columnName + "' where id='" + id + "'").executeUpdate();
    }

    private static String judgeIsNumber(String text) {
        if(StringUtils.isNotEmpty(text)) {
            try {
                Double.valueOf(text);
            } catch (Exception var2) {
                text = "'" + text + "'";
            }
        }

        return text;
    }
}
