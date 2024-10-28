package club.shukla.blogplatform.svc.impl;

import club.shukla.blogplatform.adaptors.BlogPostAdaptor;
import club.shukla.blogplatform.model.BlogPost;
import club.shukla.blogplatform.model.BlogPostRequest;
import club.shukla.blogplatform.repository.IBlogPostDao;
import club.shukla.blogplatform.svc.IBlogPlatformSvc;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogPlatformSvcImpl implements IBlogPlatformSvc {
    private final IBlogPostDao blogPostDao;

    @Override
    public BlogPost createPost(BlogPostRequest blogPostRequest) {
        BlogPost blogPost = BlogPostAdaptor.adapt(blogPostRequest);
        return blogPostDao.save(blogPost);
    }

    @Override
    public BlogPost updatePost(Long id, BlogPostRequest blogPostRequest) {
        BlogPost updatedBlogPost = BlogPostAdaptor.adapt(blogPostRequest);
        updatedBlogPost.setId(id);
        return blogPostDao.update(updatedBlogPost);
    }

    @Override
    public void deletePost(Long id) {
        blogPostDao.findById(id);
        blogPostDao.delete(id);
    }

    @Override
    public BlogPost getPost(Long id) {
        return blogPostDao.findById(id);
    }

    @Override
    public List<BlogPost> getPostsBySearchTerm(String term) {
        if (StringUtils.isBlank(term)) {
            return blogPostDao.findAll();
        }
        return blogPostDao.findByTerm(term);
    }
}
