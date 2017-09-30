<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<form:form method="post" action="some/item/save" modelAttribute="item">
		<form:input path="name" />
		<c:forEach items="${stringProperties}" var="stringProperty">
			<form:select path="stringValues" items="${stringProperty.stringValues}" itemLabel="name" itemValue="id">
			</form:select>
		</c:forEach>
		<c:forEach items="${digitProperties}" var="digitProperty" varStatus="vs">
			<form:hidden path="digitValues['${vs.index}'].digitProperty" />
			<form:input path="digitValues['${vs.index}'].value" />
		</c:forEach>
	</form:form>
</body>
</html>