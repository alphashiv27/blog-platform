package club.shukla.blogplatform.repository.model;

import club.shukla.blogplatform.repository.constants.IdSeqConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = IdSeqConstants.ID_SEQUENCE_COLLECTION)
public class IdSequenceEntity {
    @Id
    private String id;

    private Long sequence;
}
