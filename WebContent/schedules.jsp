<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CSSE3202 - CMS - Rooms</title>
</head>
<body>
	<h1>Full Schedule</h1>
	

	<div id="scheduleListContainer">
		<display:table name="scheduleList" cellspacing="5" cellpadding="0" requestURI="" 
   				defaultsort="1" id="schedules" class="table" export="false">
   			<display:column property="c_code" escapeXml="true" sortable="true" title="Course Code" style="width: 25%"
       				url="/courses" paramId="c_code" paramProperty="c_code"/>
       		<display:column property="r_num" escapeXml="true" sortable="true" title="Room Number" style="width: 25%"/>
   			<display:column property="day" sortable="true" title="Day" style="width: 25%" />
   			<display:column property="period" sortable="true" title="Period" style="width: 25%" />
       	</display:table>
	</div>
</body>
</html>