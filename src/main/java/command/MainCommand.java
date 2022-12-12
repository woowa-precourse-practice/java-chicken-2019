package command;

import java.util.Arrays;

public enum MainCommand {
    REGISTER(1, "주문등록"),
    PAYMENT(2, "결제하기"),
    QUIT(3, "프로그램 종료");

    private static final String NO_SUCH_MAIN_COMMAND = "해당 기능은 존재하지 않습니다.";
    private final int command;
    private final String description;

    MainCommand(int command, String description) {
        this.command = command;
        this.description = description;
    }

    public static MainCommand from(int command) {
        return Arrays.stream(values())
                .filter(mainCommand -> mainCommand.isSameCommand(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_MAIN_COMMAND));
    }

    private boolean isSameCommand(int command) {
        return this.command == command;
    }

    @Override
    public String toString() {
        return command + " - " + description;
    }
}
