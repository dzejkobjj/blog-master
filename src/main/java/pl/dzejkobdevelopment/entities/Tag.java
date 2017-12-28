package pl.dzejkobdevelopment.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Jakub Michałowski on 11.11.2017.
 * All rights reserved.
 */
@Entity
public class Tag {
    @Id @GeneratedValue
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public Tag(String name, Article article){
        this.name = name;
        this.article = article;
    }
    public Tag(String name){
        this.name = name;
    }
    public Tag() {};
}
