<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table table-bordered table-striped mb-0" id="products-container">
  <thead>
  <tr>
    <th>ID</th>
    <th>NAME</th>
    <th>EAN</th>
    <th>PRICE</th>
    <th>
      <c:if test="${enableSearchLogic}">
        <a href="/search/" class="btn btn-sm btn-danger w-100">Reset</a>
      </c:if>
    </th>
  </tr>
  <c:if test="${enableSearchLogic}">
    <tr>
      <th><input
              class="form-control form-control-sm search-param"
              type="text"
              value="<c:out value="${param.id}"/>"
              name="id"></th>
      <th><input
              class="form-control form-control-sm search-param"
              type="text"
              value="<c:out value="${param.name}"/>"
              name="name"></th>
      <th><input
              class="form-control form-control-sm search-param"
              type="text"
              value="<c:out value="${param.ean}"/>"
              name="ean"></th>
      <th>
        <div class="row">
          <div class="col-md-6">
            <div class="input-group">
              <span class="input-group-text"><small>from</small></span>
              <input
                      class="form-control form-control-sm search-param"
                      type="text"
                      value="<c:out value="${param.price_gte}"/>"
                      name="price_gte">
            </div>
          </div>
          <div class="col-md-6">
            <div class="input-group">
              <span class="input-group-text"><small>to</small></span>
              <input
                      class="form-control form-control-sm search-param"
                      type="text"
                      value="<c:out value="${param.price_lte}"/>"
                      name="price_lte">
            </div>
          </div>
        </div>
      </th>
      <th>
        <button
                class="btn btn-sm btn-primary w-100"
                id="search-products"
                data-js-search-route="">Search</button>
      </th>
    </tr>
  </c:if>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${!products.isEmpty()}">
      <c:forEach items="${products}" var="product">
        <tr>
          <td class="align-middle"><c:out value="${product.getId()}"/></td>
          <td class="align-middle"><c:out value="${product.getName()}"/></td>
          <td class="align-middle"><c:out value="${product.getEan()}"/></td>
          <td class="align-middle"><c:out value="${product.getPrice()}"/></td>
          <td class="text-center"><a href="/product/?id=<c:out value="${product.getId()}"/>">Show</a></td>
        </tr>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <tr>
        <td colspan="5">
          <div class="text-center text-uppercase">Products are not available.</div>
        </td>
      </tr>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>
