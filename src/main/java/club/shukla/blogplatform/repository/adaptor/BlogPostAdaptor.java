package club.shukla.blogplatform.repository.adaptor;

import club.shukla.blogplatform.model.BlogPost;
import club.shukla.blogplatform.repository.model.BlogPostEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogPostAdaptor {

    public static BlogPostEntity adapt(BlogPost blogPost) {
        return BlogPostEntity.builder()
                .title(blogPost.getTitle())
                .content(blogPost.getContent())
                .tags(blogPost.getTags())
                .category(blogPost.getCategory())
                .build();
    }

    public static BlogPost adapt(BlogPostEntity blogPostEntity) {
        return BlogPost.builder()
                .id(blogPostEntity.getId())
                .title(blogPostEntity.getTitle())
                .content(blogPostEntity.getContent())
                .tags(blogPostEntity.getTags())
                .category(blogPostEntity.getCategory())
                .createdAt(blogPostEntity.getCreatedAt())
                .updatedAt(blogPostEntity.getUpdatedAt())
                .build();
    }
}
