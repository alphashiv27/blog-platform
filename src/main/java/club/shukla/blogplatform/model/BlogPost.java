package club.shukla.blogplatform.model;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost extends BlogPostRequest{
    @Serial
    private static final long serialVersionUID = 634473572560545370L;

    private Long id;
    private Long createdAt;
    private Long updatedAt;
}
