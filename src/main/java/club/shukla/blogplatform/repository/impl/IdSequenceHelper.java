package club.shukla.blogplatform.repository.impl;

import club.shukla.blogplatform.exception.BlogPlatformError;
import club.shukla.blogplatform.exception.BlogPlatformException;
import club.shukla.blogplatform.repository.IIdSeqRepository;
import club.shukla.blogplatform.repository.constants.IdSeqConstants;
import club.shukla.blogplatform.repository.model.IdSequenceEntity;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class IdSequenceHelper {
    private final IIdSeqRepository idSeqRepository;
    private final MongoOperations mongoOperations;


    public Long generateId(String name) {
        Query query = new Query(Criteria.where(IdSeqConstants.ID).is(name));
        Update update = new Update().inc(IdSeqConstants.SEQUENCE, 1);
        log.info("Generating Seq for {}", name);
        try {
            IdSequenceEntity idSequenceEntity = mongoOperations.findAndModify(query,
                    update, FindAndModifyOptions.options().returnNew(true), IdSequenceEntity.class);
            log.info("found sequence {}", idSequenceEntity);
            return Objects.isNull(idSequenceEntity) ? saveNewSequence(name) : idSequenceEntity.getSequence();
        } catch (Exception e) {
            if (e instanceof BlogPlatformException) {
                throw (BlogPlatformException) e;
            }
            log.error("[generateId] failed for {} due to ", name, e);
            throw BlogPlatformException.create(BlogPlatformError.DATABASE_FETCH_ERROR);
        }
    }

    private Long saveNewSequence(String name) {
        try {
            idSeqRepository.save(IdSequenceEntity.builder().id(name).sequence(1L).build());
            return 1L;
        } catch (Exception e) {
            log.error("[saveNewSequence] failed for {} due to ", name, e);
            throw BlogPlatformException.create(BlogPlatformError.DATABASE_PERSIST_ERROR);
        }
    }
}
