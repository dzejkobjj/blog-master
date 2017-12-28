package pl.dzejkobdevelopment.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.dzejkobdevelopment.AppConfig;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.validators.Password;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by Jakub Michałowski on 02.11.2017.
 * All rights reserved.
 */
@Entity
public class User {

    @Getter @Setter
    @NotEmpty(message = "Musisz podać twoją nazwe użytkownika")
    @Size(min = 2, message = "Nazwa użytkownika musi mieć minimum 2 znaki")
    private String username;

    @Getter @Setter
    @NotEmpty(message = "Musisz podać hasło")
    @Password
    private String password;

    @Id
    @Getter @Setter
    @Email(message = "Musisz podać poprawny adres email")
    private String email;

    @Getter @Setter
    private String role;

    @Getter @Setter
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    public User(String username, String password, String email){
        this.username = username;
        //this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = password;
        this.email = email;
        this.registerDate = new Date();
        this.role = "USER";
    }

    public User(){};

    @PreRemove
    private void preRemove(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DatabaseManager db = (DatabaseManager)context.getBean("databaseManager");

        List<Comment> list = db.findComments(this);

        list.forEach(comment -> comment.setCommentAuthor(null));
    }
}
