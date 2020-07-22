package projectSE.Library_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import projectSE.Library_Management_System.Model.Author;
import projectSE.Library_Management_System.Model.Book;
import projectSE.Library_Management_System.Model.BookItem;
import projectSE.Library_Management_System.Model.Subject;
import projectSE.Library_Management_System.Repositories.AuthorRepository;
import projectSE.Library_Management_System.Repositories.BookItemRepository;
import projectSE.Library_Management_System.Repositories.BookRepository;
import projectSE.Library_Management_System.Repositories.SubjectRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that controls the books management
 */
@Controller
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    private String bookTitle;

    /**
     * Method which creates a new author
     * @param name
     * @return a new author
     */
    private Author createAuthor(String name){
        Author author = new Author();
        author.setName(name);
        return author;
    }

    /**
     * Method which creates a new subject
     * @param name
     * @return a new subject
     */
    private Subject createSubject(String name){
        Subject subject = new Subject();
        subject.setName(name);
        return subject;
    }

    /**
     * Method which is called when someone wants to search a book after a criteria
     * @param title
     */
    @RequestMapping(value = "userSearch")
    public ModelAndView searchBook(@RequestParam String title, @RequestParam String author, @RequestParam String subject){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");

        if(author == ""){
            if(title != ""){ // search by title
                Book book = bookRepository.findByTitle(title); // find book with given title
                if (book == null) { // book doesn't exist
                    modelAndView.addObject("noBook", "There is no book with this title."); // display a corresponding message
                } else { // book exists
                    modelAndView.addObject("searchBooks", book); // show the book
                }
                return modelAndView;
            }
            if(subject != ""){ // search by subject
                Subject s = createSubject(subject);
                List<Book> book = bookRepository.findBySubject(s); // find all books from given subject
                if (book == null) { // books don't exist
                    modelAndView.addObject("noBook", "There is no book in this subject category."); // display a corresponding message
                } else { // book exists
                    modelAndView.addObject("searchBooks", book); // show the book
                }
                return modelAndView;
            }
            return modelAndView;
        }
        else{
            if(title != ""){ // search by title and author
                Author a = createAuthor(author);
                Book book = bookRepository.findByTitleAndAuthor(title, a); // find book with given title and written by given author
                if (book == null) { // books don't exist
                    modelAndView.addObject("noBook", "There is no book with this title written by this author."); // display a corresponding
                } else { // book exists
                    modelAndView.addObject("searchBooks", book); // show the book
                }
                return modelAndView;
            }
            else {
                if (subject != "") { // search by subject and author
                    Subject s = createSubject(subject);
                    Author a = createAuthor(author);
                    List<Book> book = bookRepository.findBySubjectAndAuthor(s, a); // find books with given subject and written by given author
                    if (book == null) { // books don't exist
                        modelAndView.addObject("noBook", "There is no book in this subject category written by this author."); // display a corresponding
                    } else { // book exists
                        modelAndView.addObject("searchBooks", book); // show the books
                    }
                    return modelAndView;
                }
                else{ // search by author
                    Author a = createAuthor(author);
                    List<Book> book = bookRepository.findByAuthor(a); // find books written by given author
                    if (book == null) { // books don't exist
                        modelAndView.addObject("noBook", "There is no book written by this author."); // display a corresponding
                    } else { // book exists
                        modelAndView.addObject("searchBooks", book); // show the books
                    }
                    return modelAndView;
                }
            }
        }
    }

    /**
     * Method which is called when someone wants to see all books which exists in the library
     * @return
     */
    @RequestMapping(value = "userViewAll")
    public ModelAndView viewAllBooks(){
        List<Book> allBooks = bookRepository.findAll(); // find all books
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("allBooks", allBooks); // show the books
        return modelAndView;
    }

    @RequestMapping(value = "/userDetails/{title}")
    public ModelAndView userBookDetails(@PathVariable String title){
        //return new ModelAndView("addBook");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userBookDetails");

        Book book = bookRepository.findByTitle(title);

        modelAndView.addObject("title", "Details of the book '" + title + "'");
        modelAndView.addObject("author", "Author: " + book.getAuthor().getName());
        modelAndView.addObject("subject", "Subjetcs: " + book.getAllSubjects());
        modelAndView.addObject("description", "Description: " + book.getDescription());
        modelAndView.addObject("copies", "Number of copies: " + book.getNoOfCopies());

        List<BookItem> bookItems = book.getBookItem();
        modelAndView.addObject("userBookItems", bookItems);

        return modelAndView;
    }
}
