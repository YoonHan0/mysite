<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	UserVo vo = (UserVo)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user">
					<input type='hidden' name="a" value="update">
					<input type='hidden' name="no" value="<%=vo.getNo() %>">
					<label class="block-label" for="name">이름</label>	<!-- 수정 가능 / 기존 이름 출력 -->
					<input id="name" name="name" type="text" value="<%=vo.getName() %>">

					<label class="block-label" for="email">이메일</label>
					<h4><%=vo.getEmail() %></h4>							<!-- 수정 불가능 그냥 현재 이메일만 출력 -->
					
					<fieldset>
						<legend>성별</legend>
						<% 
							if(vo.getGender().equals("female")) {
						%>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						<%
							} else {
						%>
							<label>여</label> <input type="radio" name="gender" value="female">
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						<%
							}
						%>
							
						
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>