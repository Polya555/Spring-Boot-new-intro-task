package mate.academy.service;

import lombok.RequiredArgsConstructor;
import mate.academy.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email is not found", username)
                ));
    }
}
