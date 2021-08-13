package app.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDao {

    private final List<Game> games;

    {
        games = new ArrayList<>();
        games.add(new Game("BlackJack", "u18", "blackjack"));
        games.add(new Game("Higher Or Lower", "u12", "higherorlower"));
        games.add(new Game("Slots", "u15", "slots"));
        games.add(new Game("Tic Tac Toe", "u16", "tictactoe"));
        games.add(new Game("Baccarat", "u21", "baccarat"));
    }

    public Iterable<Game> getAllGames() {
        return games;
    }

    public Game getBookByIsbn(String isbn) {
        return games.stream().filter(b -> b.isbn.equals(isbn)).findFirst().orElse(null);
    }

    public Game getRandomBook() {
        return games.get(new Random().nextInt(games.size()));
    }
}
