package projectSE.Library_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectSE.Library_Management_System.Model.Account;
import projectSE.Library_Management_System.Repositories.AccountRepository;

import javax.servlet.http.HttpServlet;

/**
 * Class that controls the activity regarding the account
 */
@Controller
public class AccountController {
    @Autowired
    private AccountRepository repository;

    /**
     * Method which is called when the button 'Log in' is pressed
     * @param username
     * @param password
     * @return the corresponding page of the account
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        //find if there exists an account with the given username
        Account account = repository.findByUsername(username);
        ModelAndView modelAndView = new ModelAndView();

        if(username == "" && password == "") {
            modelAndView.setViewName("index");
            modelAndView.addObject("wrongData", "You must complete both fields in order to enter in the system.");
            return modelAndView;
        }
        else{
            if (account == null) { //no account with the given username
                modelAndView.setViewName("index");
                modelAndView.addObject("wrongData", "Incorrect username or password");
                return modelAndView;  //informs the user that the username or the password is incorrect
            } else { //there is an account with the given username
                if (account.getPassword().equals(password) == false) { //check if the password is correct
                    modelAndView.setViewName("index");
                    modelAndView.addObject("wrongData", "Incorrect username or password");
                    return modelAndView; //informs the user that the username or the password is incorrect
                } else { //password is correct
                    if (account.getType() == 0) { //admin account
                        return new ModelAndView("administratorPage"); // go to admin page
                    } else {
                        if (account.getType() == 11) { // librarian account
                            return new ModelAndView("librarianPage"); // go to librarian page
                        } else { // user account
                            return new ModelAndView("userPage"); // go to the user pase
                        }
                    }
                }
            }
        }
    }


    /**
     * Method which is called when the button 'Sign up' is pressed
     * @return a new page with the fields which must be completed to create an account
     */
    @RequestMapping(value = "/register")
    public ModelAndView signup(){
        ModelAndView m = new ModelAndView("registerPage");
        return m;
    }
}