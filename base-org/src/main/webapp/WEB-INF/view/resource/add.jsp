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
            <form id="add-res" class="layui-form">
                <input type="hidden" id="parentTree" name="parentTree" />

                <div class="layui-form-item">
                    <label class="layui-form-label">资源名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required  lay-verify="required" placeholder="请输入资源名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">资源类型：</label>
                    <div class="layui-input-block">
                        <input type="radio" name="type" value="1" title="模块" lay-filter="radioFilter" checked>
                        <input type="radio" name="type" value="2" title="菜单" lay-filter="radioFilter">
                        <input type="radio" name="type" value="3" title="按钮" lay-filter="radioFilter">
                    </div>
                </div>
                <div class="layui-form-item res-url">
                    <label class="layui-form-label">资源连接：</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" required  lay-verify="required" placeholder="请输入资源连接" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item res-code">
                    <label class="layui-form-label">资源代码：</label>
                    <div class="layui-input-block">
                        <input type="text" name="authCode" required  lay-verify="required" placeholder="请输入资源代码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">资源排序：</label>
                    <div class="layui-input-block">
                        <input type="text" name="orderNo" required  lay-verify="required" placeholder="请输入资源排序" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item partent-id">
                    <label class="layui-form-label">父级资源：</label>
                    <div class="layui-input-block parent-id-ds">
                        <select id="parentId" name="parentId" lay-verify="required">

                        </select>
                    </div>
                </div>
                <div class="layui-form-item partent-tree">
                    <label class="layui-form-label">父级资源：</label>
                    <div class="layui-input-block parent-id-ds">
                        <input id="ptreetext" type="text" class="layui-input" readonly="readonly">
                        &nbsp;
                        <ul id="ptree" style="background-color: #dddddd;"></ul>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="formFilter">新增</button>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
</div>

<script id="module_template" type="text/html">
    <option value=""></option>
    <c:forEach var="module" items="${modules}">
        <option value="${module.id}">${module.name}</option>
    </c:forEach>
</script>

<script id="menu_template" type="text/html">
    <option value=""></option>
    <c:forEach var="menu" items="${menus}">
        <option value="${menu.id}">${menu.name}</option>
    </c:forEach>
</script>

<script>
    layui.use(['element', 'tree'], function(){
        var element = layui.element;

        layElHide(".partent-tree");
        layElHide(".partent-id");
        layElHide(".res-url");
        layElHide(".res-code");

        layui.jquery.ajax({
            "url" : "${base}" + "/resource/data/tree",
            "type" : "post",
            "success" : function (json) {
                if (json.success){
                    layui.tree({
                        elem: '#ptree',
                        nodes: json.data.children,
                        click : function (node) {
                            layui.jquery("#parentTree").val(node.id);
                            layui.jquery("#ptreetext").val(node.name);
                        }
                    });
                }else {
                    layer.msg(json.msg);
                }
            }
        });
    });

    layui.use('form', function(){
        var form = layui.form;

        form.on('radio(radioFilter)', function(data){
            var radioValue = data.value;
            if (radioValue == 1) {
                layElHide(".partent-tree");
                layElHide(".partent-id");
                layElHide(".res-url");
                layElHide(".res-code")
            }else if (radioValue == 2) {
                layElshow(".partent-tree");
                layElHide(".partent-id");
                layElshow(".res-url");
                layElHide(".res-code");
                //module_show();
            }else if (radioValue == 3){
                layElHide(".partent-tree");
                layElshow(".partent-id");
                layElHide(".res-url");
                layElshow(".res-code");
                menu_show();
            }
            form.render();
        });

        //监听提交
        form.on('submit(formFilter)', function(data){
            if (data.field == ""){
                layer.msg("表单不能为空！");
                return false;
            }

            layui.jquery.ajax({
                "url" : "${base}" + "/resource/operate/add",
                "type" : "post",
                "data" : data.field,
                "success" : function (json) {
                    if (json.success){
                        layer.msg(json.data);
                    }else {
                        layer.msg(json.msg);
                    }
                }
            });

            return false;
        });
    });

    /**
     * 隐藏lay表单元素
     * @param el_class 元素样式名称
     */
    function layElHide(el_class) {
        layui.use('jquery', function(){
            layui.jquery(el_class).hide();
            layui.jquery(el_class).find('input[type="text"]').removeAttr("required");
            layui.jquery(el_class).find('input[type="text"]').removeAttr("lay-verify");
        });
    }

    /**
     * 元素class名称
     * @param el_class 元素样式名称
     */
    function layElshow(el_class) {
        layui.jquery(el_class).show();
        layui.jquery(el_class).find('input[type="text"]').attr("required");
        layui.jquery(el_class).find('input[type="text"]').attr("lay-verify", true);
    }

    /**
     * 模块资源
     */
    function module_show() {
        layui.jquery("#parentId").html(module_template.innerHTML);
    }

    /**
     * 菜单资源
     */
    function menu_show() {
        layui.jquery("#parentId").html(menu_template.innerHTML);
    }

</script>

</body>
</html>