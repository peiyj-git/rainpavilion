<%--
  Created by IntelliJ IDEA.
  User: peiyoujie
  Date: 2020/6/2
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all"/>
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
    </style>
</head>
<body>

<div class="<%--layui-container--%>layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md2 <%--layui-col-md-offset1--%>">
            <div id="test1"></div>
        </div>
        <div class="layui-col-md10" >
            <div id="taba">
                <table id="demo" lay-filter="test"></table>
                <style>
                    .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{top: 50%;transform: translateY(-50%)}
                </style>
            </div>
        </div>
    </div>
</div>





<%--添加的form--%>
<form class="layui-form" id="addForm" style="display:none;padding:10px 50px 0px 10px">

    <div class="layui-form-item">
        <label class="layui-form-label">轮播图</label>
        <div class="layui-input-block layui-upload">

            <button type="button" class="layui-btn" id="headImg2">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
            <input type="hidden" name="bannerImageUrl" id="songljsonglj2"/>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">图片名字</label>
        <div class="layui-input-block">
            <input type="text" name="bannerOldName" placeholder="请输入图片名字" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">上传日期</label>
        <div class="layui-input-block">
            <input type="text" id="testdate" name="bannerDate" placeholder="请输入上传日期" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">图片描述</label>
        <div class="layui-input-block">
            <input type="text" name="bannerDescription" placeholder="请输入图片描述" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<%--添加样式开始    --%>
<script type="text/html" id="toolbar">
    <button class="layui-btn <%--layui-btn-primary--%> layui-btn-sm" onclick="handleRemoveAny()">
        <i class="layui-icon layui-icon-delete" ></i>
    </button>

    <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="handleOpenAdd()" >
        <i class="layui-icon layui-icon-addition"></i>
    </button>

    <%--搜索框开始--%>
    <div class="layui-inline">
        <form action="" class="layui-form" lay-filter="search">
            <div class="layui-input-inline">
                <input type="text" name="xueshengname" id="xueshengnamelaoshi" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <button onclick="handleSearch()" type="button" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-search"> </i></button>
            </div>
        </form>
    </div>
    <%--搜索框结束--%>
</script>



