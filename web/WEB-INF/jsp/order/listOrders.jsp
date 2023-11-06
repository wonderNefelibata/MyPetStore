<%@ include file="../common/top.jsp"%>
<div>
  <script type="text/javascript">
    $(document).ready(function (){
      $("h2").hide().fadeIn();
      $("table").hide().fadeIn();
    });
  </script>
</div>
<h2>My Orders</h2>

<table>
  <tr>
    <th>Order ID</th>
    <th>Date</th>
    <th>Total Price</th>
  </tr>

  <c:forEach var="order" items="${sessionScope.orderList}">
    <tr>
      <td>
        <a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a>
      </td>
      <td><fmt:formatDate value="${order.orderDate}"
                          pattern="yyyy/MM/dd hh:mm:ss" /></td>
      <td><fmt:formatNumber value="${order.totalPrice}"
                            pattern="$#,##0.00" /></td>
    </tr>
  </c:forEach>
</table>

<%@ include file="../common/bottom.jsp"%>


