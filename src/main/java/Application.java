import domain.Menu;
import domain.Table;
import repository.MenuRepository;
import repository.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();
        Table table = TableRepository.findByNumber(tableNumber);

        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }
}
