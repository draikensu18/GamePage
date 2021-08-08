package app.game;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

public class GameDao {

    private final List<Game> games = ImmutableList.of(
        new Game("Moby Dick", "Herman Melville", "9789583001215"),
        new Game("A Christmas Carol", "Charles Dickens", "9780141324524"),
        new Game("Pride and Prejudice", "Jane Austen", "9781936594290"),
        new Game("The Fellowship of The Ring", "J. R. R. Tolkien", "0007171978"),
        new Game("Harry Potter", "J. K. Rowling", "0747532699"),
        new Game("War and Peace", "Leo Tolstoy", "9780060798871"),
        new Game("Don Quixote", "Miguel Cervantes", "9789626345221"),
        new Game("Ulysses", "James Joyce", "9780394703800"),
        new Game("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"),
        new Game("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "9780060531041"),
        new Game("The adventures of Huckleberry Finn", "Mark Twain", "9781591940296"),
        new Game("Alice In Wonderland", "Lewis Carrol", "9780439291491")
    );

    public Iterable<Game> getAllBooks() {
        return games;
    }

    public Game getBookByIsbn(String isbn) {
        return games.stream().filter(b -> b.isbn.equals(isbn)).findFirst().orElse(null);
    }

    public Game getRandomBook() {
        return games.get(new Random().nextInt(games.size()));
    }
}
