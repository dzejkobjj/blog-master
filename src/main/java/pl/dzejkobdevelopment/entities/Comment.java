package pl.dzejkobdevelopment.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jakub Michałowski on 02.11.2017.
 * All rights reserved.
 */
@Entity
public class Comment {
    @Id @GeneratedValue
    @Getter @Setter
    private long id;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User commentAuthor;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article associatedArticle;

    @Getter @Setter
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @Getter @Setter
    @Type(type="text")
    @NotEmpty(message = "Musisz podać treść komentarza")
    private String commentText;

    public Comment(User author, String text){
        this.commentAuthor = author;
        this.commentText = text;
        this.date = new Date();
    }
}
