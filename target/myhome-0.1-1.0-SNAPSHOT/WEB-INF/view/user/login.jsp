<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="${contextPath}/resources/css/user/login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${contextPath}/resources/js/jquery-2.0.0.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/user/login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
  <body style="width:97%;height: 57%;">
  <%--隐藏值区域--%>
  <input type="hidden" id="contextPath" value="${contextPath}"/>

  <div align="center">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </div>
      <div id="gray"></div>

      <div class="popup" id="popup">

        <div class="top_nav" id='top_nav'>
          <div align="center">
            <i></i>
            <span>登录账号</span>
            <a class="guanbi"></a>
          </div>
        </div>

        <div class="min">

          <div class="tc_login">

            <div class="left">
              <h4 align="center">手机扫描</h4>
              <div align="center">
                <img src="${contextPath}/resources/images/user/auth.png" width="150" height="150" />
              </div>
              <dd>
                <div align="center">
                  扫码加作者
                  <span style="font-family: 'microsoft yahei'; color: #F60; font-size: 18px;">
                    QQ
                  </span>
                </div>
              </dd>
            </div>

            <div class="right">
              <form method="POST" name="form_login" action="${contextPath}/user/home">
                <div align="center">
                  <a href="" id="message" style="color: red">短信快捷注册</a>
                  <i class="icon-mobile-phone"></i>
                  <input type="text" name="name" id="name" required="required" placeholder="用户名" autocomplete="off" class="input_yh">
                  <input type="password" name="pass" id="pass" required="required" placeholder="密码" autocomplete="off" class="input_mm">
                </div>
                <dd>
                  <div align="center">
                    <a href="javascript:;">遇到登录问题</a>
                  </div>
                </dd>
                <div align="center">
                  <input type="button" class="button" title="Sign In" value="登录">
                </div>
              </form>
              <dd>
                <div align="center">
                  <a href="${contextPath}/user/register">立即注册</a>
                </div>
              </dd>
              <hr align="center" />
              <div align="center">期待更多功能 </div>
            </div>

          </div>

        </div>

      </div>
  </body>
</html>