<%--添加样式结束    --%>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script>

    layui.use(['form','laydate','layer','table','tree','upload'], function() {
        var upload = layui.upload;
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var layer = layui.layer;
        var layerIndex;
        var tree=layui.tree;


        upload.render({
            elem: '#headImg2'
            ,url: '${pageContext.request.contextPath }/guru/upload'
            ,accept: 'file'
            ,done: function(res){
                var fileName=res.src;
                $("#songljsonglj2").val("");
                $("#songljsonglj2").val(fileName);
            }
        });


        // 时间插件
        laydate.render({
            elem: '#testdate' //指定元素
            ,trigger: 'click'
        });

        // table表格开始
        table.render({
            elem: '#demo'
            ,width : 1270
            ,height: 400
            ,url: '${pageContext.request.contextPath}/banner/selectAll'
            ,page: true //开启分页
            ,limit:6
            ,limits:[5,6,10]
            ,toolbar:"#toolbar"
            ,cols: [[ //表头


    //             private int bannerId;
    //     private String bannerImageUrl;
    //     private String bannerOldName;
    //     private int bannerState;
    //     private Date bannerDate;
    //     private String bannerDescription;

                {type:'checkbox'}
                ,{field: 'bannerId', title: '图片编号',align:'center', sort: true/*, fixed: 'left'*/}
                ,{field: 'bannerImageUrl', title: '图片路径' ,align:'center',templet:function(d){
                        return "<img src='${pageContext.request.contextPath}/img/"+d.bannerImageUrl+"'  width='40px'></img>";
                    }}
                ,{field: 'bannerOldName', title: '图片名字' ,align:'center'}
                ,{field: 'bannerDate', title: '上传时间',align:'center'}
                ,{field: 'bannerDescription', title: '图片描述',align:'center'}
                ,{field: 'right', title: '操作', width:210,align:'center',toolbar: '#barDemo'}
            ]]
        });
        // table表格结束
        // 分页居中
        $(".layui-table-page").css("text-align",'center');

        //监听工具条
        table.on('tool(test)', function(obj){
            var data = obj.data;
            var layEvent = obj.event;
            if(layEvent === 'detail'){
                alert("点击了查看,id是:"+data.bannerId);

            } else if(layEvent === 'del'){
                layer.confirm("是否确认删除？",function(index){

                    $.ajax({
                        url:"${pageContext.request.contextPath}/banner/deleteById",
                        data:"id="+data.bannerId,
                        dataType:"json",
                        type:"post",
                        success:function(data){
                            if(data.flag==1){
                                layer.msg("删除成功！");
                                table.reload("demo",{page: {curr: 1}}
                                );
                            }else {
                                layer.msg("删除失败");
                            }

                            // 分页居中
                            $(".layui-table-page").css("text-align",'center');

                        }
                    });

                    layer.close(index);
                });
            } else if(layEvent === 'edit'){
                alert("点击了修改,id是:"+data.bannerId);

            }
        });



    })


    // 批量删除开始
    function handleRemoveAny(){
        layui.use(["table","form","layer"],function(){
            var table = layui.table;
            var layer = layui.layer;
            var form = layui.form;
            var checkStatus = table.checkStatus('demo');
            // console.log(checkStatus.data);
            var ids = [];
            for(var i = 0; i < checkStatus.data.length; i++){
                ids[i] = checkStatus.data[i].bannerId;
            }
            // console.log(ids);
            if(ids.length == 0){
                layer.msg("请选中要删除的行");
            }else{
                $.ajax({
                    url:"${pageContext.request.contextPath}/banner/deletes",
                    data:"ids="+ids,
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        // console.log(data);
                        if(data.flag == 1){
                            table.reload("demo",{page: {curr: 1}});
                            $(".layui-table-page").css("text-align",'center');
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                })
            }
        })

    }
    // 批量删除结束


    // 添加开始
    function handleOpenAdd(){
        layui.use(['form','table'], function(){
            var table = layui.table;
            var form = layui.form;
            // var laydate=layui.laydate;
            var layer=layui.layer;
            var upload=layui.upload;
            var layerIndex;


            //监听提交
            form.on('submit(formDemo)', function(data){
                var data1=$("#addForm").serialize();
                $.ajax({
                    url:"${pageContext.request.contextPath}/banner/insert",
                    data:data1,
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        if(data.flag==1){
                            layer.close(layerIndex);
                            $("#addForm").get(0).reset();
                            table.reload("demo");
                            $(".layui-table-page").css("text-align",'center');
                            layer.msg("添加成功！");
                        }else{
                            alert("添加失败");
                        }
                    }
                });
                return false;
            });



            //显示弹出层
            layerIndex=layer.open({
                type:1,
                title:"添加轮播图",
                area:"500px",
                content:$("#addForm")
            });

            //给添加的表单添加表单验证
            form.verify({
                ems:function(value,item){//value就是文本框，item是文本框元素
                    if(value.length<1||value.length>100){
                        return "输入不能为空！";
                    }
                }
            });
        });
    }
    // 添加结束


    // 搜索框开始
    function handleSearch(){
        layui.use(["form","table"],function(){
            var table = layui.table;

            table.reload("demo",{
                url:"${pageContext.request.contextPath}/Xuesheng/selectAll"
                ,where:{collect:$("#xueshengnamelaoshi").val()
                }
            });

            // 刷新表格，从第一页开始
            table.reload("demo",{page: {curr: 1}});

            // 分页居中
            $(".layui-table-page").css("text-align",'center');
        })
    }
    // 搜索框结束

</script>
</body>
</html>
