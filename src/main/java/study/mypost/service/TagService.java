package study.mypost.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mypost.domain.Tag;
import study.mypost.dto.CreateTagRequestDTO;
import study.mypost.dto.CreateTagResponseDTO;
import study.mypost.exception.CustomPostException;
import study.mypost.exception.ErrorCode;
import study.mypost.repository.TagRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class TagService {
    private final TagRepository tagRepository;

    public CreateTagResponseDTO createTag(CreateTagRequestDTO request) {
        log.info("Creating new tag");
        log.info(request.getTagName());
        Optional<Tag> tag = tagRepository.findByName(request.getTagName());
        log.info(tag);
        if (tag.isPresent()) {
            throw new CustomPostException(ErrorCode.SAME_TAG_IS_ALREADY_EXIST);
        }
//                .ifPresent(tag -> {
//                    throw new CustomPostException(ErrorCode.SAME_TAG_IS_ALREADY_EXIST);
//                });

        Tag result = Tag.builder()
                .name(request.getTagName())
                .build();
        return new CreateTagResponseDTO(tagRepository.save(result));
    }
}
