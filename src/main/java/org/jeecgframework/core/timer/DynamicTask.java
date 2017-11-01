//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.timer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class DynamicTask {
    private static Logger logger = Logger.getLogger(DynamicTask.class);
    @Resource
    private Scheduler schedulerFactory;

    public DynamicTask() {
    }

    public boolean startOrStop(String triggerName, boolean start) {
        try {
            CronTrigger trigger = (CronTrigger)this.getTrigger(triggerName, "DEFAULT");
            if(start) {
                this.schedulerFactory.resumeTrigger(trigger.getName(), trigger.getGroup());
                logger.info("trigger the start successfully!!");
            } else {
                this.schedulerFactory.pauseTrigger(trigger.getName(), trigger.getGroup());
                logger.info("trigger the pause successfully!!");
            }

            return true;
        } catch (SchedulerException var4) {
            logger.error("Fail to reschedule. " + var4);
            return false;
        }
    }

    public boolean updateCronExpression(String triggerName, String cronExpression) {
        try {
            CronTrigger trigger = (CronTrigger)this.getTrigger(triggerName, "DEFAULT");
            if(trigger == null) {
                return false;
            } else if(StringUtils.equals(trigger.getCronExpression(), cronExpression)) {
                logger.info("cronExpression is same with the running Schedule , no need to update.");
                return true;
            } else {
                trigger.setCronExpression(cronExpression);
                this.schedulerFactory.rescheduleJob(trigger.getName(), trigger.getGroup(), trigger);
                updateSpringMvcTaskXML(trigger, cronExpression);
                logger.info("Update the cronExpression successfully!!");
                return true;
            }
        } catch (ParseException var4) {
            logger.error("The new cronExpression - " + cronExpression + " not conform to the standard. " + var4);
            return false;
        } catch (SchedulerException var5) {
            logger.error("Fail to reschedule. " + var5);
            return false;
        }
    }

    private Trigger getTrigger(String triggerName, String groupName) {
        Trigger trigger = null;
        if(StringUtils.isBlank(groupName)) {
            logger.warn("Schedule Job Group is empty!");
            return null;
        } else if(StringUtils.isBlank(triggerName)) {
            logger.warn("Schedule trigger Name is empty!");
            return null;
        } else {
            try {
                trigger = this.schedulerFactory.getTrigger(triggerName, groupName);
            } catch (SchedulerException var5) {
                logger.warn("Fail to get the trigger (triggerName: " + triggerName + ", groupName : " + groupName + ")");
                return null;
            }

            if(trigger == null) {
                logger.warn("Can not found the trigger of triggerName: " + triggerName + ", groupName : " + groupName);
            }

            return trigger;
        }
    }

    public static synchronized void updateSpringMvcTaskXML(CronTrigger trigger, String cronExpression) {
        Document document = null;
        File file = null;
        SAXReader saxReader = new SAXReader();

        try {
            URI url = DynamicTask.class.getClassLoader().getResource("spring-mvc-timeTask.xml").toURI();
            file = new File(url.getPath());
            document = saxReader.read(new FileInputStream(file));
        } catch (Exception var20) {
            logger.error("读取系统中用到的SQL 语句XML出错");
            throw new RuntimeException("---------读取spring-mvc-timeTask.xml文件出错:" + var20.getMessage());
        }

        Element root = document.getRootElement();
        List<Element> beans = root.elements();
        Iterator var8 = beans.iterator();

        label125:
        while(var8.hasNext()) {
            Element bean = (Element)var8.next();
            if(bean.attribute("id") != null && bean.attribute("id").getValue().equals(trigger.getName())) {
                beans = bean.elements();
                Iterator var10 = beans.iterator();

                Element temp;
                do {
                    if(!var10.hasNext()) {
                        break label125;
                    }

                    temp = (Element)var10.next();
                } while(temp.attribute("name") == null || !temp.attribute("name").getValue().equals("cronExpression"));

                temp.attribute("value").setValue(cronExpression);
                break;
            }
        }

        XMLWriter fileWriter = null;

        try {
            OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
            xmlFormat.setEncoding("utf-8");
            fileWriter = new XMLWriter(new FileOutputStream(file), xmlFormat);
            fileWriter.write(document);
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

    }
}
