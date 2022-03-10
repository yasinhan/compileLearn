public class Token {

    private TokenType type;

    private String text = "";

    public void appendText(char c) {
        text = text + c;
    }

    public void type(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", text='" + text + '\'' +
                '}';
    }
}
