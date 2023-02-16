package Entities;

public class Book {

    private int id;
    private String title;
    private int yearOfPublication;
    private Author author;

    public Book(int id, String title, int yearOfPublication, Author author) {
        this.id = id;
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", author=" + author +
                '}'+"\n";
    }
}
