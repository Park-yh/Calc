package lv2;



import java.util.ArrayList;

import java.util.List;



public class Calculator {

    private List<Double> results;



    public Calculator() {

        this.results = new ArrayList<>();

    }



    public double calculate(int num1, int num2, char operator) {

        double result = 0;



        switch (operator){

            case '+': //덧셈 계산

                result = (double) num1 + num2;

                break;



            case '-': // 뺄셈 계산

                result = (double) num1 - num2;

                break;



            case '*': //곱셈 계산

                result = (double) num1 * num2;

                break;



            case '/': //나눗셈 계산

                if(num2 != 0) result = (double) num1 / num2;

                else {

                    System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");

                }

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

            results.removeFirst();

            System.out.println("가장 오래된 연산 결과가 삭제되었습니다.");

        } else {

            System.out.println("삭제할 연산 결과가 없습니다.");

        }

    }

}