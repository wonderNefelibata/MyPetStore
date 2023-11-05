<%@ include file="../common/top.jsp"%>

<div id="BackLink">
<%--  <stripes:link--%>
<%--        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">--%>
<%--  Return to Main Menu--%>
<%--  </stripes:link>--%>

  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

  <%--记得添加一个category对象--%>
  <h2>${sessionScope.category.name}</h2>

  <table>
    <tr>
      <th>Product ID</th>
      <th>Name</th>
    </tr>
    <c:forEach var="product" items="${sessionScope.productList}">
      <tr>
<%--        <td><stripes:link--%>
<%--                beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                event="viewProduct">--%>
<%--          <stripes:param name="productId" value="${product.productId}" />--%>
<%--          ${product.productId}--%>
<%--        </stripes:link></td>--%>

        <td>
          <a href="productForm?productId=${product.productId}">${product.productId}</a>
        </td>
        <td>${product.name}</td>
      </tr>
    </c:forEach>
  </table>

</div>

<%@ include file="../common/bottom.jsp"%>


