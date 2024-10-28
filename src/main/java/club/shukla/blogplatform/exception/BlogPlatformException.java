package club.shukla.blogplatform.exception;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class BlogPlatformException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7622886955478974626L;

    private final Integer errorCode;
    private final String message;
    private final HttpStatus httpStatus;
    public static BlogPlatformException create(BlogPlatformError error){
        return BlogPlatformException.builder()
                .errorCode(error.getCode())
                .message(error.name())
                .httpStatus(error.getStatus())
                .build();
    }
}
