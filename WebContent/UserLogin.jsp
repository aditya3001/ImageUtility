<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1 {
  margin: 0px;
}
</style>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<hr>
  <div style="background-color:LightGray">
 	<h1>Login</h1>
 	<hr>
  <form action="<%=request.getContextPath()%>/login" method="post">
   <div >
    <label for="uname">Username:</label> <input type="text"
     class="form-control" id="username" placeholder="Username"
     name="username" required>
   </div>
   <div class="form-group">
    <label for="uname">Password:</label> <input type="password"
     class="form-control" id="password" placeholder="Password"
     name="password" required>
   </div>
   <hr>
   	<div>
   		<span style="color: red"><%=(request.getAttribute("LoginResult") == null) ? "" : request.getAttribute("LoginResult")%></span>
   	</div>
   <button type="submit" class="btn btn-primary" style="float: right;">Login</button>
  </form>
 </div>
</body>
</html>