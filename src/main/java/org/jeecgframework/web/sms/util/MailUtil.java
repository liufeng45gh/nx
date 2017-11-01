//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.sms.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
    public MailUtil() {
    }

    public static void sendEmail(String smtpHost, String receiver, String title, String content, String sender, String user, String pwd) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.host", smtpHost);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        Session s = Session.getDefaultInstance(props);
        s.setDebug(true);
        MimeMessage message = new MimeMessage(s);
        InternetAddress from = new InternetAddress(sender);
        message.setFrom(from);
        InternetAddress to = new InternetAddress(receiver);
        message.setRecipient(RecipientType.TO, to);
        message.setSubject(title);
        message.setSentDate(new Date());
        BodyPart mdp = new MimeBodyPart();
        mdp.setContent(content, "text/html;charset=gb2312");
        Multipart mm = new MimeMultipart();
        mm.addBodyPart(mdp);
        message.setContent(mm);
        message.saveChanges();
        Transport transport = s.getTransport("smtp");
        transport.connect(smtpHost, user, pwd);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendEmail(String smtpHost, String receiver, String copy, String title, String content, String sender, String user, String pwd) throws Exception {
        Properties props = new Properties();
        props.put("mail.host", smtpHost);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        Session s = Session.getDefaultInstance(props);
        s.setDebug(true);
        MimeMessage message = new MimeMessage(s);
        InternetAddress from = new InternetAddress(sender);
        message.setFrom(from);
        String[] receivers = receiver.split(",");
        InternetAddress[] to = new InternetAddress[receivers.length];

        for(int i = 0; i < receivers.length; ++i) {
            to[i] = new InternetAddress(receivers[i]);
        }

        message.setRecipients(RecipientType.TO, to);
        String[] copys = copy.split(",");
        InternetAddress[] cc = new InternetAddress[copys.length];

        for(int i = 0; i < copys.length; ++i) {
            cc[i] = new InternetAddress(copys[i]);
        }

        message.setRecipients(RecipientType.CC, cc);
        message.setSubject(title);
        message.setSentDate(new Date());
        BodyPart mdp = new MimeBodyPart();
        mdp.setContent(content, "text/html;charset=gb2312");
        Multipart mm = new MimeMultipart();
        mm.addBodyPart(mdp);
        message.setContent(mm);
        message.saveChanges();
        Transport transport = s.getTransport("smtp");
        transport.connect(smtpHost, user, pwd);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) {
        try {
            sendEmail("smtp.163.com", "418799587@qq.com", "系统测试邮件", "hi,all,I am AnChao!111", "tjrzlm@163.com", "tjrzlm", "tj123456");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
