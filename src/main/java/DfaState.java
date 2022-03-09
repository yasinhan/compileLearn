public enum DfaState {

    Init("init"),
    ID("identifier"),
    GT(">"),
    GE(">="),
    Int_1("intOrIdentifier"),
    Int_2("intOrIdentifier"),
    Int("int"),
    IntLiteral("intNumber");

    private String code;

    DfaState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
