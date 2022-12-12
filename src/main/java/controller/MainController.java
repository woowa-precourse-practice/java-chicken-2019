package controller;

import command.MainCommand;
import domain.Menu;
import domain.Orders;
import domain.PaymentMethod;
import domain.Quantity;
import domain.Table;
import dto.OrdersResponseDto;
import repository.MenuRepository;
import repository.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class MainController {

    private static final boolean QUIT = false;
    private static final boolean RUNNABLE = true;

    public void run() {
        boolean runnable = RUNNABLE;
        while (runnable) {
            MainCommand mainCommand = checkError(InputView::inputMainCommand);
            runnable = progressByCommand(mainCommand);
        }
    }

    private boolean progressByCommand(MainCommand mainCommand) {
        if (mainCommand.isOrder()) {
            progressOrder();
        }
        if (mainCommand.isPayment()) {
            checkError(this::progressPayment);
        }
        if (mainCommand.isQuit()) {
            return QUIT;
        }
        return RUNNABLE;
    }

    private void progressPayment() {
        Table table = checkError(this::readTable);
        Orders orders = table.getOrders();
        printOrders(orders);

        OutputView.printPaymentProcess(table.getNumber());
        int totalPayment = calculatePayment(orders);

        OutputView.printPayment(totalPayment);
        table.clearOrders();
    }

    private void progressOrder() {
        Table table = checkError(this::readTable);
        Menu menu = checkError(this::readMenu);
        Quantity quantity = checkError(InputView::inputQuantity);

        table.order(menu, quantity);
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

        return orders.calculatePayment(paymentMethod);
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

    private void checkError(Runnable inputReader) {
        try {
            inputReader.run();
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
            run();
        }
    }
}
