package projectSE.Library_Management_System.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectSE.Library_Management_System.Model.Author;
import projectSE.Library_Management_System.Model.Book;
import projectSE.Library_Management_System.Model.BookItem;
import projectSE.Library_Management_System.Model.Subject;

import java.util.List;

/**
 * Interface for the CRUD operations
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    /**
     * Method which finds a book after its title
     * @param title
     * @return book with the given title
     */
    Book findByTitle(String title);

    /**
     * Method which finds a book after its author
     * @param author
     * @return book with the given author
     */
    List<Book> findByAuthor(Author author);

    /**
     * Method which finds a book after its title and author
     * @param title
     * @param author
     * @return
     */
    Book findByTitleAndAuthor(String title, Author author);

    /**
     * Method which finds a book after its subject
     * @param subject
     * @return book with the given subject
     */
    List<Book> findBySubject(Subject subject);

    /**
     * Method which finds books after a subject and an author
     * @param subject
     * @param author
     * @return
     */
    List<Book> findBySubjectAndAuthor(Subject subject, Author author);
}
