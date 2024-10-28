package club.shukla.blogplatform.repository;

import club.shukla.blogplatform.repository.model.IdSequenceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IIdSeqRepository extends MongoRepository<IdSequenceEntity, String> {
}
