package Blog.api.demo.Dto;

import Blog.api.demo.Model.Category;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String author;
    private Category category;
    private Long userId;


}
