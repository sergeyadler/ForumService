package cohort_65.java.forumservice.post.service;

import cohort_65.java.forumservice.post.dao.PostRepository;
import cohort_65.java.forumservice.post.dto.*;
import cohort_65.java.forumservice.post.dto.exception.PostNotFoundexeption;
import cohort_65.java.forumservice.post.model.Comment;
import cohort_65.java.forumservice.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;
    final ModelMapper modelMapper;

    @Override
    public PostDto addNewPost(NewPostDto newPostDto, String author) {
        Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author, newPostDto.getTags());
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public PostDto findPostyid(String id) {
        return modelMapper.map(postRepository.findById(id), PostDto.class);
    }

    @Override
    public PostDto updatePost(NewPostDto updatePostDto, String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundexeption::new);
        if (updatePostDto.getTitle() != null) {
            post.setTitle(updatePostDto.getTitle());
        }
        if (updatePostDto.getContent() != null) {
            post.setContent(updatePostDto.getContent());
        }
        Set<String> tags = updatePostDto.getTags();
        if (tags != null) {
            tags.forEach(post::addTag);
        }
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto deletePost(String id) {
       Post post = postRepository.findById(id).orElseThrow(PostNotFoundexeption::new);
       postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundexeption::new);
        post.addLikes();
        postRepository.save(post);
    }

    @Override
    public PostDto addComment(String id, String user, NewCommentDto commentDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundexeption::new);
        post.addComment(new Comment(user,commentDto.getMessage()));
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostsByAuthor(String author) {
        return postRepository.findAllByAuthor(author).stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }


    @Override
    public List<PostDto> findPostsByTags(Set<String> tags) {
        return postRepository.findAllByTagsIn(tags).stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public List<PostDto> findPostsByPeriod(DataPeriodDto dataPeriodDto) {
        return postRepository.findPostByDateCreatedBetween(dataPeriodDto.getDateFrom(),dataPeriodDto.getDateTo()).stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }
}
