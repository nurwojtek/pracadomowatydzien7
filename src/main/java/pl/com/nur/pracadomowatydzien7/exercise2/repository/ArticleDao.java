package pl.com.nur.pracadomowatydzien7.exercise2.repository;

import pl.com.nur.pracadomowatydzien7.exercise1.model.Car;
import pl.com.nur.pracadomowatydzien7.exercise2.model.Article;
import pl.com.nur.pracadomowatydzien7.exercise2.service.ArticleDB;

import java.util.List;

public interface ArticleDao {

    void saveArticle(String author, String title, String content);

    List<ArticleDB> findAll();

    void updateArticle(ArticleDB newArticle);

    void deleteArticle(long id);

    ArticleDB getOne(long id);




}
