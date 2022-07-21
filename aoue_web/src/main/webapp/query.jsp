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
    <link rel="stylesheet" href="${path}/resource/bootstrap-4.6.1/dist/css/bootstrap.css">

    <title>查询</title>

    <style>
        .jumbotron{
            color: #28a4c9;
            font-family: "SimSun","Times New Roman";
        }
    </style>

</head>
<body style="text-align: center; padding: 5px;">
<!-- Popper 和 Bootstrap 的 JS 插件各自独立 -->
<script type="text/javascript" src="${path}/resource/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${path}/resource/bootstrap-4.6.1/dist/css/bootstrap.css"></script>


<div class="jumbotron">
    <h1 class="display-4">政治献金获得情况</h1>
    <p class="lead">Access to political contributions</p>
    <hr class="my-4">
    <p>You will know about the amount of money each state
        contributes to each candidate and the total number of people each day by inputting the following information.</p>
</div>


<form>
    <div class="form-group" style="text-align: center;">
        <label for="candidate">候选人姓名</label>
        <div style="text-align:center;vertical-align:middle;"> <input type="text"  id="candidate" placeholder="请输入候选人姓名" style="width: 500px"></div>
    </div><br>
    <div class="form-group" style="text-align: center;">
        <label for="contDate">献金时间</label>
        <div style="text-align:center;vertical-align:middle;"> <input type="date"  id="contDate" placeholder="请输入献金时间"
                                                                      value="2012-01-01" style="width: 500px"></div>
    </div><br>


    <button type="button" class="btn btn-primary" onclick="quertData()">查询</button>
</form>


<script type="text/javascript">
    function quertData() {
        location.href="${path}/contribute/view.do?candidate="+$("#candidate").val()+"&&contDate="+$("#contDate").val();
    }
</script>


<%--<img src="/images/vote1.png">--%>

<%@include file="footer.jsp"%>
</html>
