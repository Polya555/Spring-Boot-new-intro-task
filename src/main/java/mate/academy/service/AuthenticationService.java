package mate.academy.service;

import mate.academy.dto.UserLoginRequestDto;
import mate.academy.dto.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto login(UserLoginRequestDto request);
}
