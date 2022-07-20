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
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
    <title>页脚</title>
</head>
<body>
<!-- Popper 和 Bootstrap 的 JS 插件各自独立 -->
<script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>

<footer class="navbar-fixed-bottom" style="width: 100%; overflow: hidden;">
    <div class="container">
        <HR>
        <!--<div class="row" style="text-align: center;">
            <div class="col-sm-3 col-md-3 col-lg-3">
                <a href="#" style=" color: gray;">关于我们</a>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <a href="#" style=" color: gray;">联系方式</a></li>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <a href="#" style=" color: gray;">旗下网站</a></li>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <a href="#" style=" color: gray;">特别致谢</a></li>
            </div>
        </div>-->

        <div align="center"><button type="button" class="btn btn-outline-secondary" data-toggle="popover" title="组长：田翔宇"
                                    data-content="组员：卻铭恺 王天行 徐一帆 丁雪艺">点击查看第七小组成员</button>
        </div>
    </div>
</footer>
<script>
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
</script>

</body>
</html>
