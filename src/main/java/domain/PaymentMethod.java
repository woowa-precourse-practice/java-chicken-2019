package domain;

import java.util.Arrays;

public enum PaymentMethod {
    CASH("1"),
    CARD("2");

    private static final String INVALID_COMMAND_INPUT = "1 또는 2만 입력할 수 있습니다";

    private final String command;

    PaymentMethod(String command) {
        this.command = command;
    }

    public static PaymentMethod from(String command) {
        return Arrays.stream(values())
                .filter(paymentMethod -> paymentMethod.hasSameCommand(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_INPUT));
    }

    private boolean hasSameCommand(String command) {
        return this.command.equals(command);
    }

    public boolean isCash() {
        return this == CASH;
    }
}
