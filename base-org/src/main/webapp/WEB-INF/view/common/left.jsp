<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">资源管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${base}/resource/page/list">资源列表</a></dd>
                    <dd><a href="${base}/resource/page/add">新增资源</a></dd>
                    <%--<dd><a href="${base}/resource/page/update">编辑资源</a></dd>--%>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">角色管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">角色列表</a></dd>
                    <dd><a href="javascript:;">新增角色</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>