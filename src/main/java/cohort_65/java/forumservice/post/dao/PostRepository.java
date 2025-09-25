package cohort_65.java.forumservice.post.dao;

import cohort_65.java.forumservice.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PostRepository extends MongoRepository<Post,String> {
    List<Post> findAllByAuthor(String author);
    List<Post> findAllByTagsIn(Set<String> tags);
    List<Post> findPostByDateCreatedBetween(LocalDate from, LocalDate to);
   // List<Post> findAllByDateCreatedBetweenOrderByDateCreatedDesc(LocalDate from, LocalDate to);

}
