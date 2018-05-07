<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/common.css">
<title>UserDelete</title>

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
			<h1>ユーザ削除確認</h1>
		</div>

		<div class="jumbotron">
			<form class="form-horizontal" action="UserDeleteServlet" method="post">
				<div class="form-group row">
					<h4 class="offset-md-2 col-sm-8">
						ログインID：${userDelete.loginId}<br><br>を本当に削除してもよろしいでしょうか。
					</h4>
				</div>
				<br><input type="hidden" name="id" value="${userDelete.id}">
				<div class="form-group row">
					<button name="no"
						class="btn btn-default offset-md-4 col-sm-1" type="submit">No</button>
					<button name="yes"
						class="btn btn-default offset-md-2 col-sm-1" type="submit">Yes</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
