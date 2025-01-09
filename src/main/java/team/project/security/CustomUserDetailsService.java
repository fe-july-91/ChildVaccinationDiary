package team.project.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import team.project.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
                String.format("Не вдається знайти користувача за електронною адресою %s", email)));
    }
}
