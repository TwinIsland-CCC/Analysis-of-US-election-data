<%--
  Created by IntelliJ IDEA.
  User: 86157
  Date: 2022/7/18
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Bootstrap 的 CSS 文件 -->
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <link rel="stylesheet" href="${path}/resource/bootstrap-4.6.1/dist/css/bootstrap.css">

    <title>可视化</title>

</head>
<body>
    <!-- Popper 和 Bootstrap 的 JS 插件各自独立 -->
    <script type="text/javascript" src="${path}/resource/jquery/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="${path}/resource/bootstrap-4.6.1/dist/css/bootstrap.css"></script>
    <script type="text/javascript" src="${path}/resource/jquery/echarts.min.js"></script>

<%--    <c:forEach items="${contLogs}" var="cont">--%>
<%--        "${cont.candidate}"--%>
<%--    </c:forEach>--%>

    <%--导航--%>
    <div class="left">
        <iframe src="http://localhost/contribute/view.do" width="100px" height="0%"
                scrolling="no" border="0" frameborder="0" align="left"></iframe>
    </div>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="#" onclick="getHome()">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#">View</a>
        </li>
    </ul>
    <script type="text/javascript">
        function getHome(){
            top.location.href = 'http://localhost/contribute/jump.do'
        }
    </script>

    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="main" style="width: 100%;height: 80%;"></div>
    <script type="text/javascript">
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        const waterMarkText = '第7小组';
        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');
        canvas.width = canvas.height = 100;
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.globalAlpha = 0.08;
        ctx.font = '20px Microsoft Yahei';
        ctx.translate(50, 50);
        ctx.rotate(-Math.PI / 4);
        //ctx.fillText(waterMarkText, 0, 0);//水印

        option = {
            backgroundColor: {
                type: 'pattern',
                image: canvas,
                repeat: 'repeat'
            },
            tooltip: {},
            title: [
                {
                    text: '统计献金额度',
                    subtext: '${contLogs[0].date}',
                    left: '25%',
                    textAlign: 'center',
                    padding: 15,
                    textStyle:{
                        fontSize: 40,
                    },
                    subtextStyle:{
                        fontSize: 20,
                    }
                },
                {
                    text:'${contLogs[0].date} 当日献金人数：${contAmounts[0].candAmount}',
                    subtext:'${contAmounts[0].candNm} 所属政党：${contAmounts[0].candParty}',
                    left:'50%',
                    textAlign: 'center',
                    top:'7%',
                    textStyle:{
                        fontSize: 20,
                    },
                },
                {
                    text: '各州献金占比',
                    subtext:'${contLogs[0].date}',
                    left: '75%',
                    textAlign: 'center',
                    padding: 15,
                    textStyle:{
                        fontSize: 40,
                    },
                    subtextStyle:{
                        fontSize: 20,
                    }
                }
            ],
            grid: [
                {
                    top: 100,
                    width: '50%',
                    bottom: 10,
                    left: 10,
                    containLabel: true
                }
            ],
            xAxis: [
                {
                    type: 'value',
                    max: '${contLogs[0].money}',
                    splitLine: {
                        show: true
                    },
                    axisPointer:{
                        show:true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'category',
                    data: [
<%--                        <c:forEach items="${contLogs}" var="cont">--%>
<%--                        '${cont.state}',--%>
<%--                        </c:forEach>--%>
                        <c:forEach var="i" begin="0" end="5">
                        '${contLogs[i].state}',
                        </c:forEach>
                    ],
                    axisLabel: {
                        interval: 0,
                        rotate: 30
                    },
                    splitLine: {
                        show: false
                    }
                }
            ],
            series: [
                {//柱
                    type: 'bar',
                    z: 3,
                    label: {
                        position: 'right',
                        show: true
                    },
                    data: [
<%--                        <c:forEach items="${contLogs}" var="cont">--%>
<%--                        '${cont.money}',--%>
<%--                        </c:forEach>--%>
                        <c:forEach var="i" begin="0" end="5">
                        '${contLogs[i].money}',
                        </c:forEach>
                    ],
                    itemStyle:{
                        normal:{
                            color:function(params){
                                var colorList = [ '#99CCFF', '#99CCCC','#CCFF99','#99CCFF','#66CCCC','#FFFFCC','#FFFFCC','#FFCCCC',
                                    '#CCFFFF', '#CCFFCC', '#CCCCFF', '#FF9999',
                                    '#FFCC99', '#99CC99'];
                                return colorList[params.dataIndex % colorList.length];
                            }
                        }
                    },
                },

                {//饼
                    type: 'pie',
                    radius: ['30%', '70%'],
                    center: ['75%', '55%'],
                    data: [
                        <c:forEach items="${contLogs}" var="cont">
                        {
                            name:"${cont.state}",
                            value:"${cont.money}"
                        },
                        </c:forEach>
                    ],
                    itemStyle:{
                        normal:{
                            color:function(params){
                                var colorList = [ '#99CCFF', '#99CCCC', '#CCFF99','#99CCFF','#66CCCC','#FFFFCC','#FFFFCC','#FFCCCC',
                                    '#CCFFFF', '#CCFFCC', '#CCCCFF', '#FF9999',
                                    '#FFCC99', '#99CC99' ];
                                return colorList[params.dataIndex % colorList.length];
                            },
                            labelLine: {
                                show: true,
                            },
                            label:{
                                show: true,
                                formatter: '{b} : {c} ({d}%)' //带当前图例名 + 百分比
                            }
                        }
                    }
                }
            ]
        };

        option && myChart.setOption(option);


    </script>
    <%@include file="footer.jsp"%>
</body>

</html>
