import java.util.List;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        String code = "int age = 5";
        List<Token> tokens = parser.parseIntoToken(code);
        for (Token token : tokens) {
            System.out.println(token);
        }

    }
}
