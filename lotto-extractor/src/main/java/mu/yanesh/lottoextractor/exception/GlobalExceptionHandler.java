package mu.yanesh.lottoextractor.exception;

import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lottoextractor.models.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        log.error(e.toString());
        return ResponseEntity.internalServerError()
                .body(ApiError.builder().errorMessage(e.getMessage()).dateTime(LocalDateTime.now()).build());
    }

}
