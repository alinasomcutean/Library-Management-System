package projectSE.Library_Management_System.Model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Author which maps the Author table from the database
 */
@Document(collection = "Author")
public class Author {

    /**
     * Fields of the Author table
     */
    @Id
    private ObjectId _id;
    private String name;

    /**
     * An empty, public constructor
     **/
    public Author(){}

    /**
     * Getter for the name of the author
     * @return name of the author
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the author
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
