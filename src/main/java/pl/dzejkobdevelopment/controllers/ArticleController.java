package pl.dzejkobdevelopment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dzejkobdevelopment.WebsiteProporties;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.entities.Article;

/**
 * Created by Jakub Michałowski on 25.11.2017.
 * All rights reserved.
 */
@Controller
public class ArticleController {
    @Autowired
    private DatabaseManager db;
    @Autowired
    private WebsiteProporties websiteProporties;

    @RequestMapping("/article/{id}")
    public String showArticle(@PathVariable("id") int id, Model model){
        websiteProporties.initModel(model);
        model.addAttribute("article", db.getArticle(Integer.toUnsignedLong(id)));

        return "article";
    }
}

