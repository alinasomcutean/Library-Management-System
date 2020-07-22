package projectSE.Library_Management_System.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectSE.Library_Management_System.Model.BookItem;

/**
 * Interface for the CRUD operations of the BookItem
 */
@Repository
public interface BookItemRepository extends MongoRepository<BookItem, String> {
    /**
     * Method which deletes a book item after its inventory number
     * @param inventoryNo
     */
    void deleteByInventoryNo(int inventoryNo);

    /**
     * Method which finds a book item after its inventory number
     * @param inventoryNo
     * @return
     */
    BookItem findByInventoryNo(int inventoryNo);
}
