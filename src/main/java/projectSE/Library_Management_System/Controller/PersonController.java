package projectSE.Library_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import projectSE.Library_Management_System.Model.Account;
import projectSE.Library_Management_System.Model.Person;
import projectSE.Library_Management_System.Repositories.AccountRepository;
import projectSE.Library_Management_System.Repositories.PersonRepository;

/**
 * Class that controls and connect the account with the right person
 */
@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Method which creates a new account
     * @param username
     * @param password
     * @param type - 0 (admin), 11 (librarian) or other number (user)
     * @return a new account
     */
    private Account createAccount(String username, String password, String type){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setScore(0);
        account.setType(Integer.parseInt(type));
        return account;
    }

    /**
     * Method which creates a new person
     * @param firstName
     * @param lastName
     * @param gender - feminin / masculin
     * @param email
     * @param phone
     * @param account
     * @return a new person
     */
    private Person createPerson(String firstName, String lastName, String gender, String email, String phone, Account account){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setEmail(email);
        person.setPhone(phone);
        person.setAccount(account);
        return person;
    }

    /**
     * Method which is called when the button 'Register now', from the register page, is pressed
     * @param firstName
     * @param lastName
     * @param gender
     * @param email
     * @param phone
     * @param username
     * @param password
     * @param confirmPassword
     * @param type
     * @return the corresponding page of the user type, if the registration was successful, a page that informs you that the username is not valid or a page that informs you that your passwords doesn't match
     */
    @RequestMapping(value = "/registerPage")
    public ModelAndView register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender,
                                 @RequestParam String email, @RequestParam String phone, @RequestParam String username,
                                 @RequestParam String password, @RequestParam String confirmPassword, @RequestParam String type){

        if(password.equals(confirmPassword)) { // if passwords matches
            Account account = createAccount(username, password, type); // creates a new account in the database
            Account user = accountRepository.findByUsername(username);

            if(user != null) {
                if (username.equals(user.getUserame()) == false) { // check if the username is available
                    // save account in the database
                    accountRepository.save(account);

                    // create a new person in the databse
                    Person person = createPerson(firstName, lastName, gender, email, phone, account);
                    // save the person in the database
                    personRepository.save(person);

                    if (account.getType() == 0) { // administrator page
                        return new ModelAndView("administratorPage"); // go to the administrator page
                    } else {
                        if (account.getType() == 11) { // librarian page
                            return new ModelAndView("librarianPage"); // go to the librarian page
                        } else { // user page
                            return new ModelAndView("userPage"); //go to the user page
                        }
                    }
                } else { // username is not available
                    ModelAndView modelAndView = new ModelAndView();
                    modelAndView.setViewName("registerPage");
                    modelAndView.addObject("userExists", "This username is used. Please choose another one.");
                    return modelAndView; // informs that you must choose another username
                }
            }
            else{
                // save account in the database
                accountRepository.save(account);

                // create a new person in the databse
                Person person = createPerson(firstName, lastName, gender, email, phone, account);
                // save the person in the database
                personRepository.save(person);

                if (account.getType() == 0) { // administrator page
                    return new ModelAndView("administratorPage"); // go to the administrator page
                } else {
                    if (account.getType() == 11) { // librarian page
                        return new ModelAndView("librarianPage"); // go to the librarian page
                    } else { // user page
                        return new ModelAndView("userPage"); //go to the user page
                    }
                }
            }
        }
        else{ // passwords doesn't coincide
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("registerPage");
            modelAndView.addObject("passwords", "You entered different passwords");
            return modelAndView; // informs that you entered different passwords
        }
    }
}
