package team.project.service;

import team.project.model.TokenConfirmation;

public interface TokenConfirmationService {
    TokenConfirmation createToken(String email);
}
