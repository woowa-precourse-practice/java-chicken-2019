package controller;

import command.MainCommand;
import view.InputView;

public class MainController {

    private final OrderController orderController;
    private final PaymentController paymentController;

    public MainController() {
        orderController = new OrderController();
        paymentController = new PaymentController();
    }

    public void run() {
        while (true) {
            MainCommand mainCommand = InputView.inputMainCommand();
            if (mainCommand.isOrder()) {
                orderController.run();
            }
            if (mainCommand.isPayment()) {
                paymentController.run();
            }
            if (mainCommand.isQuit()) {
                break;
            }
        }
    }
}
