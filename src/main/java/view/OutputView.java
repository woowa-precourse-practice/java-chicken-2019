package view;

import domain.Menu;
import domain.Table;
import dto.OrdersResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE_WITH_NO_ORDER = "└ ─ ┘";
    private static final String BOTTOM_LINE_WITH_ORDER = "└ # ┘";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PAYMENT_PROCESS_FORMAT = "## %d번 테이블의 결제를 진행합니다.";
    private static final String PAYMENT_FORMAT = "%d원";
    private static final String TOTAL_PAYMENT_IS = "## 최종 결제할 금액";
    private static final String ORDER_LIST_IS = "## 주문 내역";
    private static final String ORDER_INFO = "메뉴 수량 금액";
    private static final String TABLES_ARE = "## 테이블 목록";

    public static void printTables(final List<Table> tables) {
        System.out.println(TABLES_ARE);
        printTopLine(tables);
        printTableNumbers(tables);
        printBottomLine(tables);
    }

    private static void printTopLine(List<Table> tables) {
        String topLine = Stream.generate(() -> TOP_LINE)
                .limit(tables.size())
                .collect(Collectors.joining());

        System.out.println(topLine);
    }

    private static void printBottomLine(List<Table> tables) {
        String bottomLine = tables.stream()
                .map(OutputView::convertToLine)
                .collect(Collectors.joining());

        System.out.println(bottomLine);
    }

    private static String convertToLine(Table table) {
        if (table.hasNoOrders()) {
            return BOTTOM_LINE_WITH_NO_ORDER;
        }
        return BOTTOM_LINE_WITH_ORDER;
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printOrders(final OrdersResponseDto responseDto) {
        System.out.println(ORDER_LIST_IS);
        System.out.println(ORDER_INFO);

        responseDto.get()
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printPayment(final int payment) {
        System.out.println(TOTAL_PAYMENT_IS);
        System.out.printf(PAYMENT_FORMAT, payment);
        System.out.println();
        System.out.println();
    }

    public static void printPaymentProcess(final int tableNumber) {
        System.out.printf(PAYMENT_PROCESS_FORMAT, tableNumber);
        System.out.println();
    }

    public static void printError(IllegalArgumentException error) {
        System.out.print(ERROR_PREFIX);
        System.out.println(error.getMessage());
    }
}
