package Blog.api.demo.Service;

import Blog.api.demo.Dto.UserDto;
import Blog.api.demo.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    Optional<UserDto> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
