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
            <div class="layui-collapse" lay-accordion="">
                <c:forEach var="resource" items="${resources}">
                    <!-- 第一级 -->
                    <div class="layui-colla-item">
                        <h2 class="layui-colla-title">${resource.name}</h2>
                        <c:if test="${resource.childList != null && resource.childList.size() > 0}">
                            <div class="layui-colla-content layui-show">
                                <!-- 第二级 -->
                                <div class="layui-collapse" lay-accordion="">
                                    <c:forEach var="menu" items="${resource.childList}">
                                        <div class="layui-colla-item">
                                            <h2 class="layui-colla-title">${menu.name}</h2>
                                            <c:if test="${menu.childList != null && menu.childList.size() > 0}">
                                                <div class="layui-colla-content layui-show">
                                                    <!-- 第三级 -->
                                                    <div class="layui-collapse" lay-accordion="">
                                                        <c:forEach var="menuButton" items="${menu.childList}">
                                                            <div class="layui-colla-item">
                                                                <h2 class="layui-colla-title">${menuButton.name}</h2>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
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
;