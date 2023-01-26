<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:set var="no" value="${no }" />
	
	<div id="container">
			<jsp:include page="/WEB-INF/views/includes/header.jsp" />
			<div id="content">
				<div id="board">
					<form class="board-form" method="post" action="${pageContext.request.contextPath }/board?a=AddBoardAction">
						<input type='hidden' name="no" value="${no}">
						
						<table class="tbl-ex">
							<tr>
								<th colspan="2">댓글</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title"></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td><textarea id="content" name="reply"></textarea></td>
							</tr>
						</table>
						<div class="bottom">
							<input type="submit" value="입력">
							<a href="${pageContext.request.contextPath }/board">뒤로가기</a>
						</div>
					</form>
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		</div>
</body>
</html>