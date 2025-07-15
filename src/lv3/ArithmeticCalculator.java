package lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticCalculator<T extends Number> {
    private List<Double> results;

    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }

    public double calculate(T num1, T num2, OperatorType operatorType) {
        double result = 0.0;
        double doubleNum1 = num1.doubleValue();
        double doubleNum2 = num2.doubleValue();

        switch (operatorType) {
            case ADD:
                result = doubleNum1 + doubleNum2;
                break;
            case SUBTRACT:
                result = doubleNum1 - doubleNum2;
                break;
            case MULTIPLY:
                result = doubleNum1 * doubleNum2;
                break;
            case DIVIDE:
                if (doubleNum2 == 0) {
                    throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                }
                result = doubleNum1 / doubleNum2;
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
    public List<Double> getResultsGreaterThan(double value) {
        return results.stream()
                .filter(result -> result > value)
                .collect(Collectors.toList());
    }

    public List<Double> getResultsLessThanOrEqualTo(double value) {
        return results.stream()
                .filter(result -> result <= value)
                .collect(Collectors.toList());
    }

    public List<Double> getEvenIntegerResults() {
        return results.stream()
                .filter(result -> result % 1 == 0)
                .filter(result -> result.intValue() % 2 == 0)
                .collect(Collectors.toList());
    }
}