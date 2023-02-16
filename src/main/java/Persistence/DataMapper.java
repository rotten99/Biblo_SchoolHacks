package Persistence;

import Entities.Author;
import Entities.Book;
import Entities.Borrower;
import Entities.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataMapper {


    //Returns a borrower from a given id
    static Borrower getBorrowerFromId(int id) {

        Borrower borrower = null;
        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM laaner where laaner_id = ?");
            }

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String name = result.getString("navn");
                String address = result.getString("adresse");
                int postalCode = result.getInt("postnr");
                borrower = new Borrower(id, name, address, postalCode);
                List<Loan> loanList = getAllLoansFromBorrowerID(borrower);
                borrower.setLoanList(loanList);
            }

        } catch (SQLException e) {
            System.out.println("A borrower with that id does not exit");
            //e.printStackTrace();
        }
        return borrower;
    }

    //Returns a list of borrowers
    static List<Borrower> getAllBorrowers() {

        List<Borrower> borrowerList = new ArrayList<>();

        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM laaner");
            }

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("laaner_id");
                String name = result.getString("navn");
                String address = result.getString("adresse");
                int postalCode = result.getInt("postnr");
                Borrower borrower = new Borrower(id, name, address, postalCode);
                List<Loan> loanList = getAllLoansFromBorrowerID(borrower);
                borrower.setLoanList(loanList);
                borrowerList.add(borrower);
            }

        } catch (SQLException e) {
            System.out.println("No borrowers were found");
            //e.printStackTrace();
        }
        return borrowerList;
    }


    //Returns all books with their author

    static List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM bog");
            }

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("bog_id");
                String title = result.getString("titel");
                int year = result.getInt("udgivelsesar");
                int author_id = result.getInt("forfatter_id");
                Author author = getAuthorFromId(author_id);
                Book book = new Book(id, title, year, author);
                bookList.add(book);
            }

        } catch (SQLException e) {
            System.out.println("No books were found");
            //e.printStackTrace();
        }
        return bookList;
    }


    //Returns an author from a given id
    static private Author getAuthorFromId(int id) {
        Author author = null;
        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM forfatter where forfatter_id = ?");
            }

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String name = result.getString("navn");
                author = new Author(id, name);
            }

        } catch (SQLException e) {
            System.out.println("An author with that id does not exit");
            //e.printStackTrace();
        }
        return author;
    }

    static private List<Loan> getAllLoansFromBorrowerID(Borrower borrower) {
        List<Loan> loanList = new ArrayList<>();
        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM udlaan where laaner_id = ?");
            }

            statement.setInt(1, borrower.getId());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Book book = getBookFromId(result.getInt("bog_id"));
                Date date = result.getDate("dato");
                Loan loan = new Loan(book, borrower, date);
                loanList.add(loan);
            }

        } catch (SQLException e) {
            System.out.println("No loans were found");
            //e.printStackTrace();
        }
        return loanList;
    }


    static private List<Book> getBooksFromBorrowerId(int id) {

        List<Book> bookList = new ArrayList<>();

        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM udlån where laaner_id =?");
            }

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int book_id = result.getInt("bog_id");
                Book book = getBookFromId(book_id);
                bookList.add(book);
            }

        } catch (SQLException e) {
            System.out.println("No books were found");
            //e.printStackTrace();
        }
        return bookList;
    }

    static Book getBookFromId(int id) {
        Book book = null;

        PreparedStatement statement;
        try {
            Connection connection = ConnectionConfiguration.getConnection();
            {
                statement = connection.prepareStatement("SELECT * FROM bog where bog_id=?");
            }

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String title = result.getString("titel");
                int year = result.getInt("udgivelsesar");
                int author_id = result.getInt("forfatter_id");
                Author author = getAuthorFromId(author_id);
                book = new Book(id, title, year, author);

            }

        } catch (SQLException e) {
            System.out.println("No books were found");
            //e.printStackTrace();
        }
        return book;
    }


    public static void createBorrower(String name, String address, int postalCode) {
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO laaner (navn, adresse, postnr)" +
                    " VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setInt(3, postalCode);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Name has already been registered");
//            e.printStackTrace();
        }
    }

    public static void createNewLoan(Book book, Borrower borrower) {
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("INSERT INTO udlaan (bog_id, laaner_id, dato)" +
                    " VALUES(?,?,?)");
            statement.setInt(1, book.getId());
            statement.setInt(2, borrower.getId());
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Loan was not created ");
//            e.printStackTrace();
        }
    }

    public static void deleteLoanFromIds(Book book, Borrower borrower) {
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("DELETE from udlaan where bog_id=? and laaner_id=?");
            statement.setInt(1, book.getId());
            statement.setInt(2, borrower.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Loan was not created ");
//            e.printStackTrace();
        }
    }

    public static void updateBookTitle(Book book, String newTitle) {
        try {
            Connection connection = ConnectionConfiguration.getConnection();

            //Saves the users information
            PreparedStatement statement = connection.prepareStatement("update bog set titel = ? where bog_id = ?");
            statement.setString(1, newTitle);
            statement.setInt(2, book.getId());



            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update was not made");
//            e.printStackTrace();
        }
    }
}
