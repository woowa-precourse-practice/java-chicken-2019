package controller;

import domain.Menu;
import domain.Order;
import domain.Quantity;
import domain.Table;
import repository.MenuRepository;
import repository.OrderRepository;
import repository.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class OrderController {

    public void run() {
        List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);

        int tableNumber = InputView.inputTableNumber();
        Table table = TableRepository.findByNumber(tableNumber);

        List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);

        int menuNumber = InputView.inputMenu();
        Menu menu = MenuRepository.findByNumber(menuNumber);

        Quantity quantity = InputView.inputQuantity();

        Order order = Order.create(table, menu, quantity);
        OrderRepository.save(order);
    }
}
