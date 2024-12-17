package study.mypost.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 게시글이 존재하지 않습니다."),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 태그가 존재하지 않습니다."),
    SAME_TAG_IS_ALREADY_EXIST(HttpStatus.NOT_ACCEPTABLE, "이미 동일한 태그가 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
