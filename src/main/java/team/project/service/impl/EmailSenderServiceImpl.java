package team.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.project.model.User;
import team.project.service.EmailSenderService;

@RequiredArgsConstructor
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendMail(User toUser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toUser.getEmail());
        message.setReplyTo(fromEmail);
        message.setSubject("Confirm registration");
        message.setText(generateBody(toUser.getName()));
        mailSender.send(message);
    }

    /* @Async
    public void sendEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Confirm registration");
        message.setText(generateBody(name));
        message.setFrom("hello@kidty.vaccination");
        mailSender.send(message);
    }*/

    private String generateBody(String name) {
        return String.format("Hello, %s!\n You received that letter because "
                + "you are registering in the vaccination application.", name);
    }
}
