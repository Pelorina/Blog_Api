package Blog.api.demo.Service;
import Blog.api.demo.Dto.PostDto;
import Blog.api.demo.Dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostDto createPost(PostDto postDto, String creatorEmail);

    PostDto getPostById(Long id);

    List<PostDto> getAllPosts();

    PostDto updatePost(Long id, PostDto postDto, String editorEmail);

    void deletePost(Long id, String userEmail);

    List<PostDto> getPendingApprovalPosts();

    PostDto approvePost(Long id);
}

