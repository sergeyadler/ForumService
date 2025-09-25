package cohort_65.java.forumservice.accounting.service;

import cohort_65.java.forumservice.accounting.dao.UserAccountRepository;
import cohort_65.java.forumservice.accounting.dto.RolesResponseDto;
import cohort_65.java.forumservice.accounting.dto.UserAccountResponceDto;
import cohort_65.java.forumservice.accounting.dto.UserRegisterDto;
import cohort_65.java.forumservice.accounting.dto.UserUpdateDto;
import cohort_65.java.forumservice.accounting.dto.exception.UserNotFoundException;
import cohort_65.java.forumservice.accounting.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService{
    final UserAccountRepository userAccountRepository;
    final ModelMapper modelMapper;


    @Override
    public UserAccountResponceDto addUser(UserRegisterDto userRegisterDto) {
        UserAccount userAccount = modelMapper.map(userRegisterDto,UserAccount.class);
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount,UserAccountResponceDto.class);
    }

    @Override
    public UserAccountResponceDto getUser(String login) {
        return modelMapper.map(userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new),UserAccountResponceDto.class);
    }

    @Override
    public UserAccountResponceDto deleteUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccountRepository.delete(userAccount);
        return modelMapper.map(userAccount,UserAccountResponceDto.class);
    }

    @Override
    public UserAccountResponceDto updateUser(String login, UserUpdateDto userUpdateDto) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if(userUpdateDto.getFirstName() != null){
            userAccount.setFirstName(userUpdateDto.getFirstName());
        }
        if(userUpdateDto.getLastName() != null){
            userAccount.setLastName(userUpdateDto.getLastName());
        }
        userAccountRepository.save(userAccount);

        return modelMapper.map(userAccount,UserAccountResponceDto.class);
    }

    @Override
    public RolesResponseDto addRole(String login, String role) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccount.addRole(role.toUpperCase());
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, RolesResponseDto.class);
    }

    @Override
    public RolesResponseDto deleteRole(String login, String role) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccount.removeRole(role.toUpperCase());
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, RolesResponseDto.class);
    }
}
