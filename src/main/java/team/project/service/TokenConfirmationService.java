package team.project.service;

import team.project.model.TokenConfirmation;
import team.project.model.User;

public interface TokenConfirmationService {
    TokenConfirmation createToken(User user);

    TokenConfirmation getByToken(String token);

    void deleteAllByUserId(Long id);
}
