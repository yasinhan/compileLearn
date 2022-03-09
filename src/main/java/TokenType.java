public enum TokenType {

    ID("ID"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    INT("int"),
    IntLiteral("intNumber");

    private String code;

    TokenType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
