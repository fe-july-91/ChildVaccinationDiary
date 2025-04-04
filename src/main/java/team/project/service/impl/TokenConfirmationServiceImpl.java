package team.project.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.exception.EntityNotFoundCustomException;
import team.project.model.TokenConfirmation;
import team.project.repository.TokenConfirmationRepository;
import team.project.service.TokenConfirmationService;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenConfirmationServiceImpl implements TokenConfirmationService {
    private final TokenConfirmationRepository tokenConfirmationRepo;

    public TokenConfirmation getByToken(String token) {
        return tokenConfirmationRepo.findByToken(token).orElseThrow(
                () -> new EntityNotFoundCustomException(
                        String.format("Запису з токеном  %s не існує", token)));
    }

    @Transactional
    @Override
    public TokenConfirmation createToken(String email) {
        TokenConfirmation tokenConfirmation = new TokenConfirmation();
        tokenConfirmation.setEmail(email);
        tokenConfirmation.setToken(UUID.randomUUID().toString());
        tokenConfirmation.setExpireDate(LocalDateTime.now().plusMinutes(15));
        return tokenConfirmationRepo.save(tokenConfirmation);
    }
}
