package club.shukla.blogplatform.repository.impl;

import club.shukla.blogplatform.exception.BlogPlatformError;
import club.shukla.blogplatform.exception.BlogPlatformException;
import club.shukla.blogplatform.model.BlogPost;
import club.shukla.blogplatform.repository.IBlogPostDao;
import club.shukla.blogplatform.repository.IBlogPostRepository;
import club.shukla.blogplatform.repository.adaptor.BlogPostAdaptor;
import club.shukla.blogplatform.repository.constants.BlogPostConstants;
import club.shukla.blogplatform.repository.model.BlogPostEntity;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BlogPostDaoImpl implements IBlogPostDao {
    private final IBlogPostRepository blogPostRepository;
    private final IdSequenceHelper idSequenceHelper;
    private final MongoClient mongoClient;
    private final MongoConverter converter;

    @Override
    public BlogPost save(BlogPost blogPost) {
        BlogPostEntity blogPostEntity = BlogPostAdaptor.adapt(blogPost);
        blogPostEntity.setCreatedAt(System.currentTimeMillis());
        blogPostEntity.setUpdatedAt(System.currentTimeMillis());
        blogPostEntity.setId(idSequenceHelper.generateId(BlogPostConstants.BLOG_POST_COLLECTION));
        try {
            BlogPostEntity blogPostEntity1 = blogPostRepository.save(blogPostEntity);
            return BlogPostAdaptor.adapt(blogPostEntity1);
        } catch (Exception e) {
            log.error("[BlogPostDaoImpl : save] failed to persist blog {} due to", blogPost, e);
            throw BlogPlatformException.create(BlogPlatformError.DATABASE_PERSIST_ERROR);
        }
    }

    @Override
    public BlogPost findById(Long id) {
        Optional<BlogPostEntity> blogPostEntity = blogPostRepository.findById(id);
        if (blogPostEntity.isEmpty()) {
            log.error("[BlogPostDaoImpl : findById] blogPostEntity is empty for id = {}", id);
            throw BlogPlatformException.create(BlogPlatformError.NOT_FOUND);
        }
        return BlogPostAdaptor.adapt(blogPostEntity.get());
    }

    @Override
    public BlogPost update(BlogPost blogPost) {
        findById(blogPost.getId());
        BlogPostEntity blogPostEntity = BlogPostAdaptor.adapt(blogPost);
        blogPostEntity.setUpdatedAt(System.currentTimeMillis());
        try {
            blogPostRepository.save(blogPostEntity);
        } catch (Exception e) {
            log.error("[BlogPostDaoImpl : update] failed to update blog {} due to", blogPost, e);
            throw BlogPlatformException.create(BlogPlatformError.DATABASE_UPDATE_ERROR);
        }
        return BlogPostAdaptor.adapt(blogPostEntity);
    }

    @Override
    public void delete(Long id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    public List<BlogPost> findAll() {
        return blogPostRepository.findAll().stream()
                .map(BlogPostAdaptor::adapt)
                .toList();
    }

    @Override
    public List<BlogPost> findByTerm(String term) {
        MongoCollection<Document> mongoCollection = mongoClient
                .getDatabase(BlogPostConstants.BLOG_DATABASE)
                .getCollection(BlogPostConstants.BLOG_POST_COLLECTION);

        AggregateIterable<Document> result = mongoCollection.aggregate(List.of(new Document(BlogPostConstants.SEARCH_AGGREGATION,
                        new Document(BlogPostConstants.INDEX, BlogPostConstants.SEARCH_INDEX_NAME)
                                .append(BlogPostConstants.TEXT,
                                        new Document(BlogPostConstants.QUERY, term)
                                                .append(BlogPostConstants.PATH,
                                                        List.of(BlogPostConstants.TITLE,
                                                                BlogPostConstants.TAGS,
                                                                BlogPostConstants.CONTENT,
                                                                BlogPostConstants.CATEGORY)))),
                new Document(BlogPostConstants.SORT_AGGREGATION,
                        new Document(BlogPostConstants.CREATED_AT, BlogPostConstants.SORT_ORDER)),
                new Document(BlogPostConstants.LIMIT_AGGREGATION, BlogPostConstants.BLOG_SEARCH_LIMIT)));


        List<BlogPost> blogPosts = new ArrayList<>();
        result.forEach(document ->
                blogPosts.add(BlogPostAdaptor.adapt(converter.
                        read(BlogPostEntity.class, document))));

        return blogPosts;
    }

}
