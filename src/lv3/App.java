package lv3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArithmeticCalculator<Double> calculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);
        String nextOperation = "";

        while (!nextOperation.equalsIgnoreCase("exit")) {
            Double num1 = null;
            Double num2 = null;
            OperatorType operatorType = null;
            boolean validInput;

            validInput = false;
            while (!validInput) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                try {
                    num1 = sc.nextDouble();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("오류: 유효한 정수를 입력해주세요.");
                    sc.next();
                } finally {
                    sc.nextLine();
                }
            }

            validInput = false;
            while (!validInput) {
                System.out.print("두 번째 숫자를 입력하세요: ");
                try {
                    num2 = sc.nextDouble();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("오류: 유효한 정수를 입력해주세요.");
                    sc.next();
                } finally {
                    sc.nextLine();
                }
            }

            validInput = false;
            while (!validInput) {
                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                String opSymbol = sc.nextLine();
                try {
                    operatorType = OperatorType.fromSymbol(opSymbol);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("입력 오류: " + e.getMessage() + " (+, -, *, / 중 하나를 입력하세요)");
                }
            }

            try {
                double currentResult = calculator.calculate(num1, num2, operatorType);
                System.out.println("결과: " + currentResult);
            } catch (ArithmeticException e) {
                System.out.println("계산 오류: " + e.getMessage());
                continue;
            }

            // 현재까지의 모든 연산 결과 출력
            List<Double> allResults = calculator.getResults();
            System.out.println("현재 저장된 모든 연산 결과 (원본): " + allResults);

            // 람다 & 스트림을 활용한 조회 기능
            System.out.print("특정 값보다 큰 결과들을 조회하시겠습니까? (y/n): ");
            String filterChoice = sc.nextLine();
            if (filterChoice.equalsIgnoreCase("y")) {
                double filterValue = 0.0;
                validInput = false;
                while (!validInput) {
                    System.out.print("기준이 될 값을 입력하세요: ");
                    try {
                        filterValue = sc.nextDouble();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("오류: 유효한 숫자를 입력해주세요.");
                        sc.next();
                    } finally {
                        sc.nextLine();
                    }
                }
                List<Double> filteredResults = calculator.getResultsGreaterThan(filterValue);
                System.out.println(filterValue + "보다 큰 결과들: " + filteredResults);

                List<Double> lessThanOrEqualResults = calculator.getResultsLessThanOrEqualTo(filterValue);
                System.out.println(filterValue + "보다 작거나 같은 결과들: " + lessThanOrEqualResults);

                List<Double> evenIntResults = calculator.getEvenIntegerResults();
                System.out.println("짝수인 정수 결과들: " + evenIntResults);
            }

            // Setter를 이용한 값 수정
            if (!allResults.isEmpty()) {
                System.out.print("특정 연산 결과를 수정하시겠습니까? (y/n): ");
                String modifyChoice = sc.nextLine();

                if (modifyChoice.equalsIgnoreCase("y")) {
                    int indexToModify = -1;
                    double newValue = 0.0;

                    validInput = false;
                    while (!validInput) {
                        System.out.print("수정할 연산 결과의 인덱스를 입력하세요 (0부터 시작): ");
                        try {
                            indexToModify = sc.nextInt();
                            if (indexToModify >= 0 && indexToModify < allResults.size()) {
                                validInput = true;
                            } else {
                                System.out.println("오류: 유효하지 않은 인덱스입니다. 범위 내에서 다시 입력하세요.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("오류: 숫자를 입력해주세요.");
                            sc.next();
                        } finally {
                            sc.nextLine();
                        }
                    }

                    validInput = false;
                    while (!validInput) {
                        System.out.print("새로운 값을 입력하세요: ");
                        try {
                            newValue = sc.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("오류: 유효한 숫자를 입력해주세요.");
                            sc.next();
                        } finally {
                            sc.nextLine();
                        }
                    }

                    List<Double> tempResults = new ArrayList<>(allResults);
                    tempResults.set(indexToModify, newValue);
                    calculator.setResults(tempResults);
                    System.out.println("수정 후 현재 저장된 모든 연산 결과: " + calculator.getResults());
                }
            }

            // 가장 오래된 연산 결과 삭제 기능
            System.out.print("가장 오래된 연산 결과를 삭제하시겠습니까? (y/n): ");
            String deleteChoice = sc.nextLine();
            if (deleteChoice.equalsIgnoreCase("y")) {
                calculator.removeFirstResult();
                System.out.println("삭제 후 현재까지의 연산 결과들: " + calculator.getResults());
            }

            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            nextOperation = sc.nextLine();
        }

        System.out.println("계산기를 종료합니다.");
        sc.close();
    }
}