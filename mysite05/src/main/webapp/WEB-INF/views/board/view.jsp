<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<%@ page import="com.douzone.mysite.vo.UserVo"%>
<%
UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:set var="vo" value="${list }" />
	
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
						<tr>
							<td class="label">제목</td>
							<td>${vo.title }</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<div class="view-content">
									${fn:replace(vo.contents, newline, "<br>") }
								</div>
							</td>
						</tr>					
				</table>
				<div class="bottom">
					<a href="#" onClick="history.go(-1)">글목록</a>
					<%
						if(authUser != null) {		// 여기서 vo.userNo랑 authUser.no랑 비교를 하고 싶은데...
					%>
						<a href="${pageContext.request.contextPath }/board/update?no=${vo.no }&&userNo=${authUser.no}">글수정</a>
						<a href="${pageContext.request.contextPath }/board/reply?no=${vo.no }">댓글</a>
					<%
						}
					%>
					
					
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>