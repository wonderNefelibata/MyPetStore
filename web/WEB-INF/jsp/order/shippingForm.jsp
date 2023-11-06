<%@ include file="../common/top.jsp"%>
<div>
  <script type="text/javascript">
    $(document).ready(function(){
      $("#Catalog").hide().fadeIn();
    });
  </script>
</div>
<div id="Catalog">
  <form action="confirmOrderForm" method="post">
    <table>
      <tr>
        <th colspan=2>Shipping Address</th>
      </tr>

      <tr>
        <td>First name:</td>
        <td><input type="text" name="order.shipToFirstName" value="${sessionScope.account.firstName}" /></td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><input type="text" name="order.shipToLastName" value="${sessionScope.account.lastName}"/></td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td><input type="text" size="40" name="order.shipAddress1" value="${sessionScope.account.address1}"/></td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td><input type="text" size="40" name="order.shipAddress2" value="${sessionScope.account.address2}"/></td>
      </tr>
      <tr>
        <td>City:</td>
        <td><input type="text" name="order.shipCity" value="${sessionScope.account.city}"/></td>
      </tr>
      <tr>
        <td>State:</td>
        <td><input type="text" size="4" name="order.shipState" value="${sessionScope.account.state}"/></td>
      </tr>
      <tr>
        <td>Zip:</td>
        <td><input type="text" size="10" name="order.shipZip" value="${sessionScope.account.zip}"/></td>
      </tr>
      <tr>
        <td>Country:</td>
        <td><input type="text" size="15" name="order.shipCountry" value="${sessionScope.account.country}"/></td>
      </tr>
    </table>

    <input type="submit" name="newOrder" value="Continue" />
  </form>
</div>

<%@ include file="../common/bottom.jsp"%>