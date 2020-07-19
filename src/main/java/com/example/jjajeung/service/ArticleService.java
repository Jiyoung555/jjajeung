package com.example.jjajeung.service;
import com.example.jjajeung.dto.ArticleForm;
import com.example.jjajeung.entity.Article;
import com.example.jjajeung.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional // 트랜잭션 처리
    public Article update(Long id, ArticleForm form) {
        // 수정한 form 데이터 확인!
        log.info("form: " + form.toString());

        // 해당 id로 기존의 수정전 데이터를 가져옴!
        Article target = articleRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 Article이 없습니다.")
                );

        log.info("target: " + target.toString());

        // 수정한 form 데이터로 => 재작성 및 db 저장
        target.rewrite(form.getTitle(), form.getContent());
        Article saved = articleRepository.save(target);
        log.info("saved: " + saved.toString());

        return saved;
    }

    @Transactional
    public Long destroy(Long id) {
        Article target = articleRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 Article이 없습니다.")
                );

        articleRepository.delete(target);

        log.info(id + "번 Article이 삭제되었습니다");
        log.info(target.toString() + "이 삭제되었습니다");

        return target.getId();
    }


}
