package pl.dzejkobdevelopment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.entities.Article;

/**
 * Created by Jakub Michałowski on 17.12.2017.
 * All rights reserved.
 */
@RestController
public class ArticleRestController{
    @Autowired
    private DatabaseManager db;

    @RequestMapping("/rest/article")
    public Article getArticle(@RequestParam(value = "id") int id){
        return db.getArticle(Integer.toUnsignedLong(id));
    }
}
