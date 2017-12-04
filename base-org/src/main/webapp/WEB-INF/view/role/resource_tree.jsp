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

        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
</div>

<script>
    layui.use('element', function(){
        var element = layui.element;
    });
</script>

</body>
</html>