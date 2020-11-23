<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%	
	String id = request.getParameter("id");
	String oldName = request.getParameter("oldName");
%>
<form method="post" action="action">
<input type="hidden" name="imageId" value="<%= id %>">
New Name:<br>
<input type="text" name="newName" value="<%= oldName %>">
</form>
</body>
</html>