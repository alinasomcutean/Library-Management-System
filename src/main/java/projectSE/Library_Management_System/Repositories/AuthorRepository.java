package projectSE.Library_Management_System.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectSE.Library_Management_System.Model.Account;
import projectSE.Library_Management_System.Model.Author;

/**
 * Interface for the CRUD operations
 */
@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    /**
     * Method which finds an author after his/her name
     * @param name
     * @return author with the given name
     */
    Author findByName(String name);
}
