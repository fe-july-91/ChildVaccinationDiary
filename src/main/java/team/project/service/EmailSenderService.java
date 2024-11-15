package team.project.service;

import team.project.model.User;

public interface EmailSenderService {
    void sendMail(User toUser);
}
