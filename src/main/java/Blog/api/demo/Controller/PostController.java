package Blog.api.demo.Controller;

import Blog.api.demo.Dto.PostDto;
import Blog.api.demo.Service.PostService;
import Blog.api.demo.Service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }


    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody  PostDto postDto,
                                              @RequestHeader("X-User-Email") String email) {
        return new ResponseEntity<>(postService.createPost(postDto, email), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id,
                                              @RequestBody PostDto postDto,
                                              @RequestHeader("X-User-Email") String email) {
        return ResponseEntity.ok(postService.updatePost(id, postDto, email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @RequestHeader("X-User-Email") String email) {
        postService.deletePost(id, email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/pending")
    public ResponseEntity<List<PostDto>> getPendingPosts() {
        return ResponseEntity.ok(postService.getPendingApprovalPosts());
    }

    @PutMapping("/admin/approve/{id}")
    public ResponseEntity<PostDto> approvePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.approvePost(id));
    }
}

