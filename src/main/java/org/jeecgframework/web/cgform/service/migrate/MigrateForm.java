//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.migrate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
import org.jeecgframework.web.cgform.entity.cgformftl.CgformFtlEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.pojo.config.CgFormFieldPojo;
import org.jeecgframework.web.cgform.pojo.config.CgFormHeadPojo;
import org.jeecgframework.web.cgform.util.PublicUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

@Service("MigrateForm")
public class MigrateForm<T> {
    private static final Logger logger = Logger.getLogger(MigrateForm.class);
    static InputStream inStream = null;
    private static String insert = "INSERT INTO";
    private static String values = "VALUES";
    private static List<String> insertList = new ArrayList();
    private static String basePath = "";

    public MigrateForm() {
    }

    /** @deprecated */
    @Deprecated
    public static List<String> createSQL(String ids, JdbcTemplate jdbcTemplate) {
        List<String> listSQL = new ArrayList();
        listSQL.clear();
        String[] idList = ids.split(",");
        String ls_sql = "";
        String ls_tmpsql = "";
        String ls_subid = "";
        String subTable = "";
        List rowsList = null;
        List subRowsList = null;
        Map sqlMap = null;
        Map subSqlMap = null;
        String[] var15 = idList;
        int var14 = idList.length;

        for(int var13 = 0; var13 < var14; ++var13) {
            String id = var15[var13];
            ls_sql = "select * from cgform_head where id='" + id + "'";
            listSQL.add(ls_sql);
            ls_tmpsql = "select * from cgform_field where table_id='" + id + "'";
            listSQL.add(ls_tmpsql);
            ls_tmpsql = "select * from cgform_button where form_id ='" + id + "'";
            listSQL.add(ls_tmpsql);
            ls_tmpsql = "select * from cgform_enhance_js where form_id ='" + id + "'";
            listSQL.add(ls_tmpsql);
            ls_tmpsql = "select * from cgform_button_sql where form_id ='" + id + "'";
            listSQL.add(ls_tmpsql);
            ls_tmpsql = "select * from cgform_ftl where cgform_id ='" + id + "'";
            listSQL.add(ls_tmpsql);
            ls_tmpsql = "select * from cgform_uploadfiles where cgform_id ='" + id + "'";
            listSQL.add(ls_tmpsql);
            rowsList = jdbcTemplate.queryForList(ls_sql);
            if(rowsList != null && rowsList.size() > 0) {
                sqlMap = (Map)rowsList.get(0);
                subTable = (String)sqlMap.get("sub_table_str");
                if(subTable != null && !"".equals(subTable)) {
                    String[] subs = subTable.split(",");
                    String[] var20 = subs;
                    int var19 = subs.length;

                    for(int var18 = 0; var18 < var19; ++var18) {
                        String sub = var20[var18];
                        ls_tmpsql = "select * from cgform_head where table_name='" + sub + "'";
                        listSQL.add(ls_tmpsql);
                        subRowsList = jdbcTemplate.queryForList(ls_tmpsql);
                        if(subRowsList != null && subRowsList.size() > 0) {
                            subSqlMap = (Map)subRowsList.get(0);
                            ls_subid = (String)subSqlMap.get("id");
                            ls_tmpsql = "select * from cgform_field where table_id='" + ls_subid + "'";
                            listSQL.add(ls_tmpsql);
                            ls_tmpsql = "select * from cgform_button where form_id ='" + ls_subid + "'";
                            listSQL.add(ls_tmpsql);
                            ls_tmpsql = "select * from cgform_enhance_js where form_id ='" + ls_subid + "'";
                            listSQL.add(ls_tmpsql);
                            ls_tmpsql = "select * from cgform_button_sql where form_id ='" + ls_subid + "'";
                            listSQL.add(ls_tmpsql);
                            ls_tmpsql = "select * from cgform_ftl where cgform_id ='" + ls_subid + "'";
                            listSQL.add(ls_tmpsql);
                            ls_tmpsql = "select * from cgform_uploadfiles where cgform_id ='" + ls_subid + "'";
                            listSQL.add(ls_tmpsql);
                        }
                    }
                }
            }
        }

        return listSQL;
    }

