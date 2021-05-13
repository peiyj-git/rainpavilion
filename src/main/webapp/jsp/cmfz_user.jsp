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



<%--//    private int guruId;--%>
<%--//     private String guruName;--%>
<%--//     private String guruImage;--%>
<%--//     private String guruNickname;--%>
<%--//     private int guruStatus;--%>

<%--添加的form--%>
<form class="layui-form" id="addForm" style="display:none;padding:10px 50px 0px 10px">

    <div class="layui-form-item">
        <label class="layui-form-label">上师名字</label>
        <div class="layui-input-block">
            <input type="text" name="guruName" placeholder="请输入上师名字" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">上师照片</label>
        <div class="layui-input-block">
            <input type="text" name="guruImage" placeholder="请上传上师照片" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">上师法号</label>
        <div class="layui-input-block">
            <input type="text" name="guruNickname" placeholder="请输入上师法号" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>



<%--/ "userId=" + userId +--%>
<%--// ", userTelphone='" + userTelphone + '\'' +--%>
<%--// ", userPassword='" + userPassword + '\'' +--%>
<%--// ", userImage='" + userImage + '\'' +--%>
<%--// ", userNickname='" + userNickname + '\'' +--%>
<%--// ", userName='" + userName + '\'' +--%>
<%--// ", userSex='" + userSex + '\'' +--%>
<%--// ", userAutograph='" + userAutograph + '\'' +--%>
<%--// ", userProvince='" + userProvince + '\'' +--%>
<%--// ", userCity='" + userCity + '\'' +--%>
<%--// ", guruId=" + guruId +--%>
<%--// ", userStatus=" + userStatus +--%>
<%--// ", userCreateDate=" + userCreateDate +--%>
<%--// '}';--%>

<%--修改开始--%>
<form class="layui-form" id="updateForm" lay-filter="updateForm" style="display:none;padding:10px 50px 0px 10px">

    <input type="hidden" id="userId" name="userId">

    <div class="layui-form-item">
        <label class="layui-form-label">用户电话</label>
        <div class="layui-input-block">
            <input type="text" name="userTelphone" placeholder="请输入用户电话" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户密码</label>
        <div class="layui-input-block">
            <input type="text" name="userPassword" placeholder="请输入用户密码" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户头像</label>
        <div class="layui-input-block">
            <input type="text" name="userImage" placeholder="请选择用户头像" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户昵称</label>
        <div class="layui-input-block">
            <input type="text" name="userNickname" placeholder="请输入用户昵称" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" placeholder="请输入真实姓名" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户性别</label>
        <div class="layui-input-block">
            <input type="text" name="userSex" placeholder="请输入用户性别" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">个性签名</label>
        <div class="layui-input-block">
            <input type="text" name="userAutograph" placeholder="请输入个性签名" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所在省份</label>
        <div class="layui-input-block">
            <input type="text" name="userProvince" placeholder="请输入所在省份" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所在市区</label>
        <div class="layui-input-block">
            <input type="text" name="userCity" placeholder="请输入所在市区" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">关注上师</label>
        <div class="layui-input-block">
            <input type="text" name="guruId" placeholder="关注上师" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">修改状态</label>
        <div class="layui-input-block">
            <input type="text" name="userStatus" placeholder="请修改状态" autocomplete="off" lay-verify="ems" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">修改时间</label>
        <div class="layui-input-block">
            <input type="text" name="userCreateDate" placeholder="请输入修改时间" autocomplete="off" lay-verify="ems" class="layui-input">
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
                <input type="text" name="userName" id="xueshengnamelaoshi" class="layui-input">
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
            ,height: 373
            ,url: '${pageContext.request.contextPath}/cmfzUser/selectAll'
            ,page: true //开启分页
            ,limit:6
            ,limits:[5,6,10]
            ,toolbar:"#toolbar"
            ,cols: [[ //表头

                // "userId=" + userId +
                // ", userTelphone='" + userTelphone + '\'' +
                // ", userPassword='" + userPassword + '\'' +
                // ", userImage='" + userImage + '\'' +
                // ", userNickname='" + userNickname + '\'' +
                // ", userName='" + userName + '\'' +
                // ", userSex='" + userSex + '\'' +
                // ", userAutograph='" + userAutograph + '\'' +
                // ", userProvince='" + userProvince + '\'' +
                // ", userCity='" + userCity + '\'' +
                // ", guruId=" + guruId +
                // ", userStatus=" + userStatus +
                // ", userCreateDate=" + userCreateDate +
                // '}';



                {type:'checkbox'}
                ,{field: 'userId', title: '用户id',align:'center', sort: true/*, fixed: 'left'*/}
                ,{field: 'userTelphone', title: '用户电话' ,align:'center'}
                ,{field: 'userPassword', title: '用户密码' ,align:'center'}
                ,{field: 'userImage', title: '用户头像' ,align:'center'}
                ,{field: 'userNickname', title: '用户昵称' ,align:'center'}
                ,{field: 'userName', title: '真实姓名' ,align:'center'}
                ,{field: 'userSex', title: '用户性别' ,align:'center'}
                ,{field: 'userAutograph', title: '个性签名' ,align:'center'}
                ,{field: 'userProvince', title: '所在省份' ,align:'center'}
                ,{field: 'userCity', title: '所在市区' ,align:'center'}
                ,{field: 'guruId', title: '关注上师' ,align:'center'}
                ,{field: 'userStatus', title: '用户状态' ,align:'center',templet:function(d){
                        return d.userStatus==0? "正常":"冻结"}}
                ,{field: 'userCreateDate', title: '入会日期' ,align:'center'}

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
                alert("点击了查看,id是:"+data.userId);

            } else if(layEvent === 'del'){
                layer.confirm("是否确认删除？",function(index){

                    $.ajax({
                        url:"${pageContext.request.contextPath}/cmfzUser/deleteById",
                        data:"id="+data.userId,
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
                // alert("点击了修改,id是:"+data.userId);
                $.ajax({
                    url:"${pageContext.request.contextPath}/cmfzUser/selectOne",
                    data:"id="+data.userId,
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
                            url:"${pageContext.request.contextPath}/cmfzUser/update",
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
                        area:["350px","500px"],
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
                $.ajax({
                    url:"${pageContext.request.contextPath}/guru/insert",
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
            // alert("进入搜索方法")

            var table = layui.table;

            table.reload("demo",{
                url:"${pageContext.request.contextPath}/cmfzUser/selectAll"
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
