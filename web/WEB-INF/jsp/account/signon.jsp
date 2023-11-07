<%@ include file="../common/top.jsp"%>

<div id="Catalog">
  <form action="signOn" method="post">
    <p>Please enter your username and password.</p>
    <c:if test="${requestScope.signOnMsg != null}">
      <p> <font color="red">${requestScope.signOnMsg} </font> </p>
    </c:if>
    <p>
      Username:<input type="text" name="username"> <br />
      Password:<input type="password" name="password"> <br  />

    <div>
      captcha:
      <input type="text" name="captcha" size = 5>
      <img src="captcha" alt="captcha image"/>
      <br/>
    </div>
    </p>
    <input  type="submit" value="Login">
    <input type="button" onclick="change_captcha()" value="change captcha">
<%--    TODO: 现在只能刷新一次, 注释的代码也没有办法正常刷新--%>
    <script>
      function change_captcha(){
        var captchaImage = document.querySelector("img[src='captcha']");
        var randomParam = new Date().getTime();
        // var originalSrc = captchaImage.src;
        //
        // 将原始URL中的查询参数替换为新的随机参数
        // var newSrc = originalSrc.replace(/\?.*$/, "?random=" + randomParam);
        //
        // 更新验证码图片的src属性
        // captchaImage.src = newSrc;
        captchaImage.src = "captcha?random=" + randomParam;
      }
    </script>
  </form>
  Need a user name and password?
  <a href="registerForm">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>

