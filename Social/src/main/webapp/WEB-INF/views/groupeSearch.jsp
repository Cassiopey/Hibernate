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
					<div class="rightside" style="box-shadow: none;">
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
						<h3>Groupe Search</h3>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-10">
								<form:form action="/groupeSearch" class="form-inline" method="get" modelAttribute="filter">
									<div class="form-group">
										<form:input path="name" placeholder="name" class="form-control" />
										<div class="form-group"></div>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary">Find</button>
									</div>
								</form:form>
							</div>
							<div class="col-md-1 col-xs-1">
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
										Sort <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<custom:sort innerHtml="name asc" paramValue="name" />
										<custom:sort innerHtml="name desc" paramValue="name,desc" />
									</ul>
								</div>
							</div>
							<div class="col-md-1 col-xs-1">
								<custom:size posibleSizes="1,2,5,10,20" size="${groupes.size}" title="Size" />
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
							<h4>name</h4>
						</div>
						<div class="col-md-4 col-xs-4">
							<h4>description</h4>
						</div>
						<div class="col-md-4 col-xs-4">
							<h4>creator</h4>
						</div>
						<c:forEach items="${adminGroupes.content}" var="groupe">
							<div class="col-md-4 col-xs-4">
								<div class="span">${groupe.name}</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="span">${groupe.description}</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="span">${groupe.creator.name}${groupe.creator.surname}</div>
							</div>
						</c:forEach>
						<div class="col-md-12 col-xs-12 text-center">
							<custom:pageable page="${adminGroupes}" cell="<li></li>" container="<ul class='pagination'></ul>" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>