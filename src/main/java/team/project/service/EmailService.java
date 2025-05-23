package team.project.service;

import team.project.dto.suppport.CreateEmailRequestDto;
import team.project.model.User;

public interface EmailService {
    void sendPasswordReset(String emailTo, String resetLink);

    void sendTokenConformation(User user, String urlWithToken);

    void sendEmailToSupport(CreateEmailRequestDto requestDto);
}
