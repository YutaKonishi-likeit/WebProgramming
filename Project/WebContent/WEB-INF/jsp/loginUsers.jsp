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
<title>loginUsers</title>

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
			<h1>ユーザ一覧</h1>
		</div>

		<div class="jumbotron">
			<div class="text-right">
				<a href="NewUserFormServlet">新規登録</a>
			</div>
			<h3>Search</h3>
			<br>
			<br>
			<form class="form-horizontal" action="LoginUsersServlet"
				method="post">

				<div class="form-group row" >
					<h5 class="offset-sm-1 col-sm-3">ID</h5>
					<input type="text" class="form-control col-sm-7" placeholder="ID" name="loginId" value="${loginIdE}">
				</div>
				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">ユーザ名</h5>
					<input type="text" class="form-control col-sm-7" placeholder="ユーザ名" name="name" value="${userNameE}">
				</div>
				<div class="form-group row">
					<h5 class="offset-sm-1 col-sm-3">生年月日</h5>
					<input type="date" class="form-control col-sm-3" name="date1" value="${BDE1}" placeholder="yyyy-MM-dd">
					<h3 class="col-xs-1 text-center">～</h3>
					<input type="date" class="form-control col-sm-3" name="date2" value="${BDE2}" placeholder="yyyy-MM-dd">
				</div>
				<c:if test="${errMsg != null }">
					<div class="alert alert-danger" role="alert">${errMsg}<br>${errs}</div>
				</c:if>
				<br>
				<div class="form-group row">
					<div class="offset-9 col-3">
						<button type="submit" class="btn btn-default">Search</button>
					</div>
				</div>
			</form>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th>ログインID</th>
					<th>ユーザ名</th>
					<th>生年月日</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.loginId}</td>
						<td>${user.name}</td>
						<td>${user.birthDate}</td>
						<!-- TODO 未実装；ログインボタンの表示制御を行う -->
						<td>
							<c:if test="${userInfo.loginId == 'admin'}">
								<a class="btn btn-primary" href="UserDetailServlet?id=${user.id}">詳細</a>
								<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}">更新</a>
								<a class="btn btn-danger" href="UserDeleteServlet?id=${user.id}">削除</a>
							</c:if>
							<c:if test="${userInfo.loginId != 'admin'}">
								<a class="btn btn-primary" href="UserDetailServlet?id=${user.id}">詳細</a>
								<c:if test="${userInfo.loginId == user.loginId}">
									<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}">更新</a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
