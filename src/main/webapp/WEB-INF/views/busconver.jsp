<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bubbles.css" />">
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		ajax_process("");
	});
	
	function ajax_process(_isay){
		$.ajax({
			type : 'POST',
			url  : 'watsonsay',
			data : 'isay=' + _isay,
			async: false,
			success : function(data) {
				console.log(data);
				var watsonsay =
					'<p class="triangle-border left">' + data.output.text;	
				
				watsonsay += '</p>';
				$('#said').append(watsonsay);	
				$('html, body').animate({scrollTop: $(document).height()}, 500);
			}
		});
	}
</script>
</head>
<body>
	<div id="said"></div>
	<input type="text" id="txt_isay" class="triangle-border center" />
	<button type="button" id="btn_isay" autofocus="autofocus"
		class="btn btn-default">전송</button>
</body>
</html>