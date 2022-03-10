import java.util.LinkedList;

public class TokenReader {

    private LinkedList<Token> tokens = new LinkedList<>();

    public Token peek() {
        return tokens.peek();
    }

    public Token read() {
        return tokens.poll();
    }

    public boolean add(Token t) {
        return tokens.add(t);
    }
}
