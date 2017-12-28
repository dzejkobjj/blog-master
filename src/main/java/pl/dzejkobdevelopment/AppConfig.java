package pl.dzejkobdevelopment;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.ui.Model;
import pl.dzejkobdevelopment.database.DatabaseManager;
import pl.dzejkobdevelopment.database.repositories.ArticleRepo;
import pl.dzejkobdevelopment.storageservice.StorageProperties;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by Jakub Michałowski on 22.10.2017.
 * All rights reserved.
 */
@Configuration
@SpringBootApplication
//@EnableJpaRepositories("pl.dzejkobdevelopment.database.repositories")
public class AppConfig {
    @Bean
    public WebsiteProporties websiteProporties(){
        return new WebsiteProporties();
    }
    @Bean
    public StorageProperties storageProporties(){ return new StorageProperties();}
    @Bean
    public DatabaseManager databaseManager(){ return new DatabaseManager();}

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/blog_master?verifyServerCertificate=false&useSSL=true");
        dataSource.setUsername("blog-master");
        dataSource.setPassword("blog-master");


        return dataSource;
    }




}
