package com.example.jjajeung.controller;
import com.example.jjajeung.dto.ArticleForm;
import com.example.jjajeung.entity.Article;
import com.example.jjajeung.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){

        Iterable<Article> articleList = articleRepository.findAll();

        model.addAttribute("articles", articleList);

        model.addAttribute("msg", "안녕하세요. 반갑습니다");

        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String letsgo(){
        return "articles/new";
    }

    //JSON 방식으로 할 거니까 => 이건 이제 쓸모 X
    //@PostMapping("/articles")
    //public String create(ArticleForm form){
    //    log.info(form.toString());
    //    return "redirect:/articles";
    //}

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        model.addAttribute("article", article);

        return "/articles/show";
    }

    @GetMapping("/articles/init")
    public String init(){
        String[] names = {"마크", "쟈니", "지성"};
        String[] contents = {"안녕", "하이", "니하오"};

        ArrayList<Article> articleList = new ArrayList();

        for(int i = 0; i < 3; i++){
            articleList.add( new Article(null, names[i], contents[i]) );
        }

        articleRepository.saveAll(articleList);

        return "redirect:/articles";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        model.addAttribute("article", article);
        return "/articles/edit";
    }
}
