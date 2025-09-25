package cohort_65.java.forumservice.post.service;

import cohort_65.java.forumservice.post.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface PostService {

    PostDto addNewPost(NewPostDto newPostDto, String author);
    List<PostDto> findAll();
    PostDto findPostyid (String id);
    PostDto updatePost(NewPostDto updatePostDto,String id);
    PostDto deletePost(String id);


    void addLike (String id);
    PostDto addComment(String id,String user, NewCommentDto commentDto);

    List<PostDto> findPostsByAuthor(String author);
    List<PostDto> findPostsByTags(Set<String> tags);
    List<PostDto> findPostsByPeriod (DataPeriodDto dataPeriodDto);


}
