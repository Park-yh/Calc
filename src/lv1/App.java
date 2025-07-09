package lv1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        String nextop = "";

        while(!nextop.equalsIgnoreCase("exit")){
            // 양의 정수 2개 입력
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();
            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            sc.nextLine();

            // 원하는 사칙연산 입력
            System.out.print("사칙연산 기호를 입력하세요: ");
            char operator = sc.next().charAt(0);

            // 연산
            switch (operator){
                case '+': //덧셈 계산
                    result = num1 + num2;
                    break;

                case '-': // 뺄셈 계산
                    result = num1 - num2;
                    break;

                case '*': //곱셈 계산
                    result = num1 * num2;
                    break;

                case '/': //나눗셈 계산
                    if(num2 != 0) result = num1 / num2;
                    else {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                        continue;
                    }
                    break;
            }
            System.out.println("결과: " + result);

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            sc.nextLine();
            nextop = sc.nextLine();
        }
        System.out.println("계산기를 종료합니다.");
        sc.close();
    }
}