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
        message.setSubject("Password Reset Request");
        message.setText("You requested a password reset. "
                + "Your new password: " + resetPassword
                + "\n\nPlease change this password after login!");
        try {
            mailSender.send(message);
            System.out.println("Password reset email sent successfully to " + emailTo);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw new RuntimeException("Failed to send email.");
        }
    }
}