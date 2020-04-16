package pl.com.nur.pracadomowatydzien7.exercise2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.nur.pracadomowatydzien7.exercise1.model.Car;
import pl.com.nur.pracadomowatydzien7.exercise2.model.Article;
import pl.com.nur.pracadomowatydzien7.exercise2.service.ArticleDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDaoList implements ArticleDao {

    private JdbcTemplate jdbcTemplate;

    public ArticleDaoList(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveArticle(String author, String title, String content) {
//        ArticleDB articleDB = new ArticleDB(article.getAuthor(), article.getTitle(), article.getContent());
            ArticleDB articleDB = new ArticleDB(author, title, content);
            String sql = "INSERT INTO articles (author, title, content) VALUES(?,?,?)";
//        System.out.println(" w save Article "  + sql );
            jdbcTemplate.update(sql, articleDB.getAuthor(), articleDB.getTitle(), articleDB.getContent());
    }

    @Override
    public List<ArticleDB> findAll() {
        String sql=  "SELECT * FROM articles";
        List<ArticleDB> articleDBListList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach((element -> articleDBListList.add(new ArticleDB(
                Long.parseLong(String.valueOf(element.get("article_id"))),
                String.valueOf(element.get("author")),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("content"))
        ))));
        return articleDBListList;
    }

    @Override
    public void updateArticle(ArticleDB newArticle) {
        String sql = "UPDATE articles SET articles.author=?,articles.title=?, articles.content=? where articles.article_id=?";
        jdbcTemplate.update(sql, newArticle.getAuthor(), newArticle.getTitle(), newArticle.getContent(), newArticle.getArticleId());
    }

    @Override
    public void deleteArticle(long id) {
        String sql = "DELETE FROM articles  WHERE article_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public ArticleDB getOne(long id) {
        String sql = "SELECT * FROM articles WHERE article_id = ?";
        // kolumny numerujemy od 1 lub po nazwach
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ArticleDB(rs.getLong(1),
                rs.getString("author"), rs.getString("title"), rs.getString("content")), id);
    }


}
