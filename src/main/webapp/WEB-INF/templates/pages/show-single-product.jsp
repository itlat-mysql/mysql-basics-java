<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../partials/header.jsp" />
<div class="card">
    <div class="card-header">
        <h4><c:out value="${product.getName()}"/></h4>
    </div>
    <div class="card-body">
        <div class="row text-center">
            <div class="col-6">
                <strong>Price:</strong> <c:out value="${product.getPrice()}"/>
            </div>
            <div class="col-6">
                <strong>Code:</strong> <c:out value="${product.getEan()}"/>
            </div>
        </div>
    </div>
    <div class="card-footer text-end">
        <strong>Created at:</strong> <c:out value="${product.getCreatedAt()}"/>
    </div>
</div>
<jsp:include page="../partials/footer.jsp" />