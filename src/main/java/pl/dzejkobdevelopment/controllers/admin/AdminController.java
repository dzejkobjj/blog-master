package pl.dzejkobdevelopment.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dzejkobdevelopment.WebsiteProporties;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.entities.Article;

import javax.validation.Valid;

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
}
