<%--
  Created by IntelliJ IDEA.
  User: 86157
  Date: 2022/7/18
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Bootstrap 的 CSS 文件 -->
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">

    <title>查询</title>
</head>
<body>
    <!-- Popper 和 Bootstrap 的 JS 插件各自独立 -->
    <script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>

    <form>
        <div class="form-group">
            <label for="candidate">候选人姓名</label>
            <input type="text" class="form-control" id="candidate" placeholder="请输入候选人姓名" style="width: 500px">
        </div>
        <div class="form-group">
            <label for="contDate">献金时间</label>
            <input type="date" class="form-control" id="contDate" placeholder="请输入献金时间"
                   value="2012-01-01" style="width: 500px">
        </div>


        <button type="button" class="btn btn-primary" onclick="quertData()">查询</button>
    </form>


    <script type="text/javascript">
        function quertData() {
            location.href="${path}/contribute/view.do?candidate="+$("#candidate").val()+"&&contDate="+$("#contDate").val();
        }
    </script>

</body>
</html>
