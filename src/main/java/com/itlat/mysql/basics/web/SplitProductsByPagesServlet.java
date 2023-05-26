package com.itlat.mysql.basics.web;

import com.itlat.mysql.basics.dao.ProductDao;
import com.itlat.mysql.basics.model.Product;
import com.itlat.mysql.basics.util.NumberValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "split-products-by-pages", value = "/pages/")
public class SplitProductsByPagesServlet extends AbstractAppServlet {

    /**
     * Показываем продукты по несколько штук на странице (с использованием постраничной навигации)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // получим общее количество продуктов и посчитаем, сколько у нас вообще может быть страниц
        int productQty = ProductDao.getProductsQty();
        int qtyPerPage = 2;
        int pages = (int)Math.ceil((double) productQty / qtyPerPage);

        // получим номер страницы (если он пришел)
        String pageCandidate = request.getParameter("page");
        int page = 1;
        if (NumberValidator.canBeRepresentedAsInt(pageCandidate)) {
            page = Integer.parseInt(pageCandidate);
        }

        // если номер пришедшей страницы слишком маленький или слишком большой - вернем 404 ошибку (данные не найдены)
        if (page < 1 || page > pages) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            int currentOffset = (page - 1) * qtyPerPage;
            List<Product> products = ProductDao.getProductsWithLimitAndOffset(qtyPerPage, currentOffset);

            request.setAttribute("pages", pages);
            request.setAttribute("page", page);
            request.setAttribute("products", products);

            request.getRequestDispatcher("/WEB-INF/templates/pages/split-products-by-pages.jsp").forward(request, response);
        }
    }
}
