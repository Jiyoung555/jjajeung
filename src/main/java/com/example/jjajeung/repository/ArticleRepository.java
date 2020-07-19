package com.example.jjajeung.repository;
import com.example.jjajeung.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
