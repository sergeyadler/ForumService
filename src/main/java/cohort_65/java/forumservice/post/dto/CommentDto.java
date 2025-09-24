package cohort_65.java.forumservice.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    String user;
    String message;
    LocalDateTime dateCreated;
    Integer likes;
}
