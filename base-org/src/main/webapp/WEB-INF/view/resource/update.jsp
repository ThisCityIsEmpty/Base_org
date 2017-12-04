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
                <input type="hidden" name="id" value="${res.id}" />
                <input type="hidden" name="deleteFlag" value="${res.deleteFlag}" />
                <input type="hidden" name="icon" value="${res.icon}" />

                <div class="layui-form-item">
                    <label class="layui-form-label">资源名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" value="${res.name}" required  lay-verify="required" placeholder="请输入资源名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">资源类型：</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="type" value="${res.type}" />
                        <c:if test="${res.type == 1}">
                            <label class="layui-form-label" style="text-align: left;">模块</label>
                        </c:if>
                        <c:if test="${res.type == 2}">
                            <label class="layui-form-label" style="text-align: left;">菜单</label>
                        </c:if>
                        <c:if test="${res.type == 3}">
                            <label class="layui-form-label" style="text-align: left;">按钮</label>
                        </c:if>
                    </div>
                </div>
                <c:if test="${res.type == 2}">
                    <div class="layui-form-item res-url">
                        <label class="layui-form-label">资源连接：</label>
                        <div class="layui-input-block">
                            <input type="text" name="url" value="${res.url}" required  lay-verify="required" placeholder="请输入资源连接" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </c:if>
                <c:if test="${res.type == 3}">
                    <div class="layui-form-item res-code">
                        <label class="layui-form-label">资源代码：</label>
                        <div class="layui-input-block">
                            <input type="text" name="authCode" value="${res.authCode}" required  lay-verify="required" placeholder="请输入资源代码" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </c:if>
                <div class="layui-form-item">
                    <label class="layui-form-label">资源排序：</label>
                    <div class="layui-input-block">
                        <input type="text" name="orderNo" value="${res.orderNo}" required  lay-verify="required" placeholder="请输入资源排序" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <c:if test="${res.type != 1}">
                    <div class="layui-form-item partent-id">
                        <label class="layui-form-label">父级资源：</label>
                        <div class="layui-input-block parent-id-ds">
                            <select id="parentId" name="parentId" lay-verify="required">
                                <option value=""></option>
                                <c:forEach var="parent" items="${parents}">
                                    <c:choose>
                                        <c:when test="${parent.id == res.parentId}">
                                            <option value="${parent.id}" selected="selected">${parent.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${parent.id}">${parent.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:if>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="formFilter">保存</button>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
</div>

<script>
    layui.use('element', function(){
        var element = layui.element;
    });

    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formFilter)', function(data){
            if (data.field == ""){
                layer.msg("表单不能为空！");
                return false;
            }

            layui.jquery.ajax({
                "url" : "${base}" + "/resource/operate/update",
                "type" : "post",
                "data" : data.field,
                "success" : function (json) {
                    if (json.success){
                        layer.msg(json.data, function () {
                            location.href = '${base}' + '/resource/page/list';
                        });
                    }else {
                        layer.msg(json.msg);
                    }
                }
            });

            return false;
        });
    });
</script>

</body>
</html>