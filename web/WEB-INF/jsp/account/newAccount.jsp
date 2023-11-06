<%@ include file="../common/top.jsp"%>
<%--<link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>--%>
<div>
<%--  <script type="text/javascript">--%>
<%--    $(document).ready(function(){--%>
<%--      $("#Catalog").hide().fadeIn();--%>
<%--    });--%>
<%--  </script>--%>
</div>
<div id="Catalog">
  <form action = "newAccount" method="post" name="userInfo" id="userInfo">
    <!--  标题进度条 start-->
    <div class="content" style="margin: 2% 30%;width: 100%;">
      <div class="processBar">
        <div id="line0" class="bar">
          <div id="point0" class="c-step c-select"></div>
        </div>
        <div class="text" style="margin: 10px -25px;"><span class='poetry1'>User Information</span></div>
      </div>
      <div class="processBar">
        <div id="line1" class="bar">
          <div id="point1" class="c-step"></div>
        </div>
        <div class="text" style="margin: 10px -30px;"><span class='poetry2'>Account Information</span></div>
      </div>
      <div class="processBar">
        <div id="line2" class="bar" style="width: 0;">
          <div id="point2" class="c-step"></div>
        </div>
        <div class="text" style="margin: 10px -30px;"><span class='poetry3'>Profile Information</span></div>
      </div>
    </div>
    <!--  标题进度条 end-->
    <div style="clear: both;"></div>
    <div id="MainContent" style="margin: 2% 30%;">
      <div class="content" id="basicInfo">
        <table>
          <tr>
            <th colspan=2>User Information</th>
          </tr>
          <tr>
            <td>User ID:</td>
            <td><input type = "text" name = "username" id="username" value="${sessionScope.order.creditCard}"/>
              </br>
              <span id="usernameTips"></span>
            </td>
          </tr>
          <font color="red">${requestScope.msg}</font>
          <tr>
          </tr>
          <tr>
            <td>New password:</td>
            <td>
              <input type="password" name="password" id="password"/></br>
              <span id="passwordTips"></span>
            </td>
          </tr>
          <tr>
            <td>Repeat password:</td>
            <td>
              <input type="password" name="repeatedPassword" id="repeatedPassword"/></br>
              <span id="repeatedPasswordTips"></span>
            </td>
          </tr>
        </table>
      </div>

      <div class="content" id="education">
        <table>
          <tr>
            <td>First name:</td>
            <td><input type="text" value="${sessionScope.account.firstName}" name="account.firstName"/></td>
          </tr>
          <tr>
            <td>Last name:</td>
            <td><input type="text" value="${sessionScope.account.lastName}" name="account.lastName"/></td>
          </tr>
          <tr>
            <td>Email:</td>
            <td>
              <input type="text" size="40" value="${sessionScope.account.email}" name="account.email" id="email"/>
              <span id="emailTips"></span>
            </td>

          </tr>
          <tr>
            <td>Phone:</td>
            <td>
              <input type="text" value="${sessionScope.account.phone}" name="account.phone" id="phone"/>
              <span id="phoneTips"></span>
            </td>
          </tr>
          <tr>
            <td>Address 1:</td>
            <td><input type="text" size="40" value="${sessionScope.account.address1}" name="account.address1"/></td>
          </tr>
          <tr>
            <td>Address 2:</td>
            <td><input type="text" size="40" value="${sessionScope.account.address2}" name="account.address2"/></td>
          </tr>
          <tr>
            <td>City:</td>
            <td><input type="text" value="${sessionScope.account.city}" name="account.city"/></td>
          </tr>
          <tr>
            <td>State:</td>
            <td><input type="text" size="4" value="${sessionScope.account.state}" name="account.state"/></td>
          </tr>
          <tr>
            <td>Zip:</td>
            <td><input type="text" size="10" value="${sessionScope.account.zip}" name="account.zip"/></td>
          </tr>
          <tr>
            <td>Country:</td>
            <td><input type="text" size="15" value="${sessionScope.account.country}" name="account.country"/></td>
          </tr>
        </table>
      </div>
      <div class="content" id="work">
        <table>
          <tr>
            <td>Language Preference:</td>
            <td>
              <select name="account.languagePreference">
                <option <c:if test="${sessionScope.account.languagePreference eq 'japanese'}">selected</c:if>>japanese
                </option>
                <option <c:if test="${sessionScope.account.languagePreference eq 'english'}">selected</c:if>>english
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>Favourite Category:</td>
            <td>
              <select name="account.favouriteCategoryId" value="${sessionScope.account.favouriteCategoryId}">
                <option <c:if test="${sessionScope.account.favouriteCategoryId eq 'BIRDS'}">selected</c:if>>BIRDS
                </option>
                <option <c:if test="${sessionScope.account.favouriteCategoryId eq 'CATS'}">selected</c:if>>CATS</option>
                <option <c:if test="${sessionScope.account.favouriteCategoryId eq 'DOGS'}">selected</c:if>>DOGS</option>
                <option <c:if test="${sessionScope.account.favouriteCategoryId eq 'FISH'}">selected</c:if>>FISH</option>
                <option <c:if test="${sessionScope.account.favouriteCategoryId eq 'REPTILES'}">selected</c:if>>
                  REPTILES
                </option>
              </select>

            </td>
          </tr>
          <tr>
            <td>Enable MyList</td>
            <td><input type="checkbox"
                       <c:if test="${sessionScope.account.listOption eq true}">checked="checked"</c:if>
                       name="account.listOption"/></td>
          </tr>
          <tr>
            <td>Enable MyBanner</td>
            <td><input type="checkbox"
                       <c:if test="${sessionScope.account.bannerOption eq true}">checked="checked"</c:if>
                       name="account.bannerOption"/></td>
          </tr>
        </table>
      </div>

    </div>
    <div style="clear: both;"></div>
    <div style="text-align: center;">
      <button type="button" id="previous_step">BACK</button>
      <button type="button" id="next_step">NEXT</button>
      <button id="finish">FINISH</button>
    </div>
    <%--onclick="window.location.href='ComfireServlet'--%>
  </form>
</div>
<%@include file="../common/bottom.jsp"%>

<%--<script type="text/javascript" src="js/index.js"> </script>--%>
<%--<script type="text/javascript" src="js/NewAccountForm.js"></script>--%>