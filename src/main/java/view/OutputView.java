package view;

import domain.Menu;
import domain.Table;
import dto.OrdersResponseDto;
import repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE_WITH_NO_ORDER = "└ ─ ┘";
    private static final String BOTTOM_LINE_WITH_ORDER = "└ # ┘";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printBottomLine(tables);
    }

    private static void printBottomLine(List<Table> tables) {
        String bottomLine = tables.stream()
                .map(OutputView::convertToLine)
                .collect(Collectors.joining());

        System.out.println(bottomLine);
    }

    private static String convertToLine(Table table) {
        if (OrderRepository.hasTable(table)) {
            return BOTTOM_LINE_WITH_ORDER;
        }
        return BOTTOM_LINE_WITH_NO_ORDER;
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printOrders(final OrdersResponseDto responseDto) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");

        responseDto.get()
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printPayment(final int payment) {
        System.out.println("## 최종 결제할 금액");
        System.out.println(payment + "원");
        System.out.println();
    }

    public static void printPaymentProcess(final int tableNumber) {
        System.out.printf("## %d번 테이블의 결제를 진행합니다.", tableNumber);
        System.out.println();
    }

    public static void printError(IllegalArgumentException error) {
        System.out.print(ERROR_PREFIX);
        System.out.println(error.getMessage());
    }
}
