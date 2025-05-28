package Blog.api.demo.Repository;

import Blog.api.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String author);

    List<Post> findAllByApprovedTrue();

    Optional<Post> findByIdAndApprovedTrue(Long id);

    List<Post> findAllByApprovedFalse();
}

