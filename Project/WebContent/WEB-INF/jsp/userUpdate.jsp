<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>userUpdate</title>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light"
		style="background-color: #e9ecef;">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginServlet">ユーザ管理システム</a>
			</div>
			<form class="form-inline my-2 my-lg-0">
				<label class="mr-sm-5">${userInfo.name} さん</label> <a
					href="LogoutServlet" class="btn btn-outline-success my-2 my-sm-0">ログアウト</a>
			</form>
		</div>
	</nav>
	<div class="container">
		<div class="page-header text-center">
			<h1>ユーザ情報更新</h1>
		</div>

		<div class="jumbotron">
			<form class="form-horizontal"  action="UserUpdateServlet" method="post">

				<div class="form-group row">
					<h5 class="offset-md-1 col-sm-3">ID</h5>
					<h5 class="col-sm-7">${userUpdate.loginId}</h5>
				</div>
				<div class="form-group row">
					<h5 class="offset-md-1 col-sm-3">Password</h5>
					<input type="password" class="form-control col-sm-7" placeholder="Password" name="password">
				</div>
				<div class="form-group row">
					<h5 class="offset-md-1 col-sm-3">Password確認</h5>
					<input type="password" class="form-control col-sm-7" placeholder="Password確認" name="passwords">
				</div>
				<div class="form-group row">
					<h5 class="offset-md-1 col-sm-3">ユーザ名</h5>
					<input type="text" class="form-control col-sm-7" placeholder="ユーザ名" name="name" value=${userUpdate.name}>
				</div>
				<div class="form-group row">
					<h5 class="offset-md-1 col-sm-3">生年月日</h5>
						<div style="display: inline-flex">
							<input type="date" class="form-control" name="birthDate" placeholder="yyyy-MM-dd" value=${userUpdate.birthDate}>
						</div>
				</div>
				<br><input type="hidden" name="id" value="${userUpdate.id}">
				<c:if test="${errMsg != null }">
					<div class="alert alert-danger" role="alert">${errMsg}<br>${errs}</div>
				</c:if>
				<div class="form-group row">
					<div class="offset-sm-10">
						<button type="submit" class="btn btn-default">更新</button>
					</div>
				</div>
				<br>
				<div class="form-group row">
					<a class="offset-sm-1" href="LoginUsersServlet">戻る</a>
				</div>
			</form>
		</div>

	</div>
</body>
</html>
