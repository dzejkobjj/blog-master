package pl.dzejkobdevelopment.database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dzejkobdevelopment.entities.Comment;
import pl.dzejkobdevelopment.entities.User;

import java.util.List;

/**
 * Created by Jakub Michałowski on 13.11.2017.
 * All rights reserved.
 */
@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findByCommentAuthor(User user);
}
