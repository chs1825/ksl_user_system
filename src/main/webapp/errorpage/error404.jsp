<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/08/01
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script language="javascript">
        function fncGoAfterErrorPage(){
            history.back(-2);
        }
    </script>
</head>

<body>

<div style="width: 1000px; margin: 50px auto 50px;">

    <p style="font-size: 18px; color: #000; margin-bottom: 10px; "><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" /></p>
    <div style="border: 0px solid #666; padding: 20px;">
        <!-- 404 -->
        <p style="color:red; margin-bottom: 8px; ">404 Error</p>

        <div class="boxType1" style="width: 500px;">
            <div class="box">
                <div class="error">
                    <p class="title">HTTP 404 Error</p>
                    <p class="cont mb20">웹 페이지를 찾을 수 없습니다.<br /></p>
                    <span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
