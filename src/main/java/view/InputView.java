package view;

import command.MainCommand;
import domain.Quantity;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
    private static final String MAIN_COMMAND_IS = "## 메인화면";
    private static final String REQUEST_MAIN_COMMAND = "## 원하는 기능을 선택하세요.";
    private static final String REQUEST_TABLE_FOR_ORDER = "## 주문할 테이블을 선택하세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public static MainCommand inputMainCommand() {
        printMain();
        System.out.println(REQUEST_MAIN_COMMAND);

        return MainCommand.from(scanner.nextInt());
    }

    private static void printMain() {
        System.out.println(MAIN_COMMAND_IS);
        Arrays.stream(MainCommand.values())
                .forEach(System.out::println);
        System.out.println();
    }

    public static int inputTableNumber() {
        System.out.println(REQUEST_TABLE_FOR_ORDER);

        return scanner.nextInt();
    }

    public static Quantity inputQuantity() {
        System.out.println("## 메뉴의 수량을 입력하세요.");

        return Quantity.from(scanner.nextInt());
    }
}
