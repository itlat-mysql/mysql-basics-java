package com.itlat.mysql.basics.web;

import com.itlat.mysql.basics.dao.ProductDao;
import com.itlat.mysql.basics.model.Product;
import com.itlat.mysql.basics.util.NumberValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "show-single-product", value = "/product/")
public class ShowSingleProductServlet extends AbstractAppServlet {

    /**
     * Показываем один конкретный продукт
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCandidate = request.getParameter("id");
        int id = 0;

        // проверка на то, пришло ли нам нормальное число
        if (NumberValidator.canBeRepresentedAsInt(idCandidate)) {
            id = Integer.parseInt(idCandidate);
        }

        Optional<Product> productCandidate = ProductDao.getSingleProduct(id);

        if (productCandidate.isPresent()) {
            request.setAttribute("product", productCandidate.get());
            request.getRequestDispatcher("/WEB-INF/templates/pages/show-single-product.jsp").forward(request, response);
        } else {
            // если продукт по запрошенному идентификатору не найден - вернем 404 ошибку (данные не найдены)
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
