package mu.yanesh.lotto.extractor.exception;

import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.models.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingExtractionLogException.class)
    public ResponseEntity<ApiError> handleException(MissingExtractionLogException e) {
        return ResponseEntity.badRequest().body(ApiError.builder().errorMessage(e.getMessage()).dateTime(LocalDateTime.now()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.internalServerError()
                .body(ApiError.builder().errorMessage(e.getMessage()).dateTime(LocalDateTime.now()).build());
    }

}
