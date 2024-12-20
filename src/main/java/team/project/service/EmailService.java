package team.project.service;

import jakarta.mail.MessagingException;
import team.project.exception.EmailFormatException;

public interface EmailService {
    void sendPasswordReset(String emailTo, String resetLink)
            throws EmailFormatException, MessagingException;
}
