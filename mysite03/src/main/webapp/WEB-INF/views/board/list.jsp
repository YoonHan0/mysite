<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.douzone.mysite.vo.UserVo"%>
<%
UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input type="submit" value="찾기">
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
						<c:choose>
							<c:when test='${empty list }'>
								<td><p style='text-align: center'>비어있는 리스트</p></td>
							</c:when>

							<c:otherwise>
								<c:set var="count" value="${fn:length(list) }" />
								<c:forEach items="${list }" var="vo" varStatus="status">
									<c:if test='${count - 5 * (pageVo.no - 1) >= (count - status.index) && count - 5 * pageVo.no + 1 <= (count - status.index)}'>
										<tr>
											<td>[${count - status.index}]</td>

											<c:choose>
												<c:when test='${vo.depth eq 0 }'>
													<td style="text-align: left; padding-left: 0px">
														<a href="${pageContext.request.contextPath }/board/viewpage?no=${vo.no}&&userNo=${vo.userNo}">${vo.title }</a>
													</td>
												</c:when>

												<c:otherwise>
													<td style="text-align:left; padding-left:${vo.depth*10}px"><img
														src="${pageContext.request.contextPath }/assets/images/reply.png">
														<a
														href="${pageContext.request.contextPath }/board/viewpage?no=${vo.no}&&userNo=${vo.userNo}">${vo.title }</a>
													</td>
												</c:otherwise>
											</c:choose>

											<td>${vo.userName }</td>
											<td>${vo.hit }</td>
											<td>${vo.regDate }</td>
											
											<td>	
												<c:if test='${authUser.no == vo.userNo}'>
													<a href="${pageContext.request.contextPath }/board/delete&&no=${vo.no }"><img
															src="${pageContext.request.contextPath }/assets/images/recycle.png"></a>
												</c:if>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</ul>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${pageVo.no != 1 }">
							<li><a href="${pageContext.request.contextPath }/board/list?page=${pageVo.no - 1}">◀</a></li>
						</c:if>

						<c:forEach var="i" begin="${pageVo.begin }" end="${pageVo.end }" step="1">
							
							<c:choose>
								<c:when test='${pageVo.no == i }'>
									<li class="selected">${i }</li>
								</c:when>
								<c:when test='${i != pageVo.no && i <= pageVo.size}'>
									<li><a href="${pageContext.request.contextPath }/board/list?page=${i }">${i }</a></li>
								</c:when>
							</c:choose>
						</c:forEach>

						<c:if test="${pageVo.no < pageVo.size }">
							<li><a href="${pageContext.request.contextPath }/board/list?page=${pageVo.no + 1}">▶</a></li>
						</c:if>

					</ul>
				</div>
				<!-- pager 추가 -->
				<%
				if (authUser != null) {
				%>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write"
						id="new-book">글쓰기</a>
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