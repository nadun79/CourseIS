<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CSSE3202 - CMS - Login</title>
</head>
<body>
	<h1><c:out value="${userType}"/> Login</h1>
	
	<div id="message"><c:out value="${message}" 
		default="Please login by entering your username and password." /></div>
	
	<div id="loginFormContainer">
		<a name="loginForm" />
		<form id="loginForm" action="login" method="post">
			<p>
				<label for="username">User name: </label>
				<input type="text" name="username" size="20" />
			</p>
			<p>
				<label for="password">Password: </label>
				<input type="password" name="password" size="20" />
			</p>
			<p>
			<input id="loginButton" type="submit" name="login" value="login" />
			</p>
			<input type="hidden" name="userType" value="${userType}"/>
		</form>
	</div>
</body>
</html>