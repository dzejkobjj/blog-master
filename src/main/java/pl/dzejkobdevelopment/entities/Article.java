package pl.dzejkobdevelopment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Jakub Michałowski on 02.11.2017.
 * All rights reserved.
 */
@Entity
public class Article {
    @Id @GeneratedValue
    @Getter @Setter
    private long id;

    @Getter
    @Setter
    @NotEmpty(message = "Musisz podać tytuł artykułu")
    private String title;

    @Getter
    @Setter
    @Type(type="text")
    @NotEmpty(message = "Musisz podać treść artykułu")
    private String articleText;

    @Getter @Setter
    @Type(type="text")
    @NotEmpty(message = "Musisz podać streszczenie artykułu")
    private String articleDescription;

    @Getter @Setter
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Getter @Setter
    @OneToMany(mappedBy = "associatedArticle", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @Getter @Setter
    @NotEmpty(message = "Musisz wybrać zdjęcie")
    private String imageUrl;

    @Getter
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    private List<Tag> tags;

    @Getter @Setter
    @Transient
    private int numberOfComments;

//    public Article(String title, String articleText, User author){
//        this.title = title;
//        this.articleText = articleText;
//        this.author = author;
//        this.date = new Date();
//        //this.imageUrl = "/images/city.jpg";
//    }

    public Article(){
        this.date = new Date();
        //this.imageUrl = "/images/city.jpg";
    };

    public void addComment(Comment comment){
        if(comments == null)
            comments = new ArrayList<Comment>();

        comment.setAssociatedArticle(this);
        comments.add(comment);
    }

    private void addTag(Tag tag){
        if(tags == null)
            tags = new ArrayList<Tag>();

        tag.setArticle(this);
        this.tags.add(tag);
    }

    public void setTags(String string){
        List<String> list = Arrays.asList(string.split("\\s*,\\s*"));
        list.forEach(text->addTag(new Tag(text)));
    }


}
