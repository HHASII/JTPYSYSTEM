package com.capgemini.jtp.utils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    public static boolean SendEmail(String address, String content) {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");

        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");

        // 设置邮件服务器主机名 使用126邮箱发送
        props.setProperty("mail.host", "smtp.126.com");

        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        try {
            // 设置邮件主题
            msg.setSubject("JTP系统邮箱验证");

            // 设置消息头
            msg.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");

            // 设置邮件内容
            msg.setText(content);
            // 设置发件人
            msg.setFrom(new InternetAddress("CENTERTREE@126.com"));

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect("CENTERTREE@126.com", "123CENTERTREE");

            // 发送邮件
            transport.sendMessage(msg, new Address[] { new InternetAddress(address) });

            // 关闭连接
            transport.close();
            System.out.println("发送邮件成功");
            System.out.println("++"+content+"++");

            return true;
        } catch (MessagingException e) {

            e.printStackTrace();

            return false;
        }


    }
}
