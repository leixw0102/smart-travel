<%@ page import="java.net.URLEncoder" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 15-8-2
  Time: 下午3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%String abc = request.getAttribute("abcd").toString();
    System.out.println(abc);
    %>
    <title></title>
</head>

<body>
<img src='http://qr.liantu.com/api.php?text=<%=abc%>'>
</body>
</html>