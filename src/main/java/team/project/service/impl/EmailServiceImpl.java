package team.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.project.dto.suppport.CreateEmailRequestDto;
import team.project.model.User;
import team.project.service.EmailService;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String ownEmail;

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
                Підтвердіть, будь ласка, реєстрацію  у вебдодатку KIDTY.
                Для цього перейдіть за посиланням: %s
                Дякуємо!""", user.getName(), urlWithToken));
        mailSender.send(message);
    }

    @Override
    public void sendEmailToSupport(CreateEmailRequestDto requestDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(ownEmail);
        message.setSubject("Повідомлення для служби підтримки");
        message.setText(String.format("""
                Name = %s
                Email = %s
                Message: %s""", requestDto.name(), requestDto.email(), requestDto.message()));
        mailSender.send(message);
    }
}
