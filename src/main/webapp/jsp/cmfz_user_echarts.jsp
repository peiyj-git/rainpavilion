<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath}/js/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/js/china.js"></script>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>


</head>
<body>

<%--style="width:1270px;height:500px"--%>

<div class="layui-container">
	<div class="layui-row">
		<div class="layui-col-xs6">
			<div class="grid-demo grid-demo-bg1">
				<div id="main" style="width: 450px;height:300px;"></div>
			</div>
		</div>
		<div class="layui-col-xs6">
			<div class="grid-demo">
				<div id="demo1" style="width: 450px;height: 300px"></div>
			</div>
		</div>

		<div class="layui-col-xs6 layui-col-md-offset2" >
			<div class="grid-demo grid-demo-bg1">
				<div id="demo3" style="width: 700px;height: 400px"></div>
			</div>
		</div>
	</div>
</div>









	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		$(function () {
			$.ajax({
				url:"${pageContext.request.contextPath}/cmfzUser/getAllCountByThread",
				success:function (r) {
                    var data = r.sex;
                    var option = {
						title: {
							text: '用户男女人数对比'
						},
						tooltip: {},
						//legend 传奇 数学中 系列
						legend: {
							data:['人数']
						},
						xAxis: {
							data: [data[0].name,data[1].name]
						},
						yAxis: {},
						series: [{
							name: '人数',
							type: 'bar',
							data: [data[0].value,data[1].value]
						}]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
				}
			})
		})
	</script>


	<%--折线图--%>
	<script>
        //基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById("demo1"));

        $(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/cmfzUser/selectWeekCount",
                success:function (data) {
                    //指定图表的配置项和数据
                    var option2 = {
                        //图标的标题
                        title: {
                            text: 'ECharts \n注册人数统计'
                        },
                        //鼠标的提示
                        tooltip: {
                            trigger:'item'//,
                            //position: [10, 10]
                        },
                        //图例，说明
                        legend: {
                            data:['注册人数']
                        },
                        //x轴
                        xAxis: {
                            data: [data[0].week,data[1].week,data[2].week]
                        },
                        //y轴 y轴数据根据实际数据自动规划生成
                        yAxis: {},
                        //系列
                        series: [{
                            name:'注册人数',
                            type:'line',     //折线图
                            data:[data[0].value,data[1].value,data[2].value]
                        }]
                    };
                    myChart2.setOption(option2)
                }
			})

		});
	</script>

		<%--地区人数--%>
		<script>
            //基于准备好的dom，初始化echarts实例
            var myChart3 = echarts.init(document.getElementById("demo3"));

            $(function () {
                $.ajax({
                    url:"${pageContext.request.contextPath}/cmfzUser/selectProvinceCount",
                    success:function (data) {
                        //    配置地区分布图表数据
                        var option3 = {
                            title : {
                                text: '用户地区分布',
                                left: 'center'
                            },
                            legend: {
                                orient: 'vertical',
                                left: 'left',
                                data:['用户人数']
                            },
                            visualMap: {
                                min: 0,
                                max: 2000,
                                left: 'left',
                                top: 'bottom',
                                text:['高','低'],           // 文本，默认为数值文本
                                calculable : false
                            },
                            toolbox: {
                                show: true,
                                orient : 'vertical',
                                left: 'right',
                                top: 'center',
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            series : [
                                {
                                    name: '用户人数',
                                    type: 'map',
                                    mapType: 'china',
                                    data: data
                                }
                            ]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart3.setOption(option3)
                    }
			})
			});


		</script>

</body>
</html>
