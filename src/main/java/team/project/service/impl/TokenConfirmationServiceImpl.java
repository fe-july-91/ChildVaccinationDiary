package team.project.service.impl;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.exception.EntityNotFoundCustomException;
import team.project.model.TokenConfirmation;
import team.project.model.User;
import team.project.repository.TokenConfirmationRepository;
import team.project.service.TokenConfirmationService;

@RequiredArgsConstructor
@Service
public class TokenConfirmationServiceImpl implements TokenConfirmationService {
    private final TokenConfirmationRepository tokenConfirmationRepo;

    @Override
    public TokenConfirmation getByToken(String token) {
        return tokenConfirmationRepo.findByToken(token).orElseThrow(
                () -> new EntityNotFoundCustomException(
                        String.format("Запису з токеном  %s не існує", token)));
    }

    @Transactional
    @Override
    public void deleteAllByUserId(Long userId) {
        tokenConfirmationRepo.deleteAllByUserId(userId);
    }

    @Transactional
    @Override
    public TokenConfirmation createToken(User user) {
        TokenConfirmation tokenConfirmation = new TokenConfirmation();
        tokenConfirmation.setUser(user);
        tokenConfirmation.setToken(UUID.randomUUID().toString());
        tokenConfirmation.setExpireDate(LocalDateTime.now().plusMinutes(15));
        return tokenConfirmationRepo.save(tokenConfirmation);
    }
}
