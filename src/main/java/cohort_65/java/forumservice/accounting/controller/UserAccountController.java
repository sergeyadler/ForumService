package cohort_65.java.forumservice.accounting.controller;

import cohort_65.java.forumservice.accounting.dto.RolesResponseDto;
import cohort_65.java.forumservice.accounting.dto.UserAccountResponceDto;
import cohort_65.java.forumservice.accounting.dto.UserRegisterDto;
import cohort_65.java.forumservice.accounting.dto.UserUpdateDto;
import cohort_65.java.forumservice.accounting.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {

    final UserAccountService userAccountService;

    @PostMapping("/register")
    public UserAccountResponceDto register(@RequestBody UserRegisterDto userRegisterDto){
        return userAccountService.addUser(userRegisterDto);
    }
    @GetMapping("/user/{login}")
    public UserAccountResponceDto getUser(@PathVariable String login){
        return userAccountService.getUser(login);
    }


    @DeleteMapping("/user/{login}")
    public UserAccountResponceDto removeUser(@PathVariable String login){
        return userAccountService.deleteUser(login);
    }

    @PutMapping("/user/{login}")
    public UserAccountResponceDto updateUser (@PathVariable String login, @RequestBody UserUpdateDto userUpdateDto){
        return userAccountService.updateUser(login,userUpdateDto);
    }

    @PutMapping("/user/{login}/role/{role}")
    public RolesResponseDto addRole(@PathVariable String login, @PathVariable String role) {
        return userAccountService.addRole(login, role);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public RolesResponseDto removeRole(@PathVariable String login, @PathVariable String role) {
        return userAccountService.deleteRole(login, role);
    }
}
