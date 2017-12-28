package pl.dzejkobdevelopment.database.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dzejkobdevelopment.entities.Article;
import java.util.List;

/**
 * Created by Jakub Michałowski on 13.11.2017.
 * All rights reserved.
 */
@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {
    List<Article> findTop3ByOrderByIdDesc();
    List<Article> findAllByOrderByIdDesc(Pageable pageable);
}
