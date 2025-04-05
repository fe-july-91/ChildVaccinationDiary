package team.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.project.model.User;
import team.project.service.EmailService;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Value("${jwt.expiration}")
    private long expiration;

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

    @Override
    public void sendTokenConformation(User user, String urlWithToken) {
        String mailContent = "<p> Вітаємо, " + user.getName() + "!</p>"
                + "<p>Підтвердіть, будь ласка, реєстрацію на kidty.com.ua,"
                + "перейшовши за цим посиланням: </p>"
                + "<a href=\"" + urlWithToken + "\">Я підтверждую реєстрації</a>"
                + "<p> Дякуємо, на все добре! </p>";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Підтвердження пошти");
        message.setText(mailContent);
        mailSender.send(message);
    }
}
