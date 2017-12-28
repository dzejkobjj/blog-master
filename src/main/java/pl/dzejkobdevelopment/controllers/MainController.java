package pl.dzejkobdevelopment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dzejkobdevelopment.AppConfig;
import pl.dzejkobdevelopment.WebsiteProporties;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.entities.Article;

import java.util.List;

/**
 * Created by Jakub Michałowski on 22.10.2017.
 * All rights reserved.
 */
@Controller
public class MainController {
    @Autowired
    private WebsiteProporties websiteProporties;
    @Autowired
    private  DatabaseManager db;

    @RequestMapping("/")
    public String index(Model model){
        websiteProporties.initModel(model);

        List<Article> list = db.getLastArticles();
        model.addAttribute("articles", list);

        return "index";
    }
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
