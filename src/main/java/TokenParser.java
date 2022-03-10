import java.util.ArrayList;
import java.util.List;

public class TokenParser {

    private Token token;

    private List<Token> recognizedToken;

    public List<Token> parseIntoToken(String seq) {
        token = new Token();
        recognizedToken = new ArrayList<>();
        DfaState state = init(seq.charAt(0));
        for (int i = 1; i < seq.length(); i++) {
            state = parse(state, seq.charAt(i));
        }
        if (token.getText().length() > 0) {
            recognizedToken.add(token);
        }
        return new ArrayList<>(recognizedToken);
    }

    private DfaState parse(DfaState state, char c) {
        DfaState newState = DfaState.Init;
        switch (state) {
            case Init:
            case GE:
            case Int:
            case Assignment:
            case Plus:
            case Multiply:
                newState = init(c);
                break;
            case GT:
                if (c == '=') {
                    newState = DfaState.GE;
                    token.type(TokenType.GE);
                    token.appendText(c);
                } else {
                    newState = init(c);
                }
                break;
            case ID:
                if (isAlpha(c) || isDigit(c)) {
                    newState = DfaState.ID;
                    token.appendText(c);
                } else {
                    newState = init(c);
                }
                break;
            case Int_1:
                if (c == 'n') {
                    newState = DfaState.Int_2;
                    token.appendText(c);
                } else {
                    newState = DfaState.ID;
                    token.appendText(c);
                }
                break;
            case Int_2:
                if (c == 't') {
                    token.appendText(c);
                    newState = DfaState.Int_3;
                } else if (isDigit(c) || isAlpha(c)) {
                    token.appendText(c);
                    newState = DfaState.ID;
                } else {
                    newState = init(c);
                }
                break;
            case Int_3:
                if (c == ' ') {
                    token.type(TokenType.INT);
                    token.appendText(c);
                    newState = init(c);
                } else {
                    token.type(TokenType.ID);
                    token.appendText(c);
                    newState = DfaState.ID;
                }
                break;
            case IntLiteral:
                if (isDigit(c)) {
                    token.appendText(c);
                } else {
                    newState = init(c);
                }
                break;
        }
        return newState;
    }

    private DfaState init(char c) {
        if (token.getText().length() > 0) {
            recognizedToken.add(token);
            token = new Token();
        }

        DfaState state = DfaState.Init;
        if (isDigit(c)) {
            state = DfaState.IntLiteral;
            token.type(TokenType.IntLiteral);
            token.appendText(c);
        } else if (isAlpha(c)) {
            if (c == 'i') {
                state = DfaState.Int_1;
            } else {
                state = DfaState.ID;
            }
            token.type(TokenType.ID);
            token.appendText(c);
        } else if (c == '>') {
            state = DfaState.GT;
            token.type(TokenType.GT);
            token.appendText(c);
        } else if (c == '=') {
            state = DfaState.Assignment;
            token.type(TokenType.ASSIGNMENT);
            token.appendText(c);
        } else if (c == '+') {
            state = DfaState.Plus;
            token.type(TokenType.PLUS);
            token.appendText(c);
        } else if (c == '*') {
            state = DfaState.Multiply;
            token.type(TokenType.MULTIPLY);
            token.appendText(c);
        }
        return state;

    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}