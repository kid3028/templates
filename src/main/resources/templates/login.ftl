<!DOCTYPE html>

<html lang="en">
<head>
    <title>login</title>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/login.js"></script>
</head>
<body>

    <form action="loginUser" method="post" onsubmit="return enableSubmit()">
        用户名：<input type="text" id="userName" name="userName" required/><span id="userNameInfo" style="color: red"></span>
        <br/><br/>
        密码:<input type="password" id="password" name="password" required/><span id="passwordInfo" style="color: red"></span>
        <br/>
        验证码:<input type="text" id="code" name="code" onblur="validateCode()" required/>
        <img id = "validCode" src="/code/getValidateCode" style="width: 80px; height: 30px"/>
        <a href="javascript:;" onclick="refreshCode()">refresh</a>
        <span id="validCodeInfo" style="color: red"></span>
        <br/>
        <input type="submit" value="login" onclick="check()"/>
        &nbsp;  &nbsp; &nbsp;  &nbsp;
        <a href="register">register</a>
    </form>


    <br>
    Message: ${message!""}

</body>

</html>