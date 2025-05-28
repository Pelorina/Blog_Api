package Blog.api.demo.Service;

import Blog.api.demo.Dto.PostDto;
import Blog.api.demo.Dto.UserDto;
import Blog.api.demo.Exceptions.ResourceNotFoundException;
import Blog.api.demo.Mapper.PostMapper;
import Blog.api.demo.Model.Post;
import Blog.api.demo.Model.Role.RoleName;
import Blog.api.demo.Model.User;
import Blog.api.demo.Repository.PostRepository;
import Blog.api.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto, String creatorEmail) {
        User user = userRepository.findByEmail(creatorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Post post = postMapper.toEntity(postDto, user);

        // Approval logic: external users need approval
        boolean isApprover = user.hasRole(RoleName.USER) || user.hasRole(RoleName.ADMIN);
        post.setApproved(isApprover);


        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findByIdAndApprovedTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Approved post not found with ID: " + id));
        return postMapper.toDto(post);
    }


    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAllByApprovedTrue().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto, String editorEmail) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        User editor = userRepository.findByEmail(editorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setTitle(postDto.getTitle());
        existing.setContent(postDto.getContent());
        existing.setCategory(postDto.getCategory());

        // Require re-approval for external users
        if (!editor.getRoles().equals("EMPLOYEE")) {
            existing.setApproved(false);
        }

        return postMapper.toDto(postRepository.save(existing));
    }

    @Override
    public void deletePost(Long id, String userEmail) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        if (!user.getRoles().equals("ADMIN")) {
            post.setApproved(false); // Flag for admin re-approval
            postRepository.save(post);
        } else {
            postRepository.delete(post);
        }
    }

    @Override
    public List<PostDto> getPendingApprovalPosts() {
        return postRepository.findAllByApprovedFalse().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto approvePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();
        post.setApproved(true);
        return postMapper.toDto(postRepository.save(post));
    }


}

