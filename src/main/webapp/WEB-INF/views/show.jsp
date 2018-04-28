<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springframework.http.HttpRequest" %><%--
  Created by IntelliJ IDEA.
  User: KitaharaHaruki
  Date: 2018/3/25
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%Float cost= (Float) request.getAttribute("cost");
        ArrayList warninginginfo= (ArrayList) request.getAttribute(("warn"));%>
</head>
<body>

    <h5>总花费为</h5><h4><%=cost%></h4>
    <%
       if (warninginginfo.size()>0)
       {for (Object str:
             warninginginfo) {
    %>
    <h4><%=str%></h4>
<%}
}%>
</body>
</html>
