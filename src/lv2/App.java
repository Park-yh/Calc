package lv2;

import java.util.ArrayList;
import java.util.InputMismatchException; // InputMismatchException 처리용
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        String nextOperation = "";

        while (!nextOperation.equalsIgnoreCase("exit")) {
            int num1 = 0;
            int num2 = 0;
            char operator = ' ';
            boolean validInput;

            // 첫 번째 숫자 입력
            validInput = false;
            while (!validInput) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                try {
                    num1 = sc.nextInt();
                    validInput = true; // 유효한 정수 입력 시 루프 종료
                } catch (InputMismatchException e) {
                    System.out.println("오류: 유효한 정수를 입력해주세요.");
                    sc.next();
                } finally {
                    sc.nextLine();
                }
            }

            // 두 번째 숫자 입력
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

            // 사칙연산 기호 입력
            validInput = false;
            while (!validInput) {
                System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
                String opStr = sc.nextLine();
                if (opStr.length() == 1) {
                    operator = opStr.charAt(0);
                    if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
                        validInput = true;
                    } else {
                        System.out.println("오류: 유효하지 않은 사칙연산 기호입니다. (+, -, *, / 중 하나를 입력하세요)");
                    }
                } else {
                    System.out.println("오류: 하나의 사칙연산 기호만 입력해주세요.");
                }
            }

            try {
                double currentResult = calculator.calculate(num1, num2, operator);
                System.out.println("결과: " + currentResult);
            } catch (ArithmeticException e) {
                System.out.println("계산 오류: " + e.getMessage());
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("입력 오류: " + e.getMessage());
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

                    // 인덱스 입력
                    validInput = false;
                    while (!validInput) {
                        System.out.print("수정할 연산 결과의 인덱스를 입력하세요 (0부터 시작): ");
                        try {
                            indexToModify = sc.nextInt();
                            // 입력된 인덱스가 리스트 범위 내에 있는지 확인
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

                    // 새로운 값 입력
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

                    List<Double> tempResults = new ArrayList<>(allResults); // 복사본 생성
                    tempResults.set(indexToModify, newValue); // 특정 인덱스 값 수정
                    calculator.setResults(tempResults); // Setter로 변경된 리스트 설정
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