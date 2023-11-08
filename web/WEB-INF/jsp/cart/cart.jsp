
<%@ include file="../common/top.jsp"%>
<div id="BackLink">
  <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

  <div id="Cart">

    <h2>Shopping Cart</h2>
    <form action="updateCartQuantities?cart=${sessionScope.cart}" method="post">
      <table>
        <tr>
          <th><b>Item ID</b></th>
          <th><b>Product ID</b></th>
          <th><b>Description</b></th>
          <th><b>In Stock?</b></th>
          <th><b>Quantity</b></th>
          <th><b>List Price</b></th>
          <th><b>Total Cost</b></th>
          <th>&nbsp;</th>
        </tr>

        <c:if test="${sessionScope.cart.numberOfItems == 0}">
          <tr>
            <td colspan="8"><b>Your cart is empty.</b></td>
          </tr>
        </c:if>

        <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
          <tr class="cartItem" id="${cartItem.item.itemId}">
            <td>
              <a href="viewItem?cartItemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
            </td>
            <td>${cartItem.item.product.productId}</td>
            <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                ${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
            <td>${cartItem.inStock}</td>
            <td>
              <input id="quantity" type="number" min="1" max="99" step="1" name="${cartItem.item.itemId}" value="${cartItem.quantity}"/>
            </td>
            <td>
              <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>

            <td id="${cartItem.item.itemId}" class="td"><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" /></td>
            <td>
              <a href="removeCartItem?workingItemId=${cartItem.item.itemId}" class="Button">Remove</a>
            </td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="7">
            Sub Total:
            <span id="subtotal"><fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" /></span>
          </td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </form>
    <c:if test="${sessionScope.cart.numberOfItems > 0}">
      <a href="newOrderForm" class="Button" id="button1">make new order</a>
    </c:if>
  </div>

  <div id="MyList">
    <c:if test="${sessionScope.loginAccount != null}">
      <c:if test="${!sessionScope.authenticated}">
        <c:if test="${!empty sessionScope.loginAccount.listOption}">
          <%@ include file="includeMyList.jsp"%>
        </c:if>
      </c:if>
    </c:if>
  </div>
  <div id="Separator">&nbsp;</div>
</div>

<script type="text/javascript" src="js/cart.js"></script>
<%@ include file="../common/bottom.jsp"%>