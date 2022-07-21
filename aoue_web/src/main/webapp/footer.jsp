<%--
  Created by IntelliJ IDEA.
  User: 86157
  Date: 2022/7/19
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap 的 CSS 文件 -->
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <link rel="stylesheet" href="${path}/resource/bootstrap-4.6.1/dist/css/bootstrap.css">
    <title>页脚</title>

    <style type="text/css">
        ul {
            display: flex;
            flex-flow: row nowrap;
            justify-content: center;
        }
        ul li {
            list-style: none;
            text-align: center;
            line-height: 30px;
            padding: 10px;
            height: 20px;
            margin: 0 10px;
            color: #5a6268;
        }
    </style>

</head>
<body>
<!-- Popper 和 Bootstrap 的 JS 插件各自独立 -->
<script type="text/javascript" src="${path}/resource/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${path}/resource/bootstrap-4.6.1/dist/css/bootstrap.css"></script>

<div  class="footer ">
    <div class="container">
        <hr>
        <div  class="row footer-bottom">
            <ul class="list-inline text-center">
                <li>南开大学计算机学院实习实训作业</li>
                <li>Copyright &copy;田翔宇 卻铭恺 徐一帆 王天行 丁雪艺</li>
            </ul>
        </div>
    </div>
</div>


</body>
</html>
