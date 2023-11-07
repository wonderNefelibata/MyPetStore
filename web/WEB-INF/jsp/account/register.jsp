<%@ include file="../common/top.jsp" %>

<script type="text/javascript" src="js/register.js"></script>

<div id="Catalog">
    <form action="register" method="post">
        <div class="content">
            <table>
                <tr>
                    <td>Username:</td>
                    <td>
                        <input type="text" name="username">
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>perference:</td>
                    <td>
                        <select name="preference">
                            <option value="DOGS">DOGS</option>
                            <option value="BIRDS">BIRDS</option>
                            <option value="REPTILES">REPTILES</option>
                            <option value="FISHS">FISHS</option>
                            <option value="CATS">CATS</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>captcha:</td>
                    <td>
                        <input type="text" name="captcha">
                        <img src="captcha" alt="captcha image"/>
                    </td>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><input type="text" name="account.firstName"/></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><input type="text" name="account.lastName"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <input type="text" size="40" name="account.email" id="email"/>
                        <span id="emailTips"></span>
                    </td>

                </tr>
                <tr>
                    <td>Phone:</td>
                    <td>
                        <input type="text" name="account.phone" id="phone"/>
                        <span id="phoneTips"></span>
                    </td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><input type="text" size="40" name="account.address1"/></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><input type="text" size="40" name="account.address2"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="account.city"/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><input type="text" size="4" name="account.state"/></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><input type="text" size="10" name="account.zip"/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><input type="text" size="15" name="account.country"/></td>
                </tr>
            </table>
        </div>
        <div>
            <div style="clear: both;"></div>
            <div style="text-align: center;"></div>
            <c:if test="${requestScope.registerMsg != null}">
                <p><font color="red">${requestScope.registerMsg} </font></p>
            </c:if>
            <input type="submit" value="signUp">
        </div>
    </form>

    already have a user?
    <a href="signOnForm">Login</a>
</div>
<%@ include file="../common/bottom.jsp"%>