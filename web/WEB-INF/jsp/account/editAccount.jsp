<%@ include file="../common/top.jsp"%>
<div id="Catalog">
    <form action="editAccount?account=${sessionScope.loginAccount}" method="post">
        <h3>User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td>${sessionScope.loginAccount.username}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password" /></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword" /></td>

                <font color="red">${requestScope.msg}</font>

            </tr>
        </table>
        <h3>Account Information</h3>
        <div>
            <script type="text/javascript">
                $(document).ready(function(){
                    $("#Catalog").hide().fadeIn();
                });
            </script>
        </div>
        <table>
            <tr>
                <td>First name:</td>
                <td><input type="text" value="${sessionScope.loginAccount.firstName}" name="account.firstName"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" value="${sessionScope.loginAccount.lastName}" name="account.lastName"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                    <input type="text" size="40" value="${sessionScope.loginAccount.email}" name="account.email" id="email"/>
                    <span id="emailTips"></span>
                </td>

            </tr>
            <tr>
                <td>Phone:</td>
                <td>
                    <input type="text" value="${sessionScope.loginAccount.phone}" name="account.phone" id="phone"/>
                    <span id="phoneTips"></span>
                </td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" size="40" value="${sessionScope.loginAccount.address1}" name="account.address1"/></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" size="40" value="${sessionScope.loginAccount.address2}" name="account.address2"/></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" value="${sessionScope.loginAccount.city}" name="account.city"/></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" size="4" value="${sessionScope.loginAccount.state}" name="account.state"/></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" size="10" value="${sessionScope.loginAccount.zip}" name="account.zip"/></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" size="15" value="${sessionScope.loginAccount.country}" name="account.country"/></td>
            </tr>
        </table>

        <h3>Profile Information</h3>
        <table>
            <tr>
                <td>Language Preference:</td>
                <td>
                    <select name="account.languagePreference">
                        <option <c:if test="${sessionScope.loginAccount.languagePreference eq 'japanese'}">selected</c:if>>japanese
                        </option>
                        <option <c:if test="${sessionScope.loginAccount.languagePreference eq 'english'}">selected</c:if>>english
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Favourite Category:</td>
                <td>
                    <select name="account.favouriteCategoryId" value="${sessionScope.loginAccount.favouriteCategoryId}">
                        <option <c:if test="${sessionScope.loginAccount.favouriteCategoryId eq 'BIRDS'}">selected</c:if>>BIRDS
                        </option>
                        <option <c:if test="${sessionScope.loginAccount.favouriteCategoryId eq 'CATS'}">selected</c:if>>CATS</option>
                        <option <c:if test="${sessionScope.loginAccount.favouriteCategoryId eq 'DOGS'}">selected</c:if>>DOGS</option>
                        <option <c:if test="${sessionScope.loginAccount.favouriteCategoryId eq 'FISH'}">selected</c:if>>FISH</option>
                        <option <c:if test="${sessionScope.loginAccount.favouriteCategoryId eq 'REPTILES'}">selected</c:if>>
                            REPTILES
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Enable MyList</td>
                <td><input type="checkbox"
                           <c:if test="${sessionScope.loginAccount.listOption eq true}">checked="checked"</c:if>
                           name="account.listOption"/></td>
            </tr>
            <tr>
                <td>Enable MyBanner</td>
                <td><input type="checkbox"
                           <c:if test="${sessionScope.loginAccount.bannerOption eq true}">checked="checked"</c:if>
                           name="account.bannerOption"/></td>
            </tr>
        </table>

        <input type="submit" name="editAccount" value="Save Account Information" />

    </form>
    <a href="listOrders">My Orders</a>
</div>

<%@ include file="../common/bottom.jsp"%>