<%@ include file="../common/top.jsp" %>

<script type="text/javascript" src="js/register.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        //1.用户合法性判断
        var reN = /^\w{3,20}$/
        $('#username').on('blur', function () {
            if (this.value.trim() == '') {
                $('#usernameTip').attr("class", "errorTips").text("username can't be empty");
                return;
            }
            if (reN.test(this.value)) {
                $.ajax({
                    type: "GET",
                    url: "UsernameExist?username=" + this.value,
                    success: function (data) {
                        if (data.code == 1)
                            $('#usernameTip').attr("class", "errorTips").text("username already exist");
                        else if (data.code == 0)
                            $('#usernameTip').attr("class", "okTips").text("Available");
                    }
                })
            }
            else
                $('#usernameTip').attr("class","errorTips").text("illegal username");
        })

//2.密码合法性判断
        var reP = /^[\w!-@#$%^&*]{6,20}$/
        $('#password').on('blur',function () {
            if(this.value.trim() == '')
                $('#passwordTip').attr("class","errorTips").text("password can't be empty");
            else if(!reP.test(this.value))
                $('#passwordTip').attr("class","errorTips").text("illegal password");
            else
                $('#passwordTip').attr("class","okTips").text("Available");
        })

//3.判断两次输入密码一致性
        $('#repeatedPassword').on('blur',function (){
            if(this.value.trim() == '')
                $('#repeatedPasswordTip').attr("class","errorTips").text("repeted password can't be empty");
            else if(!($('#password').val() == this.value))
                $('#repeatedPasswordTip').attr("class","errorTips").text("passwords should be same");
            else
                $('#repeatedPasswordTip').attr("class","okTips").text("Available");
        })

//4.邮箱合法性判断
        var reE =  /^[a-z0-9][\w\.\-]*@[a-z0-9\-]+(\.[a-z]{2,5}){1,2}$/
        $('#email').on('blur',function () {
            if(this.value.trim() == '')
                $('#emailTip').text('');
            else if(!reE.test(this.value))
                $('#emailTip').attr("class","errorTips").text("illegal email");
            else
                $('#emailTip').attr("class","okTips").text("Available");
        })

//5.电话合法性验证
        var rePh = /^\d{11}$/
        $('#phone').on('blur',function () {
            if(this.value.trim() == '')
                $('#phoneTip').text('');
            else if(!rePh.test(this.value))
                $('#phoneTip').attr("class","errorTips").text("illegal number");
            else
                $('#phoneTip').attr("class","okTips").text("Available");
        })
    });
</script>

<div id="Catalog">
    <form action="register" method="post">
        <div class="content">
            <table>
                <tr>
                    <td>Username:</td>
                    <td>
                        <input type="text" name="username" id="username">
                        <span id="usernameTip"></span>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type="password" name="password" id="password">
                        <span id="passwordTip"></span>
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
                        <img src="captcha" id="captchaImg" alt="captcha image"/>

                        <script>
                            document.getElementById('captchaImg').addEventListener('click', change_captcha_byImg);

                            function change_captcha_byImg(){
                                var captchaImage = document.querySelector("img#captchaImg");
                                var randomParam = new Date().getTime();
                                captchaImage.src = "captcha?random=" + randomParam;
                            }
                        </script>
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
    <a href="signonForm">Login</a>
</div>
<%@ include file="../common/bottom.jsp"%>