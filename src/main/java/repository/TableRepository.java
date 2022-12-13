package repository;

import domain.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableRepository {
    private static final String NO_SUCH_TABLE_NUMBER = "해당 테이블은 존재하지 않습니다.";

    private static final List<Table> tables = new ArrayList<>();

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static Table findByNumber(int number) {
        return tables().stream()
                .filter(table -> table.hasSameNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_TABLE_NUMBER));
    }
}
