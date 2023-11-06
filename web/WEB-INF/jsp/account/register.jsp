<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="register" method="post">
        <c:if test="${requestScope.registerMsg != null}">
            <p> <font color="red">${requestScope.registerMsg} </font> </p>
        </c:if>
        <p>
            Username:<input type="text" name="username"> <br />
            Password:<input type="password" name="password"> <br />
            <%--            ConfirmPassword:<input type="password" name="confirmPassword">--%>
            perference:
            <select name="preference">
                <option value="DOGS">DOGS</option>
                <option value="BIRDS">BIRDS</option>
                <option value="REPTILES">REPTILES</option>
                <option value="FISHS">FISHS</option>
                <option value="CATS">CATS</option>
            </select>
            <br/>
            captcha:
            <img src="captcha" alt="captcha image"/>
            <input type="text" name="captcha">
            <br/>
        </p>
        <input type="submit" value="signUp">
    </form>
    already have a user?
    <a href="signOnForm">Login</a>

</div>
<%@ include file="../common/bottom.jsp"%>