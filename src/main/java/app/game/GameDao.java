package app.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDao {

    private final List<Game> games;

    {
        games = new ArrayList<>();
        games.add(new Game("BlackJack", "u18", "9789583001215"));
        games.add(new Game("Higher Or Lower", "u12", "9780141324524"));
        games.add(new Game("Slots", "u15", "9781936594290"));
        games.add(new Game("Tic Tac Toe", "u16", "9781936594290"));
        games.add(new Game("Jumping Cards", "u21", "9781936594290"));
    }

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
