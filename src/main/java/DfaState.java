public enum DfaState {

    Init("init"),
    ID("identifier"),
    GT(">"),
    GE(">="),
    Int_1("intOrIdentifier"),
    Int_2("intOrIdentifier"),
    Int_3("intOrIdentifier"),
    Assignment("="),
    Int("int"),
    IntLiteral("intNumber"),
    Plus("+"),
    Multiply("*");

    private String code;

    DfaState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
