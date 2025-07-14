package lv3;

public enum OperatorType {
    ADD("+"),       // 덧셈
    SUBTRACT("-"),  // 뺄셈
    MULTIPLY("*"),  // 곱셈
    DIVIDE("/");    // 나눗셈

    private final String symbol;

    OperatorType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static OperatorType fromSymbol(String symbol) {
        for (OperatorType type : OperatorType.values()) {
            if (type.getSymbol().equals(symbol)) {
                return type; // 일치하는 상수를 찾으면 반환
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산 기호입니다: " + symbol);
    }
}