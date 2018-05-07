<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/common.css">
<title>newUserForm</title>

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
			<h1>ユーザ新規登録</h1>
		</div>

		<div class="jumbotron">
			<h3>Form</h3>
			<br>
			<form class="form-horizontal" action="NewUserFormServlet"
				method="post">

				<div class="form-group row">
					<label for="inputID" class="col-sm-2">ID</label>
					<div class="col-sm-10">
						<input type="text" name="loginId" class="form-control"
							id="inputID" placeholder="ID" value=${loginIdE}>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-2">Password</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control"
							id="inputPassword" placeholder="Password">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPasswords" class="col-sm-2">Password確認</label>
					<div class="col-sm-10">
						<input type="password" name="passwords" class="form-control"
							id="inputPasswords" placeholder="Password確認">
					</div>
				</div>

				<div class="form-group row">
					<label for="inputName" class="col-sm-2">ユーザ名</label>
					<div class="col-sm-10">
						<input type="text" name="name" class="form-control" id="inputName"
							placeholder="ユーザ名" value=${userNameE}>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">生年月日</label>
					<div class="col-sm-10">
						<div style="display: inline-flex">
							<input type="date" name="birthDate" class="form-control" placeholder="yyyy-MM-dd" value=${BDE}>
						</div>
					</div>
				</div>
				<c:if test="${errMsg != null }">
					<div class="alert alert-danger" role="alert">${errMsg}<br>${errs}</div>
				</c:if>
				<br>
				<div class="form-group row">
					<div class="offset-md-10">
						<button class="btn btn-default" type="submit">登録</button>
					</div>
				</div>
				<div class="form-group row">
					<a class="col-sm-2" href="LoginUsersServlet">戻る</a>
				</div>
			</form>
		</div>




	</div>
</body>
</html>
