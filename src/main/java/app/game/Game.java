package app.game;


public class Game {
    public final String isbn;
    public final String title;
    public final String author;

    public String getMediumCover() {
        return this.isbn + ".png";
    }

    public String getLargeCover() {
        return this.isbn + ".png";
    }

    public Game(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
