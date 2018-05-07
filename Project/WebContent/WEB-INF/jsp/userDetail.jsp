<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ一覧画面</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/common.css">

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light" style="background-color:#e9ecef;">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginServlet">ユーザ管理システム</a>
			</div>
			<form class="form-inline my-2 my-lg-0">
				<label class="mr-sm-5">${userInfo.name} さん</label>
				<a href="LogoutServlet" class="btn btn-outline-success my-2 my-sm-0">ログアウト</a>
			</form>
		</div>
	</nav>
	<div class="container">
		<div class="page-header text-center">
			<h1>ユーザ詳細情報</h1>
		</div>
		<div class="jumbotron">
			<form class="form-horizontal">

				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">ID</h5>
					<h5 class="col-sm-7">${userDetail.loginId}</h5>
				</div>

				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">ユーザ名</h5>
					<h5 class="col-sm-7">${userDetail.name}</h5>
				</div>
				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">生年月日</h5>
					<h5 class="col-sm-7">${userDetail.birthDate}</h5>
				</div>
				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">登録日時</h5>
					<h5 class="col-sm-7">${userDetail.createDate}</h5>

				</div>
				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">更新日時</h5>
					<h5 class="col-sm-7">${userDetail.updateDate}</h5>
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
