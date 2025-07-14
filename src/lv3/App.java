package lv3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);
        String nextOperation = "";

        while (!nextOperation.equalsIgnoreCase("exit")) {
            int num1 = 0;
            int num2 = 0;
            OperatorType operatorType = null;
            boolean validInput;

            // 숫자 입력 및 유효성 검사 루프
            validInput = false;
            while (!validInput) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                try {
                    num1 = sc.nextInt();
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
                    num2 = sc.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("오류: 유효한 정수를 입력해주세요.");
                    sc.next();
                } finally {
                    sc.nextLine();
                }
            }

            // 사칙연산 기호 입력 및 OperatorType 변환 루프
            validInput = false;
            while (!validInput) {
                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                String opSymbol = sc.nextLine();
                try {
                    // OperatorType.fromSymbol() 정적 팩토리 메서드를 사용하여 Enum 값으로 변환
                    operatorType = OperatorType.fromSymbol(opSymbol);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("입력 오류: " + e.getMessage() + " (+, -, *, / 중 하나를 입력하세요)");
                }
            }

            try {
                // calculate 메서드에 OperatorType Enum 값을 전달
                double currentResult = calculator.calculate(num1, num2, operatorType);
                System.out.println("결과: " + currentResult);
            } catch (ArithmeticException e) {
                System.out.println("계산 오류: " + e.getMessage());
                continue;
            }

            // 현재까지의 모든 연산 결과 출력
            List<Double> allResults = calculator.getResults();
            System.out.println("현재 저장된 모든 연산 결과 (원본): " + allResults);

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