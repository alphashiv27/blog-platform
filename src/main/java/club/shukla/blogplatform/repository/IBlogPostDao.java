package club.shukla.blogplatform.repository;

import club.shukla.blogplatform.model.BlogPost;
import java.util.List;

public interface IBlogPostDao {
    BlogPost save(BlogPost blogPost);

    BlogPost findById(Long id);

    BlogPost update(BlogPost blogPost);

    void delete(Long id);

    List<BlogPost> findAll();

    List<BlogPost> findByTerm(String term);
}
