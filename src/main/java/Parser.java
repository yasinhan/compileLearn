import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Token token;

    private List<Token> recognizedToken;

    public List<Token> parseIntoToken(String seq) {
        token = new Token();
        recognizedToken = new ArrayList<>();
        DfaState state = init(seq.charAt(0));
        for (int i = 1; i < seq.length(); i++) {
            state = parse(state, seq.charAt(i));
        }
        return new ArrayList<>(recognizedToken);
    }

    private DfaState parse(DfaState state, char c) {
        DfaState newState = DfaState.Init;
        switch (state) {
            case Init:
            case GE:
            case Int:
                newState = init(c);
                break;
            case GT:
                if (c == '=') {
                    newState = DfaState.GE;
                    token.type(TokenType.GE);
                    token.append(c);
                } else {
                    newState = init(c);
                }
                break;
            case ID:
                if (isAlpha(c) || isDigit(c)) {
                    newState = DfaState.ID;
                    token.append(c);
                } else {
                    newState = init(c);
                }
                break;
            case Int_1:
                if (c == 'n') {
                    newState = DfaState.Int_2;
                    token.append(c);
                } else {
                    newState = DfaState.ID;
                    token.append(c);
                }
                break;
            case Int_2:
                if (c == 't') {
                    token.type(TokenType.IntLiteral);
                    token.append(c);
                    newState = DfaState.Int;
                } else if (isDigit(c) || isAlpha(c)) {
                    token.append(c);
                    newState = DfaState.ID;
                } else {
                    newState = init(c);
                }
                break;
            case IntLiteral:
                if (isDigit(c)) {
                    token.append(c);
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
            token.append(c);
        } else if (isAlpha(c)) {
            if (c == 'i') {
                state = DfaState.Int_1;
            } else {
                state = DfaState.ID;
            }
            token.type(TokenType.ID);
            token.append(c);
        } else if (c == '>') {
            state = DfaState.GT;
            token.type(TokenType.GT);
            token.append(c);
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