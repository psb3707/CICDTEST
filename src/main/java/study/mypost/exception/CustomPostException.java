package study.mypost.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomPostException extends RuntimeException {
    private ErrorCode errorCode;
}
