package team.project.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
    private static final String MAIL_DEBUG = "mail.debug";

    @Bean(name = "gmailMailSender")
    public JavaMailSender gmailMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "onlineMailSender")
    public JavaMailSender onlineMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.online.ua");
        mailSender.setPort(465);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtps");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_SSL_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "mailUaMailSender")
    public JavaMailSender mailUaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ua");
        mailSender.setPort(465);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_SSL_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "ukrNetMailSender")
    public JavaMailSender ukrNetMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ukr.net");
        mailSender.setPort(465);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_SSL_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "iUaMailSender")
    public JavaMailSender iuamailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.i.ua");
        mailSender.setPort(465);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_SSL_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "metaUaMailSender")
    public JavaMailSender metaUaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.meta.ua");
        mailSender.setPort(465);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_SSL_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "yahooMailSender")
    public JavaMailSender yahooMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.yahoo.com");
        mailSender.setPort(587);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }

    @Bean(name = "outlookMailSender")
    public JavaMailSender outlookMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp-mail.outlook.com");
        mailSender.setPort(587);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
        props.put(MAIL_SMTP_AUTH, "true");
        props.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
        props.put(MAIL_DEBUG, "true");

        return mailSender;
    }
}

