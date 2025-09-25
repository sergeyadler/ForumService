package cohort_65.java.forumservice.accounting.dto;

import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccountResponceDto {
    String login;
    String firstName;
    String lastName;
    @Singular
    Set<String> roles;
}
