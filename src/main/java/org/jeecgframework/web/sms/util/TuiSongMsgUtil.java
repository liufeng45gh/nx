//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.sms.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.sms.entity.TSSmsEntity;
import org.jeecgframework.web.sms.entity.TSSmsSqlEntity;
import org.jeecgframework.web.sms.entity.TSSmsTemplateEntity;
import org.jeecgframework.web.sms.entity.TSSmsTemplateSqlEntity;
import org.jeecgframework.web.sms.service.TSSmsServiceI;
import org.jeecgframework.web.sms.service.TSSmsSqlServiceI;
import org.jeecgframework.web.sms.service.TSSmsTemplateServiceI;
import org.jeecgframework.web.sms.service.TSSmsTemplateSqlServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class TuiSongMsgUtil {
    private static TSSmsServiceI tSSmsService;
    private static SystemService systemService;
    private static TSSmsTemplateSqlServiceI tSSmsTemplateSqlService;
    private static TSSmsTemplateServiceI tSSmsTemplateService;
    private static TSSmsSqlServiceI tSSmsSqlService;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static Configuration configuration;

    public TuiSongMsgUtil() {
    }

    public static String sendMessage(String title, String msgType, String code, Map<String, Object> map, String sentTo) {
        try {
            TSSmsEntity tss = new TSSmsEntity();
            tss.setEsType(msgType);
            tss.setEsTitle(title);
            tss.setEsReceiver(sentTo);
            tss.setEsStatus("1");
            String hql = "from TSSmsTemplateSqlEntity as tempSql where tempSql.code=? ";
            String smsContent = "";
            List<TSSmsTemplateSqlEntity> tssmsTemplateSqlList = getTssmsTemplateSqlInstance().findHql(hql, new Object[]{code});

            StringWriter stringWriter;
            for(Iterator var10 = tssmsTemplateSqlList.iterator(); var10.hasNext(); smsContent = stringWriter.toString()) {
                TSSmsTemplateSqlEntity tsSmsTemplateSqlEntity = (TSSmsTemplateSqlEntity)var10.next();
                TSSmsSqlEntity tsSmsSqlEntity = (TSSmsSqlEntity)getTSSmsServiceInstance().getEntity(TSSmsSqlEntity.class, tsSmsTemplateSqlEntity.getSqlId());
                String templateSql = tsSmsSqlEntity.getSqlContent();
                TSSmsTemplateEntity tsSmsTemplateEntity = (TSSmsTemplateEntity)getTSSmsServiceInstance().getEntity(TSSmsTemplateEntity.class, tsSmsTemplateSqlEntity.getTemplateId());
                String templateContent = tsSmsTemplateEntity.getTemplateContent();
                Map<String, Object> rootMap = getRootMapBySql(templateSql, map);
                StringReader strR = new StringReader(templateContent);
                Template template = new Template("strTemplate", strR, new Configuration());
                stringWriter = new StringWriter();
                BufferedWriter writer = new BufferedWriter(stringWriter);
                template.process(rootMap, writer);
            }

            tss.setEsContent(smsContent);
            getTSSmsServiceInstance().save(tss);
            return "success";
        } catch (Exception var20) {
            var20.printStackTrace();
            return var20.getMessage();
        }
    }

    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        if(namedParameterJdbcTemplate == null) {
            namedParameterJdbcTemplate = (NamedParameterJdbcTemplate)ApplicationContextUtil.getContext().getBean(NamedParameterJdbcTemplate.class);
        }

        return namedParameterJdbcTemplate;
    }

    public static Configuration getConfiguration() {
        if(configuration == null) {
            configuration = (Configuration)ApplicationContextUtil.getContext().getBean(Configuration.class);
        }

        return configuration;
    }

    public static Map<String, Object> getRootMapBySql(String templateSql, Map<String, Object> map) {
        SqlParameterSource sqlp = new MapSqlParameterSource(map);
        return getNamedParameterJdbcTemplate().queryForMap(templateSql, sqlp);
    }

    public static String getTemplateSql(String sqlId) {
        String hql = "from TSSmsSqlEntity as tssSql where tssSql.id=?";
        List<TSSmsSqlEntity> tssmsSqlList = getTSSmsSqlInstance().findHql(hql, new Object[]{sqlId});
        String sqlContent = "";

        TSSmsSqlEntity tsSmsSqlEntity;
        for(Iterator var5 = tssmsSqlList.iterator(); var5.hasNext(); sqlContent = tsSmsSqlEntity.getSqlContent()) {
            tsSmsSqlEntity = (TSSmsSqlEntity)var5.next();
        }

        return sqlContent;
    }

    public static String getTemplateContent(String templateId) {
        String hql = "from TSSmsTemplateEntity as template where template.id=? ";
        List<TSSmsTemplateEntity> tSSmsTemplateList = getTssmsTemplateInstance().findHql(hql, new Object[]{templateId});
        String templateConetent = "";

        TSSmsTemplateEntity tsSmsTemplateEntity;
        for(Iterator var5 = tSSmsTemplateList.iterator(); var5.hasNext(); templateConetent = tsSmsTemplateEntity.getTemplateContent()) {
            tsSmsTemplateEntity = (TSSmsTemplateEntity)var5.next();
        }

        return templateConetent;
    }

    public static TSSmsServiceI getTSSmsServiceInstance() {
        if(tSSmsService == null) {
            tSSmsService = (TSSmsServiceI)ApplicationContextUtil.getContext().getBean(TSSmsServiceI.class);
        }

        return tSSmsService;
    }

    public static TSSmsTemplateSqlServiceI getTssmsTemplateSqlInstance() {
        if(tSSmsTemplateSqlService == null) {
            tSSmsTemplateSqlService = (TSSmsTemplateSqlServiceI)ApplicationContextUtil.getContext().getBean(TSSmsTemplateSqlServiceI.class);
        }

        return tSSmsTemplateSqlService;
    }

    public static TSSmsTemplateServiceI getTssmsTemplateInstance() {
        if(tSSmsTemplateService == null) {
            tSSmsTemplateService = (TSSmsTemplateServiceI)ApplicationContextUtil.getContext().getBean(TSSmsTemplateServiceI.class);
        }

        return tSSmsTemplateService;
    }

    public static TSSmsSqlServiceI getTSSmsSqlInstance() {
        if(tSSmsSqlService == null) {
            tSSmsSqlService = (TSSmsSqlServiceI)ApplicationContextUtil.getContext().getBean(TSSmsSqlServiceI.class);
        }

        return tSSmsSqlService;
    }
}
