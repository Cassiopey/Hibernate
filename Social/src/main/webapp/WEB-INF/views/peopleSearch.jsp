<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
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
						<h3>People Search</h3>
						<div class="col-md-4 col-xs-12">
							<div class="col-md-6">
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort</button>
									<ul class="dropdown-menu">
										<custom:sort innerHtml="Name asc" paramValue="name" />
										<custom:sort innerHtml="Name desc" paramValue="name,desc" />
										<custom:sort innerHtml="Surname asc" paramValue="surname" />
										<custom:sort innerHtml="Surname desc" paramValue="surname,desc" />
										<custom:sort innerHtml="Email asc" paramValue="email" />
										<custom:sort innerHtml="Email desc" paramValue="email,desc" />
										<custom:sort innerHtml="Phone asc" paramValue="phone" />
										<custom:sort innerHtml="Phone desc" paramValue="phone,desc" />
										<custom:sort innerHtml="Country name asc" paramValue="country.name" />
										<custom:sort innerHtml="Country name desc" paramValue="country.name,desc" />
										<custom:sort innerHtml="Sex name asc" paramValue="sex.name" />
										<custom:sort innerHtml="Sex name desc" paramValue="sex.name,desc" />
										<custom:sort innerHtml="DayOfBirth name asc" paramValue="dayOfBirth.name" />
										<custom:sort innerHtml="DayOfBirth name desc" paramValue="dayOfBirth.name,desc" />
										<custom:sort innerHtml="MounthOfBirth name asc" paramValue="mounthOfBirth.name" />
										<custom:sort innerHtml="MounthOfBirth name desc" paramValue="mounthOfBirth.name,desc" />
										<custom:sort innerHtml="YearOfBirth name asc" paramValue="yearOfBirth.name" />
										<custom:sort innerHtml="YearOfBirth name desc" paramValue="yearOfBirth.name,desc" />
									</ul>
								</div>
							</div>
							<div class="col-md-6">
								<custom:size posibleSizes="1,2,5,10,20" size="${userPages.size}" title="Size" />
							</div>
							<br> <br> <br> <br>
							<form:form action="/peopleSearch" class="form-inline" method="get" modelAttribute="filter">
								<custom:hiddenInputs
									excludeParams=" name,surname, email, password, phone, country,city,  countryIds,
									 sexIds, _sexIds, _countryIds, dayOfBirthIds, _dayOfBirth, mounthOfBirth, _mounthOfBirth,yearOfBirth, _yearOfBirth" />
								<div class="form-group">
									<button type="submit" class="btn btn-primary">
										<img width="30px" src="/resources/image/search.png?version=1" /><B> Find</B>
									</button>
								</div>
								<div class="form-group">
									<form:input path="name" placeholder="name" class="form-control" />
									<form:input path="surname" placeholder="surname" class="form-control" />
									<form:input path="email" placeholder="email" class="form-control" />
									<form:input path="phone" placeholder="phone" class="form-control" />
								</div>
								<div class="form-group">
									<h4>Country</h4>
								</div>
								<div class="form-group">
									<form:checkboxes items="${countries}" path="countryIds" itemLabel="name" itemValue="id" />
								</div>
								<div>
									<h4>Sex</h4>
								</div>
								<div class="form-group">
									<form:checkboxes items="${sexs}" path="sexIds" itemLabel="name" itemValue="id" />
								</div>
								<div>
									<h4>DaysOfBirth</h4>
								</div>
								<div class="form-group">
									<form:checkboxes items="${daysOfBirth}" path="dayOfBirthIds" itemLabel="name" itemValue="id" />
								</div>
								<div>
									<h4>MounthOfBirth</h4>
								</div>
								<div class="form-group">
									<form:checkboxes items="${mounsesOfBirth}" path="mounthOfBirthIds" itemLabel="name" itemValue="id" />
								</div>
								<div>
									<h4>YearOfBirth</h4>
								</div>
								<div class="form-group">
									<form:checkboxes items="${yearsOfBirth}" path="yearOfBirthIds" itemLabel="name" itemValue="id" />
								</div>
							</form:form>
						</div>
						<div class="row-fluid">
							<div class="col-md-8">
								<div class="row">
									<div class="row">
										<div class="col-md-3">
											<h4>Name</h4>
										</div>
										<div class="col-md-3">
											<h4>Surname</h4>
										</div>
										<div class="col-md-6">
											<h4>Email</h4>
										</div>
									</div>
									<br>
									<c:forEach items="${userPages.content}" var="userPage">
										<div class="row">
											<div class="col-md-3">
												<div class="span">${userPage.name}</div>
											</div>
											<div class="col-md-3">
												<div class="span">${userPage.surname}</div>
											</div>
											<div class="col-md-6">
												<div class="span">${userPage.email}</div>
											</div>
										</div>
									</c:forEach>
									<div class="col-md-12 text-center">
										<custom:pageable page="${userPages}" cell="<li></li>" container="<ul class='pagination'></ul>" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</security:authorize>
</body>
</html>