package com.itlat.mysql.basics.web;

import com.itlat.mysql.basics.dao.ProductDao;
import com.itlat.mysql.basics.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "show-all-products", value = "")
public class ShowAllProductsServlet extends AbstractAppServlet {

    /**
     * Показываем все продукты безо всяких условий
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = ProductDao.getAllProducts();
        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/templates/pages/show-all-products.jsp").forward(request, response);
    }
}