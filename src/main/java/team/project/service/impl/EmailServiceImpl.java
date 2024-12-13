package team.project.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.exception.EmailFormatException;
import team.project.service.EmailService;
import team.project.smtp.MultiMailService;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final MultiMailService mailService;

    @Override
    public void sendPasswordReset(String emailTo, String resetLink)
            throws EmailFormatException, MessagingException {
        String subject = "Password Reset Request";
        String text = "<p>To reset your password, please click the following link:</p>"
                + "<a href=\"" + resetLink + "\">Reset Password</a>";
        mailService.sendEmail(emailTo, subject, text, getProvider(emailTo));
    }

    private String getProvider(String email) throws EmailFormatException {
        int atIndex = email.indexOf('@');
        int lastDotIndex = email.lastIndexOf('.');
        if (atIndex != -1 && lastDotIndex != -1 && atIndex < lastDotIndex) {
            return email.substring(atIndex + 1, lastDotIndex);
        } else {
            throw new EmailFormatException("Email format is incorrect");
        }
    }
}
