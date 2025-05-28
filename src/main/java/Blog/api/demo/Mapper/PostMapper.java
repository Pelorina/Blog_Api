package Blog.api.demo.Mapper;

import Blog.api.demo.Dto.PostDto;
import Blog.api.demo.Model.Post;
import Blog.api.demo.Model.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        dto.setCategory(post.getCategory());
        dto.setUserId(post.getUser().getId());
        return dto;
    }

    public Post toEntity(PostDto dto, User user) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        post.setCategory(dto.getCategory());
        post.setUser(user);
        return post;
    }
}
