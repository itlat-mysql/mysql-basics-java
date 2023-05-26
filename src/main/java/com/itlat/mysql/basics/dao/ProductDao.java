package com.itlat.mysql.basics.dao;

import com.itlat.mysql.basics.exception.DaoException;
import com.itlat.mysql.basics.filtration.Filtration;
import com.itlat.mysql.basics.model.Product;
import com.itlat.mysql.basics.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private static final String QUERY_GET_ALL_PRODUCTS = "SELECT id, name, ean, price, created_at FROM products ORDER BY created_at ASC;";
    private static final String QUERY_GET_SINGLE_PRODUCT = "SELECT id, name, ean, price, created_at FROM products WHERE id = ?;";
    private static final String QUERY_GET_PRODUCTS_QTY = "SELECT COUNT(*) FROM products;";
    private static final String QUERY_GET_PRODUCTS_WITH_LIMIT_AND_OFFSET = "SELECT id, name, ean, price, created_at FROM products ORDER BY created_at ASC LIMIT ? OFFSET ?;";
    private static final String QUERY_GET_PRODUCTS_WITH_FILTRATION = "SELECT id, name, ean, price, created_at FROM products %s ORDER BY created_at ASC;";

    /**
     * Простой запрос на выбор все строк с сортировкой по столбцу created_at (по возрастанию)
     */
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionManager.open()) {
            try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_PRODUCTS)) {
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    products.add(mapResultSetToProduct(result));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return products;
    }

    /**
     * Запрос, позволяющий пропустить несколько строк и ограничить количество получаемых строк
     */
    public static List<Product> getProductsWithLimitAndOffset(int limit, int offset) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionManager.open()) {
            try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_PRODUCTS_WITH_LIMIT_AND_OFFSET)) {
                statement.setInt(1, limit);
                statement.setInt(2, offset);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    products.add(mapResultSetToProduct(result));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return products;
    }

    /**
     * Запрос, подсчитывающий количество строк в таблице
     */
    public static int getProductsQty() {
        try (Connection connection = ConnectionManager.open()) {
            try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_PRODUCTS_QTY)) {
                ResultSet result = statement.executeQuery();

                int qty = 0;
                if (result.next()) {
                    qty = result.getInt(1);
                }
                return qty;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Запрос, возвращающий продукт, который содержит id, равный присланному значению product_id
     */
    public static Optional<Product> getSingleProduct(long productId) {
        try (Connection connection = ConnectionManager.open()) {
            try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_SINGLE_PRODUCT)) {
                statement.setLong(1, productId);
                ResultSet result = statement.executeQuery();

                Product product = null;
                if (result.next()) {
                    product = mapResultSetToProduct(result);
                }
                return Optional.ofNullable(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Запрос, который возвращает строки, соответствующие присланным условиям фильтрации
     */
    public static List<Product> getProductsWithFiltration(Filtration filtration) {
        FilterDaoWhereMapper.FilterDaoMapperWhereData whereData = FilterDaoWhereMapper
                .transformFiltrationToQueryWhereData(filtration);
        String query = String.format(QUERY_GET_PRODUCTS_WITH_FILTRATION, whereData.whereQuery);
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionManager.open()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                int index = 0;
                for (String value : whereData.whereValues) {
                    statement.setString(++index, value);
                }
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    products.add(mapResultSetToProduct(result));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return products;
    }

    /**
     * Трансформация результата запроса в объект типа Product
     */
    private static Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("ean"),
                resultSet.getDouble("price"),
                resultSet.getString("created_at")
        );
    }
}
