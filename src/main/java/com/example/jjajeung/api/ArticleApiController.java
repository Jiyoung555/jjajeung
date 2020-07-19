package com.example.jjajeung.api;
import com.example.jjajeung.dto.ArticleForm;
import com.example.jjajeung.entity.Article;
import com.example.jjajeung.repository.ArticleRepository;
import com.example.jjajeung.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.MimeTable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form){
        log.info(form.toString());

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return saved.getId();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id){
        Article entity = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        return new ArticleForm(entity);
    }

    @GetMapping("/api/articles")
    public List<ArticleForm> getArticleAll(){
        Iterable<Article> entities= articleRepository.findAll();

        List<ArticleForm> articleFormList = new ArrayList();

        for(Article article : entities){
            ArticleForm dto = new ArticleForm(article);
            articleFormList.add(dto);
        }

        return articleFormList;
    }

    @PutMapping("/api/articles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form){
        log.info("form: " + form.toString());

        Article saved = articleService.update(id, form);

        return saved.getId();
    }

    @DeleteMapping("/api/articles/{id}") // HTTP의 DELETE 메소드로 "/api/articles{id}" 요청이 온다면,
    public Long destroy(@PathVariable Long id) {
        return articleService.destroy(id); // 서비스 객체가 destroy()를 수행!
    }


}
