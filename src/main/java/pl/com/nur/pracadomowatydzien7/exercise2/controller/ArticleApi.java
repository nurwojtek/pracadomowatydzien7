package pl.com.nur.pracadomowatydzien7.exercise2.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.nur.pracadomowatydzien7.exercise1.model.Car;
import pl.com.nur.pracadomowatydzien7.exercise2.repository.ArticleDaoList;
import pl.com.nur.pracadomowatydzien7.exercise2.service.ArticleDB;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequestMapping("/newslist")
public class ArticleApi {

    ArticleDaoList articleDaoList;

    public ArticleApi(ArticleDaoList articleDaoList) {
        this.articleDaoList = articleDaoList;
    }

    @GetMapping
    public String getVehicles(Model model){
        model.addAttribute("articles", articleDaoList.findAll());
        model.addAttribute("delNews", new ArticleDB());
        model.addAttribute("modNews", new ArticleDB());

        return "news";
    }


    @GetMapping("/modification")
    public String modElementVehicle(@ModelAttribute ArticleDB articleDB){
            articleDaoList.updateArticle(articleDB);
            return "redirect:/newslist";
        }


    @GetMapping("/delete/{id}")
    public String removeVehicle(@PathVariable Long id){
        articleDaoList.deleteArticle(id);
        return "redirect:/newslist";
    }


}
