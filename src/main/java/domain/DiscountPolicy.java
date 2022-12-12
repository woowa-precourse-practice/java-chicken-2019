package domain;

public class DiscountPolicy {

    private static final double DISCOUNTED = 0.95;

    private final PaymentMethod paymentMethod;

    private DiscountPolicy(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static DiscountPolicy from(PaymentMethod paymentMethod) {
        return new DiscountPolicy(paymentMethod);
    }

    public int apply(int payment, Quantity chickenCount) {
        payment = discountByChickenCount(payment, chickenCount);

        return discountByPaymentMethod(payment, paymentMethod);
    }

    private int discountByPaymentMethod(int payment, PaymentMethod paymentMethod) {
        if (paymentMethod.isCash()) {
            payment *= DISCOUNTED;
        }
        return payment;
    }

    private int discountByChickenCount(int payment, Quantity chickenCount) {
        payment -= chickenCount.calculateDiscountAmount();

        return payment;
    }
}
