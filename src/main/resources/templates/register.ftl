<!DOCTYPE html>

<html lang="en">
<head>
    <title>register</title>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/register.js"></script>
</head>
</head>
<body>

<form action="registerUser" method="post" onsubmit="return enableSubmit()">
    userName：<input type="text" id="userName" name="loginName" required/><span id="userNameInfo" style="color: red"></span>
    <br/><br/>
    nickName:<input type="text" id="nickName" name="nickName" required/><span id="nickNameInfo" style="color: red"></span>
    password:<input type="password" id="password" name="password" required/><span id="passwordInfo" style="color: red"></span>
    <br/><br/>
    validCode:<input type="text" id="code" name="code" onblur="validateCode()" required/>
    <img id = "validCode" src="/code/getValidateCode" style="width: 80px; height: 30px"/>
    <a href="javascript:;" onclick="refreshCode()">refresh</a>
    <span id="validCodeInfo" style="color: red"></span>
    <br/><br/>
    <input type="submit" value="register" onclick="check()"/>
</form>


<br>
Message: ${message!""}

</body>

</html>