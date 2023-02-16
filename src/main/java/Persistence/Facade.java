package Persistence;

import Entities.Book;
import Entities.Borrower;

import java.util.List;

public class Facade {
    public static Borrower getBorrowerFromId(int id){
        return DataMapper.getBorrowerFromId(id);
    }

    public static List<Borrower> getAllBorrowers(){
        return DataMapper.getAllBorrowers();
    }

    public static List<Book> getAllBooks(){
        return DataMapper.getAllBooks();
    }


    public static void createBorrower(String name, String address, int postalCode) {
        DataMapper.createBorrower(name, address ,postalCode);
    }

    public static Book getBookFromId(int id){
        return DataMapper.getBookFromId(id);
    }

    public static void createNewLoanFrom(Book book, Borrower borrower) {
        DataMapper.createNewLoan(book,borrower);
    }

    public static void deleteLoanFromIds(Book book, Borrower borrower) {
        DataMapper.deleteLoanFromIds(book,borrower);
    }

    public static void updateBookTitle(Book book, String newTitle) {
        DataMapper.updateBookTitle(book,newTitle);
    }
}
