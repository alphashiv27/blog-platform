package club.shukla.blogplatform.svc;

import club.shukla.blogplatform.model.BlogPost;
import club.shukla.blogplatform.model.BlogPostRequest;
import java.util.List;

public interface IBlogPlatformSvc {
    BlogPost createPost(BlogPostRequest blogPostRequest);

    BlogPost updatePost(Long id, BlogPostRequest blogPostRequest);

    void deletePost(Long id);

    BlogPost getPost(Long id);

    List<BlogPost> getPostsBySearchTerm(String term);
}
