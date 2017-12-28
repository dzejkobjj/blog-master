package pl.dzejkobdevelopment.database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dzejkobdevelopment.entities.User;

/**
 * Created by Jakub Michałowski on 13.11.2017.
 * All rights reserved.
 */
@Repository
public interface UserRepo extends CrudRepository<User, String> {
}
