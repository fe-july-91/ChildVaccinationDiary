package team.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import team.project.exception.EntityNotFoundCustomException;
import team.project.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundCustomException {
        return userRepo.findByEmail(email).orElseThrow(() -> new EntityNotFoundCustomException(
                String.format("Не вдається знайти користувача за електронною адресою %s", email)));
    }
}
