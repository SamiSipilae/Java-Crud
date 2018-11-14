<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play</title>
<style>
a:visited {
	color: blue;
}
</style>
</head>
<body
	style="font-size: 200%; font-family: Courier New, Courier, monospace">
	<c:forEach items="${lines}" var="line" varStatus="yc">
		<c:forEach items="${line.line}" var="num" varStatus="xc">

			<c:if test="${num == 0}">
				<c:if test="${victory == 0}">
					<a href="XOController.do?action=turn&x=${xc.index}&y=${yc.index}">#</a>
				</c:if>
				<c:if test="${victory == 1}">
				#
				</c:if>
			</c:if>
			<c:if test="${num == 1}">
				<c:out value="X" />
			</c:if>
			<c:if test="${num == 2}">
				<c:out value="O" />
			</c:if>

		</c:forEach>
		<br />
	</c:forEach>
	<p>
		<a href="XOController.do?action=reset">Reset</a> Turn:
		<c:if test="${turn == 1}">
			<c:out value="X" />
		</c:if>
		<c:if test="${turn == 2}">
			<c:out value="O" />
		</c:if>
		<c:if test="${victory == 1}">
				VICTORY
				</c:if>
	</p>
</body>
</html>