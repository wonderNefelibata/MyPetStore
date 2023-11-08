<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
    <table>
        <tr>
            <th colspan=2>Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td>
                ${sessionScope.order.cardType}
            </td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td>
                ${sessionScope.order.creditCard}
            </td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td>
                ${sessionScope.order.expiryDate}
            </td>
        </tr>

        <tr>
            <th colspan=2>Billing Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td>
                ${sessionScope.order.billToFirstName}
            </td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td>
                ${sessionScope.order.billToLastName}
            </td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td>
                ${sessionScope.order.billAddress1}
            </td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td>
                ${sessionScope.order.billAddress2}
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                ${sessionScope.order.billCity}
            </td>
        </tr>
        <tr>
            <td>State:</td>
            <td>
                ${sessionScope.order.billState}
            </td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td>
                ${sessionScope.order.billZip}
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                ${sessionScope.order.billCountry}
            </td>
        </tr>
    </table>
</div>
<%@ include file="../common/bottom.jsp"%>
