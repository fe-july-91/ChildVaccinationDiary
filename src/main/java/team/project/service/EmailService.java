package team.project.service;

public interface EmailService {
    void sendPasswordReset(String emailTo, String resetLink);

    void sendTokenConformation(String emailTo, String token);
}
