package com.itlat.mysql.basics.web;

import com.itlat.mysql.basics.dao.ProductDao;
import com.itlat.mysql.basics.filtration.Filtration;
import com.itlat.mysql.basics.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "search-products", value = "/search/")
public class SearchProductsServlet extends AbstractAppServlet {

    /**
     * Показываем продукты, которые соответствуют присланным в запросе условиям
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Filtration filtration = new Filtration()
                .equal("id", request.getParameter("id"), 255)
                .like("name", request.getParameter("name"), 255)
                .like("ean", request.getParameter("ean"), 255)
                .greaterEqual("price", request.getParameter("price_gte"), 255)
                .lessEqual("price", request.getParameter("price_lte"), 255);

        List<Product> products = ProductDao.getProductsWithFiltration(filtration);

        request.setAttribute("products", products);
        request.setAttribute("enableSearchLogic", true);
        request.getRequestDispatcher("/WEB-INF/templates/pages/search-products.jsp").forward(request, response);
    }
}
