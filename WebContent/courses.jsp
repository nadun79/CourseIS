<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CSSE3202 - CMS - Courses</title>
</head>
<body>
	<c:choose>
		<c:when test="${param.c_code != null}">
			<h1>Info for <c:out value="${param.c_code}"/></h1>
		
			<h2>Lecturers</h2>
			<display:table name="lecturers"/>
			
			<h2>Schedule</h2>
			<display:table name="schedule" cellspacing="5" cellpadding="0" requestURI="" 
    				defaultsort="1" id="schedule" class="table" export="false">
    			<display:column property="r_num" title="Room Number" style="width: 10%"/>
        		<display:column property="day" escapeXml="true"  title="Day" style="width: 15%"/>
    			<display:column property="period" title="Period" style="width: 20%" />
        	</display:table>
		</c:when>
		<c:otherwise>
			<h1>All Courses <c:out value="${user.name}"/></h1>
			
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
		</c:otherwise>
	</c:choose>
</body>
</html>