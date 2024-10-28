package club.shukla.blogplatform.adaptors;

import club.shukla.blogplatform.model.BlogPost;
import club.shukla.blogplatform.model.BlogPostRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogPostAdaptor {
    public static BlogPost adapt(BlogPostRequest request) {
        return  BlogPost.builder()
                .tags(request.getTags())
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();
    }

}
