<%@ include file="../common/top.jsp"%>
<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<style>
  .tab {
    display: none;
  }

  .active {
    display: block;
  }

  .tabButton {
    cursor: pointer;
  }

</style>

<script>
  var currentTab = 0;  // 用于跟踪当前选项卡的索引

  function openTab(tabName) {
    // 获取所有选项卡和按钮
    var tabs = document.querySelectorAll('.tab');
    // 隐藏所有选项卡
    tabs.forEach(function (tab) {
      tab.classList.remove('active');
    });
    // 显示选中的选项卡
    document.getElementById(tabName + 'Tab').classList.add('active');
    // 高亮选中的按钮
    document.querySelector('.tabButton[data-tab="' + tabName + '"]').classList.add('active');
  }

  function nextStep() {
    // 切换到下一个选项卡
    currentTab = (currentTab + 1) % 2;
    openTab(currentTab === 0 ? 'payment' : 'address');
  }
</script>

<script type="text/javascript" src="js/index.js"> </script>
<div id="Catalog">

  <br>
  <br>
  <form action="newOrder" method="post">
    <div>
      <div id="paymentTab" class="tab active">
        <table>
          <tr>
            <th colspan=2>Payment Details</th>
          </tr>
          <tr>
            <td>Card Type:</td>
            <td>
              <select name="order.cardType">
                <option selected="selected" value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="American Express">American Express</option>
              </select>
            </td>
          </tr>
          <tr>
            <td>Card Number:</td>
            <td>
              <input type="text" name="order.creditCard" value = "${sessionScope.order.getCreditCard()}"/>
            </td>
          </tr>
          <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><input type="text" name="order.expiryDate" value = "${sessionScope.order.getExpiryDate()}"/></td>
          </tr>
        </table>
        <button type="button" class="tabButton" onclick="nextStep()">Next Step</button>
      </div>

      <div id="addressTab" class="tab">
        <table>
          <tr>
            <th colspan=2>Billing Address</th>
          </tr>
          <tr>
            <td>First name:</td>
            <td><input type="text" name="order.billToFirstName" value = "${sessionScope.loginAccount.firstName}"/></td>
          </tr>
          <tr>
            <td>Last name:</td>
            <td><input type="text" name="order.billToLastName" value = "${sessionScope.loginAccount.lastName}"/></td>
          </tr>
          <tr>
            <td>Address 1:</td>
            <td><input type="text" size="40" name="order.billAddress1" value = "${sessionScope.loginAccount.address1}"/></td>
          </tr>
          <tr>
            <td>Address 2:</td>
            <td><input type="text" size="40" name="order.billAddress2" value = "${sessionScope.loginAccount.address2}"/></td>
          </tr>
          <tr>
            <td>City:</td>
            <td><input type="text" name="order.billCity" value = "${sessionScope.loginAccount.city}"/></td>
          </tr>
          <tr>
            <td>State:</td>
            <td><input type="text" size="4" name="order.billState" value="${sessionScope.loginAccount.state}"/></td>
          </tr>
          <tr>
            <td>Zip:</td>
            <td><input type="text" size="10" name="order.billZip" value="${sessionScope.loginAccount.zip}"/></td>
          </tr>
          <tr>
            <td>Country:</td>
            <td><input type="text" size="15" name="order.billCountry" value="${sessionScope.loginAccount.country}"/></td>
          </tr>
        </table>
        <button type="button" class="tabButton" onclick="nextStep()">Last Step</button>
        <button type="submit">submit</button>
      </div>
    </div>
  </form>
</div>

<%@ include file="../common/bottom.jsp"%>