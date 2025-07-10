package lv2;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Double> results;

    public Calculator() {
        this.results = new ArrayList<>();
    }

    public double calculate(int num1, int num2, char operator) {
        double result;

        switch (operator) {
            case '+': // 덧셈 계산
                result = (double) num1 + num2;
                break;
            case '-': // 뺄셈 계산
                result = (double) num1 - num2;
                break;
            case '*': // 곱셈 계산
                result = (double) num1 * num2;
                break;
            case '/': // 나눗셈 계산
                if (num2 == 0) {
                    // 0으로 나누는 경우 ArithmeticException 발생시킴
                    throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                }
                result = (double) num1 / num2;
                break;
            default:
                // 유효하지 않은 연산 기호의 경우 IllegalArgumentException 발생시킴
                throw new IllegalArgumentException("유효하지 않은 사칙연산 기호입니다.");
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
            results.remove(0); // 가장 오래된 (인덱스 0) 요소 삭제
            System.out.println("가장 오래된 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("삭제할 연산 결과가 없습니다.");
        }
    }
}