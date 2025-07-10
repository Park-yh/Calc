package lv2;



import java.util.ArrayList; // ArrayList import

import java.util.List;

import java.util.Scanner;



public class App {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        Scanner sc = new Scanner(System.in);

        String nextOperation = "";



        while (!nextOperation.equalsIgnoreCase("exit")) {

            System.out.print("첫 번째 숫자를 입력하세요: ");

            int num1 = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");

            int num2 = sc.nextInt();

            sc.nextLine();



// 사칙연산 기호 입력

            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");

            char operator = sc.nextLine().charAt(0);





            double currentResult = calculator.calculate(num1, num2, operator);

            System.out.println("결과: " + currentResult);



// 현재까지의 모든 연산 결과 출력

            List<Double> allResults = calculator.getResults();

            System.out.println("현재 저장된 모든 연산 결과 (원본): " + allResults);



// Setter를 이용한 값 수정

            if (!allResults.isEmpty()) {

                System.out.print("특정 연산 결과를 수정하시겠습니까? (y/n): ");

                String modifyChoice = sc.nextLine();



                if (modifyChoice.equalsIgnoreCase("y")) {

                    System.out.print("수정할 연산 결과의 인덱스를 입력하세요 (0부터 시작): ");

                    int indexToModify = sc.nextInt();

                    sc.nextLine();



                    System.out.print("새로운 값을 입력하세요: ");

                    double newValue = sc.nextDouble();

                    sc.nextLine();



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