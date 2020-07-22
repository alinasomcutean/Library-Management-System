package projectSE.Library_Management_System.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Person which maps the Person table from the database
 */
@Document(collection = "Person")
public class Person {
    /**
     * Fields of the Person table
     */
    @Id
    private ObjectId _id;

    private String lastName;
    private String firstName;
    private String gender;
    private String email;
    private String phone;

    @DBRef
    @Indexed(unique = true)
    private Account account;

    /**
     * An empty, public constructor
     */
    public Person(){}

    /**
     * Getter for the last name of the person
     * @return last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Setter for the last name of the person
     * @param lastName
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Getter for the first name of the person
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name of the person
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the gender of the person
     * @return gender - Feminin or Masculin
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter for the gender of the person
     * @param gender - Feminin or Masculin
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter for the email of the person
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email of the person
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the phone of the person
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for the phone of the person
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for the account of the person
     * @return account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Setter for the account of the person
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
