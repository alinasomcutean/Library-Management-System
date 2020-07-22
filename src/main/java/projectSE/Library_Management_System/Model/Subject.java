package projectSE.Library_Management_System.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Subject which maps the Subject table from the database
 */
@Document(collection = "Subject")
public class Subject {

    /**
     * Fields of the Subject table
     */
    @Id
    private ObjectId _id;
    private String name;

    /**
     * An empty, public constructor
     */
    public Subject(){}

    /**
     * Getter for the name of the subject
     * @return name fo the subject
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the subject
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
