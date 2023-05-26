package com.itlat.mysql.basics.web;

import com.itlat.mysql.basics.menu.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAppServlet extends HttpServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        List<MenuItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MenuItem("/", "All Products", ShowAllProductsServlet.class.getName()));
        mainMenuItems.add(new MenuItem("/pages/", "Pages", SplitProductsByPagesServlet.class.getName()));
        mainMenuItems.add(new MenuItem("/search/", "Search", SearchProductsServlet.class.getName()));

        String currentServletClassName = this.getClass().getName();

        request.setAttribute("mainMenuItems", mainMenuItems);
        request.setAttribute("currentServletClassName", currentServletClassName);

        super.service(request, response);
    }
}
