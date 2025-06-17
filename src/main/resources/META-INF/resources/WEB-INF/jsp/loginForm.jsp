<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

	<div class="container">
		<h1>Welcome to the login Page !</h1>
		<pre>${errorMessage}</pre>
		<form method="post">
			Name: <input type="text" name="name">
			Password: <input type="password" name="password">
			<input type="submit">
		</form>
	</div>
	
	

</body>
</html>