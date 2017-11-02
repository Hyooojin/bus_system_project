<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

<!-- 자바스크립트에서 서버로 데이터를 넘겨주고, 서버에서 그 결과를 DB에 저장한 후, 다시 게시글 목록으로 이동시킨다. -->
<script type="text/javascript">
	$(document).ready(function(){
		$("#list").on("click", function(e){
			e.preventDefault();
			fn_openBoardList();
		});
	});
	
	function fn_openBoardList(){
		var comSubmit = new Comsubmit();
		comSubmit.setUrl("<c:url value='/sample/openBoardList.do'/>");
	}
</script>

</head>



<body>

<form action="getkeyword">
	<h1>광역급행버스 시간표</h1>
	
		<a>지금 시각은 </a>${serverTime}<br/>
	
	Enter your bus: 
	<input name="busname" type="text" value="${bus_keyword}"><input type="submit" class="btn btn-success" value="검색" required><br/>
	<P>The time on the server is ${serverTime}. </P>
</form>

</body>
</html>