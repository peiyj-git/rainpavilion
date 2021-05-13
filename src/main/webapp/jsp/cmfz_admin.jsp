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

    <%--// "User{" +--%>
    <%--// "id=" + id +--%>
    <%--// ", username='" + username + '\'' +--%>
    <%--// ", password='" + password + '\'' +--%>
    <%--// '}';--%>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员名字</label>
        <div class="layui-input-block">
            <input type="text" name="username" placeholder="请输入管理员名字" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员密码</label>
        <div class="layui-input-block">
            <input type="text" name="password" placeholder="请输入管理员密码" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

        <div class="layui-form-item">
            <label class="layui-form-label">管理员角色</label>
            <div class="layui-input-block">
                <select id="selectopa" lay-filter="selectopa" lay-verify="required">

                </select>
            </div>
        </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>



<%--修改开始--%>
<form class="layui-form" id="updateForm" lay-filter="updateForm" style="display:none;padding:10px 50px 0px 10px">

    <input type="hidden" id="id" name="id">

    <div class="layui-form-item">
        <label class="layui-form-label">管理员名字</label>
        <div class="layui-input-block">
            <input type="text" name="username" placeholder="请输入管理员名字" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员密码</label>
        <div class="layui-input-block">
            <input type="text" name="password" placeholder="请输入管理员密码" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">确认修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<%--修改结束--%>



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
                <input type="text" name="username" id="xueshengnamelaoshi" class="layui-input">
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

    // 角色名
    $.ajax({
        url:"${pageContext.request.contextPath}/User/cmfzRoleServiceselectAll",
        dataType:"json",
        type:"post",
        async:false,
        success:function(data){
            // alert(data);
            var html="<option  value='1'>--请选择角色--</option>";
            for(var i=0;i<data.length;i++){
                html+="<option value='"+data[i].roleId+"'>"+data[i].roleName+"</option>";
            }
            $("#selectopa").html(html);
        }
    });


    layui.use(['form','laydate','layer','table','tree'], function() {
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var layer = layui.layer;
        var layerIndex;
        var tree=layui.tree;

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
            ,url: '${pageContext.request.contextPath}/User/selectAll'
            ,page: true //开启分页
            ,limit:6
            ,limits:[5,6,10]
            ,toolbar:"#toolbar"
            ,cols: [[ //表头

                // "User{" +
                // "id=" + id +
                // ", username='" + username + '\'' +
                // ", password='" + password + '\'' +
                // '}';

                {type:'checkbox'}
                ,{field: 'id', title: '管理员ID',align:'center', sort: true/*, fixed: 'left'*/}
                ,{field: 'username', title: '管理员名字' ,align:'center'}
                ,{field: 'password', title: '管理员密码' ,align:'center'}
                ,{field: 'roleName', title: '角色' ,align:'center',templet:function(d){
                    if(d.roleName == "superadmin"){
                        return "超级管理员";
                    }else if(d.roleName == "admin"){
                        return "管理员";
                    }else {
                        return "实习管理员";
                    }
                    }}
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
                alert("点击了查看,id是:"+data.id);

            } else if(layEvent === 'del'){
                layer.confirm("是否确认删除？",function(index){

                    $.ajax({
                        url:"${pageContext.request.contextPath}/User/del",
                        data:"id="+data.id,
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
                // alert("点击了修改,id是:"+data.id);
                $.ajax({
                    url:"${pageContext.request.contextPath}/User/selectOne",
                    data:"id="+data.id,
                    dataType:"json",
                    type:"post",
                    success:function(data){

                        // alert(data);
                        // console.log(data);

                        form.val("updateForm",data);
                        // 分页居中
                        $(".layui-table-page").css("text-align",'center');
                    }

                });
                layui.use(['form'], function(){
                    var form = layui.form;
                    var laydate=layui.laydate;
                    var layer=layui.layer;
                    var upload=layui.upload;
                    var layerIndex;


                    //监听提交
                    form.on('submit(formDemo)', function(data){
                        var data=$("#updateForm").serialize();
                        layer.msg(JSON.stringify(data.field));

                        // alert(data);
                        // console.log(data);

                        $.ajax({
                            url:"${pageContext.request.contextPath}/User/update",
                            data:data,
                            dataType:"json",
                            type:"post",
                            success:function(data){
                                if(data.flag==1){
                                    layer.close(layerIndex);
                                    table.reload("demo");
                                    $(".layui-table-page").css("text-align",'center');
                                    layer.msg("修改成功！");
                                }else{
                                    alert("修改失败");
                                }
                            }
                        });
                        return false;
                    });

                    //显示弹出层
                    layerIndex=layer.open({
                        type:1,
                        title:"修改上师信息",
                        area:["350px","300px"],
                        content:$("#updateForm")
                    });
                });
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
                ids[i] = checkStatus.data[i].xueshengid;
            }
            // console.log(ids);
            if(ids.length == 0){
                layer.msg("请选中要删除的行");
            }else{
                $.ajax({
                    url:"${pageContext.request.contextPath}/Xuesheng/deletes",
                    data:"ids="+ids,
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        // console.log(data);
                        if(data.status == 1){
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

                var roleId=$("#selectopa").val();

                $.ajax({
                    url:"${pageContext.request.contextPath}/User/insert",
                    data:data1+"&roleId="+roleId,
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
                title:"添加管理员",
                area:["500px","400px"],
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
            // alert("进入搜索方法")

            var table = layui.table;

            table.reload("demo",{
                url:"${pageContext.request.contextPath}/User/selectAll"
                ,where:{username:$("#xueshengnamelaoshi").val()
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
