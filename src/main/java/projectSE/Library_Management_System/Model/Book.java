package projectSE.Library_Management_System.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class Book which maps the Book table from the database
 */
@Document(collection = "Book")
public class Book {

    /**
     * Fields from the Book table
     */
    @Id
    private ObjectId _id;

    private String title;
    private Author author;
    private List<Subject> subject;
    private String description;
    private int noOfCopies;
    private List<BookItem> bookItem;

    /**
     * An empty, public constructor
     */
    public Book(){}

    /**
     * Getter for the title of the book
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title of the book
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the author of the book
     * @return author of the book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Setter for the author of the book
     * @param author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Getter for the subjects of the book
     * @return a list with the subjects of the book
     */
    public List<Subject> getSubject() {
        return subject;
    }

    /**
     * Setter for the subjects of the book
     * @param subjectList
     */
    public void setSubject(List<Subject> subjectList) {
        this.subject = subjectList;
    }

    /**
     * Getter for the description of the book
     * @return description of the book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the book
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the number of copies of the book
     * @return number of copies of the book
     */
    public int getNoOfCopies() {
        return noOfCopies;
    }

    /**
     * Setter for the number of copies of the book
     * @param noOfCopies
     */
    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    /**
     * Getter for the BookItem object, which gives more details of the book
     * @return BookItem object
     */
    public List<BookItem> getBookItem() {
        return bookItem;
    }

    /**
     * Setter for the BookItem object
     * @param bookItem
     */
    public void setBookItem(List<BookItem> bookItem) {
        this.bookItem = bookItem;
    }

    /**
     * Method which creates a string with all the subjects of a book
     * @return a string with the book's subjects
     */
    public String getAllSubjects(){
        String str = "";
        int i = 0;

        while(i < 4){ // there can be maximum 5 subjects for a book
            if(subject.get(i) != null){
                if(subject.get(i+1) != null){
                    str = str + subject.get(i).getName() + ", ";
                }
                else{
                    str = str + subject.get(i).getName();
                }
            }
            i++;
        }

        return str;
    }
}
