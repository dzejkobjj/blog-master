package pl.dzejkobdevelopment.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.dzejkobdevelopment.database.repositories.ArticleRepo;
import pl.dzejkobdevelopment.database.repositories.CommentRepo;
import pl.dzejkobdevelopment.database.repositories.TagRepo;
import pl.dzejkobdevelopment.database.repositories.UserRepo;
import pl.dzejkobdevelopment.entities.Article;
import pl.dzejkobdevelopment.entities.Comment;
import pl.dzejkobdevelopment.entities.Tag;
import pl.dzejkobdevelopment.entities.User;

import java.util.List;

/**
 * Created by Jakub Michałowski on 16.11.2017.
 * All rights reserved.
 */
@Service
public class DatabaseManager {

    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private UserRepo userRepo;

    public void addArticle(Article article){
        articleRepo.save(article);
        //article.getTags().forEach(tag ->this.addTag(tag));
    }

    public List<Comment> findComments(User user){
        return commentRepo.findByCommentAuthor(user);
    }

    private void addTag(Tag tag){
        tagRepo.save(tag);
    }

    public List<Article> getLastArticles(){
        List<Article> list = articleRepo.findTop3ByOrderByIdDesc();
        list.forEach(article -> article.setNumberOfComments(article.getComments().size()));

        return list;
    }

    public Article getArticle(long id){
        return articleRepo.findOne(id);
    }

    public List<Article> getArticlesList(Pageable pageable){
        return  articleRepo.findAllByOrderByIdDesc(pageable);
    }


}
