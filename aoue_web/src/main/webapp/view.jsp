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
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">

    <title>可视化</title>
</head>
<body>
<!-- Popper 和 Bootstrap 的 JS 插件各自独立 -->
<script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${path}/resource/jquery/echarts.min.js"></script>
<script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>

<%--    ${contLogs}--%>
<%--    <c:forEach items="${contLogs}" var="cont">--%>
<%--        "${cont.candidate}"--%>
<%--    </c:forEach>--%>

<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="main" style="width: 100%;height: 90%;"></div>
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
                textAlign: 'center'
            },
            {
                text: '各州献金占比',
                subtext:'${contLogs[0].date}',
                left: '75%',
                textAlign: 'center'
            },
            {
                text: '统计献金额度',
                subtext: '${contLogs[0].date}',
                left: '25%',
                top: '50%',
                textAlign: 'center'
            },
            {
                text: '各州献金占比',
                subtext:'${contLogs[0].date}',
                left: '75%',
                top: '50%',
                textAlign: 'center'
            }
        ],
        grid: [
            {
                top: 50,
                width: '50%',
                bottom: '50%',
                left: 10,
                containLabel: true
            },
            {
                top: '55%',
                width: '50%',
                bottom: 10,
                left: 10,
                containLabel: true
            }
        ],
        xAxis: [
            {
                type: 'value',
                max: 2500,
                splitLine: {
                    show: false
                },
                axisPointer:{
                    show:true
                }
            },
            {
                type: 'value',
                max: 2500,
                gridIndex: 1,
                splitLine: {
                    show: false
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
                    <c:forEach items="${contLogs}" var="cont">
                    '${cont.state}',
                    </c:forEach>
                ],
                axisLabel: {
                    interval: 0,
                    rotate: 30
                },
                splitLine: {
                    show: false
                }
            },
            {
                gridIndex: 1,
                type: 'category',
                data: [
                    <c:forEach items="${contLogs}" var="cont">
                    '${cont.state}',
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
            {//第一个正常数据
                type: 'bar',
                z: 3,
                label: {
                    position: 'right',
                    show: true
                },
                data: [
                    <c:forEach items="${contLogs}" var="cont">
                    '${cont.money}',
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
                }
            },

            {//第二个正常数据
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                z: 3,
                label: {
                    position: 'right',
                    show: true
                },
                data: [
                    <c:forEach items="${contLogs}" var="cont">
                    '${cont.money}',
                    </c:forEach>
                ],
                itemStyle: {
                    normal: {
                        color: function (params) {
                            var colorList = [ '#FFFFCC','#FFCCCC', '#CCFFFF', '#CCFFCC', '#CCCCFF', '#FF9999',
                                '#FFCC99', '#99CC99', '#99CCFF', '#99CCCC', '#CCFF99','#99CCFF','#66CCCC','#FFFFCC'];
                            return colorList[params.dataIndex % colorList.length];
                        },
                    }
                }
            },

            {//1饼
                type: 'pie',
                radius: [0, '30%'],
                center: ['75%', '30%'],
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
                        label:{
                            show: true,
                            formatter: '{b} : {c} ({d}%)' //带当前图例名 + 百分比
                        },
                        labelLine :{show:true}
                    }
                }
            },

            {//2饼
                type: 'pie',
                radius: ['10%', '40%'],
                center: ['75%', '80%'],
                data: [
                    <c:forEach items="${contLogs}" var="cont">
                    {
                        name:"${cont.state}",
                        value:"${cont.money}"
                    },
                    </c:forEach>
                ],
                itemStyle: {
                    normal: {
                        color:function(params){
                            var colorList = [ '#FFFFCC','#FFCCCC', '#CCFFFF', '#CCFFCC', '#CCCCFF', '#FF9999',
                                '#FFCC99', '#99CC99', '#99CCFF', '#99CCCC', '#CCFF99','#99CCFF','#66CCCC','#FFFFCC'];
                            return colorList[params.dataIndex % colorList.length];
                        },
                        label:{
                            show: true,
                            formatter: '{b} : {c} ({d}%)' //带当前图例名 + 百分比
                        },
                        labelLine :{show:true}
                    },
                }
            }
        ]
    };

    option && myChart.setOption(option);



</script>
<%@include file="footer.jsp"%>
</body>
</html>
