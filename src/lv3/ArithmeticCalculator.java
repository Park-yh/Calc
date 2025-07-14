package lv3;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator {
    private List<Double> results;

    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }

    public double calculate(int num1, int num2, OperatorType operatorType) {
        double result = 0.0;

        switch (operatorType) {
            case ADD:
                result = (double) num1 + num2;
                break;
            case SUBTRACT:
                result = (double) num1 - num2;
                break;
            case MULTIPLY:
                result = (double) num1 * num2;
                break;
            case DIVIDE:
                if (num2 == 0) {
                    throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                }
                result = (double) num1 / num2;
                break;
        }
        results.add(result);
        return result;
    }

    public List<Double> getResults() {
        return new ArrayList<>(results);
    }

    public void setResults(List<Double> results) {
        this.results = new ArrayList<>(results);
    }

    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0);
            System.out.println("가장 오래된 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("삭제할 연산 결과가 없습니다.");
        }
    }
}