package pl.dzejkobdevelopment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.dzejkobdevelopment.database.repositories.TagRepo;
import pl.dzejkobdevelopment.entities.Article;
import pl.dzejkobdevelopment.entities.Tag;
import pl.dzejkobdevelopment.entities.User;
import pl.dzejkobdevelopment.database.repositories.ArticleRepo;
import pl.dzejkobdevelopment.database.repositories.UserRepo;

@SpringBootApplication
public class BlogMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogMasterApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(UserRepo userRepo) {
		return (args) -> {
			User user = userRepo.findOne("admin@admin.pl");
			if (user == null) {
				user = new User("admin", "Admin123", "admin@admin.pl");
				user.setRole("ADMIN");
				userRepo.save(user);
			}

		};
	}
}
