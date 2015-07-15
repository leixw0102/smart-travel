<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 15-7-15
  Time: 上午11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.uploadify.min.js"></script>
    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>--%>
</head>

<body>
<h1>Uploadify Demo</h1>
<form id="abcd">
    <div id="queue"></div>
    <input name="title" id="title">sd</input>
    <input id="file_upload" name="file_upload" type="file" multiple="true">
</form>

<script type="text/javascript">
    $(function() {
        $('#file_upload').uploadify({
        'formData'     : { 'title':'dsfds'},
        'swf'      : '<%=request.getContextPath()%>/js/uploadify.swf',
        'uploader' : '<%=request.getContextPath()%>/1.0/news/create'
        });
    })
    <%--$(function() {--%>
        <%--$('#file_upload').uploadify({--%>
            <%--'formData'     : $('#abcd').serialize(),--%>
            <%--'swf'      : '<%=request.getContextPath()%>/js/uploadify.swf',--%>
            <%--'uploader' : '<%=request.getContextPath()%>/1.0/news/create'--%>
        <%--});--%>
    <%--});--%>
</script>
</body>
</html>