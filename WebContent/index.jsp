<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" lang="no">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Oppland Search</title>
<style type="text/css">
div.container{width:500px}
div.header {background-color:#99bbbb;}
div.l {background-color:#ffff99;height:100px;width:20%;float:left;display:block}
div.m {background-color:#ffff99;height:100px;width:40%;float:left;}
div.r {background-color:#EEEEEE;height:100px;width:20px;float:left;}
div.c {background-color:#99bbbb;clear:both;text-align:center;}
</style>
</head>
<body>
<center>
<h1>Oppland Search</h1>
<div class="c">
	<form action="PageServelet" method="GET">
	<input type="text" name="s"><input type="submit" value="Search" >
	</form>
</div>
<h6><hr/></h6>
<div class="c">
<c:forEach var="doc" items="${docs }" varStatus="status" > 

<table width='100%' border='1' style='background-color:lightgray;padding:0px' >
	<tr><th>${status.count }</th><th><a href="${doc.url }" target="_blank">${doc.title}</a><br><hr>${doc.content}</th></tr>
</table>
</c:forEach> 
</div>
</center>

</body>
</html>