package mate.academy.service;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRegistrationRequestDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.entity.User;
import mate.academy.mapper.UserMapper;
import mate.academy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        User user = userMapper.toModel(request);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
