package Blog.api.demo.Mapper;

import Blog.api.demo.Dto.UserDto;
import Blog.api.demo.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
