<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
	<style type="text/css">
		.layui-table img {
			width: auto;
			height: auto;
			max-width: 100% !important;
			max-height: 100%;
		}

		.layui-form-item .layui-input-inline {
			width: 100px;
		}

		/*.layui-from-title .layui-this:after{border-color: transparent;}*/
	</style>

	<style type="text/css">
		.layui-tab-content {
			padding: 0;/*layui-tab-content:默认有padding: 10px;的值，因为iframe的绝对定位脱离文档流，所以会存在20px的空白空间*/
		}
		.show-frame {
			top: 50px!important;
			/*默认.layui-layout-admin .layui-body {
                top: 60px;
                bottom: 44px;
            }*/
			overflow: hidden;/*消除浏览器最右边的滚动条*/
		}
		.frame {
			position: absolute;
			padding: 10px;/*与layui-footer隔开一段距离*/
			width: 100%;
			height: 85%;
		}
	</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">layui 后台布局</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
			<li class="layui-nav-item"><a href="">商品管理</a></li>
			<li class="layui-nav-item"><a href="">用户</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">其它系统</a>
				<dl class="layui-nav-child">
					<dd><a href="">邮件管理</a></dd>
					<dd><a href="">消息管理</a></dd>
					<dd><a href="">授权管理</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="${pageContext.request.contextPath}/img/1f8d1888-a113-416c-9eb7-cc7202454392+111_20200506094957.jpg" class="layui-nav-img">
					<shiro:principal/>
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">安全设置</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退了</a></li>
		</ul>
	</div>
	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree" id="nav_menu"  lay-filter="nav_menu">


			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<%--<div style="padding: 15px;">--%>
			<%--<div class="layui-tab layui-tab-brief layui-tab-card" lay-allowClose="true" lay-filter="myTab">--%>
				<%--<ul class="layui-tab-title">--%>
					<%--<li class="layui-this">欢迎页</li>--%>
				<%--</ul>--%>

				<%--<div class="layui-tab-content">--%>
					<%--<div class="layui-tab-item layui-show">欢迎使用..xxxx</div>--%>
				<%--</div>--%>

			<%--</div>--%>
		<%--</div>--%>



		<div style="padding: 15px;">
			<div class="layui-tab layui-tab-card" lay-allowClose="true" lay-filter="myTab">
				<ul class="layui-tab-title">
					<li class="layui-this" lay-id="1a">后台首页</li>

				</ul>
				<div class="layui-tab-content" style="height: 500px;">
					<div class="layui-tab-item layui-show" align="center">
						<%--<iframe width="100%" height="100%" frameborder='0' class='frame' src="${pageContext.request.contextPath }/login.jsp"></iframe>--%>
						<img src="${pageContext.request.contextPath }/layui/images/111_20200506094957.jpg" style="width:640px;height:426px;"/>

					</div>


				</div>
			</div>
		</div>




	</div>




	<div class="layui-footer">
		<!-- 底部固定区域 -->
		<div align="center">
		© layui.com - 寺院官网
		</div>
	</div>


</div>

<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>
	<%--打开选项卡--%>
	function addTabs(menuName,menuUrl,menuId){
		layui.use('element', function(){
			var element = layui.element;
			// title 二级菜单的名字
			// content 引入新的页面 menuUrl
			// id 二级菜单的id
			// 以上三个参数 需要在调用函数的时候传进来

            if($("[lay-id="+menuId+"]").size()!=1){
                element.tabAdd('myTab', {
                    title: menuName,
                    content: '<iframe frameborder=\'0\' class=\'frame\' src="${pageContext.request.contextPath}'+menuUrl+'"></iframe>',
                    id: menuId
                });
            }

			// 打开刚添加的选项卡
			element.tabChange('myTab', menuId);
		});
	}

	$(function () {
		$.ajax({
			url:"${pageContext.request.contextPath}/menu/selectAll",
			success:function (data) {
				var content = "";
				// 1.拼接出来一级菜单内容 遍历所有的一级菜单
				// 参数1  被遍历的数据  参数2 是一个匿名函数
				// 匿名函数的参数 位置1 是被遍历的下标  位置2 被遍历的元素
				$.each(data,function (index1,menu1) {
					// 1.1 拼接一级菜单前标签
					content += "<li lay-id=\"1a\" class=\"layui-nav-item\"><a class=\"\" href=\"javascript:;\">";
					// 1.2 拼接一级菜单名字
					content += menu1.menuName+"</a>";

					// 2 拼接二级菜单内容 遍历二级菜单数据
					$.each(menu1.child,function (index2, menu2) {
						// 2.1 拼接二级菜单前标签
						// 在js中 引号 都有语法含义 默认会被当做语法解析 就不能写到页面中
						// 有时候需要一个字符串的引号 不能被当做语法解析  解决方案：转义 \"  \转义符号
						// " 语法  \" 字符串的引号
						content += "<dl class=\"layui-nav-child\"><dd><a href=\"javascript:;\" " +
								"onclick='addTabs(\"" + menu2.menuName+"\",\""+menu2.menuUrl+"\","+menu2.menuId+ ")'>";
						// 2.2 拼接二级菜单名字
						content += menu2.menuName;
						// 2.3 拼接二级菜单后标签
						content += "</a></dd></dl>";
					});
					
					// 1.3 拼接一级菜单后标签
					content += "</li>";
				});

				// 将拼接出来的菜单内容 写到页面中
				$("#nav_menu").html(content)

				// 更新渲染
				layui.use('element', function(){
					var element = layui.element;
					element.render('nav', 'nav_menu');
				});
			}
		})
	})
	
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

	});
</script>
</body>
</html>