    public static List<DBTable> buildExportDbTableList(String ids, JdbcTemplate jdbcTemplate) throws Exception {
        List<DBTable> listTables = new ArrayList();
        listTables.clear();
        String ls_sql = "";
        String ls_tmpsql = "";
        String ls_subid = "";
        String subTable = "";
        List rowsList = null;
        List subRowsList = null;
        Map sqlMap = null;
        Map subSqlMap = null;
        String[] idList = ids.split(",");
        String[] var15 = idList;
        int var14 = idList.length;

        for(int var13 = 0; var13 < var14; ++var13) {
            String id = var15[var13];
            ls_sql = "select * from cgform_head where id='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_sql, CgFormHeadPojo.class, jdbcTemplate));
            ls_tmpsql = "select * from cgform_field where table_id='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgFormFieldPojo.class, jdbcTemplate));
            ls_tmpsql = "select * from cgform_button where form_id ='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformButtonEntity.class, jdbcTemplate));
            ls_tmpsql = "select * from cgform_enhance_js where form_id ='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformEnhanceJsEntity.class, jdbcTemplate));
            ls_tmpsql = "select * from cgform_button_sql where form_id ='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformButtonSqlEntity.class, jdbcTemplate));
            ls_tmpsql = "select * from cgform_ftl where cgform_id ='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformFtlEntity.class, jdbcTemplate));
            ls_tmpsql = "select * from cgform_uploadfiles where cgform_id ='" + id + "'";
            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgUploadEntity.class, jdbcTemplate));
            rowsList = jdbcTemplate.queryForList(ls_sql);
            if(rowsList != null && rowsList.size() > 0) {
                sqlMap = (Map)rowsList.get(0);
                subTable = (String)sqlMap.get("sub_table_str");
                if(subTable != null && !"".equals(subTable)) {
                    String[] subs = subTable.split(",");
                    String[] var20 = subs;
                    int var19 = subs.length;

                    for(int var18 = 0; var18 < var19; ++var18) {
                        String sub = var20[var18];
                        ls_tmpsql = "select * from cgform_head where table_name='" + sub + "'";
                        listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgFormHeadPojo.class, jdbcTemplate));
                        subRowsList = jdbcTemplate.queryForList(ls_tmpsql);
                        if(subRowsList != null && subRowsList.size() > 0) {
                            subSqlMap = (Map)subRowsList.get(0);
                            ls_subid = (String)subSqlMap.get("id");
                            ls_tmpsql = "select * from cgform_field where table_id='" + ls_subid + "'";
                            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgFormFieldPojo.class, jdbcTemplate));
                            ls_tmpsql = "select * from cgform_button where form_id ='" + ls_subid + "'";
                            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformButtonEntity.class, jdbcTemplate));
                            ls_tmpsql = "select * from cgform_enhance_js where form_id ='" + ls_subid + "'";
                            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformEnhanceJsEntity.class, jdbcTemplate));
                            ls_tmpsql = "select * from cgform_button_sql where form_id ='" + ls_subid + "'";
                            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformButtonSqlEntity.class, jdbcTemplate));
                            ls_tmpsql = "select * from cgform_ftl where cgform_id ='" + ls_subid + "'";
                            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgformFtlEntity.class, jdbcTemplate));
                            ls_tmpsql = "select * from cgform_uploadfiles where cgform_id ='" + ls_subid + "'";
                            listTables.add(bulidDbTableFromSQL(ls_tmpsql, CgUploadEntity.class, jdbcTemplate));
                        }
                    }
                }
            }
        }

        return listTables;
    }

    public static void executeSQL(List<String> listSQL, JdbcTemplate jdbcTemplate) throws Exception {
        getColumnNameAndColumeValue(listSQL, jdbcTemplate);
    }

    public static <T> DBTable<T> bulidDbTableFromSQL(String sql, Class<T> clazz, JdbcTemplate jdbcTemplate) throws InstantiationException, IllegalAccessException, Exception {
        DBTable<T> dbTable = new DBTable();
        dbTable.setTableName(PublicUtil.getTableName(sql));
        dbTable.setClass1(clazz);
        List<T> dataList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
        dbTable.setTableData(dataList);
        return dbTable;
    }

    public static void getColumnNameAndColumeValue(List<String> listSQL, JdbcTemplate jdbcTemplate) throws Exception {
        if(listSQL.size() > 0) {
            insertList.clear();
            insertList.add("SET FOREIGN_KEY_CHECKS=0;");
            SqlRowSet sqlRowSet = null;
            String ls_id = "";

            for(int j = 0; j < listSQL.size(); ++j) {
                String sql = String.valueOf(listSQL.get(j));
                sqlRowSet = jdbcTemplate.queryForRowSet(sql);
                SqlRowSetMetaData sqlRsmd = sqlRowSet.getMetaData();
                int columnCount = sqlRsmd.getColumnCount();
                String tableName = sqlRsmd.getTableName(columnCount);
                if(StringUtils.isEmpty(tableName)) {
                    tableName = PublicUtil.getTableName(sql);
                }

                String tableId = "";

                while(sqlRowSet.next()) {
                    StringBuffer ColumnName = new StringBuffer();
                    StringBuffer ColumnValue = new StringBuffer();

                    for(int i = 1; i <= columnCount; ++i) {
                        String value = sqlRowSet.getString(i);
                        if(value == null || "".equals(value)) {
                            value = "";
                        }

                        Map<String, String> fieldMap = new HashMap();
                        fieldMap.put("name", sqlRsmd.getColumnName(i));
                        fieldMap.put("fieldType", String.valueOf(sqlRsmd.getColumnType(i)));
                        if(i == 1) {
                            insertList.add("delete from " + tableName + " where " + sqlRsmd.getColumnName(i) + "='" + value + "';");
                            ColumnName.append(sqlRsmd.getColumnName(i));
                            ls_id = value;
                            tableId = value;
                            if(1 != sqlRsmd.getColumnType(i) && 12 != sqlRsmd.getColumnType(i)) {
                                if(5 != sqlRsmd.getColumnType(i) && 4 != sqlRsmd.getColumnType(i) && -5 != sqlRsmd.getColumnType(i) && 6 != sqlRsmd.getColumnType(i) && 8 != sqlRsmd.getColumnType(i) && 2 != sqlRsmd.getColumnType(i) && 3 != sqlRsmd.getColumnType(i)) {
                                    if(91 != sqlRsmd.getColumnType(i) && 92 != sqlRsmd.getColumnType(i) && 93 != sqlRsmd.getColumnType(i)) {
                                        ColumnValue.append(value).append(",");
                                    } else {
                                        if("".equals(value)) {
                                            value = "2000-01-01";
                                        }

                                        ColumnValue.append("'").append(value).append("',");
                                    }
                                } else {
                                    if("".equals(value)) {
                                        value = "0";
                                    }

                                    ColumnValue.append(value).append(",");
                                }
                            } else {
                                ColumnValue.append("'").append(value).append("',");
                            }
                        } else if(i == columnCount) {
                            ColumnName.append("," + sqlRsmd.getColumnName(i));
                            if(1 != sqlRsmd.getColumnType(i) && 12 != sqlRsmd.getColumnType(i) && -1 != sqlRsmd.getColumnType(i)) {
                                if(5 != sqlRsmd.getColumnType(i) && 4 != sqlRsmd.getColumnType(i) && -5 != sqlRsmd.getColumnType(i) && 6 != sqlRsmd.getColumnType(i) && 8 != sqlRsmd.getColumnType(i) && 2 != sqlRsmd.getColumnType(i) && 3 != sqlRsmd.getColumnType(i)) {
                                    if(91 != sqlRsmd.getColumnType(i) && 92 != sqlRsmd.getColumnType(i) && 93 != sqlRsmd.getColumnType(i)) {
                                        ColumnValue.append(value).append("");
                                    } else {
                                        if("".equals(value)) {
                                            value = "2000-01-01";
                                        }

                                        ColumnValue.append("'").append(value).append("'");
                                    }
                                } else {
                                    if("".equals(value)) {
                                        value = "0";
                                    }

                                    ColumnValue.append(value);
                                }
                            } else {
                                ColumnValue.append("'").append(value).append("'");
                            }
                        } else {
                            ColumnName.append("," + sqlRsmd.getColumnName(i));
                            if(1 != sqlRsmd.getColumnType(i) && 12 != sqlRsmd.getColumnType(i) && -1 != sqlRsmd.getColumnType(i)) {
                                if(5 != sqlRsmd.getColumnType(i) && 4 != sqlRsmd.getColumnType(i) && -5 != sqlRsmd.getColumnType(i) && 6 != sqlRsmd.getColumnType(i) && 8 != sqlRsmd.getColumnType(i) && 2 != sqlRsmd.getColumnType(i) && 3 != sqlRsmd.getColumnType(i)) {
                                    if(91 != sqlRsmd.getColumnType(i) && 92 != sqlRsmd.getColumnType(i) && 93 != sqlRsmd.getColumnType(i)) {
                                        if(2004 != sqlRsmd.getColumnType(i) && -1 != sqlRsmd.getColumnType(i) && -16 != sqlRsmd.getColumnType(i) && -2 != sqlRsmd.getColumnType(i) && -4 != sqlRsmd.getColumnType(i) && -3 != sqlRsmd.getColumnType(i)) {
                                            ColumnValue.append(value).append(",");
                                        } else {
                                            String ls_tmp = getBlob(ls_id, tableName, sqlRsmd.getColumnName(i), jdbcTemplate);
                                            ColumnValue.append(ls_tmp).append(",");
                                        }
                                    } else {
                                        if("".equals(value)) {
                                            value = "2000-01-01";
                                        }

                                        ColumnValue.append("'").append(value).append("',");
                                    }
                                } else {
                                    if("".equals(value)) {
                                        value = "0";
                                    }

                                    ColumnValue.append(value).append(",");
                                }
                            } else {
                                ColumnValue.append("'").append(value).append("'").append(",");
                            }
                        }
                    }

                    insertSQL(tableName, ColumnName, ColumnValue);
                    if(tableName.equals("cgform_head")) {
                        insertList.add("update cgform_head set is_dbsynch='N' where id='" + tableId + "';");
                    }
                }
            }
        }

    }

    public static void insertSQL(String tablename, StringBuffer ColumnName, StringBuffer ColumnValue) {
        StringBuffer insertSQL = new StringBuffer();
        insertSQL.append(insert).append(" ").append(tablename).append("(").append(ColumnName.toString()).append(")").append(values).append("(").append(ColumnValue.toString()).append(");");
        insertList.add(insertSQL.toString());
    }

    public static void generateXmlDataOutFlieContent(List<DBTable> dbTables, String parentDir) throws BusinessException {
        File file = new File(parentDir);
        if(!file.exists()) {
            buildFile(parentDir, true);
        }

        try {
            XStream xStream = new XStream();
            xStream.registerConverter(new NullConverter());
            xStream.processAnnotations(DBTable.class);
            FileOutputStream outputStream = new FileOutputStream(buildFile(parentDir + "/migrateExport.xml", false));
            Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
            xStream.toXML(dbTables, writer);
        } catch (Exception var6) {
            throw new BusinessException(var6.getMessage());
        }
    }

    public static String createFile(HttpServletRequest request, String ids) {
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/" + ids + "_migrate.sql";
        File file = new File(savePath);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var16) {
                logger.info("创建文件名失败！！");
                var16.printStackTrace();
            }
        }

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            if(insertList.size() > 0) {
                for(int i = 0; i < insertList.size(); ++i) {
                    bw.append((CharSequence)insertList.get(i));
                    bw.append("\n");
                }
            }
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return savePath;
    }

    public static String getBlob(String id, String tableName, final String columnName, JdbcTemplate jdbcTemplate) {
        String ls_sql = "select " + columnName + " from " + tableName + " where id='" + id + "'";
        jdbcTemplate.query(ls_sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                MigrateForm.inStream = rs.getBinaryStream(columnName);
            }
        });
        if(inStream == null) {
            return "0x00";
        } else {
            StringBuffer readInBuffer = new StringBuffer();
            readInBuffer.append("0x");
            byte[] b = new byte[4096];

            try {
                while(inStream.read(b) != -1) {
                    readInBuffer.append(byte2HexStr(b));
                }
            } catch (IOException var8) {
                var8.printStackTrace();
            }

            String ls_return = readInBuffer.toString().trim();
            if("0x".equals(ls_return)) {
                ls_return = ls_return + "00";
            }

            return ls_return;
        }
    }

    public static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < b.length && b[n] != 0; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if(stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }

    public static String zip(String zipFileName, String relativePath, String directory) throws FileNotFoundException, IOException {
        String fileName = zipFileName;
        if(zipFileName == null || zipFileName.trim().equals("")) {
            File temp = new File(directory);
            if(temp.isDirectory()) {
                fileName = directory + ".zip";
            } else if(directory.indexOf(".") > 0) {
                fileName = directory.substring(0, directory.lastIndexOf(".")) + ".zip";
            } else {
                fileName = directory + ".zip";
            }
        }

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName));

        try {
            zip(zos, relativePath, directory);
        } catch (IOException var9) {
            throw var9;
        } finally {
            if(zos != null) {
                zos.close();
            }

        }

        return fileName;
    }

    private static void zip(ZipOutputStream zos, String relativePath, String absolutPath) throws IOException {
        File file = new File(absolutPath);
        if(file.isDirectory()) {
            File[] files = file.listFiles();

            for(int i = 0; i < files.length; ++i) {
                File tempFile = files[i];
                if(tempFile.isDirectory()) {
                    String newRelativePath = relativePath + tempFile.getName() + File.separator;
                    createZipNode(zos, newRelativePath);
                    zip(zos, newRelativePath, tempFile.getPath());
                } else {
                    zipFile(zos, tempFile, relativePath);
                }
            }
        } else {
            zipFile(zos, file, relativePath);
        }

    }

    private static void zipFile(ZipOutputStream zos, File file, String relativePath) throws IOException {
        ZipEntry entry = new ZipEntry(relativePath + file.getName());
        zos.putNextEntry(entry);
        FileInputStream is = null;

        try {
            is = new FileInputStream(file);
            int BUFFERSIZE = 2048;
            int length = 0;
            byte[] buffer = new byte[BUFFERSIZE];

            //int length;
            while((length = is.read(buffer, 0, BUFFERSIZE)) >= 0) {
                zos.write(buffer, 0, length);
            }

            zos.flush();
            zos.closeEntry();
        } catch (IOException var11) {
            throw var11;
        } finally {
            if(is != null) {
                is.close();
            }

        }
    }

    private static void createZipNode(ZipOutputStream zos, String relativePath) throws IOException {
        ZipEntry zipEntry = new ZipEntry(relativePath);
        zos.putNextEntry(zipEntry);
        zos.closeEntry();
    }

    public static void unzip(String zipFilePath, String targetPath) throws IOException {
        OutputStream os = null;
        InputStream is = null;
        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile(zipFilePath);
            String directoryPath = "";
            if(targetPath != null && !"".equals(targetPath)) {
                directoryPath = targetPath;
            } else {
                directoryPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
            }

            Enumeration entryEnum = zipFile.getEntries();
            if(entryEnum != null) {
                ZipEntry zipEntry = null;

                while(true) {
                    while(entryEnum.hasMoreElements()) {
                        zipEntry = (ZipEntry)entryEnum.nextElement();
                        if(zipEntry.isDirectory()) {
                            directoryPath = directoryPath + File.separator + zipEntry.getName();
                            LogUtil.info(directoryPath);
                        } else if(zipEntry.getSize() <= 0L) {
                            buildFile(directoryPath + File.separator + zipEntry.getName(), true);
                        } else {
                            File targetFile = buildFile(directoryPath + File.separator + zipEntry.getName(), false);
                            os = new BufferedOutputStream(new FileOutputStream(targetFile));
                            is = zipFile.getInputStream(zipEntry);
                            byte[] buffer = new byte[4096];
                            boolean var10 = false;

                            int readLen;
                            while((readLen = is.read(buffer, 0, 4096)) >= 0) {
                                os.write(buffer, 0, readLen);
                            }

                            os.flush();
                            os.close();
                        }
                    }

                    return;
                }
            }
        } catch (IOException var14) {
            throw var14;
        } finally {
            if(zipFile != null) {
                zipFile = null;
            }

            if(is != null) {
                is.close();
            }

            if(os != null) {
                os.close();
            }

        }

    }

    public static File buildFile(String fileName, boolean isDirectory) {
        File target = new File(fileName);
        if(isDirectory) {
            target.mkdirs();
        } else if(!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
            target = new File(target.getAbsolutePath());
        }

        return target;
    }

    public static <T> String generateInsertSql(String tableName, Class<T> clazz, List<String> ignores) {
        StringBuffer insertSql = new StringBuffer("insert into " + tableName + "(");
        String tableField = "";
        String clazzProperties = "";
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
        PropertyDescriptor[] var10 = pds;
        int var9 = pds.length;

        for(int var8 = 0; var8 < var9; ++var8) {
            PropertyDescriptor pd = var10[var8];
            if((ignores == null || ignores.size() <= 0 || !ignores.contains(pd.getName())) && pd.getWriteMethod() != null) {
                if(tableField.length() > 0 && clazzProperties.length() > 0) {
                    tableField = tableField + ",";
                    clazzProperties = clazzProperties + ",";
                }

                tableField = tableField + underscoreName(pd.getName());
                clazzProperties = clazzProperties + ":" + pd.getName();
            }
        }

        insertSql.append(tableField);
        insertSql.append(") values(");
        insertSql.append(clazzProperties);
        insertSql.append(")");
        LogUtil.info("generate insertSql for " + clazz.getName() + ":" + insertSql.toString());
        return insertSql.toString();
    }

    public static <T> String generateUpdateSql(String tableName, Class<T> clazz, List<String> ignores) {
        StringBuffer updateSql = new StringBuffer("update " + tableName + " set ");
        String updateProperties = "";
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
        PropertyDescriptor[] var9 = pds;
        int var8 = pds.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            PropertyDescriptor pd = var9[var7];
            if((ignores == null || ignores.size() <= 0 || !ignores.contains(pd.getName())) && !pd.getName().toLowerCase().equals("id") && pd.getWriteMethod() != null) {
                if(updateProperties.length() > 0) {
                    updateProperties = updateProperties + ",";
                }

                updateProperties = updateProperties + underscoreName(pd.getName()) + "=:" + pd.getName();
            }
        }

        updateSql.append(updateProperties);
        updateSql.append(" where id=:id");
        LogUtil.info("generate updateSql for " + clazz.getName() + ":" + updateSql.toString());
        return updateSql.toString();
    }

    public static SqlParameterSource generateParameterMap(Object t, List<String> ignores) {
        Map<String, Object> paramMap = new HashMap();
        ReflectHelper reflectHelper = new ReflectHelper(t);
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(t.getClass());
        PropertyDescriptor[] var8 = pds;
        int var7 = pds.length;

        for(int var6 = 0; var6 < var7; ++var6) {
            PropertyDescriptor pd = var8[var6];
            if(ignores == null || !ignores.contains(pd.getName())) {
                paramMap.put(pd.getName(), reflectHelper.getMethodValue(pd.getName()));
            }
        }

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramMap);
        return sqlParameterSource;
    }

    private static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if(name != null && name.length() > 0) {
            result.append(name.substring(0, 1).toLowerCase());

            for(int i = 1; i < name.length(); ++i) {
                String s = name.substring(i, i + 1);
                if(s.equals(s.toUpperCase())) {
                    result.append("_");
                    result.append(s.toLowerCase());
                } else {
                    result.append(s);
                }
            }
        }

        return result.toString();
    }
}
