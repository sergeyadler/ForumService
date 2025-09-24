package cohort_65.java.forumservice.post.controller;

import cohort_65.java.forumservice.post.dto.CommentDto;
import cohort_65.java.forumservice.post.dto.NewPostDto;
import cohort_65.java.forumservice.post.dto.PostDto;
import cohort_65.java.forumservice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping("post/{author}")
    public PostDto addNewPost(@RequestBody NewPostDto newPostDto, @PathVariable String author) {
        return postService.addNewPost(newPostDto, author);
    }

    @GetMapping("/posts")
    public List<PostDto> findAll() {
        return postService.findAll();
    }

    @GetMapping("post/{id}")
    public PostDto findPostById(@PathVariable String id) {
        return postService.findPostyid(id);
    }

    @PutMapping("post/{id}")
    public PostDto updatePostById(@PathVariable String id, @RequestBody NewPostDto updatePostDto) {
        return postService.updatePost(updatePostDto, id);
    }

    @DeleteMapping("post/{id}")
    public PostDto deletePostById(@PathVariable String id) {
        return postService.deletePost(id);
    }

    @PutMapping("post/{id}/like")

    public PostDto likePost(@PathVariable String id) {
        return postService.addLike(id);
    }

    @PutMapping("post/{id}/comment/{user}")

    public PostDto commentPost(@PathVariable String id, @PathVariable String user, @RequestBody CommentDto commentDto) {
        return postService.addComment(id, user, commentDto);
    }

    @GetMapping("posts/author/{user}")
    public List<PostDto> findPostsByAuthor(@PathVariable String user) {
        return postService.findPostsByAuthor(user);
    }
    @PostMapping("posts/tags")
    public List<PostDto> findPostsByTags(@RequestBody Set<String> tags) {
        return postService.findPostsByTags(tags);
    }
//    @PostMapping("posts/period")
//    public List<PostDto> findPostsByPeriod1(@RequestBody LocalDateTime from, @RequestBody LocalDateTime to) {
//        return postService.findPostsByPeriod(from, to);
//    }

    @PostMapping("/posts/period")
    public List<PostDto> findPostsByPeriod(@RequestBody Map<String, String> body) {
        String fromStr = body.get("dateFrom");
        String toStr   = body.get("dateTo");
        if (fromStr == null || toStr == null) return List.of();

        LocalDate fromDate = LocalDate.parse(fromStr); // "2020-08-25"
        LocalDate toDate   = LocalDate.parse(toStr);   // "2020-08-26"

        // включительно: [dateFrom 00:00:00, dateTo 23:59:59.999]
        LocalDateTime from = fromDate.atStartOfDay();
        LocalDateTime to   = toDate.atTime(23, 59, 59, 999_000_000);

        if (from.isAfter(to)) { var t = from; from = to; to = t; } // на всякий

        return postService.findPostsByPeriod(from, to);
    }

}
