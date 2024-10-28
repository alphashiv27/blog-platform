package club.shukla.blogplatform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BlogPlatformError {
    INTERNAL_SERVER_ERROR(0, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_PERSIST_ERROR(2, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_DELETE_ERROR(3, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_UPDATE_ERROR(4, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_FETCH_ERROR(5, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(6, HttpStatus.NOT_FOUND),
    CLIENT_ERROR(5, HttpStatus.BAD_REQUEST),;

    private final Integer code;
    private final HttpStatus status;
}
