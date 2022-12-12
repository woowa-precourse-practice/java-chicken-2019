package controller;

import command.MainCommand;
import domain.Menu;
import domain.Order;
import domain.Orders;
import domain.PaymentMethod;
import domain.Quantity;
import domain.Table;
import dto.OrdersResponseDto;
import repository.MenuRepository;
import repository.OrderRepository;
import repository.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MainController {

    private static final String NO_ORDERS_ERROR = "주문 내역이 존재하지 않습니다.";

    public void run() {
        while (true) {
            MainCommand mainCommand = checkError(InputView::inputMainCommand);
            if (mainCommand.isOrder()) {
                progressOrder();
            }
            if (mainCommand.isPayment()) {
                progressPayment();
            }
            if (mainCommand.isQuit()) {
                break;
            }
        }
    }

    private void progressPayment() {
        Table table = checkError(this::readTable);
        Orders orders = checkError(() -> createOrder(table));
        printOrders(orders);

        OutputView.printPaymentProcess(table.getNumber());
        int totalPayment = calculatePayment(orders);

        OutputView.printPayment(totalPayment);
        OrderRepository.clearByTable(table);
    }

    private Orders createOrder(Table table) {
        Set<Order> orders = OrderRepository.findByTable(table);
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(NO_ORDERS_ERROR);
        }
        return Orders.create(orders);
    }

    private void progressOrder() {
        Table table = checkError(this::readTable);
        Menu menu = checkError(this::readMenu);
        Quantity quantity = checkError(InputView::inputQuantity);

        Order order = Order.create(table, menu, quantity);
        OrderRepository.save(order);
    }

    private void printTables() {
        List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);
    }

    private Table readTable() {
        printTables();
        int tableNumber = InputView.inputTableNumber();

        return TableRepository.findByNumber(tableNumber);
    }

    private void printMenus() {
        List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private Menu readMenu() {
        printMenus();
        int menuNumber = InputView.inputMenu();

        return MenuRepository.findByNumber(menuNumber);
    }

    private int calculatePayment(Orders orders) {
        PaymentMethod paymentMethod = checkError(InputView::inputPaymentMethod);

        return orders.calculateTotalPayment(paymentMethod);
    }

    private void printOrders(Orders orders) {
        OrdersResponseDto responseDto = OrdersResponseDto.from(orders);
        OutputView.printOrders(responseDto);
    }

    private <T> T checkError(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
            return checkError(inputReader);
        }
    }
}
