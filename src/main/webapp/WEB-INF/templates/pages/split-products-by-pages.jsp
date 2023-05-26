<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../partials/header.jsp" />

<jsp:include page="../partials/products-items.jsp"/>

<c:if test="${pages > 0}">
    <div class="mt-3 d-flex justify-content-center">
        <ul class="pagination">
            <c:forEach begin="1" end="${pages}" varStatus="loop">
                <li class="page-item <c:if test="${page == loop.index}">active</c:if>">
                    <a class="page-link" href="/pages/?page=${loop.index}">${loop.index}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<jsp:include page="../partials/footer.jsp" />