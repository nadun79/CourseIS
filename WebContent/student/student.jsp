<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CSSE3202 - CMS - Administration</title>
</head>
<body>
	<h1>Welcome Student <c:out value="${user.name}"/></h1>
	<h2><a href="logout">Logout</a></h2>
	
	<div id="message"><c:out value="${message}" 
		default="Place Holder Message" /></div>
		
		
	<h1>Enrolled Courses</h1>
	
	<div id="courseListContainer">
		<display:table name="courseList" cellspacing="5" cellpadding="0" requestURI="" 
   				defaultsort="1" id="courses" class="table" export="false">
   			<display:column property="code" escapeXml="true" sortable="true" title="Course Code" style="width: 25%"
       				url="/courses" paramId="c_code" paramProperty="code"/>
       		<display:column property="title" escapeXml="true" sortable="true" title="Title" style="width: 25%"/>
   			<display:column property="desc" sortable="false" title="Description" style="width: 34%" />
   			<display:column property="units" sortable="true" title="Units" style="width: 16%" />
       	</display:table>
	</div>
	
	<form id="enrollmentForm" action="enrollment" method="post">
		<p>
		<c:choose>
			<c:when test="${!running}">
				<input id="runSchedulerButton" type="submit" name="enrollment" value="Modify Enrollment" />
			</c:when>
			<c:otherwise>
				<input id="runSchedulerButton" type="button" name="enrollment" value="Undergoing Maintenance" />
			</c:otherwise>
		</c:choose>
		</p>
	</form>
</body>
</html>