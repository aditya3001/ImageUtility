<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.assignment.jpa.ImageInfo"%>
<%@ page import="com.assignment.operations.DBOperationImpl"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.Math"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image Management Utility</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

table.center {
	margin-left: auto;
	margin-right: auto;
}

th, td {
	padding: 10px;
}

table {
	align: center;
	width: 900px;
}
</style>
</head>
<body>
	<table border="1" align="center" width="900px">
		<tr>
			<td>
				<div align="center">
					<h3>Image Management Utility</h3>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>Please select an image file to upload (Max Size 1 MB)</div>
				&nbsp;
				<form action="MainUtility" method="post"
					enctype="multipart/form-data">
					<input type="file" name="image" size="50" />
					<div align="right">
						<input type="submit" name="sbtnSubmit" value="Submit" /> <input
							type="button" name="btnCancel" value="Cancel" />
					</div>
					<span style="color: red"><%=(request.getAttribute("UploadResult") == null) ? "" : request.getAttribute("UploadResult")%></span>
				</form>
				<div>
					<h3>Uploaded Images</h3>
				</div>
			</td>
		</tr>
		<tr>
			
			<table border="1" align="center" width="900px">
				<tr>
					<th>S.No</th>
					<th>Name</th>
					<th>Size</th>
					<th>Preview</th>
					<th>Actions</th>
				</tr>
				<%
					DBOperationImpl fb = new DBOperationImpl();
					String userName = (String) session.getAttribute("userName");
					List<ImageInfo> imageInfoList = new ArrayList<>();
					imageInfoList = fb.getImages(userName);
					int Sno = 1;
					float totalSize = 0;
					for (ImageInfo img : imageInfoList) {
				%>
				<tr>
					<td><%=Sno%></td>
					<td><%=img.getName()%></td>
					<td><%=img.getSize()%> MB</td>
					<%
							String encodedImage = Base64.getEncoder().encodeToString(img.getImage());
							String pic = "data:image/jpg;base64," + encodedImage;
			    	%>
		
					<td>
						<img src="<%=pic%>" width="200" height="200" alt="not showing">
					</td>
					<td><a href="action?id=<%=img.getImageId()%>">
							<button
								type="button" class="delete">Delete
							</button>
						</a> 
						<a href="Update.jsp?id=<%=img.getImageId()%>&oldName=<%=img.getName()%>">
							<button
								type="button">Edit
							</button>
						</a>
					</td>
				</tr>
				<%
				Sno++;
				totalSize += img.getSize();
					}
				%>
		
			</table>
		</tr>
		<tr>
			<td>
				<div align="center">
					Total Size is : <%= (float) (Math.round(totalSize * 100.0) / 100.0) %> MB
				</div>
		</tr>
	</table>
	
</body>
</html>