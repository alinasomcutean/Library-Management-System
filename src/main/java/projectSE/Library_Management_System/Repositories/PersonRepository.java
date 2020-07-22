package projectSE.Library_Management_System.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectSE.Library_Management_System.Model.Person;

/**
 * Interface for the CRUD operations
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

}
