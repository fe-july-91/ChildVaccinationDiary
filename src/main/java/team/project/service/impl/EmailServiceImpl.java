package team.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.project.model.User;
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
        message.setText(String.format("""
               Ви надіслали запит на скидання пароля. "
               Ваш новий пароль: %s
               Будь ласка, змініть цей пароль після авторизації!""", resetPassword));
        mailSender.send(message);
    }

    @Override
    public void sendTokenConformation(User user, String urlWithToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Підтвердження пошти при реєстрації");
        message.setText(String.format("""
                Вітаємо, %s!
                Підтвердіть, будь ласка, реєстрацію на kidty.com.ua.
                Для цього перейдіть за посиланням: %s
                Дякуємо!""", user.getName(), urlWithToken));
        mailSender.send(message);
    }
}
