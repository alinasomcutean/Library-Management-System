package projectSE.Library_Management_System.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectSE.Library_Management_System.Model.Subject;

/**
 * Interface for the CRUD operations
 */
@Repository
public interface SubjectRepository extends MongoRepository<Subject, String> {
    /**
     * Method which finds a subject after its name
     * @param name
     * @return subject with the given name
     */
    Subject findByName(String name);
}
