import java.util.List;

public class Main {

    public static void main(String[] args) {
        TokenParser tokenParser = new TokenParser();
        String code = "int intA = 2 * 3 + 5";
        List<Token> tokens = tokenParser.parseIntoToken(code);
        for (Token token : tokens) {
            System.out.println(token);
        }

    }
}
