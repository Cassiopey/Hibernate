<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<security:authorize access="!isAuthenticated()">
		<div class="azx">
			<h3>To view this page you need to SignIn</h3>
			<h3>
				<a href="/login">SingIn</a>
			</h3>
			<h3>Still not registered? Register now!</h3>
			<h3>
				<a href="/registration">Register</a>
			</h3>
		</div>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<div class="container">
			<div class="row">
				<div class="col-xs-3 col-3sm-3 col-md-3 col-lg-3">
					<div class="leftsize">
						<div class="logo">
							<img class="img-thumbnail" width="100%" src="/resources/image/network.png?version=1" />
						</div>
						<div class="MainMenu">
							<ul class=leftul>
								<li><a href="/">My Page</a></li>
								<li><a href="/post">My Post</a></li>
								<li><a href="/message">My Message</a></li>
								<li><a href="/groupe">My Groupe</a></li>
								<security:authorize access="isAuthenticated()and hasRole('ROLE_ADMIN')">
									<li><a href="/admin">Admin panel</a></li>
								</security:authorize>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9" style="box-shadow: 0 0 0 4px green;">
					<div class="rightside">
						<div class="Header">
							<div class="name">
								<a><security:authentication property="principal.name" /> <security:authentication
										property="principal.surname" /></a>
							</div>
							<div class="topMenu">
								<ul class="topul">
									<li><a href="/peopleSearch">People Search</a></li>
									<li><a href="/groupeSearch">Groupe Search</a></li>
								</ul>
							</div>
							<div class="logout">
								<form:form action="/logout" method="post">
									<button type="submit" class="btn btn-default">
										<img src="/resources/image/logout.png" height="25px">
									</button>
								</form:form>
							</div>
						</div>
						<div class="information">
							<div class="col-md-4">
								<div class="col-md-3"></div>
							</div>
							<div class="inf">
								<table>
									<tr>
										<td>Phone</td>
										<td><security:authentication property="principal.phone" /></td>
									</tr>
									<tr>
										<td>Place of residence</td>
										<td><security:authentication property="principal.city" /></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><security:authentication property="principal.email" /></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="Wall">
							<div class="records">
								<h3>MY POSTS</h3>
								<div class="col-md-3 col-sm-3 col-xs-3">
									<h3>From</h3>
								</div>
								<div class="col-md-9 col-sm-3 col-xs-9">
									<h3>Posts</h3>
									<br>
								</div>
								<c:forEach items="${posts}" var="post">
									<div class="col-md-3 col-sm-3 col-xs-3">
										<b>${post.writer.name} ${post.writer.surname}</b>
									</div>
									<div class="col-md-9 col-sm-3 col-xs-9">
										<div>
											<b>${post.name}</b>
										</div>
										<div>${post.text}</div>
									</div>
								</c:forEach>
								<hr>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>