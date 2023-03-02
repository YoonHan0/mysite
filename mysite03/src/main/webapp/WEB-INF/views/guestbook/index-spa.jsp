<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

	var render = function(vo, mode) {
		var htmls = 
			"<li data-no='" + vo.no + "'>" +
			"	<strong>" + vo.name + "</strong>" +
			"	<p>" + vo.message + "</p>" +
			"	<strong></strong>" +
			"	<a href='' data-no='" + vo.no + "'>삭제</a>" + 
			"</li>";
		
		$("#list-guestbook")[mode? "prepend" : "append"](htmls);
	}
	
	var fetch = function() {
		$.ajax({
			url: "${pageContext.request.contextPath}/guestbook/api?sno=10",
			type: "get",
			dataType: "json",
			success: function(response) { 
				if(response.result === 'fail') {
					console.error(response.message);
					return;
				}
				
				response.data.forEach(function(vo){
					render(vo);
				});
			}
		});	
	}
	
	$(function() {
		
		// 최초 리스트 출력
		fetch();
		
		$('#add-form').submit(function(event) {
			event.preventDefault();
			
			/* validation & messagebox 띄우기 */
			// form serialization
			var vo = {};
			vo.name = $('#input-name').val();
			vo.password = $('#input-password').val();
			vo.message = $('#tx-content').val();
			
			// OK, this.submit() 대신에 ajax로 통신
			$.ajax({
				url: "${pageContext.request.contextPath }/guestbook/api/add",
				type: "post",
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify(vo),
				success: function(response) {
					// console.log(response);
					if(response.result === "fail") {
						console.error(response.error);
						return;
					}
					render(response.data, true);
					$('#input-name').val(""); $('#input-password').val(""); $('#tx-content').val("");
				}
			});
		});
		
		// 삭제 다이알로그 jQuery 객체 미리 만들기
		var $dialogDelete = $("#dialog-delete-form").dialog({
			autoOpen: false,
			modal: true,
			buttons: {
				"삭제": function() {
					console.log("ajax 삭제하기");
					var $no = $("#hidden-no").val();
					var $pw = $('#password-delete').val();
					var _this = this;
					
					$.ajax({
						url: "${pageContext.request.contextPath}/guestbook/api/delete",
						type: "delete",
						dataType: "json",
						data: {
							no: $no,
							pw: $pw
						},
						success: function(response) { 
							if(response.result === 'fail') {
								console.error(response.message);
								// $('#dialog-delete-form p').css('display', 'block';)
								return;
							} 
							$(_this).dialog('close');
							fetch();	// 여기서 리스트가 불러와져야함
						}
					});
				},
				"취소": function() {
					console.log("삭제 다이알로그의 폼 데이터 리셋하기");
					$('#password-delete').val("");
					$(this).dialog('close');
				}
			}
		});
		
		// 메세지 삭제 버튼 click 이벤트 처리(Live Event)
		$(document).on('click', "#list-guestbook li a", function(event){
			event.preventDefault();
			
			// console.log($(this).data("no"));
			($("#hidden-no").val($(this).data("no")));
			// console.log($("#hidden-no").val());
			$dialogDelete.dialog('open');
		}); 
		
	});
	

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
					<!-- 리스트 추가 -->
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>