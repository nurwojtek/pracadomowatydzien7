package pl.com.nur.pracadomowatydzien7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource getDataSource;

    @Autowired
    public DbConfig(DataSource getDataSource) {
        this.getDataSource = getDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return  new JdbcTemplate(getDataSource);
    }

//    @EventListener(ApplicationEvent.class)
//    public void init() {
//        String sql = "CREATE TABLE cars(car_id int, mark varchar(50), model varchar(50), color varchar(100), date_production varchar(10),  PRIMARY KEY (car_id))";
//        getJdbcTemplate().update(sql);
//        String sql = "DROP TABLE articles";
//        getJdbcTemplate()..update(sql);
//        String sql = "CREATE TABLE articles (article_id int NOT NULL AUTO_INCREMENT, author varchar(50), title varchar(250),content varchar(1000), PRIMARY KEY (article_id))";
//        getJdbcTemplate().update(sql);
//    }

//    }

//    CREATE TABLE articles
//            (
//             article_id int NOT NULL AUTO_INCREMENT,
//             author     varchar(50),
//             title      varchar(250),
//             content    varchar(1000),
//             PRIMARY KEY (article_id));

}
