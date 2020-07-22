package projectSE.Library_Management_System.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class BookItem which maps the BookItem table from database
 */
@Document(collection = "BookItem")
public class BookItem {

    /**
     * Fields from the BookItem table
     */
    @Id
    private ObjectId _id;

    private int inventoryNo;
    private String status;
    private String edition;
    private int noOfPages;
    private double price;

    /**
     * An empty, public constructor
     */
    public BookItem(){}

    /**
     * Getter for the inventory number of the book
     * @return inventory number
     */
    public int getInventoryNo() {
        return inventoryNo;
    }

    /**
     * Setter for the inventory number of the book
     * @param inventoryNo
     */
    public void setInventoryNo(int inventoryNo) {
        this.inventoryNo = inventoryNo;
    }

    /**
     * Getter for the status of the book
     * @return status of the book
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status of the book
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for the edition of the book
     * @return edition of the book
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Setter for the edition of the book
     * @param edition
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Getter for the number of pages of the book
     * @return number of pages of the book
     */
    public int getNoOfPages() {
        return noOfPages;
    }

    /**
     * Setter for the number of pages of the book
     * @param noOfPages
     */
    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    /**
     * Getter for the price of the book
     * @return price of he book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price of the book
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
