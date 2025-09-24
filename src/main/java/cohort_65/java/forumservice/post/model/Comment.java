package cohort_65.java.forumservice.post.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"user", "dateCreated"})

public class Comment {
    @Setter
    String user;
    @Setter
    String message;
    LocalDateTime dateCreated = LocalDateTime.now();

    int likes;

    public Comment(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public void addlikes(){
        this.likes++;
    }
}



