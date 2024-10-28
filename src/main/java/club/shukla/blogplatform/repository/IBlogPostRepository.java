package club.shukla.blogplatform.repository;

import club.shukla.blogplatform.repository.model.BlogPostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBlogPostRepository extends MongoRepository<BlogPostEntity, Long> {
}
