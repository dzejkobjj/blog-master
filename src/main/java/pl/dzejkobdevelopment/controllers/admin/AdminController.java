package pl.dzejkobdevelopment.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dzejkobdevelopment.WebsiteProporties;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.entities.Article;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jakub Michałowski on 27.10.2017.
 * All rights reserved.
 */
@Controller
public class AdminController {
    @Autowired
    private DatabaseManager db;
    @Autowired
    private WebsiteProporties websiteProporties;

    @GetMapping("/admin/add")
    public String showForm(@RequestParam(value = "isAdded", required = false) String isAdded, Model model, Article article){
        if(isAdded != null && isAdded.equals("true")) {
            model.addAttribute("isAdded", isAdded);
        }
        websiteProporties.initModel(model);
        return "admin/add";
    }

    @PostMapping("/admin/add")
    public String addArticle(@Valid Article article, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/admin/add";
        }
        db.addArticle(article);
        return "redirect:/admin/add?isAdded=true";
    }

    @GetMapping("/admin/articlelist")
    public String showList(@RequestParam(value = "page", required = false) String stringPage, Model model){
        int page;
        try{
            page = Integer.parseInt(stringPage);
        }catch (NumberFormatException e){
            page = 0;
        }
        List articles = db.getArticlesList(new PageRequest(page,10));
        model.addAttribute("articles", articles);

        return "admin/list";
    }
}
