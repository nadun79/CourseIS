<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CSSE3202 - CMS - Administration</title>
</head>
<body>
	<h1>Welcome Administrator <c:out value="${user.name}"/></h1>
	<h2><a href="logout">Logout</a></h2>
	
	<div id="message"><c:out value="${message}" 
		default="Fire when Ready" /></div>
	
	<div id="schedulerFormContainer">
		<a name="schedulerForm" />
		<form id="schedulerForm" action="scheduler" method="post">
			<p>
			<c:choose>
				<c:when test="${running}">
					<input id="runSchedulerButton" type="submit" name="scheduler" value="Stop Scheduler" />
				</c:when>
				<c:otherwise>
					<input id="runSchedulerButton" type="submit" name="scheduler" value="Start Scheduler" />
				</c:otherwise>
			</c:choose>
			</p>
		</form>
	</div>
</body>
</html>