package projectSE.Library_Management_System.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Account which maps the Account table from database
 */
@Document(collection = "Account")
public class Account {
    /**
     * Fields of the Account table
     */
    @Id
    private ObjectId _id;

    private String username;
    private String password;
    private int type;
    private int score;

    /**
     * An empty, public constructor
     */
    public Account(){}

    /**
     * Getter for the username of the account
     * @return username
     */
    public String getUserame() {
        return username;
    }

    /**
     * Setter for the username of the account
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Getter for the password of the account
     * @return password
     */
    public String getPassword(){
        return  password;
    }

    /**
     * Setter for the password of the account
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Getter for the type of the account
     * @return 0 for administrator, 11 for librarian and any other number of 3 digits for user
     */
    public int getType() {
        return type;
    }

    /**
     * Setter for the type of the account
     * @param type - 0 for administrator, 11 for librarian and any other number of 3 digits for user
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Getter for the score of the account
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for the score of the account
     * @param score - initially, set with 0
     */
    public void setScore(int score) {
        this.score = score;
    }
}
