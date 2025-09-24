package cohort_65.java.forumservice.post.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Document(collection = "post")
@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor
public class Post {

    String id;
    @Setter
    String title;
    @Setter
    String content;
    @Setter
    String author;
    Set<String> tags = new HashSet<String>();
    LocalDateTime dateCreated =  LocalDateTime.now();
    int likes = 0;
    List<Comment> comments = new ArrayList<Comment>();

    public Post(String title, String content, String author, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
    }

    public void addLikes() {
        this.likes++;
    }

    public boolean addTag(String tag) {
        return tags.add(tag);

    }
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }
    public boolean removeComment(Comment comment) {
        return comments.remove(comment);
    }
}
