package view;

import domain.Menu;
import domain.OrderRepository;
import domain.Table;
import dto.OrdersResponseDto;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE_WITH_NO_ORDER = "└ ─ ┘";
    private static final String BOTTOM_LINE_WITH_ORDER = "└ # ┘";

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printLine(getBottomLine(tables), size);
    }

    private static String getBottomLine(final List<Table> tables) {
        for (Table table : tables) {
            if (OrderRepository.hasTable(table)) {
                return BOTTOM_LINE_WITH_ORDER;
            }
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
        System.out.println(payment);
        System.out.println();
    }
}
