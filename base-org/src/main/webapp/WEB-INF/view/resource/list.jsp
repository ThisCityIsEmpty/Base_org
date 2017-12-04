<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/script.jsp" />
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/header.jsp" />
    <jsp:include page="../common/left.jsp" />

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="demoTable">
                资源名称：
                <div class="layui-inline">
                    <input class="layui-input" name="name" id="name" autocomplete="off">
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>

            <table class="layui-hide" id="res_list" lay-filter="res_list"></table>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
</div>

<script type="text/html" id="type_template">
    {{# if(d.type == 1){ }}
        模块
    {{# } }}
    {{# if(d.type == 2){ }}
        菜单
    {{# } }}
    {{# if(d.type == 3){ }}
        按钮
    {{# } }}
</script>

<script type="text/html" id="status_template">
    {{# if(d.deleteFlag){ }}
        冻结
    {{# } }}
    {{# if(!d.deleteFlag){ }}
        正常
    {{# } }}
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

    {{# if(d.deleteFlag){ }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="use">启用</a>
    {{# } }}
    {{# if(!d.deleteFlag){ }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="frozen">冻结</a>
    {{# } }}
</script>

<script>
    layui.use('element', function(){
        var element = layui.element;
    });

    layui.use('table', function(){
        var table = layui.table;

        //方法级渲染
        table.render({
            elem: '#res_list'
            ,url: '${base}' + '/resource/data/list'
            ,cellMinWidth: 120
            ,cols: [[
                {field:'name', title: '资源名称', sort: true, align:'center' }
                ,{field:'type', title: '资源类型', sort: true, align:'center', templet:'#type_template', unresize: true}
                ,{field:'deleteFlag', title: '资源状态', align:'center', templet:'#status_template', unresize: true}
                ,{title: '操作', fixed: 'right', width:178, align:'center', toolbar: '#bar'}
            ]]
            ,id: 'resList'
            ,page: true
            ,height: 'full-200'
        });

        var $ = layui.$, active = {
            reload: function(){
                //执行重载
                table.reload('resList', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        resName : $('#name').val()
                    }
                });
            }
        };

        table.on('tool(res_list)', function(obj){
            var data = obj.data;

            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'edit'){
                location.href = '${base}' + '/resource/page/update?id='+data.id;
            } else if(obj.event === 'use'){
                layer.confirm('真的启用资源吗？', function(index){
                    layui.jquery.ajax({
                        "url" : "${base}" + "/resource/operate/thaw",
                        "type" : "post",
                        "data" : {
                            "id" : data.id
                        },
                        "success" : function (json) {
                            if (json.success){
                                layer.msg(json.data);
                            }else {
                                layer.msg(json.msg);
                            }
                            layer.close(index);
                            active["reload"].call(this);
                        }
                    });
                });
            } else if(obj.event === 'frozen'){
                layer.confirm('真的冻结资源吗？', function(index){
                    layui.jquery.ajax({
                        "url" : "${base}" + "/resource/operate/frozen",
                        "type" : "post",
                        "data" : {
                          "id" : data.id
                        },
                        "success" : function (json) {
                            if (json.success){
                                layer.msg(json.data);
                            }else {
                                layer.msg(json.msg);
                            }
                            layer.close(index);
                            active["reload"].call(this);
                        }
                    });
                });
            }
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>
</html>