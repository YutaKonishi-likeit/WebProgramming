<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/origin/common.css">
	<!-- Jqeryの読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
	<title>Login</title>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color:#e9ecef;">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginServlet">ユーザ管理システム</a>
			</div>

		</div>
	</nav>

	<div class="container">
		<div class="page-header text-center">
			<h1>LOGIN</h1>
		</div>

		<div class="jumbotron">
		<c:if test="${errMsg != null }">
			<div class="alert alert-danger" role="alert">
				${errMsg}
			</div>
		</c:if>
			<form class="form-horizontal" action="LoginServlet" method="post">
				<div class="form-group row">
					<label for="inputID" class="offset-md-1 col-sm-2">ID</label>
					<div class="col-sm-7">
						<input type="text" name="loginId" class="form-control" id="inputID" placeholder="ID" value="${errLoginId}">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword" class="offset-md-1 col-sm-2">Password</label>
					<div class="col-sm-7">
						<input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="offset-md-3 col-sm-9">
						<div class="checkbox">
							<label> <input type="checkbox"> Remember me
							</label>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="offset-9 col-3">
						<button type="submit" class="btn btn-default">Sign in</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
