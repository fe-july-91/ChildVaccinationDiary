package team.project.service;

public interface EmailService {
    void sendPasswordReset(String emailTo, String resetLink);
}
