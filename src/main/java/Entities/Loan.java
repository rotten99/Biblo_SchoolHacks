package Entities;

import java.util.Date;

public class Loan {

    private Book book;
    private Borrower borrower;
    private Date date;

    public Loan(Book book, Borrower borrower, Date date) {
        this.book = book;
        this.borrower = borrower;
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book +
                ", date=" + date +
                '}';
    }
}
