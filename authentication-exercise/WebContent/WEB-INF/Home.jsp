<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>welcome ${username}!</h1>
	<p> 
		The chipmunks are:
		<c:forEach items="${chipmunks}" var="chipmunk" varStatus="i">
			<c:if test='${!chipmunk.equals("Simon")}'>
				<br>${i.count} ${chipmunk}
			</c:if>
		</c:forEach>
	</p>
</body>
</html>