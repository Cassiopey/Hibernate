<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
								<a><security:authentication property="principal.name" />
									<security:authentication property="principal.surname" /></a>
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
						<div class="message">
							<h3>Create new Groupe</h3>
							<form:form action="/groupe" method="post" class="form-inline" modelAttribute="form">
								<form:hidden path="id" />
								<custom:hiddenInputs excludeParams="name, id" />
								<div class="form-group">
									<div>
										<label for="name"><form:errors path="name" /></label>
										<form:input path="name" class="form-control" placeholder="name" />
									</div>
									<div>
										<label for="name"><form:errors path="name" /></label>
										<form:input path="description" class="form-control" placeholder="Description" />
									</div>
									<br> <br>
									<button type="submit" class="btn btn-primary">Create Groupe</button>
								</div>
							</form:form>
							<hr>
							<br> <br> <br>
							<h3>My Groupes</h3>
							<div class="col-md-3 col-xs-3">
								<h4>name</h4>
							</div>
							<div class="col-md-9 col-xs-9">
								<h4>description</h4>
							</div>
							<c:forEach items="${myGroupes}" var="groupe">
								<div class="col-md-3 col-xs-3">
									<div class="span">${groupe.name}</div>
								</div>
								<div class="col-md-9 col-xs-9">
									<div class="span">${groupe.description}</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>