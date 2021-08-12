package app.util;

public class Path {

    public static class Web {
        public static final String INDEX = "/index";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String BOOKS = "/books";
        public static final String ONE_BOOK = "/books/:isbn";
        public static final String REGISTER = "/register";
        public static final String BLACKJACK = "/blackjack";
        public static final String BLACKJACKGAME = "/blackjack/game";
        public static final String SLOTS = "/slots";
    }

    public static class Template {
        public static final String INDEX = "/velocity/index/index.vm";
        public static final String LOGIN = "/velocity/login/login.vm";
        public static final String BOOKS_ALL = "/velocity/game/all.vm";
        public static final String BOOKS_ONE = "/velocity/game/one.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
        public static final String REGISTER = "/velocity/register/register.vm";
        public static final String BLACKJACKTEMPLATE = "/velocity/game/BlackJack.vm";
        public static final String BLACKJACKGAMETEMPLATE = "/velocity/game/BlackJackGame.vm";
        public static final String SLOTSTEMPLATE = "/velocity/game/slots.vm";
    }

}
