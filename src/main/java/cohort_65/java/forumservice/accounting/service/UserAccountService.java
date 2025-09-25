package cohort_65.java.forumservice.accounting.service;

import cohort_65.java.forumservice.accounting.dto.RolesResponseDto;
import cohort_65.java.forumservice.accounting.dto.UserAccountResponceDto;
import cohort_65.java.forumservice.accounting.dto.UserRegisterDto;
import cohort_65.java.forumservice.accounting.dto.UserUpdateDto;

public interface UserAccountService {
    UserAccountResponceDto addUser (UserRegisterDto userRegisterDto);
    UserAccountResponceDto getUser (String login);
    UserAccountResponceDto updateUser (String login, UserUpdateDto userUpdateDto);
    UserAccountResponceDto deleteUser (String login);

    RolesResponseDto addRole (String login, String role);
    RolesResponseDto deleteRole (String login, String role);

}
