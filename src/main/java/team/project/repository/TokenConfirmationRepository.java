package team.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.TokenConfirmation;

public interface TokenConfirmationRepository extends JpaRepository<TokenConfirmation, Long> {

    Optional<TokenConfirmation> findByToken(String token);

    Optional<TokenConfirmation> findByUserId(Long userId);
}
