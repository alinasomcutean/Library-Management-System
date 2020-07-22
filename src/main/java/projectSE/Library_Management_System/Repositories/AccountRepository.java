package projectSE.Library_Management_System.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectSE.Library_Management_System.Model.Account;

/**
 * Interface for the CRUD operations
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    /**
     * Method which finds an account after the username
     * @param username
     * @return account with the given username
     */
    Account findByUsername(String username);
}