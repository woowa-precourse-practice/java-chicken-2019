package controller;

import domain.Orders;
import domain.PaymentMethod;
import domain.Table;
import dto.OrdersResponseDto;
import repository.OrderRepository;
import repository.TableRepository;
import view.InputView;
import view.OutputView;

public class PaymentController {

    public void run() {
        int tableNumber = InputView.inputTableNumber();
        Table table = TableRepository.findByNumber(tableNumber);

        Orders orders = Orders.create(OrderRepository.findByTable(table));
        OrdersResponseDto responseDto = OrdersResponseDto.from(orders);
        OutputView.printOrders(responseDto);

        OutputView.printPaymentProcess(tableNumber);
        PaymentMethod paymentMethod = InputView.inputPaymentMethod();
        int totalPayment = orders.calculateTotalPayment(paymentMethod);

        OutputView.printPayment(totalPayment);
    }
}
