package cohort_65.java.forumservice.accounting.dto;

import lombok.*;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolesResponseDto {
    String login;
    @Singular
    Set<String> roles;
}
