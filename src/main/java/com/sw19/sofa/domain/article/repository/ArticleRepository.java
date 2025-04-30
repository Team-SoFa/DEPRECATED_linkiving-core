package com.sw19.sofa.domain.article.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sw19.sofa.domain.article.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	Optional<Article> findByUrl(String url);
}
