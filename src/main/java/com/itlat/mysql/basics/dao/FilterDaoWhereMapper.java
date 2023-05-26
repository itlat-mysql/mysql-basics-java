package com.itlat.mysql.basics.dao;

import com.itlat.mysql.basics.filtration.Filtration;

import java.util.ArrayList;
import java.util.List;

public class FilterDaoWhereMapper {
    // Трансформация объекта фильтрации в специальный объект, содержащий SQL строку (WHERE ... AND ...) и значения для фильтрации
    public static FilterDaoMapperWhereData transformFiltrationToQueryWhereData(Filtration filtration) {
        FilterDaoMapperWhereData whereData = new FilterDaoMapperWhereData();

        List<String> filters = new ArrayList<>();
        for (Filtration.FiltrationRule rule : filtration.filters) {
            if (rule.getOperationType() == Filtration.OPERATION_EQUAL) {
                filters.add(String.format(" %s = ? ", rule.getName()));
                whereData.whereValues.add(rule.getValue());
            } else if (rule.getOperationType() == Filtration.OPERATION_LIKE) {
                filters.add(String.format(" %s LIKE ? ", rule.getName()));
                whereData.whereValues.add(String.format("%%%s%%",  rule.getValue()));
            } else if (rule.getOperationType() == Filtration.OPERATION_GREATER_EQ) {
                filters.add(String.format(" %s >= ? ", rule.getName()));
                whereData.whereValues.add(rule.getValue());
            } else if (rule.getOperationType() == Filtration.OPERATION_LESS_EQ) {
                filters.add(String.format(" %s <= ? ", rule.getName()));
                whereData.whereValues.add(rule.getValue());
            }
        }

        if (!filters.isEmpty()) {
            whereData.whereQuery = " WHERE " + String.join(" AND ", filters);
        }

        return whereData;
    }

    public static class FilterDaoMapperWhereData {
        public String whereQuery = "";
        public List<String> whereValues = new ArrayList<>();
    }
}
