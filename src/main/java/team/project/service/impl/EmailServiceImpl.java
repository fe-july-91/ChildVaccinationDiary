package team.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.project.service.EmailService;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendPasswordReset(String emailTo, String resetPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject("Запит на скидання пароля.");
        message.setText("Ви надіслали запит на скидання пароля. "
                + "Ваш новий пароль: " + resetPassword
                + "\n\nБудь ласка, змініть цей пароль після авторизації!");
        mailSender.send(message);
    }
}
