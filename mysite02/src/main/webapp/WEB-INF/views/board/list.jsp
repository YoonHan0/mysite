<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.douzone.mysite.vo.UserVo"%>
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<ul>
						<c:set var="list" value="${list }" />
						<c:choose>
							<c:when test='${empty list }'>
								<td><p style='text-align: center'>비어있는 리스트</p></td>
							</c:when>
							
							<c:otherwise>
								<c:set var="count" value="${fn:length(list) }" />
								<c:forEach items="${list }" var ="vo" varStatus="status">
									<tr>
										<td>[${count - status.index }]</td>
										
										<c:choose>
											<c:when test='${vo.depth eq 0 }'>
												<td style="text-align:left; padding-left:0px">
													<a href="${pageContext.request.contextPath }/board?a=viewpage&&no=${vo.no}&&userNo=${vo.userNo}">${vo.title }</a>
												</td>
											</c:when>
											
											<c:otherwise>
												<td style="text-align:left; padding-left:${vo.depth*10}px"><img src="${pageContext.request.contextPath }/assets/images/reply.png">
													<a href="${pageContext.request.contextPath }/board?a=viewpage&&no=${vo.no}&&userNo=${vo.userNo}">${vo.title }</a>
												</td>
											</c:otherwise>
										</c:choose>
										
										<td>${vo.userName }</td>
										<td>${vo.hit }</td>
										<td>${vo.reg_date }</td>
										<%
											if(authUser != null ) {
										%>
											<td><a href="${pageContext.request.contextPath }/board?a=delete&&no=${vo.no }"><img src="${pageContext.request.contextPath }/assets/images/recycle.png"></a></td>
										<%
											}
										%>
									</tr>
							
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
					</ul>				
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				<%
					if(authUser != null ) {
				%>	
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=write" id="new-book">글쓰기</a>
					</div>
				<%
					}
				%>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>