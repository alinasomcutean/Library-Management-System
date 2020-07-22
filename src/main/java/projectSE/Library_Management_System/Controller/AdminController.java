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
public class AdminController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private BookItemRepository bookItemRepository;

    private List<BookItem> bookItems = new LinkedList<BookItem>();
    private List<Subject> subjects = new LinkedList<Subject>();
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
     * Method which creates a new BookItem
     * @param inventoryNo
     * @param status
     * @param edition
     * @param noOfPages
     * @param price
     * @return a new BookItem
     */
    private BookItem createBookItem(int inventoryNo, String status, String edition, int noOfPages, double price){
        BookItem bookItem = new BookItem();
        bookItem.setInventoryNo(inventoryNo);
        bookItem.setStatus(status);
        bookItem.setEdition(edition);
        bookItem.setNoOfPages(noOfPages);
        bookItem.setPrice(price);
        return bookItem;
    }

    /**
     * Method which creates a new book
     * @param title
     * @param author
     * @param subject
     * @param description
     * @param noOfCopies
     * @param bookItem
     * @return a new book
     */
    private Book createBook(String title, Author author, List<Subject> subject, String description, int noOfCopies, List<BookItem> bookItem) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setSubject(subject);
        book.setDescription(description);
        book.setNoOfCopies(noOfCopies);
        book.setBookItem(bookItem);
        return book;
    }

    /**
     * Method which is called when someone wants to search a book after a criteria
     * @param title
     */
    @RequestMapping(value = "search")
    public ModelAndView searchBook(@RequestParam String title, @RequestParam String author, @RequestParam String subject){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administratorPage");

        if(author == ""){ //doesn't search after autor
            if(title != ""){ // search after title
                Book book = bookRepository.findByTitle(title); // find book after title
                if (book == null) { // book doesn't exist
                    modelAndView.addObject("noBook", "There is no book with this title."); // Display a message
                } else { // book exists
                    modelAndView.addObject("searchBooks", book); // Display the book
                }
                return modelAndView;
            }
            if(subject != ""){ // search after subject
                Subject s = createSubject(subject); // book doesn't exist
                List<Book> book = bookRepository.findBySubject(s); // find book after subject
                if (book.size() == 0) { // book doesn't exist
                    modelAndView.addObject("noBook", "There is no book in this subject category."); // Display a message
                } else { // book exists
                    modelAndView.addObject("searchBooks", book); // Display the book
                }
                return modelAndView;
            }
            return modelAndView;
        }
        else{
            if(title != ""){ // search after author and title
                Author a = createAuthor(author);
                Book book = bookRepository.findByTitleAndAuthor(title, a); // find book after title and author
                if (book == null) { // book doesn't exist
                    modelAndView.addObject("noBook", "There is no book with this title written by this author."); // Display a message
                } else { // book exists
                    modelAndView.addObject("searchBooks", book); // Display the book
                }
                return modelAndView;
            }
            else {
                if (subject != "") { // search after author and subject
                    Subject s = createSubject(subject);
                    Author a = createAuthor(author);
                    List<Book> book = bookRepository.findBySubjectAndAuthor(s, a); // find book after subject and author
                    if (book.size() == 0) { // book doesn't exist
                        modelAndView.addObject("noBook", "There is no book in this subject category written by this author."); // Display a message
                    } else { // book exists
                        modelAndView.addObject("searchBooks", book); // Display the book
                    }
                    return modelAndView;
                }
                else{ // search after author
                    Author a = createAuthor(author);
                    List<Book> book = bookRepository.findByAuthor(a); // find book after author
                    if (book.size() == 0) { // book doesn't exist
                        modelAndView.addObject("noBook", "There is no book written by this author."); // Display a message
                    } else { // book exists
                        modelAndView.addObject("searchBooks", book); // Display the book
                    }
                    return modelAndView;
                }
            }
        }
    }

    /**
     * Method which is called when the administrator wants to add a new book
     * @return page where the admin has to complete the fields of the new book
     */
    @RequestMapping(value = "/add")
    public ModelAndView goToAddBookPage(){
        return new ModelAndView("addBook");
    }

    /**
     * Method which creates BookItem objects and save them in a list
     * @param status
     * @param edition
     * @param pages
     * @param inventory
     * @param price
     * @return the page to continue adding the book
     */
    @RequestMapping(value = "/addBookItem")
    public ModelAndView addBookItems(@RequestParam String status, @RequestParam String edition,
                                     @RequestParam String pages, @RequestParam String inventory, @RequestParam String price){
        // create a new BookItem
        BookItem bookItem = createBookItem(Integer.parseInt(inventory), status, edition, Integer.parseInt(pages), Double.parseDouble(price));

        // add the BookItem object to the list
        bookItems.add(bookItem);
        return new ModelAndView("addBook");
    }

    /**
     * Method which adds a new valid book in the database
     * @param title
     * @param author
     * @param subject1
     * @param subject2
     * @param subject3
     * @param subject4
     * @param subject5
     * @param description
     * @param copies
     * @return the page to continue adding another book
     */
    @RequestMapping(value = "/addBook")
    public ModelAndView addBook(@RequestParam String title, @RequestParam String author, @RequestParam String subject1,
                                @RequestParam String subject2, @RequestParam String subject3, @RequestParam String subject4,
                                @RequestParam String subject5, @RequestParam String description, @RequestParam String copies){

        Author newAuthor = createAuthor(author); // create a new Author object
        Author existsAuthor = authorRepository.findByName(author); // search after this author in the database

        if(existsAuthor == null){ // if the author didn't exist before, save it in the database
            authorRepository.save(newAuthor);
        }

        // for each of the 5 fields for subjects, check if the field is empty. If not, create the Subject object
        // and add it in the list of subjects.
        Subject newSubject = createSubject(subject1);
        if(subject1 != null){
            subjects.add(newSubject);
        }

        newSubject = createSubject(subject2);
        if(subject2 != null){
            subjects.add(newSubject);
        }

        newSubject = createSubject(subject3);
        if(subject3 != null){
            subjects.add(newSubject);
        }

        newSubject = createSubject(subject4);
        if(subject4 != null){
            subjects.add(newSubject);
        }

        newSubject = createSubject(subject5);
        if(subject5 != null){
            subjects.add(newSubject);
        }

        // create a new book
        Book book = createBook(title, newAuthor, subjects, description, Integer.parseInt(copies), bookItems);

        // if the book doesn't exist before
        if(bookRepository.findByTitle(title) == null){
            bookRepository.save(book); // save the book in the database

            for(int i = 0; i < bookItems.size();i++){ // save every BookItem in the database
                bookItemRepository.save(bookItems.get(i));
            }

            for(int i = 0; i < subjects.size();i++){ // save every Subject in the database, if it doesn't already exists
                Subject existsSubject = subjectRepository.findByName(subjects.get(i).getName());
                if(existsSubject == null) {
                    subjectRepository.save(subjects.get(i));
                }
            }

            // clear the lists for BookItem and Subject objects
            bookItems.clear();
            subjects.clear();
            return new ModelAndView("addBook");
        }
        else{ // book exists
            // doesn't save anything and just clear the lists
            bookItems.clear();
            subjects.clear();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("addBook");
            modelAndView.addObject("exists", "This book already exists in the library. You can go and update it.");
            return  modelAndView;
        }
    }

    /**
     * Method which gets all the books from the database and shows them
     * @return the page with all the books
     */
    @RequestMapping(value = "viewAll")
    public ModelAndView viewAllBooks(){
        // find all books
        List<Book> allBooks = bookRepository.findAll();

        // display the books
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("administratorPage");
        modelAndView.addObject("allBooks", allBooks);
        return modelAndView;
    }

    /**
     * Method which deletes a book with all its book items
     * @param title
     * @return
     */
    @RequestMapping(value = "/delete/{title}")
    public ModelAndView deleteBook(@PathVariable String title){
        // find the book which want to delete
        Book book = bookRepository.findByTitle(title);

        // find the list of book item of the book
        List<BookItem> bookItems = book.getBookItem();

        for(int i = 0; i < bookItems.size(); i++){
            //delete all book items
            bookItemRepository.deleteByInventoryNo(bookItems.get(i).getInventoryNo());
        }

        // delete the book
        bookRepository.delete(book);

        return new ModelAndView("redirect:/viewAll");
    }

    /**
     * Method which displays the details of a book
     * @param title
     * @return
     */
    @RequestMapping(value = "/details/{title}")
    public ModelAndView bookDetails(@PathVariable String title){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookDetails");

        Book book = bookRepository.findByTitle(title); // find the book for which you want to see the details
        bookTitle = title;

        // display title, author, subjects, description and no of copies of the book
        modelAndView.addObject("title", "Details of the book '" + title + "'");
        modelAndView.addObject("author", "Author: " + book.getAuthor().getName());
        modelAndView.addObject("subject", "Subjetcs: " + book.getAllSubjects());
        modelAndView.addObject("description", "Description: " + book.getDescription());
        modelAndView.addObject("copies", "Number of copies: " + book.getNoOfCopies());

        // find the list with book items of the book
        List<BookItem> bookItems = book.getBookItem();
        modelAndView.addObject("allBookItems", bookItems);

        return modelAndView;
    }

    /**
     * Method which deletes a book item of a book
     * @param inventoryNo
     * @return
     */
    @RequestMapping(value = "/details/delete/{inventoryNo}")
    public ModelAndView deleteBookItem(@PathVariable String inventoryNo){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookDetails");

        int inventNo = Integer.parseInt(inventoryNo);
        BookItem bookItem = bookItemRepository.findByInventoryNo(inventNo); // find the book after the inventory no
        bookItemRepository.delete(bookItem); // delete the book item from the database

        Book book = bookRepository.findByTitle(bookTitle); // find the book which contains the book item deleted
        List<BookItem> bookItemList = book.getBookItem(); // find the list of the book items

        for(int i = 0; i < bookItemList.size(); i++){
            if(inventNo == bookItemList.get(i).getInventoryNo()){ // delete the book item from the list of the book
                bookItemList.remove(i);
            }
        }

        bookRepository.delete(book); // delete the book from the database
        book.setBookItem(bookItemList); // set for the same book the updated list with book items
        book.setNoOfCopies(bookItemList.size()); // update the no of copies
        bookRepository.save(book); // save the updated book in the database

        modelAndView = bookDetails(bookTitle);
        return modelAndView;
    }

    /*@RequestMapping(value = "/details/update/{inventoryNo}")
    public ModelAndView updateBookItem(@PathVariable String inventoryNo, @RequestParam String updateStatus,
                                       @RequestParam String updateEdition,
                                       @RequestParam String updatePages,
                                       @RequestParam String updatePrice){
        ModelAndView modelAndView = new ModelAndView();

        System.out.println(updatePrice + " " + updatePages + " " + updateStatus);
        int inventNo = Integer.parseInt(inventoryNo);
        int noPages = Integer.parseInt(updatePages);
        double bookPrice = Double.parseDouble(updatePrice);
        BookItem bookItem = bookItemRepository.findByInventoryNo(inventNo);

        System.out.println(inventoryNo);
        bookItem.setInventoryNo(inventNo);
        bookItem.setStatus(updateStatus);
        bookItem.setEdition(updateEdition);
        bookItem.setNoOfPages(noPages);
        bookItem.setPrice(bookPrice);

        bookItemRepository.deleteByInventoryNo(inventNo);
        bookItemRepository.save(bookItem);

        return new ModelAndView("redirect:/details");
    }*/
}
