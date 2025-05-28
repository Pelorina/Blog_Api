package Blog.api.demo.Service;
import Blog.api.demo.Dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, String creatorEmail);

    PostDto getPostById(Long id);

    List<PostDto> getAllPosts(); // Only approved posts

    PostDto updatePost(Long id, PostDto postDto, String editorEmail);

    void deletePost(Long id, String userEmail);

    List<PostDto> getPendingApprovalPosts();

    PostDto approvePost(Long id);

    Page<PostDto> getPaginatedPosts(Pageable pageable);
}

