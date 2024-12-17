package study.mypost.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //
public class PostExceptionHandler {

    @ExceptionHandler(CustomPostException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomPostException(CustomPostException ex) { //
        return ErrorResponseEntity.errorResponseEntity(ex.getErrorCode());
    }

}
