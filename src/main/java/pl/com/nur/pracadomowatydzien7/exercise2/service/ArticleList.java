package pl.com.nur.pracadomowatydzien7.exercise2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.com.nur.pracadomowatydzien7.exercise2.model.Article;
import pl.com.nur.pracadomowatydzien7.exercise2.model.Example;
import pl.com.nur.pracadomowatydzien7.exercise2.repository.ArticleDaoList;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleList {

    RestTemplate restTemplate;   // do ściagania api
//    List<Article> articleList;
    Example example;
    ArticleDaoList articleDaoList;

    @Autowired
    public ArticleList(ArticleDaoList articleDaoList) {
        this.articleDaoList = articleDaoList;
//        articleList = new ArrayList<>();
        restTemplate = new RestTemplate();
        get();
    }


    public void get(){

        String url =  "http://newsapi.org/v2/everything?q=diving&sortBy=publishedAt&apiKey=d6a95994c8494860b37eb49f1921e372";
        Example forObject = restTemplate.getForObject(url, Example.class);
//        System.out.println("ilosc dodawanych newsow " + forObject.getArticles().size() );
        for (int i = 0; i <1 ; i++) {     // dodaje 1 news   forObject.getArticles().size()
          //  forObject.getArticles().get(i);
//            articleList.add(forObject.getArticles().get(i));
            articleDaoList.saveArticle(forObject.getArticles().get(i).getAuthor(), forObject.getArticles().get(i).getTitle(),
                        forObject.getArticles().get(i).getContent());
        }
//        System.out.println(articleList);
    }


}
