package com.itlat.mysql.basics.filtration;

import java.util.ArrayList;
import java.util.List;

public class Filtration {
    public static final int OPERATION_EQUAL = 1;
    public static final int OPERATION_LIKE = 2;
    public static final int OPERATION_GREATER_EQ = 3;
    public static final int OPERATION_LESS_EQ = 4;

    public List<FiltrationRule> filters = new ArrayList<>();

    /**
     * Применение фильтра для строгого сравнения (=)
     */
    public Filtration equal(String name, String value, int maxSize) {
        if (validate(value, maxSize)) {
            filters.add(new FiltrationRule(name, value, OPERATION_EQUAL));
        }
        return this;
    }

    /**
     * Применение фильтра для нестрогого сравнения (LIKE)
     */
    public Filtration like(String name, String value, int maxSize) {
        if (validate(value, maxSize)) {
            filters.add(new FiltrationRule(name, value, OPERATION_LIKE));
        }
        return this;
    }

    /**
     * Применение фильтра для сравнения на больше или равно (>=)
     */
    public Filtration greaterEqual(String name, String value, int maxSize) {
        if (validate(value, maxSize)) {
            filters.add(new FiltrationRule(name, value, OPERATION_GREATER_EQ));
        }
        return this;
    }

    /**
     * Применение фильтра для сравнения на меньше или равно (<=)
     */
    public Filtration lessEqual(String name, String value, int maxSize) {
        if (validate(value, maxSize)) {
            filters.add(new FiltrationRule(name, value, OPERATION_LESS_EQ));
        }
        return this;
    }

    /**
     * Проверка значения для фильтрации (не должно быть пустой строкой, а его длина не должна превышать лимита)
     */
    public boolean validate(String value, int maxSize) {
        if (value == null || value.equals("")) {
            return false;
        }

        if (maxSize > 0 && value.length() > maxSize) {
            return false;
        }

        return true;
    }

    public static class FiltrationRule {
        private final String name;
        private final String value;
        private final int operationType;

        public FiltrationRule(String name, String value, int operationType) {
            this.name = name;
            this.value = value;
            this.operationType = operationType;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public int getOperationType() {
            return operationType;
        }
    }
}
