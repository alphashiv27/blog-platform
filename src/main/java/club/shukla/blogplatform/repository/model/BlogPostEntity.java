package club.shukla.blogplatform.repository.model;

import club.shukla.blogplatform.repository.constants.BlogPostConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = BlogPostConstants.BLOG_POST_COLLECTION)
public class BlogPostEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1127094707307894536L;

    @Id
    private Long id;
    private String title;
    private String content;
    private String category;
    private List<String> tags;
    private Long createdAt;
    private Long updatedAt;

}
